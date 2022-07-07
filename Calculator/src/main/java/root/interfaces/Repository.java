package root.interfaces;

import java.util.Date;
import java.util.List;

import root.modules.logs.entities.Log;

public interface Repository {

    List<Log> getErrorsLog();

    List<Log> getErrorsLogFromDate(Date from);

    List<Log> getErrorsLogToDate(Date to);

    List<Log> getErrorsLogByTwoFilters(Date from, Date to);

}
