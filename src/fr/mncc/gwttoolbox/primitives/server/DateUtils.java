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
package fr.mncc.gwttoolbox.primitives.server;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@Deprecated
public class DateUtils {

  private static ThreadLocal<SimpleDateFormat> sdfDateIso = new ThreadLocal<SimpleDateFormat>() {
    @Override
    protected synchronized SimpleDateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd");
    }
  };
  private static ThreadLocal<SimpleDateFormat> sdfTimeIso = new ThreadLocal<SimpleDateFormat>() {
    @Override
    protected synchronized SimpleDateFormat initialValue() {
      return new SimpleDateFormat("HH:mm:ss");
    }
  };
  private static ThreadLocal<SimpleDateFormat> sdfIso = new ThreadLocal<SimpleDateFormat>() {
    @Override
    protected synchronized SimpleDateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
  };

  /**
   * Convert date into a string whose format is yyyy-MM-dd
   * 
   * @param date as Date object
   * @return date as String
   */
  public static String toDateIso(Date date) {
    String val = "";
    try {
      val = sdfDateIso.get().format(date);
    } catch (Exception e) {
      val = sdfDateIso.get().format(new Date(0));
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
      val = sdfDateIso.get().parse(date);
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
  public static String toTimeIso(Date date) {
    String val = "";
    try {
      val = sdfTimeIso.get().format(date);
    } catch (Exception e) {
      val = sdfTimeIso.get().format(new Time(0));
    }
    return val;
  }

  /**
   * Parse a string whose format is HH:mm:ss into a Date object
   * 
   * @param date as String
   * @return date as Date object
   */
  public static Date fromTimeIso(String date) {
    Date val = new Time(0);
    try {
      val = sdfTimeIso.get().parse(date);
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
      val = sdfIso.get().format(date);
    } catch (Exception e) {
      val = sdfIso.get().format(new Date(0));
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
      val = sdfIso.get().parse(date);
    } catch (Exception e) {
    }
    return val;
  }
}
