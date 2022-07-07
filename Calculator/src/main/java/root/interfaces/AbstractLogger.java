package root.interfaces;

import root.loggers.entities.LogEntry;
import root.modules.calculation.entities.Response;

public interface AbstractLogger {

    void error(LogEntry logEntry);

    void log(Response response);
}
