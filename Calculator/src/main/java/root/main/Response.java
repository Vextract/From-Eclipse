package root.main;

public class Response {
	
	private String message;

    private Double result;

    private Exception exception;

	public Response(String message, Double result, Exception exception) {
		this.message = message;
		this.result = result;
		this.exception = exception;
	}

	public Double getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

	public String getMessage() {
		return message;
	}
}
