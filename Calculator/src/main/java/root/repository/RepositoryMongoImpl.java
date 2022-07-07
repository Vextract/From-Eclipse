package root.repository;

import com.mongodb.*;

import root.loggers.entities.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMongoImpl implements Repository {

    private DB database;

    public RepositoryMongoImpl(DB database) {
        this.database = database;
    }


    @Override
    public List<Log> getErrorsLog() {
        DBCollection collection = database.getCollection("Errors");
        List<Log> logs = new ArrayList<>();
        try (DBCursor results = collection.find()) {
            for (DBObject result: results) {
                Log log = new Log(
                        new Date(((java.util.Date) result.get("date")).getTime()),
                        (String) result.get("loggerName"),
                        (String) result.get("level"),
                        (String) result.get("message"),
                        (String) result.get("cause")
                );

                logs.add(log);
            }
        }
        return logs;
    }

    @Override
    public List<Log> getErrorsLogFromDate(java.util.Date from) {
        List<Log> logs =  getErrorsLog();
        return logs.stream()
                .filter(log -> !log.getDate().before(from))
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> getErrorsLogToDate(java.util.Date to) {
        List<Log> logs =  getErrorsLog();
        return logs.stream()
                .filter(log -> !log.getDate().after(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> getErrorsLogByTwoFilters(java.util.Date from, java.util.Date to) {
        List<Log> logs =  getErrorsLog();
        return logs.stream()
                .filter(log -> !log.getDate().before(from)
                        && !log.getDate().after(to))
                .collect(Collectors.toList());
    }
}
