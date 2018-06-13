package exception;

public class IlluminanceTooLittleException extends Exception {
	public IlluminanceTooLittleException(){
		super("Too little Illuminance. Illuminance should be in the range 300 - 4000");
	}
}
