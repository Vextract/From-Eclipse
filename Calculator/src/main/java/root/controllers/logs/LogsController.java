package root.controllers.logs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import root.repository.LogService;
import root.repository.Repository;
import root.repository.validator.FiltersValidator;
import root.repository.valueObjects.DateFilter;
import root.repository.valueObjects.LogResponse;
import root.utility.customExceptions.FilterValidityException;

@RequestMapping("repositorySql")
@RestController()
public class LogsController {
    private LogService logService;

    @Autowired
    public LogsController(LogService logService) {
        this.logService = logService;
    }

    @RequestMapping("/logs")
    @GetMapping
    public LogResponse getErrorsLog() throws FilterValidityException {
        return new LogResponse("Successful.", logService.validateAndGetFromDB(null));
    }

    @RequestMapping("/filteredLogs")
    @PostMapping
    public LogResponse getErrorsLogByFilter(@RequestBody DateFilter[] dateFilters) {
        try {
            return new LogResponse("Successful", logService.validateAndGetFromDB(dateFilters));
        } catch (FilterValidityException e) {
            return new LogResponse(e.getMessage(), null);
        }
    }
}
