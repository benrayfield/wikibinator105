package wikibinator105.plugins.jsoundcard;
import jsoundcard.JSoundCard;
import jsoundcard.SoundFunc;
import wikibinator105.spec.*;

public class Play{
	private Play(){}
	
	/** soundstream.e(double[1]).e(Tru).d(0) and .d(1) are next 2 speaker outputs,
	and the double[1] is microphone input, all in range -1 to 1 (else truncates into that range),
	and soundstream.e(double[1]).e(Fal) is next soundstream (a stateless snapshot of its next state),
	though it can have a tiny amount of state in the Op.Wiki function,
	in the way that (Wiki x)->y and (Wiki x)->z cant both be true unless y==z
	aka the infinite space inside Wiki can each part only be written once ever,
	and maybe (Wiki [timeUtcNanoseconds "user3345346547453e45345345.microphoneX"])->2_float64Amplitudes
	which soundstream might know to look in that, and externally you create
	(AxA (Fpr Wiki [1612969561952000000L "user3345346547453e45345345.microphoneX"] cbtOf_-.563345634_.77889)),
	though that might need some adjustments to this play_float64_2Speakers_1microphone_44100fps
	function to sync the time better, such as maybe timeUtcNanoseconds should be 1 of the params
	or in the cbt as the first double (can only have microsecond precision if double, or still use it as long?)
	as 1 of the sound channels of the λ soundstream??? or just another param of the soundstream?
	<br><br>
	TODO test this. The code isnt even compiling as of 2021-2-10 but occamsfuncer works
	and this is a variant of occamsfuncer. Will get it working soon, and this will fit in,
	other than it might be too slow and need to be called in blocks
	of 16 frames (wave amplitudes per speaker/microphone) or 256 of them like WebAudioAPI does etc,
	but since its only called 44100 times per second, that shouldnt be the bottleneck.
	*/
	public static void play_float64_2Speakers_1Microphone_44100Fps(λ soundstream){
		if(soundstream == null) play(null);
		else play(new SoundStreamFloat64() {
			λ state = soundstream;
			final λ T = soundstream.op(Op.Tru); //can get these from any λ
			final λ F = soundstream.op(Op.Fal);
			public double[] apply(double[] ins){
				//TODO use e(maxSpend,param) to avoid infiniteloops etc, considering its limited to 0.9/fps seconds.
				λ pairOfOutsAndNextState = state.e(ins);
				λ outs = pairOfOutsAndNextState.e(T); //same as lispCar if its a pair
				double[] ret = new double[]{
					outs.d(0), //speaker
					outs.d(1)  //speaker
				};
				state = pairOfOutsAndNextState.e(F); //same as lispCdr if its a pair
				return ret;
			}
			public int outs(){
				return 2;
			}
			public int ins(){
				return 1;
			}
			public double fps(){
				return 44100;
			}
		});
	}
	
	/** Play an interactive SoundStream, such as to change your voice into microphone out speakers.
	This is low enough lag in linux for live music performances, but in windows
	(at least earlier versions, todo test again in win10) its noticably higher lag.
	play(null) to stop.
	*/
	public static void play(SoundStream s){
		if(s == null){ //stop playing
			JSoundCard.stop();
		}else{ //start playing
			if(s.ins() != 1 || s.outs() != 2) throw new RuntimeException(
				"TODO upgrade jsoundcard. As of 2021-2-10 it has to be 2 speakers and 1 microphone (or whatevers plugged into those such as an electric guitar into microphone hole). The code can easily be upgraded to handle for example 20 microphones and 17 speakers, was planning to do it eventually.");
			try{
				if(s instanceof SoundStreamFloat64){
					SoundStreamFloat64 S = (SoundStreamFloat64) s;
					JSoundCard.play(
						new SoundFunc(){
							public int frameSize(){
								return 3; //speaker speaker microphone
							}
							public void readWriteFrame(double[] frame){
								double[] out = S.apply(new double[]{
									frame[2] //microphone
								});
								frame[0] = out[0]; //speaker
								frame[1] = out[1]; //speaker
							}
						},
						s.outs(),
						s.ins(),
						s.fps()
					);
				}else if(s instanceof SoundStreamInt16){
					SoundStreamInt16 S = (SoundStreamInt16) s;
					JSoundCard.play(
						new SoundFunc(){
							public int frameSize(){
								return 3; //speaker speaker microphone
							}
							public void readWriteFrame(double[] frame){
								short[] out = S.apply(new short[]{
									bifractionToShort(frame[2]) //microphone
								});
								frame[0] = shortToBifraction(out[0]); //speaker
								frame[1] = shortToBifraction(out[1]); //speaker
							}
						},
						s.outs(),
						s.ins(),
						s.fps()
					);
				}else{
					throw new RuntimeException("Unknown type: "+s);
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
	
	/** bifraction is in range -1 to 1. Truncates if its not. */
	public static short bifractionToShort(double bifraction){
		return (short)Math.max(Short.MIN_VALUE, Math.min(bifraction*(1<<15), Short.MAX_VALUE));
	}
	
	/** FIXME bifractionToShort(shortToBifraction(short)) should equal for all possible shorts,
	needs testing for Short.MAX_VALUE and Short.MIN_VALUE and 0 etc,
	and the double values 1 and -1 and 0 for the other direction
	shortToBifraction(bifractionToShort(double))???
	*/
	public static double shortToBifraction(short s){
		return s/(double)(1<<15);
	}
	
	public static void main(String[] args){
		System.out.println("Start playing 1 frequency");
		play(new SoundStreamInt16(){
			double[] state = new double[1];
			public short[] apply(short[] ins){
				double volume = .2;
				double dt = 1/fps();
				double microphone = shortToBifraction(ins[0]);
				state[0] += dt;
				double freq = 500;
				double circle = Math.PI*2;
				double wave = volume*Math.sin(state[0]*circle*freq);
				return new short[]{
					bifractionToShort(wave), //speaker
					bifractionToShort(wave) //speaker
				};
			}
			public int outs(){
				return 2;
			}
			public int ins(){
				return 1;
			}
			public double fps(){
				return 44100;
			}
		});
		try{
			Thread.sleep(2000L);
		}catch(InterruptedException e){ throw new RuntimeException("TODO"); }
		System.out.println("Stop that, and start amplifying microphone but with volume norming so it never explodes in volume or goes quiet");
		play(new SoundStreamFloat64(){
			double maxRecent = .00000000001;
			double decayMaxRecent = .3;
			public double[] apply(double[] ins){
				double volume = .1;
				double dt = 1/fps();
				double microphone = ins[0];
				maxRecent = Math.max(maxRecent*(1-dt*decayMaxRecent), Math.abs(microphone));
				double normedMicrophone = microphone/maxRecent; //range -1 to 1, nomatter how fast it jumps
				double wave = volume*normedMicrophone;
				return new double[]{
					wave, //speaker
					wave //speaker
				};
			}
			public int outs(){
				return 2;
			}
			public int ins(){
				return 1;
			}
			public double fps(){
				return 44100;
			}
		});
		try{
			Thread.sleep(28000L);
		}catch(InterruptedException e){ throw new RuntimeException("TODO"); }
		System.out.println("Stop playing");
		play(null);
	}

}