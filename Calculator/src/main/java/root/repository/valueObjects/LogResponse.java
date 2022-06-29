package root.repository.valueObjects;

import java.util.List;

import root.loggers.entities.Log;

public class LogResponse {

    private String message;

    private List<Log> errorsList;

    public LogResponse(String message, List<Log> errorsList) {
        this.message = message;
        this.errorsList = errorsList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Log> getErrorsList() {
        return errorsList;
    }

    public void setErrorsList(List<Log> errorsList) {
        this.errorsList = errorsList;
    }
}
