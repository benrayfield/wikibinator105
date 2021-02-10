package wikibinator105.plugins.jsoundcard;
import java.util.function.UnaryOperator;

/** wave amplitudes range -1 to 1 */
public interface SoundStreamFloat64 extends SoundStream, UnaryOperator<double[]>{

}
