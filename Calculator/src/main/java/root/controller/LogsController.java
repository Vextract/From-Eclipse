package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import root.customExceptions.FilterValidityException;
import root.loggers.LogService;
import root.repository.DateFilter;
import root.repository.FiltersValidator;
import root.repository.LogResponse;
import root.repository.Repository;

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
