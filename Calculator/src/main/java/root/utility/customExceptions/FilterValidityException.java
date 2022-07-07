package root.utility.customExceptions;

/**
 * 
 * This exception occur when FiltersValidator 
 * isn't confirm validity of DateFilters which it accepted.
 * 
 * Constructor accepts message (String).
 *
 */
public class FilterValidityException extends Exception {

	private static final long serialVersionUID = 596834219798918373L;

	public FilterValidityException(String message) {
        super(message);
    }
}
