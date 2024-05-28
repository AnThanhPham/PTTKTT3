public class Date implements Comparable<Date>{
    private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private final int month;   // month (between 1 and 12)
    private final int day;     // day   (between 1 and DAYS[month]
    private final int year;    // year

    /**
     * Initializes a new date from the month, day, and year.
     * @param month the month (between 1 and 12)
     * @param day the day (between 1 and 28-31, depending on the month)
     * @param year the year
     * @throws IllegalArgumentException if this date is invalid
     */
    public Date(int day, int month, int year) {
        if (!isValid(day, month, year)) throw new IllegalArgumentException("Invalid date");
        this.month = month;
        this.day   = day;
        this.year  = year;
    }

    /**
     * Initializes new date specified as a string in form MM/DD/YYYY.
     * @param date the string representation of this date
     * @throws IllegalArgumentException if this date is invalid
     */
    public Date(String date) {
        String[] fields = null;
        if(date.contains("/")){
            fields = date.split("/");
        }else if(date.contains("-")){
            fields = date.split("-");
        }else if (date.contains("_")){
            fields = date.split("_");
        }else {
            fields = convertInputVietNam(date);
        }
        day   = Integer.parseInt(fields[0].trim());
        month = Integer.parseInt(fields[1].trim());
        year  = Integer.parseInt(fields[2].trim());
        new Date(day,month,year);
    }

    public String[] convertInputVietNam(String a){
        String[] tmp = a.split("\\s+");
        String[] res = new String[3];
        res[0] = tmp[1];
        res[1] = tmp[3];
        res[2] = tmp[5];
        return res;
    }

    /**
     * Return the month.
     * @return the month (an integer between 1 and 12)
     */
    public int month() {
        return month;
    }

    /**
     * Returns the day.
     * @return the day (an integer between 1 and 31)
     */
    public int day() {
        return day;
    }

    /**
     * Returns the year.
     * @return the year
     */
    public int year() {
        return year;
    }


    // is the given date valid?
    private static boolean isValid(int d, int m, int y) {
        if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DAYS[m]) return false;
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }

    // is y a leap year?
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    /**
     * Returns the next date in the calendar.
     *
     * @return a date that represents the next day after this day
     */
    public Date next() {
        if (isValid(day+1, month, year))    return new Date(day+1, month, year);
        else if (isValid(1,month + 1, year)) return new Date(1,month+1, year);
        else                                  return new Date(1, 1, year + 1);
    }

    /**
     * Compares two dates chronologically.
     *
     * @param  that the other date
     * @return {@code true} if this date is after that date; {@code false} otherwise
     */
    public boolean isAfter(Date that) {
        return compareTo(that) > 0;
    }

    /**
     * Compares two dates chronologically.
     *
     * @param  that the other date
     * @return {@code true} if this date is before that date; {@code false} otherwise
     */
    public boolean isBefore(Date that) {
        return compareTo(that) < 0;
    }

    /**
     * Compares two dates chronologically.
     *
     * @return the value {@code 0} if the argument date is equal to this date;
     *         a negative integer if this date is chronologically less than
     *         the argument date; and a positive ineger if this date is chronologically
     *         after the argument date
     */

    @Override
    public int compareTo(Date that) {
        if (this.year  < that.year)  return -1;
        if (this.year  > that.year)  return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day   < that.day)   return -1;
        if (this.day   > that.day)   return +1;
        return 0;
    }

    /**
     * Returns a string representation of this date.
     *
     * @return the string representation in the format MM/DD/YYYY
     */
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    /**
     * Compares this date to the specified date.
     *
     * @param  other the other date
     * @return {@code true} if this date equals {@code other}; {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Date that = (Date) other;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
    }

    /**
     * Returns an integer hash code for this date.
     *
     * @return an integer hash code for this date
     */
    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31*hash + month;
        hash = 31*hash + day;
        hash = 31*hash + year;
        return hash;
    }

    /**
     * Unit tests the {@code Date} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Date today = new Date("Ngày 28 tháng 10 năm  2024");
        System.out.println("Ngày vừa nhập là:"+today+"\n");
        for (int i = 0; i < 5; i++) {
            today = today.next();
            System.out.println("Ngày tiếp theo là:"+today);
        }
        System.out.println("\nNgày sau khi tịnh tiến:"+today);
        // System.out.println(today.isAfter(today.next()));
        // System.out.println(today.isAfter(today));
        // System.out.println(today.next().isAfter(today));
    }
}
