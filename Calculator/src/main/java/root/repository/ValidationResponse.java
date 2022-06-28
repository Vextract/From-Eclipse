package root.repository;

public class ValidationResponse {

    private boolean valid;

    private String message;

    public ValidationResponse(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
