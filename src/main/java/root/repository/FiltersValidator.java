package root.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.loggers.Log;

import java.util.*;

@Component
public class FiltersValidator {

    public ValidationResponse validate(DateFilter[] dateFilters) {
        if (dateFilters.length > 0) {
            if (dateFilters.length > 2) {
                return new ValidationResponse(false,"Должно быть не больше двух фильтров.");
            }

            if (dateFilters.length == 2) {
                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")
                        && dateFilters[1].getFromOrTo().equalsIgnoreCase("from") ||
                        dateFilters[0].getFromOrTo().equalsIgnoreCase("to")
                                && dateFilters[1].getFromOrTo().equalsIgnoreCase("to")
                ) {
                    return new ValidationResponse(false,"Оба фильтра не могут быть from/to");
                }

                if (dateFilters[0].getDate().before(new Date(100, Calendar.JANUARY, 1)) ||
                        dateFilters[1].getDate().before(new Date(100, Calendar.JANUARY, 1))) {
                    return new ValidationResponse(false,"Даты фильтров не должны быть раньше 01.01.2000.");
                }

                DateFilter from = Arrays.stream(dateFilters)
                        .filter(dateFilter ->
                                dateFilter.getFromOrTo()
                                        .equalsIgnoreCase("from"))
                        .findAny()
                        .get();

                DateFilter to = Arrays.stream(dateFilters)
                        .filter(dateFilter ->
                                dateFilter.getFromOrTo()
                                        .equalsIgnoreCase("to"))
                        .findAny()
                        .get();

                // Если неправильно прописана последовательность дат - пустой список.
                if (from.getDate().after(to.getDate())) {
                    return new ValidationResponse(false,"From позднее даты To или наоборот.");
                }
            } else {
                if (dateFilters[0].getDate().before(new Date(100, Calendar.JANUARY, 1))) {
                    return new ValidationResponse(false,"Даты фильтров не должны быть раньше 01.01.2000.");
                }
            }
            return new ValidationResponse(true,null);
        }
        return new ValidationResponse(false,"Должен быть передан как минимум один фильтр.");
    }
}
