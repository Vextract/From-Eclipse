package root.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import root.interfaces.Storage;
import root.loggers.entities.LogEntry;
import root.modules.calculation.entities.Response;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

@Component
public class StorageSqlImpl implements Storage {

    private final JdbcTemplate jdbcTemplate;

    public StorageSqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void error(LogEntry logEntry) {
        StringWriter sw = new StringWriter();
        logEntry.getException().printStackTrace(new PrintWriter(sw));
        String cause = sw.toString();
        jdbcTemplate.update("INSERT INTO logs VALUES(?,?,?,?,?)",
                new Date(logEntry.getCreated().getTime()),
                logEntry.getLoggerName(),
                logEntry.getLevel(),
                logEntry.getMessage(),
                cause);
    }

    @Override
    public void log(Response response) {

    }
}
