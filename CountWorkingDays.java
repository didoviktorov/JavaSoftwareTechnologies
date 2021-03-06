import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CountWorkingDays {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date firstDate = formatter.parse(scanner.nextLine());
        Date secondDate = formatter.parse(scanner.nextLine());

        Date [] holidays = {
                getDateFromString("01-01"),
                getDateFromString("03-03"),
                getDateFromString("01-05"),
                getDateFromString("06-05"),
                getDateFromString("24-05"),
                getDateFromString("06-09"),
                getDateFromString("22-09"),
                getDateFromString("01-11"),
                getDateFromString("24-12"),
                getDateFromString("25-12"),
                getDateFromString("26-12"),
        };

        int workingDays = 0;
        while (!firstDate.after(secondDate)) {
            if(!isHoliday(firstDate, holidays)) {
                workingDays++;
            }
            firstDate = incrementDate(firstDate);
        }

        System.out.println(workingDays);
    }

    private static Date incrementDate(Date firstDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(firstDate);
        cal.add(Calendar.DATE, 1);
        firstDate = cal.getTime();

        return firstDate;
    }

    public static Date getDateFromString(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        Date result = sdf.parse(str);
        return result;
    }


    public static boolean isHoliday(Date date, Date [] holidays) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);

        for (int i = 0; i < holidays.length; i++) {
            cal2.setTime(holidays[i]);
            if((cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
                    || cal1.get(Calendar.DAY_OF_WEEK) == 1
                    || cal1.get(Calendar.DAY_OF_WEEK) == 7){
                return true;
            }

        }
        return false;
    }
}