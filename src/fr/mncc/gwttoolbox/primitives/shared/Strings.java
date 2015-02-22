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
package fr.mncc.gwttoolbox.primitives.shared;

import java.sql.Timestamp;
import java.util.Date;

import static fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull;

import com.google.gwt.core.shared.GWT;

/**
 * String parsing with default value on error
 */
public final class Strings {

  private static final int TYPE_STRING = 1;
  private static final int TYPE_INTEGER = 2;
  private static final int TYPE_DOUBLE = 3;
  private static final int TYPE_FLOAT = 4;
  private static final int TYPE_LONG = 5;
  private static final int TYPE_BOOLEAN = 6;
  private static final int TYPE_DATE = 7;
  private final static String NON_THIN = "[^iIl1\\.,']";
  private final static char[] ALPHABET =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
  private final static int[] toInt = new int[128];
  static {
    for (int i = 0, len = ALPHABET.length; i < len; i++) {
      toInt[ALPHABET[i]] = i;
    }
  }

  private Strings() {

  }

  /**
   * Translates the specified byte array into a Base64 string.
   * 
   * @param buffer the byte array (not null)
   * @return the translated Base64 string (not null)
   */
  public static String base64Encode(byte[] buffer) {
    int size = checkNotNull(buffer).length;
    char[] ar = new char[((size + 2) / 3) * 4];
    int a = 0;
    int i = 0;
    while (i < size) {
      byte b0 = buffer[i++];
      byte b1 = (i < size) ? buffer[i++] : 0;
      byte b2 = (i < size) ? buffer[i++] : 0;
      int mask = 0x3F;
      ar[a++] = ALPHABET[(b0 >> 2) & mask];
      ar[a++] = ALPHABET[((b0 << 4) | ((b1 & 0xFF) >> 4)) & mask];
      ar[a++] = ALPHABET[((b1 << 2) | ((b2 & 0xFF) >> 6)) & mask];
      ar[a++] = ALPHABET[b2 & mask];
    }
    switch (size % 3) {
      case 1:
        ar[--a] = '=';
      case 2:
        ar[--a] = '=';
    }
    return new String(ar);
  }

  /**
   * Translates the specified Base64 string into a byte array.
   * 
   * @param base64String the Base64 string (not null)
   * @return the byte array (not null)
   */
  public static byte[] base64Decode(String base64String) throws NullPointerException {
    int delta = checkNotNull(base64String).endsWith("==") ? 2 : base64String.endsWith("=") ? 1 : 0;
    byte[] buffer = new byte[base64String.length() * 3 / 4 - delta];
    int mask = 0xFF;
    int index = 0;
    for (int i = 0; i < base64String.length(); i += 4) {
      int c0 = toInt[base64String.charAt(i)];
      int c1 = toInt[base64String.charAt(i + 1)];
      buffer[index++] = (byte) (((c0 << 2) | (c1 >> 4)) & mask);
      if (index >= buffer.length)
        return buffer;
      int c2 = toInt[base64String.charAt(i + 2)];
      buffer[index++] = (byte) (((c1 << 4) | (c2 >> 2)) & mask);
      if (index >= buffer.length)
        return buffer;
      int c3 = toInt[base64String.charAt(i + 3)];
      buffer[index++] = (byte) (((c2 << 6) | c3) & mask);
    }
    return buffer;
  }

  /**
   * Convert a string to an integer
   * 
   * @param str string to convert
   * @return an integer
   */
  public static int parseInt(String str) {
    if (str == null)
      return 0;

    str = str.trim();
    int val = 0;
    try {
      val = Integer.parseInt(str, 10);
    } catch (Exception e) {
      GWT.log(e.toString());
    }
    return val;
  }

  /**
   * Convert a string to a long
   * 
   * @param str string to convert
   * @return a long
   */
  public static long parseLong(String str) {
    if (str == null)
      return 0L;

    str = str.trim();
    long val = 0L;
    try {
      val = Long.parseLong(str, 10);
    } catch (Exception e) {
      GWT.log(e.toString());
    }
    return val;
  }

