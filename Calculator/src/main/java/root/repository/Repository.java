package root.repository;

import java.util.Date;
import java.util.List;

import root.loggers.entities.Log;

public interface Repository {

    List<Log> getErrorsLog();

    List<Log> getErrorsLogFromDate(Date from);

    List<Log> getErrorsLogToDate(Date to);

    List<Log> getErrorsLogByTwoFilters(Date from, Date to);
}
