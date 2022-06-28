package root.loggers;

import root.customExceptions.NotEnoughArgumentsException;
import root.customExceptions.UnsupportedOperationExceptionCustom;

import java.util.Date;

public class LogEntry {

    private Date created;
    private String loggerName;
    private String level;
    private String message;
    private Exception exception;
    private String cause;

    public LogEntry(Exception e) {
        this.created = new Date();
        if (e != null) {
            if (e instanceof NotEnoughArgumentsException) {
                this.message = "Недостаточно аргументов";
            } else if (e instanceof NumberFormatException) {
                this.message = "Неправильный формат аргументов";
            } else if (e instanceof UnsupportedOperationExceptionCustom) {
                this.message = "Операция " +
                        ((UnsupportedOperationExceptionCustom) e).getOperation() +
                        " не поддерживается";
            } else {
                this.message = "Неизвестная ошибка";
            }
        }
        this.exception = e;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