  /**
   * Convert a string to a float
   * 
   * @param str string to convert
   * @return a float
   */
  public static float parseFloat(String str) {
    if (str == null)
      return 0.0f;

    str = str.trim().replace(',', '.');
    float val = 0.0f;
    try {
      val = Float.parseFloat(str);
    } catch (Exception e) {
      GWT.log(e.toString());
    }
    return val;
  }

  /**
   * Convert a string to a double
   * 
   * @param str string to convert
   * @return a double
   */
  public static double parseDouble(String str) {
    if (str == null)
      return 0.0d;

    str = str.trim().replace(',', '.');
    double val = 0.0d;
    try {
      val = Double.parseDouble(str);
    } catch (Exception e) {
      GWT.log(e.toString());
    }
    return val;
  }

  /**
   * Convert a string to a boolean
   * 
   * @param str string to convert
   * @return a boolean
   */
  public static boolean parseBoolean(String str) {
    if (str == null)
      return false;

    str = str.trim();
    boolean val = false;
    try {
      val = Boolean.parseBoolean(str);
    } catch (Exception e) {
      GWT.log(e.toString());
    }
    return val;
  }

  /**
   * Convert String to a Date object.
   * 
   * @param string
   * @return date
   */
  @SuppressWarnings("deprecation")
  public static Date parseDate(String string) {
    if (string == null || string.isEmpty())
      return new Timestamp(0);
    long time;
    try {
      time = Timestamp.parse(string);
    } catch (IllegalArgumentException e) {
      time = 0;
    }
    return new Timestamp(time);
  }

  /**
   * Serialize Date object as a String.
   * 
   * @param date
   * @return string
   */
  @SuppressWarnings("deprecation")
  public static String serializeDate(Date date) {
    if (date == null)
      return new Timestamp(0).toGMTString();
    return new Timestamp(date.getTime()).toGMTString();
  }

  /**
   * Escape a string for LaTex.
   * 
   * @param str string to escape
   * @return escaped string
   */
  public static String escapeToLaTex(String str) {
    return str == null ? "" : str.replaceAll("\\Q\\\\E", "\\\\textbackslash").replaceAll("\\Q{\\E",
        "\\\\{").replaceAll("\\Q_\\E", "\\\\_").replaceAll("\\Q^\\E", "\\\\textasciicircum")
        .replaceAll("\\Q}\\E", "\\\\}").replaceAll("\\Q#\\E", "\\\\#").replaceAll("\\Q&\\E",
            "\\\\&").replaceAll("\\Q$\\E", "\\\\textdollar").replaceAll("\\Q%\\E", "\\\\%")
        .replaceAll("\\Q~\\E", "\\\\textasciitilde").replaceAll("\\Qà\\E", "\\\\`a").replaceAll(
            "\\Qé\\E", "\\\\'e").replaceAll("\\Qè\\E", "\\\\`e");
  }

