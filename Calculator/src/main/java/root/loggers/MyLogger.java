package root.loggers;

import root.main.Response;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger implements AbstractLogger {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd - HH:mm:ss");
    private Logger logger;
    private String loggerName;

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
