package root.utility.customExceptions;

/**
 * 
 * This exception occur when operation passed
 * to calculationApi method not apply to any listed in the Model.
 * 
 */

public class UnsupportedOperationExceptionCustom extends Exception{

	private static final long serialVersionUID = 7994442898799161918L;

    public UnsupportedOperationExceptionCustom(String message) {
        super(message);
    }
}
