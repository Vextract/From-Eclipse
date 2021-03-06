package root.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import root.interfaces.AbstractLogger;
import root.interfaces.Storage;
import root.loggers.entities.LogEntry;
import root.modules.calculation.entities.Response;

import java.util.logging.Level;

@Component
public class LoggerToDB implements AbstractLogger {

	
    private Storage storage;

    @Autowired
    public LoggerToDB(@Qualifier("storageMongoImpl") Storage storage) {
        this.storage = storage;
    }

    @Override
    public void error(LogEntry logEntry) {
        if (storage != null) {
            logEntry.setLoggerName(this.getClass().getSimpleName());
            logEntry.setLevel(Level.WARNING.getName());
            storage.error(logEntry);
        } else System.out.println("root.storage.Storage не проиницилизирован");
    }

    @Override
    public void log(Response response) {
        if (storage != null) {
            storage.log(response);
        } else System.out.println("root.storage.Storage не проиницилизирован");
    }
}
