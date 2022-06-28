package root.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import root.main.Response;
import root.storage.Storage;

import java.util.logging.Level;

@Component
public class LoggerToDB implements AbstractLogger {

    private Storage storage;

    @Autowired
    public LoggerToDB(Storage storage) {
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
