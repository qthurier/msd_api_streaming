import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import com.deezer.Deezer;
import com.echonest.Echonest;
import com.errordeezer.ErrorDeezer;
import com.errorechonest.ErrorEchonest;
import com.errorlastfm.ErrorLastfm;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lastfm.Lastfm;
import com.lookup.Lookup;
import com.lookup.Song;
import com.lookup.Track;
import com.spotify.Spotify;

public class Utils {
	
	/* 
	 * Get the content of the bucket 'audio_summary' in the Echo Nest Api for 
	 * a given echonest track id. 
	 * 
	 * If the http header doesn't equal 200 (success) and if the returned code 
	 * equals 3 (meaning that the rate limiting has been kicked in for the key) 
	 * then the function is called again after 5 seconds, else a pipe separated
	 * record with the id, the error message and the error code is returned. 
	 * 
	 * Otherwise a json with relevant information is returned.
	 * 
	 */
	static String GetEchoNestTrackInfo(String enid, String key) throws IOException, InterruptedException{
		String out = "", msg;
		String urlString = "http://developer.echonest.com/api/v4/track/profile?"
				+ "api_key=" + key + "&id=" + enid + "&format=json&bucket=audio_summary";
		URL url = new URL(urlString);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStream is;
		BufferedReader reader;
		Gson gson = new Gson();
		Gson json = new Gson();
		if (httpConn.getResponseCode() > 200) {
		    is = httpConn.getErrorStream();
		    reader = new BufferedReader(new InputStreamReader(is));
			msg = reader.readLine();
			ErrorEchonest error = gson.fromJson(msg, ErrorEchonest.class);
			if(error.getResponse().getStatus().getCode() == 3){
				httpConn.disconnect();
				reader.close();
				Thread.sleep(5000);
				GetEchoNestTrackInfo(enid, key);
			}
			else {
				out = "KO|" + enid + "|" + error.getResponse().getStatus().getMessage() + "|" + error.getResponse().getStatus().getCode();
			    reader.close();
			}
		} else {
		    is = httpConn.getInputStream();
		    reader = new BufferedReader(new InputStreamReader(is));
			msg = reader.readLine();
			try {
				Echonest echonest = gson.fromJson(msg, Echonest.class);
				/* 
				 * 
				 * Select required attributes here.
				 * All are selected by default.
				 * 
				 */
				out = json.toJson(echonest);
			} catch(JsonSyntaxException e) {
				out = "KO|" + enid + "|Echonest Error|Bad json entry - " + msg;
			}	
			reader.close();
		}
		httpConn.disconnect();
		return out;
	}
	
	/* 
	 * Get the content of the Deezer tracks Api for a given deezer track id.
	 * 
	 * If the http header doesn't equal 200 (success) a pipe separated record 
	 * with the id, the error message and the error code is returned.
	 * 
	 * Otherwise a json with relevant information is returned.
	 * 
	 * I did'nt manage to find the error code meaning that the rate limiting has 
	 * been kicked in for the ip, but Deezer Api is almost unlimited (50 queries/s).
	 * 
	 */
	static String GetDeezerTrackInfo(String id) throws IOException, InterruptedException{
		String out = "", msg;
		String urlString = "http://api.deezer.com/track/" + id;
		URL url = new URL(urlString);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStream is;
		BufferedReader reader;
		Gson gson = new Gson();
		Gson json = new Gson();
		if (httpConn.getResponseCode() > 200) { 
		    is = httpConn.getErrorStream();
		    reader = new BufferedReader(new InputStreamReader(is));
			msg = reader.readLine();
			ErrorDeezer error = gson.fromJson(msg, ErrorDeezer.class);
			out = "KO|" + id + "|" + error.getError().getMessage() + "|" + error.getError().getCode();
			reader.close();
		} else {
		    is = httpConn.getInputStream();
		    reader = new BufferedReader(new InputStreamReader(is));
			msg = reader.readLine();
			try {
				Deezer deezer = gson.fromJson(msg, Deezer.class);
				/* 
				 * 
				 * Select required attributes here.
				 * All are selected by default.
				 * 
				 */
				out = json.toJson(deezer);
			} catch(JsonSyntaxException e) {
				out = "KO|" + id + "|Deezer Error|Bad json entry - " + msg;
			}	
			reader.close();
		}
		httpConn.disconnect();
		return out;
	}
	
