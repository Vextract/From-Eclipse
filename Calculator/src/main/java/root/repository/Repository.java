package root.repository;

import root.loggers.Log;

import java.util.Date;
import java.util.List;

public interface Repository {

    List<Log> getErrorsLog();

    List<Log> getErrorsLogFromDate(Date from);

    List<Log> getErrorsLogToDate(Date to);

    List<Log> getErrorsLogByTwoFilters(Date from, Date to);
}
