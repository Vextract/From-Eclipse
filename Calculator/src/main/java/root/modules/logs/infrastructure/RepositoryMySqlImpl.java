package root.modules.logs.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import root.interfaces.Repository;
import root.loggers.utility.LogMapper;
import root.modules.logs.entities.Log;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RepositoryMySqlImpl implements Repository {

    private final JdbcTemplate jdbcTemplate;

    public RepositoryMySqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Log> getErrorsLog() {
        return jdbcTemplate.query("SELECT * FROM logs", new LogMapper());
    }

    @Override
    public List<Log> getErrorsLogFromDate(Date from) {
        List<Log> logs =  getErrorsLog();
        return logs.stream()
                .filter(log -> !log.getDate().before(from))
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> getErrorsLogToDate(Date to) {
        List<Log> logs =  getErrorsLog();
        return logs.stream()
                .filter(log -> !log.getDate().after(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> getErrorsLogByTwoFilters(Date from, Date to) {
        List<Log> logs =  getErrorsLog();
        return logs.stream()
                .filter(log -> !log.getDate().before(from)
                        && !log.getDate().after(to))
                .collect(Collectors.toList());
    }
}
