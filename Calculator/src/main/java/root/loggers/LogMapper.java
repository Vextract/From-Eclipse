package root.loggers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogMapper implements RowMapper<Log> {

    @Override
    public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
        Log log = new Log();

        log.setDate(rs.getDate("dated"));
        log.setCapturedBy(rs.getString("logger"));
        log.setLevel(rs.getString("level"));
        log.setShortMessage(rs.getString("message"));
        log.setStacktrace(rs.getString("cause"));

        return log;
    }
}
