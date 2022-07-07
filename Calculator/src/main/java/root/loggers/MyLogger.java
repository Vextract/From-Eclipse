package root.loggers;

import root.loggers.entities.LogEntry;
import root.main.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger implements AbstractLogger {
	
    private Logger logger;

    public MyLogger(Logger logger) {
        this.logger = logger;
    }

    public void error(Response response) {
        logger.log(Level.SEVERE, response.getException().getMessage());
    }

    @Override
    public void error(LogEntry logEntry) {

    }

    public void log(Response response) {
        logger.log(Level.INFO, "Получен ответ: " + response.getResult());
    }
}