	/* 
	 * Get the content of the Spotify lookup Api for a given spotify track id. 
	 * 
	 * If the http header equals 403 (meaning that the the rate limiting has been 
	 * kicked in for the ip - 10 queries/s) then the function is called again after 
	 * 5 seconds.
	 * 
	 * Else if the http header doesn't equal 200 (success) a pipe separated record 
	 * with the id, the message 'Spotify Error' and the http header code is returned.
	 * 
	 * Otherwise a json with relevant information is returned.
	 * 
	 */
	static String GetSpotifyTrackInfo(String id) throws IOException, InterruptedException{
		String out = "", msg;
		String urlString = "http://ws.spotify.com/lookup/1/.json?uri=spotify:track:" + id;
		URL url = new URL(urlString);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStream is;
		BufferedReader reader;
		Gson gson = new Gson();
		Gson json = new Gson();
		if (httpConn.getResponseCode() == 403) {
			is = httpConn.getErrorStream();
		    reader = new BufferedReader(new InputStreamReader(is));
			msg = reader.readLine();
			httpConn.disconnect();
			reader.close();
			Thread.sleep(5000);
			GetSpotifyTrackInfo(id);
		}
		else if (httpConn.getResponseCode() > 200) {
			//is = httpConn.getErrorStream();
		    //reader = new BufferedReader(new InputStreamReader(is));
			//msg = reader.readLine();
			out = "KO|" + id + "|Spotify Error|" + httpConn.getResponseCode();
			//reader.close();
		} else {
		    is = httpConn.getInputStream();
		    reader = new BufferedReader(new InputStreamReader(is));
			msg = reader.readLine();
			try {
				Spotify spotify = gson.fromJson(msg, Spotify.class);
				/* 
				 * 
				 * Select required attributes here.
				 * All are selected by default.
				 * 
				 */
				out = json.toJson(spotify);
			} catch(JsonSyntaxException e) {
				out = "KO|" + id + "|Spotify Error|Bad json entry - " + msg;
			}	
			reader.close();
		}
		httpConn.disconnect();
		return out;
	}
	
