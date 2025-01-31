package util;

import javafx.util.StringConverter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlDateStringConverter extends StringConverter<Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String toString(Date date) {
        if (date != null) {
            return dateFormat.format(date);
        } else {
            return "";
        }
    }

    @Override
    public Date fromString(String string) {
        try {
            return new Date(dateFormat.parse(string).getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}