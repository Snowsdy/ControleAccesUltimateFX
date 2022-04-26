package fr.snowsdy.controleAcces.metier.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Manage a time slot beetween two date get the day, hour and minutes
 * of the begin and the end of the time slot
 * 
 * @author fanou
 */
@Entity
public class TimeSlot implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int beginHour;
    private int endHour;
    private int beginMinutes;
    private int endMinutes;
    @Enumerated(EnumType.STRING)
    private Day beginDay;
    @Enumerated(EnumType.STRING)
    private Day endDay;
    
    @Transient
    private long numberOfDay;

    /**
     * constructor
     * @param beginDate : date object of the begin of the time slot
     * @param endDate : date object of the end of the time slot
     * @throws Exception : if end is before begin raise an error
     */
    public TimeSlot(Date beginDate, Date endDate) throws Exception {
        if (beginDate.after(endDate)) {
            throw new Exception("begin date is after end date");
        }
        this.extract(beginDate, true);
        this.extract(endDate, false);

        final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
        long diff = Math.abs(beginDate.getTime() - endDate.getTime());
        this.numberOfDay = (long) diff / MILLISECONDS_PER_DAY + 1;
    }
    
    public TimeSlot(Day beginDay, int beginHour, int beginMinutes, Day endDay, int endHour, int endMinutes) {
        this.beginDay = beginDay;
        this.beginHour = beginHour;
        this.beginMinutes = beginMinutes;
        this.endDay = endDay;
        this.endHour = endHour;
        this.endMinutes = endMinutes;
        
        this.numberOfDay = Math.abs(beginDay.ordinal() - endDay.ordinal())+ 1;
    }

    public TimeSlot() {
    }
    
    /**
     * extarct day, hour and minute from a Date object
     * @param date : the date to extract data
     * @param begin : true if the is date is the begin of the time slot, false if end
     */
    private void extract(Date date, boolean begin) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEE;HH;mm", Locale.UK);
        String str = sdf.format(date);
        String[] split = str.split(";");
        int hour = Integer.parseInt(split[1]);
        int minutes = Integer.parseInt(split[2]);
        Day day = Day.valueOf(split[0]);
        if (begin) {
            this.beginDay = day;
            this.beginHour = hour;
            this.beginMinutes = minutes;
        } else {
            this.endDay = day;
            this.endHour = hour;
            this.endMinutes = minutes;
        }
    }

    /**
     * check if a date is in the time slot
     *
     * @param date
     * @return boolean true if date is in the time slot
     */
    public boolean isIn(Date date) {
        boolean isIn = false;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEE;HH;mm", Locale.FRANCE);
        String str = sdf.format(date);

        // extract day, hour and minutes of date
        String[] split = str.split(";");
        int hour = Integer.parseInt(split[1]);
        int minutes = Integer.parseInt(split[2]);
        Day day = Day.valueOf(split[0]);

        // check if time slot is on one or more days
        if (this.numberOfDay == 1) {
            // good day ?
            if (day.compareTo(this.beginDay) == 0) {
                // on one day check if actual hour is beetween begin and end hours
                if (hour >= this.beginHour & hour <= this.endHour) {
                    // if time slot on more than one hour
                    if (this.beginHour != this.endHour) {
                        // if actual hour equal begin hour check if minutes greater or equals than begin minutes
                        if (hour == this.beginHour) {
                            isIn = (minutes >= this.beginMinutes);
                        } else {
                            // else check if minutes minus or equals than end minutes
                            if (hour == this.endHour) {
                                isIn = (minutes <= this.endMinutes);
                            } else {
                                // else is in !
                                isIn = true;
                            }
                        }
                    } else {
                        // time slot on one hour => check minutes is beetween minutes begin and end
                        isIn = (minutes >= this.beginMinutes & minutes <= this.endMinutes);
                    }
                }
            }
        } else {
            if (day.equals(this.beginDay)) {
                isIn = (hour >= this.beginHour & minutes >= this.beginMinutes);
            } else {
                if (day.equals(this.endDay)) {
                    isIn = (hour <= this.endHour & minutes <= this.endMinutes);
                } else {
                    if (this.beginDay.compareTo(this.endDay) > 0) {
                        isIn = !(day.compareTo(this.beginDay) < 0 & day.compareTo(this.endDay) > 0);
                    } else {
                        isIn = (day.compareTo(this.beginDay) > 0 & day.compareTo(this.endDay) < 0);
                    }
                }
            }
        }

        return isIn;
    }

    /**
     * @return the beginHour
     */
    public int getBeginHour() {
        return beginHour;
    }

    /**
     * @return the endHour
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * @return the beginMinutes
     */
    public int getBeginMinutes() {
        return beginMinutes;
    }

    /**
     * @return the endMinutes
     */
    public int getEndMinutes() {
        return endMinutes;
    }

    /**
     * @return the beginDay
     */
    public Day getBeginDay() {
        return beginDay;
    }

    /**
     * @return the endDay
     */
    public Day getEndDay() {
        return endDay;
    }

    /**
     * @return the numberOfDay
     */
    public long getNumberOfDay() {
        return numberOfDay;
    }

    @Override
    public String toString() {
        return "De " + this.beginDay + " " + this.beginHour + ":" + this.beginMinutes + " à " + this.endDay + " " + this.endHour + ":" + this.endMinutes;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