	/* 
	 * Get the content of the Lastfm Api track.getInfo method for a given couple
	 * (title, artist). 
	 * 
	 * If the http header doesn't equal 200 (success) and if the returned code 
	 * equals 29 (meaning that the rate limiting has been kicked in for the ip) 
	 * then the function is called again after 5 seconds, else a pipe separated
	 * record with the pair (title, artist), the error message and the error code 
	 * is returned. 
	 * 
	 * Otherwise a json with relevant information is returned.
	 * 
	 * Not for the Lastfm we have to handle few cases which may cause errors,
	 * details are in the code.
	 * 
	 */
	static String GetLastfmTrackInfo(String artist, String title, String key) throws IOException, InterruptedException{
		artist = URLEncoder.encode(artist, "UTF-8");
		title = URLEncoder.encode(title, "UTF-8");
		String out = "", msg;
		String urlString = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo"
				+ "&track=" + title + "&artist=" + artist + "&api_key=" + key + "&format=json";
		//System.out.println(urlString);
		URL url = new URL(urlString);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStream is;
		BufferedReader reader;
		Gson gson = new Gson();
		Gson json = new Gson();
		int where;
		String tag;
		if (httpConn.getResponseCode() > 200) {
		    is = httpConn.getErrorStream();
		    reader = new BufferedReader(new InputStreamReader(is));
			msg = reader.readLine();
			ErrorLastfm error = gson.fromJson(msg, ErrorLastfm.class);
			if(error.getError() == 29){
				httpConn.disconnect();
				reader.close();
				Thread.sleep(5000);
				GetLastfmTrackInfo(artist, title, key);
			}
			else {
				out = "KO|(" + title + "," + artist + ")|" + error.getMessage() + "|" + error.getError();
			    reader.close();
			}
		} else {
		    is = httpConn.getInputStream();
		    reader = new BufferedReader(new InputStreamReader(is));
			msg = reader.readLine();
			/* 
			 * A little workaround to handle two cases :
			 * 
			 * 			- there is no top tag
			 * 			- there is only one top tag
			 * 
			 */
			msg = msg.replace(",\"toptags\":\"\\n      \"", ""); // -->  no top tag
			where = msg.indexOf("\"tag\":{");
			if(where>0){ // --> only one top tag
				tag = msg.substring(where);
				tag = tag.replace("\"tag\":{", "\"tag\":[{"); 
				tag = tag.replace("}}}}", "}]}}}"); 
				msg = msg.substring(0, where) + tag;
			}
			try{
				Lastfm lastfm = gson.fromJson(msg, Lastfm.class);
				/* 
				 * 
				 * Select required attributes here.
				 * All are selected by default.
				 * 
				 */
				out = json.toJson(lastfm);
			} catch(JsonSyntaxException e) {
				out = "KO|(" + title + "," + artist + ")|Lastfm Error|Bad json entry - " + msg;
			}		
			reader.close();
		}
		httpConn.disconnect();
		/* 
		 * A little workaround to handle the case when the http header 
		 * is succesfull but there is no track found.
		 * 
		 */
		if(out.equals("{}")){
			out = "KO|(" + title + "," + artist + ")|Lastfm Error|Successful http header but nothing found - " + httpConn.getResponseCode() + " - " + msg;
		}
		return out;
	}
	
//	Commenter le comportement attendu
	public static void main(String[] args) throws IOException, InterruptedException {	
		String enkey = "GXGVZDP19FKVQYUBO";
		String lfmkey = "5b4535425418d2fc3baaa75713a59ede";
		
		
		//FileInputStream fstream = new FileInputStream("/home/ubuntu/Desktop/training/aws/msd_sample/msd/AdditionalFiles/unique_tracks_500.txt");
		FileWriter log = new FileWriter("/home/ubuntu/Desktop/training/aws/msd_sample/msd_tsv/logjson.txt");
		FileInputStream fstream = new FileInputStream("/home/ubuntu/Desktop/training/aws/msd_sample/msd_tsv/A.tsv.a");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine, enid, songid, urlString, result, msg, forid, artist, title, jsontsv, json, lastcatalogue, tmpjson;
		//String[] vrecords;
		URL url;
		HttpURLConnection httpConn;	
		InputStream is;
		BufferedReader reader;
		Gson gson = new Gson();
		Lookup lkp;
		int i = 0;
		while (((strLine = br.readLine()) != null) && i < 200) {
			FileWriter f0 = new FileWriter("/home/ubuntu/Desktop/training/aws/msd_sample/msd_tsv/json2/output"+ i +".json");		
			i++;
			//vrecords = strLine.replace("<SEP>", "|").split("\\|");
			//songid = vrecords[1];
		    Tsv track = new Tsv(strLine);
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
		    
		    enid = track.getTrack_id();
		    result = GetEchoNestTrackInfo(enid, enkey); // gérer cas erreur 
		    
		    if(!result.startsWith("K")) json += ",\"echonesttrack\":" + result;
		    else log.write(result); 
		    	
		    songid = track.getSong_id();
			urlString = "http://developer.echonest.com/api/v4/song/profile"
				+ "?api_key=" + enkey + "&format=json&id=" + songid 
				+ "&bucket=id:deezer&bucket=id:spotify-WW&bucket=tracks";
			url = new URL(urlString);
			httpConn = (HttpURLConnection) url.openConnection();
			while(httpConn.getResponseCode() == 429) { // 429 is the http code for having reached the rate query limit
				httpConn.disconnect();
				Thread.sleep(5000);
				httpConn = (HttpURLConnection) url.openConnection();
			}
			if (httpConn.getResponseCode() > 200) {
			    is = httpConn.getErrorStream();
			    reader = new BufferedReader(new InputStreamReader(is));
				msg = reader.readLine();
				ErrorEchonest error = gson.fromJson(msg, ErrorEchonest.class);
				log.write("KO|" + songid + "|" + error.getResponse().getStatus().getMessage() + "|" + error.getResponse().getStatus().getCode());	    
				reader.close();
			} 
			else {
				is = httpConn.getInputStream();
			    reader = new BufferedReader(new InputStreamReader(is));
				msg = reader.readLine();
				reader.close();
				httpConn.disconnect();
				lkp = gson.fromJson(msg, Lookup.class);
				if(lkp.getResponse().getSongs().size()>0){
				    json += ",\"rosettastone\":[";
				    tmpjson = "";
					for(Song s : lkp.getResponse().getSongs()){
						lastcatalogue = "";
						tmpjson = "{";
						for(Track t : s.getTracks()){
							forid = t.getForeign_id().split(":")[2];
							result = "nothing";
						    if(t.getCatalog().equals("deezer")){
						    	result = GetDeezerTrackInfo(forid);
						    	if(!result.startsWith("K")){
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
						    		 log.write(result);
						    	}
						    }
						    else if(t.getCatalog().equals("spotify-WW")){
						    	result = GetSpotifyTrackInfo(forid);
						    	if(!result.startsWith("K")){
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
						    		 log.write(result);
						    	}
						    }
						}
						if(tmpjson.equals("{")) tmpjson += "}"; // a {} within the rosetta array mean that all track call api ended with an error for this song
						else tmpjson += "]}"; // at least one api call successful, end of deezer or spotify tracks array + end of song objet
						if(tmpjson.equals("{}") || tmpjson.equals("{[]}")) log.write(i);
					}
					json += tmpjson + "]"; // end of songs array + end of rosettastone objet
				}
				artist = track.getArtist_name();
				title = track.getTitle();
				result = GetLastfmTrackInfo(artist, title, lfmkey);
				if(!result.startsWith("K")) json += ",\"lastfmtrack\":" + result;
		    	else log.write(result);
				json += "}";
			    f0.write(json.toString());
			    f0.close();
			}
		}
		br.close();
		log.close();
	}
	
}
