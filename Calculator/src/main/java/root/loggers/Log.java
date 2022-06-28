package root.loggers;

import java.util.Date;

public class Log {

    private Date date;
    private String capturedBy;
    private String level;
    private String shortMessage;
    private String stacktrace;

    public Log() {
    }

    public Log(Date date, String capturedBy, String level, String shortMessage, String stacktrace) {
        this.date = date;
        this.capturedBy = capturedBy;
        this.level = level;
        this.shortMessage = shortMessage;
        this.stacktrace = stacktrace;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCapturedBy() {
        return capturedBy;
    }

    public void setCapturedBy(String capturedBy) {
        this.capturedBy = capturedBy;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    @Override
    public String toString() {
        return "root.loggers.Log{" +
                "date=" + date +
                ", capturedBy='" + capturedBy + '\'' +
                ", level='" + level + '\'' +
                ", shortMessage='" + shortMessage + '\'' +
                ", stacktrace='\n" + stacktrace + '\'' +
                '}' + "\n";
    }
}
