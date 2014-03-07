
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.errorechonest.ErrorEchonest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lookup.Lookup;
import com.lookup.Song;
import com.lookup.Track;

public class ApiStreaming extends Configured implements Tool {
	
	/*
	 * 
	 * Few counters to have an idea of:
	 * 
	 * - the number of tracks with at least one song in the rosetta space, 
	 * - the number of tracks with at least one deezer corresponding track, 
	 * - the number of tracks with at least one spotify corresponding track,
	 * - the number of tracks with one lastfm corresponding track, 
	 * - the number of tracks which are still in the echonest api
	 * 
	 */
	public static enum COUNTERS {
		ROSETTA,
		DEEZER,
		SPOTIFY,
		LASTFM,
		ECHONEST
	}
	
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		Job job = JobBuilder.parseInputAndOutput(this, conf, args);
		if(job == null){
			return -1;
		}
	    job.setJobName("Get information from Deezer, Spotify, Lastfm and Echonest to complete the msd");
	    int yearmin = Integer.parseInt(conf.get("min")); // parameter required to select which track to process
	    int yearmax = Integer.parseInt(conf.get("max")); // parameter required to select which track to process
	    job.setNumReduceTasks(yearmax - yearmin + 1); // one reducer per year + one log reducer
	    job.setMapperClass(QueryApiMapper.class);
	    job.setReducerClass(PrintValuesReducer.class);
	    job.setMapOutputKeyClass(Text.class);
	    job.setMapOutputValueClass(Text.class);
	    job.setOutputKeyClass(NullWritable.class);
	    job.setOutputValueClass(Text.class);
	    return job.waitForCompletion(true) ? 0 : 1;
	}
	
	static class PrintValuesReducer extends Reducer<Text, Text, NullWritable, Text> {
		/*
		 * 
		 * This reducer only print what he received..
		 * 
		 */
		@Override
		 public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			for(Text value : values){ 
				context.write(NullWritable.get(), value);
			}
	     }         
	}
	
	static class QueryApiMapper extends Mapper<LongWritable, Text, Text, Text> {				
		/*
		 * 
		 * This mapper process a line from a msd tsv file if the year of the track
		 * belongs to the range [yearmin, yearmax]. 
		 * 
		 * It makes a json string with the tsv data, fresh data from echonest for the
		 * track, additional data from deezer/spotify according to the related songs
		 * in the rosetta stone space and lastfm data for the matching track on the 
		 * key (artist,  title).
		 * 
		 */
		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			
			// major variables
			Configuration conf = context.getConfiguration();
			int yearmin = Integer.parseInt(conf.get("min")); 
		    int yearmax = Integer.parseInt(conf.get("max"));
			String record = value.toString(); 
			Tsv track = new Tsv(record); 
			String enid = track.getTrack_id();
			String songid = track.getSong_id();
			int year = track.getYear();
			int spotify = 0;
			int deezer = 0;
			// -------
			
			// process the track only if it is in the year range and if it has a songid
			if((!songid.equals("-1")) && year <= yearmax && yearmin <= year) { 
				
				// default values for the api keys
				String enkey = "echonestkey1"; 
				String lfmkey = "lastfmkey1";
				// -------
				
				// shuffle the api keys according to the number of processed tracks
				int nrecords = (int) context.getCounter("org.apache.hadoop.mapreduce.TaskCounter", "MAP_INPUT_RECORDS").getValue();
				switch (nrecords % 5) {
		            case 0:  enkey = "echonestkey1";
		            		 lfmkey = "lastfmkey1";
		                     break;
		            case 1:  enkey = "echonestkey2";
		            		 lfmkey = "lastfmkey2";
		                     break;
		            case 2:  enkey = "echonestkey3";
		            		 lfmkey = "lastfmkey3"; 
		             		 break;
		            case 3:  enkey = "echonestkey4";
		            		 lfmkey = "lastfmkey4"; 
		                     break;
		            case 4:  enkey = "echonestkey5";
		            		 lfmkey = "lastfmkey5"; 
		                     break;
		        }
				// -------
				
				// variables declaration
		        String urlString, result, msg, forid, artist, title, jsontsv, json, lastcatalogue, tmpjson;
				URL url;
				HttpURLConnection httpConn;	
				InputStream is;
				BufferedReader reader;
				Gson gson = new Gson();
				Lookup lkp;
				// -------
				
				// start the json string - all attributes from the tsv are kept
			    List<String> attribs = 
			    		Arrays.asList("sample_rate","artist_7digitalid","artist_familiarity","artist_hotttnesss",
			    		"artist_id","artist_latitude","artist_location","artist_longitude","artist_mbid",
			    		"artist_mb_tags","artist_mb_tags_count","artist_name","artist_playmeid",
			    		"artist_terms","artist_terms_freq","artist_terms_weight","audio_md5",
			    		"bars_confidence","bars_start","beats_confidence","beats_start",
			    		"danceability","duration","end_of_fade_in","energy","key","key_confidence",
			    		"loudness","mode","mode_confidence","release","release_7digitalid",
			    		"sections_confidence","sections_start","segments_confidence",
			    		"segments_loudness_max","segments_loudness_max_time","segments_loudness_max_start",
			    		"segments_pitches","segments_start","segments_timbre","similar_artists",
			    		"song_hotttnesss","song_id","start_of_fade_out","tatums_confidence","tatums_start","tempo",
			    		"time_signature","time_signature_confidence","title","track_7digitalid","year");
				jsontsv = track.toJson(attribs);	
			    json = "{\"tsvtrack\":" + jsontsv;
			    // -------
			    
			    // add fresh data from echonest
			    result = Utils.GetEchoNestTrackInfo(enid, enkey);  
			    if(!result.startsWith("K")) {
			    	json += ",\"echonesttrack\":" + result;
			    	context.getCounter(COUNTERS.ECHONEST).increment(1);
			    }
			    else context.write(new Text("log"), new Text(result)); 
			    // -------	
			    
			    // build a lookup object to get related tracks according to the 'rosetta stone'
				urlString = "http://developer.echonest.com/api/v4/song/profile"
					+ "?api_key=" + enkey + "&format=json&id=" + songid 
					+ "&bucket=id:deezer&bucket=id:spotify-WW&bucket=tracks";
				url = new URL(urlString);
				httpConn = (HttpURLConnection) url.openConnection();
				while(httpConn.getResponseCode() == 429) { // --> 429 is the http header when the rate query limit has been reached
					httpConn.disconnect();
					Thread.sleep(5000);
					httpConn = (HttpURLConnection) url.openConnection();
				}
				if (httpConn.getResponseCode() > 200) {
				    is = httpConn.getErrorStream();
				    reader = new BufferedReader(new InputStreamReader(is));
					msg = reader.readLine();
					ErrorEchonest error = gson.fromJson(msg, ErrorEchonest.class);
					context.write(new Text("log"), new Text("KO|" + songid + "|" + error.getResponse().getStatus().getMessage() + "|" + error.getResponse().getStatus().getCode()));    
					reader.close();
				}
				// -------	
				
				// add data from deezer and spotify
				else {
					is = httpConn.getInputStream();
				    reader = new BufferedReader(new InputStreamReader(is));
					msg = reader.readLine();
					reader.close();
					httpConn.disconnect();
					try {
						lkp = gson.fromJson(msg, Lookup.class);
						if(lkp.getResponse().getSongs().size()>0){
							context.getCounter(COUNTERS.ROSETTA).increment(1);
						    json += ",\"rosettastone\":[";
						    tmpjson = "";
							for(Song s : lkp.getResponse().getSongs()){
								lastcatalogue = "";
								tmpjson = "{";
								for(Track t : s.getTracks()){
									forid = t.getForeign_id().split(":")[2];
									result = "nothing";
								    if(t.getCatalog().equals("deezer")){
								    	result = Utils.GetDeezerTrackInfo(forid);
								    	if(!result.startsWith("K")){
								    		// increment the counter only the first time we found a deezer track
								    		context.getCounter(COUNTERS.DEEZER).increment((deezer==0) ? 1 : 0);
								    		deezer += 1;
									    	if(!lastcatalogue.equals("deezer")){
									    		lastcatalogue = "deezer";	
									    		tmpjson += "\"deezertracks\":[";
									    		tmpjson += result;
									    	}
									    	else {
									    		tmpjson += "," + result;
									    	}
								    	}
								    	else{
								    		context.write(new Text("log"), new Text(result));
								    	}
								    }
								    else if(t.getCatalog().equals("spotify-WW")){
								    	result = Utils.GetSpotifyTrackInfo(forid);
								    	if(!result.startsWith("K")){
								    		// increment the counter only the first time we found a spotify track
								    		context.getCounter(COUNTERS.SPOTIFY).increment((spotify==0) ? 1 : 0);
								    		spotify += 1;
									    	if(!lastcatalogue.equals("spotify-WW")){
									    		if(lastcatalogue.equals("deezer")){
									    			tmpjson += "],";
									    		}
									    		lastcatalogue = "spotify-WW";
									    		tmpjson += "\"spotifytracks\":[";
									    		tmpjson += result;
									    	}
									    	else {
									    		tmpjson += "," + result;
									    	}
								    	}
								    	else{
								    		context.write(new Text("log"), new Text(result));
								    	}
								    }
								}
								if(tmpjson.equals("{")) tmpjson += "}"; // a {} within the rosetta array means that all tracks api call ended with an error for the related song
								else tmpjson += "]}"; // at least one successful api call successful for a track, end of deezer or spotify tracks array + end of the song objet
								if(tmpjson.equals("{}") || tmpjson.equals("{[]}")) context.write(new Text("log"), new Text("KO|" + songid + "|Rosetta found|Empty Result"));
							}
							json += tmpjson + "]"; // end of songs array + end of rosettastone objet
						}
						// -------	
					} catch(JsonSyntaxException e){
						context.write(new Text("log"), new Text("KO|Lookup Syntax Error|" + msg));
					}					
					
					// add data from lastfm & finish the json
					artist = track.getArtist_name();
					title = track.getTitle();
					result = Utils.GetLastfmTrackInfo(artist, title, lfmkey);
					if(!result.startsWith("K")) {
						json += ",\"lastfmtrack\":" + result;
						context.getCounter(COUNTERS.LASTFM).increment(1);
					}
					else context.write(new Text("log"), new Text(result));	
					json += "}";
					// -------
					
				}
				context.write(new Text(String.valueOf(track.getYear())), new Text(json));
			}
			else if(songid.equals("-1")) context.write(new Text("log"), new Text("KO|" + track.getTrack_id() + "|Missing songid"));
		}
	}
	
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new ApiStreaming(), args);
		System.exit(exitCode);
	}
	
}


