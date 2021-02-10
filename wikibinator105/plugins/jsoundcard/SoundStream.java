package wikibinator105.plugins.jsoundcard;

/** any number of speakers and microphones.
This is called many times per second (maybe 22050 or 44100?),
each time receiving next m microphone amplitudes (int16s or float64s)
and outputting speaker amplitudes (int16s or float64s).
*/
public interface SoundStream{
	
	/** fps is framesPerSecond, such as 44100 is called "cd quality",
	and 22050 sounds ok, but 11025 is very distorted.
	*/
	public double fps();
	
	public int ins();
	
	public int outs();

}
