

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.gson.stream.JsonWriter;

/* 
 * This class aims to turn a line from a tsv file from s3n://tbmmsd to a track object.
 * 
 * Be aware that nan values or missing values are converted into to -1 for simple attributes
 * and filtered from tags lists. After a quick data exploration with the value -1 there is no 
 * 'collision' with a true value. 
 * 
 * Such values are not written in the json returned by the method toJson() which returns the 
 * json version of the object based on a given list of attributes.
 * 
 */

public class Tsv {
	private String tsv_row;
	private float sample_rate;
	private int artist_7digitalid;
	private float artist_familiarity;
	private float artist_hotttnesss;
	private String artist_id;
	private float artist_latitude;
	private String artist_location;
	private float artist_longitude;
	private String artist_mbid;
	private String[] artist_mbtags;
	private int[] artist_mbtags_count;
	private String artist_name;
	private int artist_playmeid;
	private String[] artist_terms;
	private float[] artist_terms_freq;
	private float[] artist_terms_weight;
	private String audio_md5;
	private float[] bars_confidence;
	private float[] bars_start;
	private float[] beats_confidence;
	private float[] beats_start;
	private float danceability;
	private float duration;
	private float end_of_fade_in;
	private float energy;
	private int key;
	private float key_confidence;
	private float loudness;
	private int mode;
	private float mode_confidence;
	private String release;
	private int release_7digitalid;
	private float[] sections_confidence;
	private float[] sections_start;
	private float[] segments_confidence;
	private float[] segments_loudness_max;
	private float[] segments_loudness_max_time;
	private float[] segments_loudness_max_start;
	private float[][] segments_pitches;
	private float[] segments_start;
	private float[][] segments_timbre;
	private String[] similar_artists;
	private float song_hotttnesss;
	private String song_id;
	private float start_of_fade_out;
	private float[] tatums_confidence;
	private float[] tatums_start;
	private float tempo;
	private int time_signature;
	private float time_signature_confidence;
	private String title;
	private String track_id;
	private int track_7digitalid;
	private int year;
	