  /**
   * Replace the first letter of a word with an upper-case letter.
   * 
   * @param str
   * @return
   */
  public static String capitalize(String str) {
    return str == null || str.isEmpty() ? "" : str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  /**
   * Replace the first letter of a word with a lower-case letter.
   * 
   * @param str
   * @return
   */
  public static String uncapitalize(String str) {
    return str == null || str.isEmpty() ? "" : str.substring(0, 1).toLowerCase() + str.substring(1);
  }

  /**
   * Replace the first letter of each word with an upper-case letter.
   * 
   * @param str
   * @return
   */
  public static String capitalizeWords(String str) {
    if (str == null || str.isEmpty())
      return "";

    StringBuilder stringBuilder = new StringBuilder();
    String[] words = str.trim().split(" ");
    for (int i = 0; i < words.length; i++)
      stringBuilder.append((i > 0 ? " " : "") + capitalize(words[i]));
    return stringBuilder.toString();
  }

  /**
   * Replace the first letter of each word with a lower-case letter.
   * 
   * @param str
   * @return
   */
  public static String uncapitalizeWords(String str) {
    if (str == null || str.isEmpty())
      return "";

    StringBuilder stringBuilder = new StringBuilder();
    String[] words = str.trim().split(" ");
    for (int i = 0; i < words.length; i++)
      stringBuilder.append((i > 0 ? " " : "") + uncapitalize(words[i]));
    return stringBuilder.toString();
  }

  private static int textWidth(String str) {
    return (int) (str.length() - str.replaceAll(NON_THIN, "").length() / 2);
  }

  /**
   * Truncate string and add ellipsis. cf. http://stackoverflow.com/a/3657496
   * 
   * @param text text to truncate
   * @param max maximum number of characters allowed
   * @return truncated text
   */
  public static String ellipsize(String text, int max) {

    if (text == null || max <= 0) {
      return "";
    }

    if (textWidth(text) <= max) {
      return text;
    }

    // Start by chopping off at the word before max
    // This is an over-approximation due to thin-characters...
    int end = text.lastIndexOf(' ', max - 3);

    // Just one long word. Chop it off.
    if (end == -1) {
      return text.substring(0, max - 3) + "...";
    }

    // Step forward as long as textWidth allows.
    int newEnd = end;
    do {
      end = newEnd;
      newEnd = text.indexOf(' ', end + 1);

      // No more spaces.
      if (newEnd == -1) {
        newEnd = text.length();
      }

    } while (textWidth(text.substring(0, newEnd) + "...") < max);
    return text.substring(0, end) + "...";
  }

  /**
   * Serialize a few basic types.
   * 
   * @param value object
   * @return value as string
   */
  public static String objectToString(Object value) {

    checkNotNull(value);

    if (value instanceof String) {
      return TYPE_STRING + ":" + ((String) value);
    }
    if (value instanceof Integer) {
      return TYPE_INTEGER + ":" + Integer.toString((Integer) value, 10);
    }
    if (value instanceof Double) {
      return TYPE_DOUBLE + ":" + Double.toString((Double) value);
    }
    if (value instanceof Float) {
      return TYPE_FLOAT + ":" + Float.toString((Float) value);
    }
    if (value instanceof Long) {
      return TYPE_LONG + ":" + Long.toString((Long) value);
    }
    if (value instanceof Boolean) {
      return TYPE_BOOLEAN + ":" + Boolean.toString((Boolean) value);
    }
    if (value instanceof Date) {
      return TYPE_DATE + ":" + dateToString((Date) value);
    }
    throw new Error("Value is not of a valid type.");
  }

  /**
   * De-serialize strings serialized by Strings.objectToString().
   * 
   * @param value string
   * @return value as object
   */
  public static Object objectFromString(String value) {

    checkNotNull(value);

    int type = parseInt(value.substring(0, 1));
    value = value.substring(2);
    switch (type) {
      case TYPE_STRING: {
        return value;
      }
      case TYPE_INTEGER: {
        return parseInt(value);
      }
      case TYPE_DOUBLE: {
        return parseDouble(value);
      }
      case TYPE_FLOAT: {
        return parseFloat(value);
      }
      case TYPE_LONG: {
        return parseLong(value);
      }
      case TYPE_BOOLEAN: {
        return parseBoolean(value);
      }
      case TYPE_DATE: {
        return dateFromString(value);
      }
      default: {
        throw new Error("Unknown type for value " + value + ".");
      }
    }
  }

  /**
   * Serialize date.
   * 
   * @param date
   * @return date as GMT string
   */
  @SuppressWarnings("deprecation")
  public static String dateToString(Date date) {
    if (date == null) {
      return new Timestamp(0).toGMTString();
    }
    return new Timestamp(date.getTime()).toGMTString();
  }

  /**
   * De-serialize date serialized by Strings.dateToString().
   * 
   * @param string
   * @return date as Date object
   */
  @SuppressWarnings("deprecation")
  public static Date dateFromString(String string) {
    if (string == null || string.isEmpty()) {
      return new Timestamp(0);
    }
    return new Timestamp(Timestamp.parse(string));
  }
}
