package info.caprese.fettuccine.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static String format(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);

    }

    public static LocalDateTime toLocalDateTime(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date formatDate = null;
        try {
            formatDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return LocalDateTime.ofInstant(formatDate.toInstant(), ZoneId.systemDefault());
    }
}
