/**
 * Copyright (c) 2011 MNCC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @author http://www.mncc.fr
 */
package fr.mncc.gwttoolbox.primitives.client;

import com.google.gwt.i18n.client.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Deprecated
public class DateUtils {

  public static final DateTimeFormat dtfDateIso = DateTimeFormat.getFormat("yyyy-MM-dd");
  public static final DateTimeFormat dtfTimeIso = DateTimeFormat.getFormat("HH:mm:ss");
  public static final DateTimeFormat dtfIso = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * Convert date into a string whose format is yyyy-MM-dd
   * 
   * @param date as Date object
   * @return date as String
   */
  public static String toDateIso(Date date) {
    String val = "";
    try {
      val = dtfDateIso.format(date);
    } catch (Exception e) {
      val = dtfDateIso.format(new Date(0));
    }
    return val;
  }

  /**
   * Parse a string whose format is yyyy-MM-dd into a Date object
   * 
   * @param date as String
   * @return date as Date object
   */
  public static Date fromDateIso(String date) {
    Date val = new Date(0);
    try {
      val = dtfDateIso.parse(date);
    } catch (Exception e) {
    }
    return val;
  }

  /**
   * Convert date into a string whose format is HH:mm:ss
   * 
   * @param date as Date object
   * @return date as String
   */
  public static String toTimeIso(Time date) {
    String val = "";
    try {
      val = dtfTimeIso.format(date);
    } catch (Exception e) {
      val = dtfTimeIso.format(new Time(0));
    }
    return val;
  }

  /**
   * Parse a string whose format is HH:mm:ss into a Date object
   * 
   * @param date as String
   * @return date as Date object
   */
  public static Time fromTimeIso(String date) {
    Time val = new Time(0);
    try {
      val = new Time(dtfTimeIso.parse(date).getTime());
    } catch (Exception e) {
    }
    return val;
  }

  /**
   * Convert date into a string whose format is yyyy-MM-dd HH:mm:ss
   * 
   * @param date as Date object
   * @return date as String
   */
  public static String toTimestampIso(Date date) {
    String val = "";
    try {
      val = dtfIso.format(date);
    } catch (Exception e) {
      val = dtfIso.format(new Date(0));
    }
    return val;
  }

  /**
   * Convert a string whose format is yyyy-MM-dd HH:mm:ss into a Date object
   * 
   * @param date as String
   * @return date as Date object
   */
  public static Date fromTimestampIso(String date) {
    Date val = new Date(0);
    try {
      val = dtfIso.parse(date);
    } catch (Exception e) {
    }
    return val;
  }

  /**
   * Get the week number for a given date
   * 
   * @param date
   * @return the week number for this date
   */
  @SuppressWarnings("deprecation")
  public static int getWeekOfYear(Date date) {
    return getWeekOfYear(date.getYear() + 1900, date.getMonth(), date.getDate());
  }

  /**
   * Get UNIX timestamp
   * 
   * @return UNIX timestamp
   */
  public static int getUnixTimeStamp() {
    return (int) ((new Date()).getTime() * .001);
  }

  /**
   * Check if two dates belong to the same week of year
   * 
   * @param date1
   * @param date2
   * @return true if both date belong to the same week of year
   */
  public static boolean isSameWeekOfYear(Date date1, Date date2) {
    return getWeekOfYear(date1) == getWeekOfYear(date2);
  }

  /**
   * Get the week number for a given date
   * 
   * @param year
   * @param month
   * @param day
   * @return the week number for this date
   */
  private static native int getWeekOfYear(int year, int month, int day) /*-{

    // getWeek() was developed by Nick Baicoianu at MeanFreePath: http://www.meanfreepath.com
    // Returns the week number for this date.  dowOffset is the day of week the week
    // "starts" on for your locale - it can be from 0 to 6. If dowOffset is 1 (Monday),
    // the week returned is the ISO 8601 week number.
    // @param int dowOffset
    // @return int
    Date.prototype.getWeek = function(dowOffset) {
      dowOffset = typeof (dowOffset) == 'int' ? dowOffset : 1; // default dowOffset to 1
      var newYear = new Date(this.getFullYear(), 0, 1);
      var day = newYear.getDay() - dowOffset; // the day of week the year begins on
      day = (day >= 0 ? day : day + 7);
      var daynum = Math.floor((this.getTime() - newYear.getTime() - (this.getTimezoneOffset() - newYear.getTimezoneOffset()) * 60000) / 86400000) + 1;
      var weeknum;

      // if the year starts before the middle of a week
      if (day < 4) {
        weeknum = Math.floor((daynum + day - 1) / 7) + 1;
        if (weeknum > 52) {
          nYear = new Date(this.getFullYear() + 1, 0, 1);
          nday = nYear.getDay() - dowOffset;
          nday = nday >= 0 ? nday : nday + 7;

          // if the next year starts before the middle of
          // the week, it is week #1 of that year
          weeknum = nday < 4 ? 1 : 53;
        }
      } else
        weeknum = Math.floor((daynum + day - 1) / 7);
      return weeknum;
    };

    var myDate = new Date();
    myDate.setFullYear(year, month, day);
    return myDate.getWeek();
  }-*/;
}
