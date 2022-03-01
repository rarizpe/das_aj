package com.project.utils.date;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by Elí.Giacomelli on 02/04/2020.
 */
@Component
public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String DATE_FORMAT_NANO_SEC = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ";
    private static final String DATE_FORMAT_FRONT = "dd-MM-yyyy HH:mm:ss";
    private static final String DATE_FORMAT_LAST_POSITION = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_ARCHIVE = "ddMMyyyy";
    private static final String DATE_FORMAT_ARCHIVE_TRACKING = "yyyyMMdd";
    private static final String DATE_FORMAT_TEMP = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String DATE_PICKER_FORMAT="dd-MM-yyyy";
    private static final String LOCAL_TIME_FORMAT="HH:mm";
    private static final String RIGHT_TO_LEFT_FORMAT="yyyy/MM/dd";
    private static ZoneId zone = ZoneId.of("Mexico/General");

    public static LocalDateTime stringToDateTime(String dateString){
        if(dateString == null || dateString.equals(""))
            return null;

        Date dateToConvert = null;
        List<String> formatStrings = Arrays.asList(DATE_FORMAT,
                DATE_FORMAT_NANO_SEC,
                DATE_PICKER_FORMAT);
        for (String formatString : formatStrings){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat(formatString);
                sdf.setTimeZone(TimeZone.getTimeZone("Mexico/General"));
                dateToConvert = sdf.parse(dateString);
                break;
            }catch (Exception e) {
                //e.printStackTrace();
            }
        }
        if(dateToConvert != null){
            return dateToConvert.toInstant()
                    .atZone(zone).toLocalDateTime();
        }else{
            throw new DateTimeParseException("Couldn't find the right format",dateString,1);
        }
    }

    public static LocalTime stringToLocalTime(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(LOCAL_TIME_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("Mexico/General"));
        Date dateToConvert = sdf.parse(dateString);
        return dateToConvert.toInstant()
                .atZone(zone).toLocalTime();
    }

    public static ZoneId getZone(){
        return zone;
    }

    public static LocalDateTime getCurrentDateTime(){
        LocalDateTime currentTime = LocalDateTime.now(zone);
        return currentTime;
    }

    public static LocalDateTime millsToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(zone).toLocalDateTime();
        return date;
    }

    public static LocalDate millsToLocalDate(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        return instant.atZone(zone).toLocalDate();
    }

    public static Timestamp millsToTimestamp(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(zone).toLocalDateTime();
        return Timestamp.valueOf(date);
    }

    public static long getCurrentEpoch(){
        LocalDateTime currentTime = LocalDateTime.now(zone);
        return currentTime.atZone(zone).toEpochSecond();
    }

    public static String getCurrentDateTimeString(){
        return getCurrentDateTime().atZone(zone).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String formatDateTimeString(LocalDateTime dateTime){
        return dateTime.atZone(zone).format(DateTimeFormatter.ofPattern(DATE_FORMAT_FRONT));
    }

    public static String formatDateTimeYMD(LocalDateTime dateTime){
        return dateTime.atZone(zone).format(DateTimeFormatter.ofPattern(DATE_FORMAT_LAST_POSITION));
    }

    public static String formatDateTimeStringHistory(LocalDateTime dateTime){
        return dateTime.atZone(zone).format(DateTimeFormatter.ofPattern(DATE_FORMAT_ARCHIVE));
    }

    public static String formatDateTimeStringHistoryTracking(LocalDateTime dateTime){
        return dateTime.atZone(zone).format(DateTimeFormatter.ofPattern(DATE_FORMAT_ARCHIVE_TRACKING));
    }

    public static String getAsDatePickerFormat(LocalDateTime dateTime){
        return dateTime.atZone(zone).format(DateTimeFormatter.ofPattern(DATE_PICKER_FORMAT));
    }

    public static String getAsColumnDateFormat(LocalDateTime dateTime){
        return dateTime.atZone(zone).format(DateTimeFormatter.ofPattern(DATE_FORMAT_FRONT));
    }

    public static String getAsTimePickerFormat(LocalTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern(LOCAL_TIME_FORMAT));
    }

    public static long getEpochFromDateTime(LocalDateTime dateTime){
        return dateTime.atZone(zone).toEpochSecond();
    }

    public static boolean isBetweenHours(int orgOpenTime,int orgCloseTime) throws Exception{
        int awsTime = LocalTime.now().toSecondOfDay();
        if(awsTime > orgOpenTime && awsTime < orgCloseTime){
            return true;
        }else {
            return false;
        }
    }

    public static String formatTimeInHours(long different){
        double hours = ((float)different / 3600);
        String hoursS = hours+"";
        String[] parts = hoursS.split(Pattern.quote("."));
        String hourS = parts[0];
        String decimalS = "."+parts[1];
        String decimal = (Double.parseDouble(decimalS)*60)+"";
        long hou = Long.valueOf(hourS);
        parts = decimal.split(Pattern.quote("."));
        long dec = Long.valueOf(parts[0]);

        return String.format("%d:%02d", hou,dec);
    }

    public static String convertToDays(long time)
    {
        double days = ((float)time / (60*60*24*1000));
        String daysH = days+"";
        String[] parts = daysH.split(Pattern.quote("."));
        String daysS = parts[0];
        String decimalS = "."+parts[1];
        String decimal = (Double.parseDouble(decimalS)*24)+"";
        long hou = Long.valueOf(daysS);
        parts = decimal.split(Pattern.quote("."));
        long dec = Long.valueOf(parts[0]);

        return String.format("%d:%02d", hou,dec);
    }

    public static String formatTimeDifference(long millis){
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public static String timeBetweenLocalDateTimes(LocalDateTime init, LocalDateTime end){
        Duration dur = Duration.between(init, end);
        long millis = dur.toMillis();

        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public static String dayBetweenLocalDateTimes(LocalDateTime init, LocalDateTime end){
        Duration dur = Duration.between(init, end);
        long millis = dur.toMillis();

        return convertToDays(millis);
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public static LocalDate findFirstDayOfWeek(long weekDayDate){
        LocalDate date = millsToLocalDateTime(weekDayDate).toLocalDate();
        int dayOfWeek = date.getDayOfWeek().getValue();
        return date.minusDays((dayOfWeek-1));
    }

    public static String getAsDateRightToLeftFormat(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern(RIGHT_TO_LEFT_FORMAT));
    }

}
