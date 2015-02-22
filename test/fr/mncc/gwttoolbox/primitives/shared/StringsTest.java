/**
 * Copyright (c) 2012 MNCC
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

import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public final class StringsTest {

  @Test
  public void testBase64EncodeNull() {
    try {
      Strings.base64Encode(null);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testBase64DecodeNull() {
    try {
      Strings.base64Decode(null);
      assertEquals(false, true);
    } catch (NullPointerException e) {
      assertEquals(true, true);
    }
  }

  @Test
  public void testBase64Encode() {
    byte[] bytes =
        Strings
            .base64Decode("iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAP0lEQVQYV2NkIBIwQtX9R1KPVYyQQpA8yBBGQgphFsEVEnQpzGiiFcKcANIA8xiKGNyxMEcToxBmPV4TCboRAGDZEwb4RJqnAAAAAElFTkSuQmCC");
    assertEquals(
        "iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAP0lEQVQYV2NkIBIwQtX9R1KPVYyQQpA8yBBGQgphFsEVEnQpzGiiFcKcANIA8xiKGNyxMEcToxBmPV4TCboRAGDZEwb4RJqnAAAAAElFTkSuQmCC",
        Strings.base64Encode(bytes));
  }

  @Test
  public void testBase64Decode() {
    byte[] bytes =
        Strings
            .base64Decode("TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlzIHNpbmd1bGFyIHBhc3Npb24gZnJvbSBvdGhlciBhbmltYWxzLCB3aGljaCBpcyBhIGx1c3Qgb2YgdGhlIG1pbmQsIHRoYXQgYnkgYSBwZXJzZXZlcmFuY2Ugb2YgZGVsaWdodCBpbiB0aGUgY29udGludWVkIGFuZCBpbmRlZmF0aWdhYmxlIGdlbmVyYXRpb24gb2Yga25vd2xlZGdlLCBleGNlZWRzIHRoZSBzaG9ydCB2ZWhlbWVuY2Ugb2YgYW55IGNhcm5hbCBwbGVhc3VyZS4=");
    assertEquals(
        "Man is distinguished, not only by his reason, but by this singular passion from other animals, which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation of knowledge, exceeds the short vehemence of any carnal pleasure.",
        new String(bytes));
  }

  @Test
  public void testParseInt() {
    assertEquals(0, Strings.parseInt(null));
    assertEquals(0, Strings.parseInt(""));
    assertEquals(0, Strings.parseInt("test"));
    assertEquals(0, Strings.parseInt("8.8"));
    assertEquals(8, Strings.parseInt("8"));
    assertEquals(8, Strings.parseInt("08"));
  }

  @Test
  public void testParseLong() {
    assertEquals(0, Strings.parseLong(null));
    assertEquals(0, Strings.parseLong(""));
    assertEquals(0, Strings.parseLong("test"));
    assertEquals(0, Strings.parseLong("8.8"));
    assertEquals(8, Strings.parseLong("8"));
    assertEquals(8, Strings.parseLong("08"));
  }

  @Test
  public void testParseFloat() {
    assertEquals(0.0f, Strings.parseFloat(null), 1e-8);
    assertEquals(0.0f, Strings.parseFloat(""), 1e-8);
    assertEquals(0.0f, Strings.parseFloat("test"), 1e-8);
    assertEquals(8.0f, Strings.parseFloat("8"), 1e-8);
    assertEquals(8.8f, Strings.parseFloat("8.8"), 1e-8);
    assertEquals(8.8f, Strings.parseFloat("08.8"), 1e-8);
  }

  @Test
  public void testParseDouble() {
    assertEquals(0.0d, Strings.parseDouble(null), 1e-8);
    assertEquals(0.0d, Strings.parseDouble(""), 1e-8);
    assertEquals(0.0d, Strings.parseDouble("test"), 1e-8);
    assertEquals(8.0d, Strings.parseDouble("8"), 1e-8);
    assertEquals(8.8d, Strings.parseDouble("8.8"), 1e-8);
    assertEquals(8.8d, Strings.parseDouble("08.8"), 1e-8);
  }

  @Test
  public void testParseBoolean() {
    assertFalse(Strings.parseBoolean(null));
    assertFalse(Strings.parseBoolean(""));
    assertFalse(Strings.parseBoolean("t"));
    assertFalse(Strings.parseBoolean("f"));
    assertTrue(Strings.parseBoolean("true"));
    assertTrue(Strings.parseBoolean("True"));
    assertTrue(Strings.parseBoolean("TRUE"));
    assertFalse(Strings.parseBoolean("false"));
    assertFalse(Strings.parseBoolean("False"));
    assertFalse(Strings.parseBoolean("FALSE"));
  }

  @Test
  public void testParseDate() {
    Date date = new Date(0);
    String string = Strings.serializeDate(date);
    assertEquals(date.getTime(), Strings.parseDate(string).getTime());
    assertEquals(date.getTime(), Strings.parseDate(null).getTime());
    assertEquals(date.getTime(), Strings.parseDate("").getTime());
    assertEquals(date.getTime(), Strings.parseDate("123").getTime());
  }

  @Test
  public void testSerializeDate() {
    assertEquals("1 Jan 1970 00:00:00 GMT", Strings.serializeDate(null));
    assertEquals("1 Jan 1970 00:00:00 GMT", Strings.serializeDate(new Date(0)));
    assertEquals("1 Jan 1970 00:00:00 GMT", Strings.serializeDate(new Time(0)));
    assertEquals("1 Jan 1970 00:00:00 GMT", Strings.serializeDate(new Timestamp(0)));
  }

  @Test
  public void testEscapeToLaTex() {
    assertEquals(Strings.escapeToLaTex(null), "");
    assertEquals(Strings.escapeToLaTex(""), "");
    assertEquals("\\textbackslash'\\'el\\'ephant'", Strings.escapeToLaTex("\\'éléphant'"));
  }

  @Test
  public void testCapitalizeWords() {
    assertEquals("", Strings.capitalizeWords(null));
    assertEquals("", Strings.capitalizeWords(""));
    assertEquals("Hey There", Strings.capitalizeWords("hey there"));
    assertEquals("HeY TherE", Strings.capitalizeWords("heY therE"));
  }

  @Test
  public void testUncapitalizeWords() {
    assertEquals("", Strings.uncapitalizeWords(null));
    assertEquals("", Strings.uncapitalizeWords(""));
    assertEquals("hey there", Strings.uncapitalizeWords("Hey There"));
    assertEquals("heY therE", Strings.uncapitalizeWords("HeY TherE"));
  }

  @Test
  public void testEllipsize() {
    assertEquals("", Strings.ellipsize(null, 5));
    assertEquals("", Strings.ellipsize("Le beau renard blanc.", -1));
    assertEquals("", Strings.ellipsize("Le beau renard blanc.", 0));
    assertEquals("Le...", Strings.ellipsize("Le beau renard blanc.", 5));
  }

  @Test
  public void testObjectFromString() {
    try {
      Strings.objectFromString(null);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
    assertEquals("Test", Strings.objectFromString("1:Test"));
    assertEquals(0, Strings.objectFromString("2:0"));
    assertEquals(0l, Strings.objectFromString("5:0"));
    assertEquals(0.0d, Strings.objectFromString("3:0.0"));
    assertEquals(0.0f, Strings.objectFromString("4:0.0"));
    assertEquals(true, Strings.objectFromString("6:true"));
    assertEquals(false, Strings.objectFromString("6:false"));
    assertEquals(new Date(0), Strings.objectFromString("7:1 Jan 1970 00:00:00 GMT"));
    assertEquals(new Time(0), Strings.objectFromString("7:1 Jan 1970 00:00:00 GMT"));
    assertEquals(new Timestamp(0), Strings.objectFromString("7:1 Jan 1970 00:00:00 GMT"));
  }

  @Test
  public void testObjectToString() {
    try {
      Strings.objectToString(null);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
    try {
      Strings.objectToString(this);
      assertTrue(false);
    } catch (Error e) {
      assertTrue(true);
    }
    assertEquals("1:Test", Strings.objectToString("Test"));
    assertEquals("2:0", Strings.objectToString(0));
    assertEquals("5:0", Strings.objectToString(0l));
    assertEquals("3:0.0", Strings.objectToString(0.0d));
    assertEquals("4:0.0", Strings.objectToString(0.0f));
    assertEquals("6:true", Strings.objectToString(true));
    assertEquals("6:false", Strings.objectToString(false));
    assertEquals("7:1 Jan 1970 00:00:00 GMT", Strings.objectToString(new Date(0)));
    assertEquals("7:1 Jan 1970 00:00:00 GMT", Strings.objectToString(new Time(0)));
    assertEquals("7:1 Jan 1970 00:00:00 GMT", Strings.objectToString(new Timestamp(0)));
  }

  @Test
  public void testDateFromString() {
    assertEquals(new Date(0), Strings.dateFromString(null));
    assertEquals(new Time(0), Strings.dateFromString(null));
    assertEquals(new Timestamp(0), Strings.dateFromString(null));
    assertEquals(new Date(0), Strings.dateFromString("1 Jan 1970 00:00:00 GMT"));
    assertEquals(new Time(0), Strings.dateFromString("1 Jan 1970 00:00:00 GMT"));
    assertEquals(new Timestamp(0), Strings.dateFromString("1 Jan 1970 00:00:00 GMT"));
  }

  @Test
  public void testDateToString() {
    assertEquals("1 Jan 1970 00:00:00 GMT", Strings.dateToString(null));
    assertEquals("1 Jan 1970 00:00:00 GMT", Strings.dateToString(new Date(0)));
    assertEquals("1 Jan 1970 00:00:00 GMT", Strings.dateToString(new Time(0)));
    assertEquals("1 Jan 1970 00:00:00 GMT", Strings.dateToString(new Timestamp(0)));
  }
}
