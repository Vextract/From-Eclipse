package root.modules.logs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import root.modules.logs.entities.DateFilter;
import root.modules.logs.entities.LogResponse;
import root.modules.logs.services.LogService;
import root.utility.customExceptions.FilterValidityException;

@RequestMapping("repository")
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
