package it.interno.personefisiche.util;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Utils {

    private final static String FORMAT_TIMESTAMP = "yyyy-MM-dd-HH.mm.ss.SSSSSS" ;

    public static String convertTimestampToStringByFormat(Timestamp tmspDaConvertire) {
        if(Objects.isNull(tmspDaConvertire))
            return null ;

        ZonedDateTime zdt = ZonedDateTime.ofInstant(tmspDaConvertire.toInstant(), ZoneId.of("Europe/Rome")) ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TIMESTAMP);
        return zdt.format(formatter) ;
    }

    public static Timestamp convertStringToTimestampByFormat(String stringaDaConvertire) {
        if(Objects.isNull(stringaDaConvertire))
            return null ;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TIMESTAMP);
        ZonedDateTime zdt = ZonedDateTime.parse(stringaDaConvertire, formatter.withZone(ZoneId.of("Europe/Rome"))) ;

        return Timestamp.from(zdt.toInstant());
    }

}
