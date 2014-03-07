import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class FindRateLimit {
	
	public static void main(String[] args) throws IOException {	
		String urlString = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo"
				+ "&track=The%20Nomad&artist=Iron%20Maiden&api_key=f88c4a34093b5d83d47050fb2a42df0a"
				+ "&format=json";
		URL url = new URL(urlString); 
		URLConnection conn; 
		InputStream is;
		BufferedReader reader;
		for(int i=0; i<10000; i++){
			conn = url.openConnection();
			is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			System.out.println(reader.readLine());
			reader.close();
		}
		
		
//		String urlString = "http://api.deezer.com/track/3135556";
//		URL url = new URL(urlString); 
//		URLConnection conn = url.openConnection();
//		InputStream is = conn.getInputStream();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		String original = reader.readLine();
//		Gson gson1 = new Gson();
//		Deezer track1 = gson1.fromJson(original, Deezer.class);
//		System.out.println(original);
//		Gson gson2 = new Gson();
//		System.out.println(gson2.toJson(track1));
//		
//		urlString = "http://ws.spotify.com/lookup/1/.json?uri=spotify:track:6mKSsdNY76jqhPCvygsQjO";
//		url = new URL(urlString); 
//		conn = url.openConnection();
//		is = conn.getInputStream();
//		reader = new BufferedReader(new InputStreamReader(is));
//		original = reader.readLine();
//		Gson gson3 = new Gson();
//		Spotify track2 = gson3.fromJson(original, Spotify.class);
//		System.out.println(original);
//		Gson gson4 = new Gson();
//		System.out.println(gson4.toJson(track2));
//		
//		
//		
//		urlString = "http://developer.echonest.com/api/v4/song/profile"
//				+ "?api_key=GXGVZDP19FKVQYUBO&format=json&id=SOQMMHC12AB0180CB8&" 
//				+ "bucket=id:deezer&bucket=id:spotify-WW&bucket=tracks";
//		url = new URL(urlString); 
//		conn = url.openConnection();
//		is = conn.getInputStream();
//		reader = new BufferedReader(new InputStreamReader(is));
//		original = reader.readLine();
//		Gson gson5 = new Gson();
//		Lookup lookup = gson5.fromJson(original, Lookup.class);
//		System.out.println(original);
//		Gson gson6 = new Gson();
//		System.out.println(gson4.toJson(lookup));
//		
//		Song song = lookup.getResponse().getSongs().get(0);
//		for(Track t : song.getTracks())	System.out.println(t.getForeign_id());
//		
//		
//		5b4535425418d2fc3baaa75713a59ede
//		c8216859c5c4afe85b1feaec5ce81a3e
//		2c907cc1c8b71e1013e147d4a392ef6a
//		f88c4a34093b5d83d47050fb2a42df0a
//		1fad4549d1ffd6d1f866e540352af3f4
		
//		String artist = URLEncoder.encode(song.getArtist_name(), "UTF-8");
//		String title = URLEncoder.encode(song.getTitle(), "UTF-8");
//		urlString = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo"
//				+ "&track=" + title + "&artist=" + artist + "&api_key=f88c4a34093b5d83d47050fb2a42df0a"
//				+ "&format=json";
//		url = new URL(urlString);
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		con.connect();
//		int code = con.getResponseCode();
//		System.out.println(code);
//		is = con.getInputStream();
//		reader = new BufferedReader(new InputStreamReader(is));
//		original = reader.readLine();
//		Gson gson7 = new Gson();
//		Lastfm lastfm = gson7.fromJson(original, Lastfm.class);
//		System.out.println(original);
//		Gson gson8 = new Gson();
//		System.out.println(gson8.toJson(lastfm));
		
		
//			String urlString = "http://developer.echonest.com/api/v4/track/profile?api_key=GXGVZDP19FKVQYUBO&format=json&id=TRMMMYQ128F932D901&bucket=audio_summary";
//			URL url = new URL(urlString); 
//			con = (HttpURLConnection) url.openConnection();
//			con.connect();
//			code = con.getResponseCode();
//			System.out.println(code);
//			is = con.getInputStream();
//			reader = new BufferedReader(new InputStreamReader(is));
//			original = reader.readLine();
//			Gson gson9 = new Gson();
//			Echonest echonest = gson9.fromJson(original, Echonest.class);
//			System.out.println(original);
//			if(code == 200){
//				Gson gson10 = new Gson();
//				System.out.println(gson10.toJson(echonest));
//			}
//			else{
//				Integer error = echonest.getResponse().getStatus().getCode();
//				System.out.println(error);
//			}
			
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//			InputStream is;
//			if (httpConn.getResponseCode() >= 400) {
//			    is = httpConn.getErrorStream();
//			    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//				String original = reader.readLine();
//			    System.out.println(original);
//			} else {
//			    is = httpConn.getInputStream();
//			    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//				String original = reader.readLine();
//			    System.out.println(original);
//			}

	}
	
	
}
