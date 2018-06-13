package exception;

public class IlluminanceTooMuchException extends Exception {
	public IlluminanceTooMuchException(){
		super("Too much Illuminance. Illuminance should be in the range 300 - 4000");
	}
}
