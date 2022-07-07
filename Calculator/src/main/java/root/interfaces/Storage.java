package root.interfaces;

import root.loggers.entities.LogEntry;
import root.modules.calculation.entities.Response;

public interface Storage {

    void log(Response response);

    void error(LogEntry logEntry);
}
