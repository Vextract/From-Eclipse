package root.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.customExceptions.FilterValidityException;
import root.repository.*;

import java.util.List;

@Component
public class LogService {

    private Repository repository;
    private FiltersValidator validator;

    @Autowired
    public LogService(Repository repository, FiltersValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public LogService(Repository repository) {
        this.repository = repository;
    }

    public List<Log> validateAndGetFromDB(DateFilter[] dateFilters) throws FilterValidityException {
        // Если фильтров нет.
        if (dateFilters == null) {
            return repository.getErrorsLog();
        }

        ValidationResponse resp = validator.validate(dateFilters);
        // Если ответ невалидный - бросаем исключение с сообщением.
        if (!resp.isValid()) {
            throw new FilterValidityException(resp.getMessage());
        } else {
            // Определяем количество фильтров/последовательность
            if (dateFilters.length == 2) {
                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")) {
                    return repository.getErrorsLogByTwoFilters(dateFilters[0].getDate(), dateFilters[1].getDate());
                } else {
                    return repository.getErrorsLogByTwoFilters(dateFilters[1].getDate(), dateFilters[0].getDate());
                }
            } else {
                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")) {
                    return repository.getErrorsLogFromDate(dateFilters[0].getDate());
                } else {
                    return repository.getErrorsLogToDate(dateFilters[0].getDate());
                }
            }
        }
    }
}
