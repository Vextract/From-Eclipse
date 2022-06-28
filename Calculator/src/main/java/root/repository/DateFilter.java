package root.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DateFilter {

    private String fromOrTo;
    private Date date;

    public DateFilter(@JsonProperty("fromOrTo") String fromOrTo,
                      @JsonProperty("date") Date date) {
        this.fromOrTo = fromOrTo;
        this.date = date;
    }

    public String getFromOrTo() {
        return fromOrTo;
    }

    public void setFromOrTo(String fromOrTo) {
        this.fromOrTo = fromOrTo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