	public String getTsv_row() {
		return tsv_row;
	}
	public void setTsv_row(String tsv_row) {
		this.tsv_row = tsv_row;
	}
	public float getSample_rate() {
		return sample_rate;
	}
	public void setSample_rate(float sample_rate) {
		this.sample_rate = sample_rate;
	}
	public int getArtist_7digitalid() {
		return artist_7digitalid;
	}
	public void setArtist_7digitalid(int artist_7digitalid) {
		this.artist_7digitalid = artist_7digitalid;
	}
	public float getArtist_familiarity() {
		return artist_familiarity;
	}
	public void setArtist_familiarity(float artist_familiarity) {
		this.artist_familiarity = artist_familiarity;
	}
	public float getArtist_hotttnesss() {
		return artist_hotttnesss;
	}
	public void setArtist_hotttnesss(float artist_hotttness) {
		this.artist_hotttnesss = artist_hotttness;
	}
	public String getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}
	public float getArtist_latitude() {
		return artist_latitude;
	}
	public void setArtist_latitude(float artist_latitude) {
		this.artist_latitude = artist_latitude;
	}
	public String getArtist_location() {
		return artist_location;
	}
	public void setArtist_location(String artist_location) {
		this.artist_location = artist_location;
	}
	public float getArtist_longitude() {
		return artist_longitude;
	}
	public void setArtist_longitude(float artist_longitude) {
		this.artist_longitude = artist_longitude;
	}
	public String getArtist_mbid() {
		return artist_mbid;
	}
	public void setArtist_mbid(String artist_mbid) {
		this.artist_mbid = artist_mbid;
	}
	public String[] getArtist_mbtags() {
		return artist_mbtags;
	}
	public void setArtist_mbtags(String[] artist_mbtags) {
		this.artist_mbtags = artist_mbtags;
	}
	public int[] getArtist_mbtags_count() {
		return artist_mbtags_count;
	}
	public void setArtist_mbtags_count(int[] artist_mbtags_count) {
		this.artist_mbtags_count = artist_mbtags_count;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public int getArtist_playmeid() {
		return artist_playmeid;
	}
	public void setArtist_playmeid(int artist_playmeid) {
		this.artist_playmeid = artist_playmeid;
	}
	public String[] getArtist_terms() {
		return artist_terms;
	}
	public void setArtist_terms(String[] artist_terms) {
		this.artist_terms = artist_terms;
	}
	public float[] getArtist_terms_freq() {
		return artist_terms_freq;
	}
	public void setArtist_terms_freq(float[] artist_terms_freq) {
		this.artist_terms_freq = artist_terms_freq;
	}
	public float[] getArtist_terms_weight() {
		return artist_terms_weight;
	}
	public void setArtist_terms_weight(float[] artist_terms_weight) {
		this.artist_terms_weight = artist_terms_weight;
	}
	public String getAudio_md5() {
		return audio_md5;
	}
	public void setAudio_md5(String audio_md5) {
		this.audio_md5 = audio_md5;
	}
	public float[] getBars_confidence() {
		return bars_confidence;
	}
	public void setBars_confidence(float[] bars_confidence) {
		this.bars_confidence = bars_confidence;
	}
	public float[] getBars_start() {
		return bars_start;
	}
	public void setBars_start(float[] bars_start) {
		this.bars_start = bars_start;
	}
	public float[] getBeats_confidence() {
		return beats_confidence;
	}
	public void setBeats_confidence(float[] beats_confidence) {
		this.beats_confidence = beats_confidence;
	}
	public float[] getBeats_start() {
		return beats_start;
	}
	public void setBeats_start(float[] beats_start) {
		this.beats_start = beats_start;
	}
	public float getDanceability() {
		return danceability;
	}
	public void setDanceability(float danceability) {
		this.danceability = danceability;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public float getEnd_of_fade_in() {
		return end_of_fade_in;
	}
	public void setEnd_of_fade_in(float end_of_fade_in) {
		this.end_of_fade_in = end_of_fade_in;
	}
	public float getEnergy() {
		return energy;
	}
	public void setEnergy(float energy) {
		this.energy = energy;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public float getKey_confidence() {
		return key_confidence;
	}
	public void setKey_confidence(float key_confidence) {
		this.key_confidence = key_confidence;
	}
	public float getLoudness() {
		return loudness;
	}
	public void setLoudness(float loudness) {
		this.loudness = loudness;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public float getMode_confidence() {
		return mode_confidence;
	}
	public void setMode_confidence(float mode_confidence) {
		this.mode_confidence = mode_confidence;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public int getRelease_7digitalid() {
		return release_7digitalid;
	}
	public void setRelease_7digitalid(int release_7digitalid) {
		this.release_7digitalid = release_7digitalid;
	}
	public float[] getSections_confidence() {
		return sections_confidence;
	}
	public void setSections_confidence(float[] sections_confidence) {
		this.sections_confidence = sections_confidence;
	}
	public float[] getSections_start() {
		return sections_start;
	}
	public void setSections_start(float[] sections_start) {
		this.sections_start = sections_start;
	}
	public float[] getSegments_confidence() {
		return segments_confidence;
	}
	public void setSegments_confidence(float[] segments_confidence) {
		this.segments_confidence = segments_confidence;
	}
	public float[] getSegments_loudness_max() {
		return segments_loudness_max;
	}
	public void setSegments_loudness_max(float[] segments_loudness_max) {
		this.segments_loudness_max = segments_loudness_max;
	}
	public float[] getSegments_loudness_max_time() {
		return segments_loudness_max_time;
	}
	public void setSegments_loudness_max_time(float[] segments_loudness_max_time) {
		this.segments_loudness_max_time = segments_loudness_max_time;
	}
	public float[] getSegments_loudness_max_start() {
		return segments_loudness_max_start;
	}
	public void setSegments_loudness_max_start(
			float[] segments_loudness_max_start) {
		this.segments_loudness_max_start = segments_loudness_max_start;
	}
	public float[][] getSegments_pitches() {
		return segments_pitches;
	}
	public void setSegments_pitches(float[][] segments_pitches) {
		this.segments_pitches = segments_pitches;
	}
	public float[] getSegments_start() {
		return segments_start;
	}
	public void setSegments_start(float[] segments_start) {
		this.segments_start = segments_start;
	}
	public float[][] getSegments_timbre() {
		return segments_timbre;
	}
	public void setSegments_timbre(float[][] segments_timbre) {
		this.segments_timbre = segments_timbre;
	}
	public String[] getSimilar_artists() {
		return similar_artists;
	}
	public void setSimilar_artists(String[] similar_artists) {
		this.similar_artists = similar_artists;
	}
	public float getSong_hotttnesss() {
		return song_hotttnesss;
	}
	public void setSong_hotttnesss(float song_hotttnesss) {
		this.song_hotttnesss = song_hotttnesss;
	}
	public String getSong_id() {
		return song_id;
	}
	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}
	public float getStart_of_fade_out() {
		return start_of_fade_out;
	}
	public void setStart_of_fade_out(float start_of_fade_out) {
		this.start_of_fade_out = start_of_fade_out;
	}
	public float[] getTatums_confidence() {
		return tatums_confidence;
	}
	public void setTatums_confidence(float[] tatums_confidence) {
		this.tatums_confidence = tatums_confidence;
	}
	public float[] getTatums_start() {
		return tatums_start;
	}
	public void setTatums_start(float[] tatums_start) {
		this.tatums_start = tatums_start;
	}
	public float getTempo() {
		return tempo;
	}
	public void setTempo(float tempo) {
		this.tempo = tempo;
	}
	public int getTime_signature() {
		return time_signature;
	}
	public void setTime_signature(int time_signature) {
		this.time_signature = time_signature;
	}
	public float getTime_signature_confidence() {
		return time_signature_confidence;
	}
	public void setTime_signature_confidence(float time_signature_confidence) {
		this.time_signature_confidence = time_signature_confidence;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTrack_id() {
		return track_id;
	}
	public void setTrack_id(String track_id) {
		this.track_id = track_id;
	}
	public int getTrack_7digitalid() {
		return track_7digitalid;
	}
	public void setTrack_7digitalid(int track_7digitalid) {
		this.track_7digitalid = track_7digitalid;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public float[] getPitche(){
		float[][] tab = getSegments_pitches();
		int ncols = tab[0].length;
		int nrows = tab.length;
		float[] out = new float[ncols];
		float average;
		for(int i=0; i<ncols; i++){
			average = 0;
			for(int j=0; j<nrows; j++){
				average += tab[i][j];
			}
			out[i] = average/nrows;
		}
		return out;
	}
	
	public float[] getTimbre(){
		float[][] tab = getSegments_timbre();
		int ncols = tab[0].length;
		int nrows = tab.length;
		float[] out = new float[ncols];
		float average;
		for(int i=0; i<ncols; i++){
			average = 0;
			for(int j=0; j<nrows; j++){
				average += tab[i][j];
			}
			out[i] = average/nrows;
		}
		return out;
	}
	public Tsv(String record){
		String[] values = record.split("\t");
		for(int i=0; i<values.length; i++){
			if(values[i].equals("nan") || values[i].equals("")){
				values[i] = "-1";
			}
			if(values[i].equals("")) System.out.println(i);
		}
		setTrack_id(values[0]);
		setSample_rate(Float.parseFloat(values[1]));
		setArtist_7digitalid(Integer.parseInt(values[2]));
		setArtist_familiarity(Float.parseFloat(values[3]));
		setArtist_hotttnesss(Float.parseFloat(values[4]));
		setArtist_id(values[5]);
		setArtist_latitude(Float.parseFloat(values[6]));
		setArtist_location(values[7]);
		setArtist_longitude(Float.parseFloat(values[8]));
		setArtist_mbid(values[9]);
		setArtist_mbtags(to1dArrayString(values[10], ","));
		setArtist_mbtags_count(to1dArrayInt(values[11], ","));
		setArtist_name(values[12]);
		setArtist_playmeid(Integer.parseInt(values[13]));
		setArtist_terms(to1dArrayString(values[14], ","));
		setArtist_terms_freq(to1dArrayFloat(values[15], ","));
		setArtist_terms_weight(to1dArrayFloat(values[16], ","));
		setAudio_md5(values[17]);
		setBars_confidence(to1dArrayFloat(values[18], ","));
		setBars_start(to1dArrayFloat(values[19], ","));
		setBeats_confidence(to1dArrayFloat(values[20], ","));
		setBeats_start(to1dArrayFloat(values[21], ","));
		setDanceability(Float.parseFloat(values[22]));
		setDuration(Float.parseFloat(values[23]));
		setEnd_of_fade_in(Float.parseFloat(values[24]));
		setEnergy(Float.parseFloat(values[25]));
		setKey(Integer.parseInt(values[26]));
		setKey_confidence(Float.parseFloat(values[27]));
		setLoudness(Float.parseFloat(values[28]));
		setMode(Integer.parseInt(values[29]));
		setMode_confidence(Float.parseFloat(values[30]));
		setRelease(values[31]);
		setRelease_7digitalid(Integer.parseInt(values[32]));
		setSections_confidence(to1dArrayFloat(values[33], ","));
		setSections_start(to1dArrayFloat(values[34], ","));
		setSegments_confidence(to1dArrayFloat(values[35], ","));
		setSegments_loudness_max(to1dArrayFloat(values[36], ","));
		setSegments_loudness_max_time(to1dArrayFloat(values[37], ","));
		setSegments_loudness_max_start(to1dArrayFloat(values[38], ","));
		setSegments_pitches(to2dArrayFloat(values[39], ",", getSegments_confidence().length));	
		setSegments_start(to1dArrayFloat(values[40], ","));
		setSegments_timbre(to2dArrayFloat(values[41], ",", getSegments_confidence().length));
		setSimilar_artists(to1dArrayString(values[42], ","));
		setSong_hotttnesss(Float.parseFloat(values[43]));
		setSong_id(values[44]);
		setStart_of_fade_out(Float.parseFloat(values[45]));
		setTatums_confidence(to1dArrayFloat(values[46], ","));
		setTatums_start(to1dArrayFloat(values[47], ","));
		setTempo(Float.parseFloat(values[48]));
		setTime_signature(Integer.parseInt(values[49]));
		setTime_signature_confidence(Float.parseFloat(values[50]));
		setTitle(values[51]);
		setTrack_7digitalid(Integer.parseInt(values[52]));
		setYear(Integer.parseInt(values[53]));
	}
	
	private static String[] to1dArrayString(String content, String sep){
		if(content.contains(sep)){
			String[] out  = content.split(sep);
			for(int i=0; i<out.length; i++){
				if(out[i].equals("nan") || out[i].equals("") ){
					//out[i] = "-1";
					out = ArrayUtils.removeElement(out, i);
				}
			}
			return out;
		}
		else{
			String[] out = new String[1];
			if(! content.equals("")) out[0] = content;
			else out[0] = "-1";
			return out;
		}
	}
	
	private static void json1dArrayString(JsonWriter writer, String attribute, String[] values) throws IOException{
		writer.name(attribute);
		writer.beginArray();
		for(String val : values) writer.value(val); 
		writer.endArray();
	}
	
	private static int[] to1dArrayInt(String content, String sep){
		if(content.contains(sep)){
			String[] values = content.split(sep);
			for(int i=0; i<values.length; i++){
				if(values[i].equals("nan") || values[i].equals("") ){
					values[i] = "-1";
				}
			}
			int[] out = new int[values.length];
			for(int i=0; i<values.length; i++){
				out[i] = Integer.parseInt(values[i]);
			}
			return out;
		}
		else{
			int[] out = new int[1];
			if(! content.equals("")) out[0] = Integer.parseInt(content);
			else out[0] = -1;
			return out;
		}
	}
	
	private static void json1dArrayInt(JsonWriter writer, String attribute, int[] values) throws IOException{
		writer.name(attribute);
		writer.beginArray();
		for(int val : values) writer.value(val); 
		writer.endArray();
	}
	
	private static float[] to1dArrayFloat(String content, String sep){
		if(content.contains(sep)){
			String[] values = content.split(sep);
			for(int i=0; i<values.length; i++){
				if(values[i].equals("nan") || values[i].equals("") ){
					values[i] = "-1";
				}
			}
			float[] out = new float[values.length];
			for(int i=0; i<values.length; i++){
				out[i] = Float.parseFloat(values[i]);
			}
			return out;
		}
		else{
			float[] out = new float[1];
			if(! content.equals("")) out[0] = Float.parseFloat(content);
			else out[0] = -1;			
			return out;
		}

	}
	
	private static void json1dArrayFloat(JsonWriter writer, String attribute, float[] values) throws IOException{
		writer.name(attribute);
		writer.beginArray();
		for(float val : values) writer.value(val); 
		writer.endArray();
	}
	
	private static float[][] to2dArrayFloat(String content, String sep, int nrows){
		if(content.contains(sep)){
			String[] values = content.split(sep);
			for(int i=0; i<values.length; i++){
				if(values[i].equals("nan") || values[i].equals("") ){
					values[i] = "-1";
				}
			}
			int ncols = values.length/nrows;
			float[][] out = new float[nrows][ncols];
			for(int i=0; i<values.length; i++){
				out[(i - i % ncols)/ncols][i % ncols] = Float.parseFloat(values[i]);
			}
			return out;
		}
		else{
			float[][] out = new float[1][1];
			if(! content.equals("")) out[0][0] = Float.parseFloat(content);
			else out[0][0] = -1;
			return out;
		}
		
	}
	
	private static void json2dArrayFloat(JsonWriter writer, String attribute, float[][] values) throws IOException{
		writer.name(attribute);
		writer.beginObject();
		int ncols = values[0].length;
		int nrows = values.length;
		for(int i=0; i<ncols; i++){
			writer.name("col" + i);
			writer.beginArray();
			for(int j=0; j<nrows; j++) writer.value(values[j][i]); 
			writer.endArray();
		}
		writer.endObject();
	}
	
	public static boolean isMissing(String value){
		if(value.equals("-1") || value.equals("-1.0")) return true;
		else return false;
	}
	
	public String toJson(List<String> attributes) throws IOException{
		StringWriter json = new StringWriter();
		JsonWriter writer = new JsonWriter(json);
	    writer.beginObject();
	    writer.name("track_id").value(getTrack_id());
	    if(attributes.contains("sample_rate") && ! isMissing(String.valueOf(getSample_rate()))) writer.name("sample_rate").value(getSample_rate());
	    if(attributes.contains("artist_7digitalid") && ! isMissing(String.valueOf(getArtist_7digitalid()))) writer.name("Artist_7digitalid").value(getArtist_7digitalid());
	    if(attributes.contains("artist_familiarity") && ! isMissing(String.valueOf(getArtist_familiarity()))) writer.name("artist_familiarity").value(getArtist_familiarity());
	    if(attributes.contains("artist_hotttnesss") && ! isMissing(String.valueOf(getArtist_hotttnesss()))) writer.name("artist_hotttnesss").value(getArtist_hotttnesss());
	    if(attributes.contains("artist_id") && ! isMissing(String.valueOf(getArtist_id()))) writer.name("artist_id").value(getArtist_id());
	    if(attributes.contains("artist_latitude") && ! isMissing(String.valueOf(getArtist_latitude()))) writer.name("artist_latitude").value(getArtist_latitude());
	    if(attributes.contains("artist_location") && ! isMissing(String.valueOf(getArtist_location()))) writer.name("artist_location").value(getArtist_location());
	    if(attributes.contains("artist_longitude") && ! isMissing(String.valueOf(getArtist_longitude()))) writer.name("artist_longitude").value(getArtist_longitude());
	    if(attributes.contains("artist_mb_tags") && ! isMissing(String.valueOf(getArtist_mbtags()[0]))) json1dArrayString(writer, "artist_mb_tags", getArtist_mbtags());
	    if(attributes.contains("artist_mb_tags_count") && ! isMissing(String.valueOf(getArtist_mbtags_count()[0]))) json1dArrayInt(writer, "artist_mb_tags_count", getArtist_mbtags_count());
	    if(attributes.contains("artist_name") && ! isMissing(String.valueOf(getArtist_name()))) writer.name("artist_name").value(getArtist_name());
	    if(attributes.contains("artist_playmeid") && ! isMissing(String.valueOf(getArtist_playmeid()))) writer.name("artist_playmeid").value(getArtist_playmeid());
	    if(attributes.contains("artist_terms") && ! isMissing(String.valueOf(getArtist_terms()[0]))) json1dArrayString(writer, "artist_terms", getArtist_terms());
	    if(attributes.contains("artist_terms_freq") && ! isMissing(String.valueOf(getArtist_terms_freq()[0]))) json1dArrayFloat(writer, "artist_terms_freq", getArtist_terms_freq());
	    if(attributes.contains("artist_terms_weight") && ! isMissing(String.valueOf(getArtist_terms_weight()[0]))) json1dArrayFloat(writer, "artist_terms_weight", getArtist_terms_weight());
	    if(attributes.contains("audio_md5") && ! isMissing(String.valueOf(getAudio_md5()))) writer.name("audio_md5").value(getAudio_md5());
	 	if(attributes.contains("bars_confidence") && ! isMissing(String.valueOf(getBars_confidence()[0]))) json1dArrayFloat(writer, "bars_confidence", getBars_confidence());
	 	if(attributes.contains("bars_start") && ! isMissing(String.valueOf(getBars_start()[0]))) json1dArrayFloat(writer, "bars_start", getBars_start());
	 	if(attributes.contains("beats_confidence") && ! isMissing(String.valueOf(getBeats_start()[0]))) json1dArrayFloat(writer, "beats_confidence", getBeats_confidence());
	 	if(attributes.contains("beats_start") && ! isMissing(String.valueOf(getBeats_start()[0]))) json1dArrayFloat(writer, "beats_start", getBeats_start());
	    if(attributes.contains("danceability") && ! isMissing(String.valueOf(getDanceability()))) writer.name("danceability").value(getDanceability());
	    if(attributes.contains("duration") && ! isMissing(String.valueOf(getDuration()))) writer.name("duration").value(getDuration());
	    if(attributes.contains("end_of_fade_in") && ! isMissing(String.valueOf(getEnd_of_fade_in()))) writer.name("end_of_fade_in").value(getEnd_of_fade_in());
	    if(attributes.contains("energy") && ! isMissing(String.valueOf(getEnergy()))) writer.name("energy").value(getEnergy());
	    if(attributes.contains("key") && ! isMissing(String.valueOf(getKey()))) writer.name("key").value(getKey());
	    if(attributes.contains("key_confidence") && ! isMissing(String.valueOf(getKey_confidence()))) writer.name("key_confidence").value(getKey_confidence());
	    if(attributes.contains("loudness") && ! isMissing(String.valueOf(getLoudness()))) writer.name("loudness").value(getLoudness());
	    if(attributes.contains("mode") && ! isMissing(String.valueOf(getMode()))) writer.name("mode").value(getMode());
	    if(attributes.contains("mode_confidence") && ! isMissing(String.valueOf(getMode_confidence()))) writer.name("mode_confidence").value(getMode_confidence());
	    if(attributes.contains("release") && ! isMissing(String.valueOf(getRelease()))) writer.name("release").value(getRelease());
	    if(attributes.contains("release_7digitalid") && ! isMissing(String.valueOf(getRelease_7digitalid()))) writer.name("release_7digitalid").value(getRelease_7digitalid());
	    if(attributes.contains("sections_confidence") && ! isMissing(String.valueOf(getSections_confidence()[0]))) json1dArrayFloat(writer, "sections_confidence", getSections_confidence());
	    if(attributes.contains("sections_start") && ! isMissing(String.valueOf(getSections_start()[0]))) json1dArrayFloat(writer, "sections_start", getSections_start());
	    if(attributes.contains("segments_confidence") && ! isMissing(String.valueOf(getSegments_confidence()[0]))) json1dArrayFloat(writer, "segments_confidence", getSegments_confidence());
	    if(attributes.contains("segments_loudness_max") && ! isMissing(String.valueOf(getSegments_loudness_max()[0]))) json1dArrayFloat(writer, "Segments_loudness_max", getSegments_loudness_max());
	    if(attributes.contains("segments_loudness_max_time") && ! isMissing(String.valueOf(getSegments_loudness_max_time()[0]))) json1dArrayFloat(writer, "Segments_loudness_max_time", getSegments_loudness_max_time());
	    if(attributes.contains("segments_loudness_max_start") && ! isMissing(String.valueOf(getSegments_loudness_max_start()[0]))) json1dArrayFloat(writer, "segments_loudness_max_start", getSegments_loudness_max_start());
	    if(attributes.contains("segments_pitches") && ! isMissing(String.valueOf(getSegments_pitches()[0][0]))) json2dArrayFloat(writer, "segments_pitches", getSegments_pitches());
	    if(attributes.contains("segments_start") && ! isMissing(String.valueOf(getSegments_start()[0]))) json1dArrayFloat(writer, "Segments_start", getSegments_start());
	    if(attributes.contains("segments_timbre") && ! isMissing(String.valueOf(getSegments_timbre()[0][0]))) json2dArrayFloat(writer, "segments_timbre", getSegments_timbre());
	    if(attributes.contains("similar_artists") && ! isMissing(String.valueOf(getSimilar_artists()[0]))) json1dArrayString(writer, "similar_artists", getSimilar_artists());
	    if(attributes.contains("song_hotttnesss") && ! isMissing(String.valueOf(getSong_hotttnesss()))) writer.name("song_hotttnesss").value(getSong_hotttnesss());
	    if(attributes.contains("song_id") && ! isMissing(String.valueOf(getSong_id()))) writer.name("song_id").value(getSong_id());
	    if(attributes.contains("start_of_fade_out") && ! isMissing(String.valueOf(getStart_of_fade_out()))) writer.name("start_of_fade_out").value(getStart_of_fade_out());
	 	if(attributes.contains("tatums_confidence") && ! isMissing(String.valueOf(getTatums_confidence()[0]))) json1dArrayFloat(writer, "tatums_confidence", getTatums_confidence());
	 	if(attributes.contains("tatums_start") && ! isMissing(String.valueOf(getTatums_start()[0]))) json1dArrayFloat(writer, "Tatums_start", getTatums_start());
	    if(attributes.contains("tempo") && ! isMissing(String.valueOf(getTempo()))) writer.name("tempo").value(getTempo());
	    if(attributes.contains("time_signature") && ! isMissing(String.valueOf(getTime_signature()))) writer.name("time_signature").value(getTime_signature());
	    if(attributes.contains("time_signature_confidence") && ! isMissing(String.valueOf(getTime_signature_confidence()))) writer.name("time_signature_confidence").value(getTime_signature_confidence());
	    if(attributes.contains("title") && ! isMissing(String.valueOf(getTitle()))) writer.name("title").value(getTitle());
	    if(attributes.contains("track_7digitalid") && ! isMissing(String.valueOf(getTrack_7digitalid()))) writer.name("track_7digitalid").value(getTrack_7digitalid());
	    if(attributes.contains("year") && ! isMissing(String.valueOf(getYear()))) writer.name("year").value(getYear());
	    writer.endObject();
	    writer.flush();
	    writer.close();
	    return json.toString();
	}
  	    
}
