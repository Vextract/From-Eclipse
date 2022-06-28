package root.storage;

import root.loggers.LogEntry;
import root.main.Response;

public interface Storage {

    void log(Response response);

    void error(LogEntry logEntry);
}
