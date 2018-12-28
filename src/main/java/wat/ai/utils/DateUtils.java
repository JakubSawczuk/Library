package wat.ai.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtils {
    static final Logger LOGGER = Logger.getLogger(DateUtils.class.getName());

    public static java.sql.Date stringToDate(String s){
        java.util.Date date = null;
        java.sql.Date sqlDate = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(s);
            sqlDate = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        return sqlDate;
    }
}
