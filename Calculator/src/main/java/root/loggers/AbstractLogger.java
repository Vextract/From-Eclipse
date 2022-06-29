package root.loggers;

import root.loggers.entities.LogEntry;
import root.main.Response;

public interface AbstractLogger {

    void error(LogEntry logEntry);

    void log(Response response);
}
