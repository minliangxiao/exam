package prj.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {
    public static java.sql.Timestamp StringToTimestamp(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(s);
        } catch (ParseException ex) {
        }
        return new Timestamp(d.getTime());
    }
    public static Date StringToDate(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(s);
        } catch (ParseException ex) {
        }
        return d;
    }    
}
