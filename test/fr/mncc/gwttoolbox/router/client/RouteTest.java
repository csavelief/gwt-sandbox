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
package fr.mncc.gwttoolbox.router.client;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RouteTest {

  @Test
  public void testNullName() {

    Route route = new Route(null);
    assertEquals(route.getName(), "");
  }

  @Test
  public void testEmptyName() {

    Route route = new Route("");
    assertEquals(route.getName(), "");
  }

  @Test
  public void testWrongName() {

    Route route = new Route("42");
    assertEquals(route.getName(), "");
  }

  @Test
  public void testNoParameters() {

    Route route = new Route("test");
    assertEquals(route.parseArguments(null, "?").isEmpty(), true);
    assertEquals(route.parseArguments("?", null).isEmpty(), true);
    assertEquals(route.parseArguments(null, "/").isEmpty(), true);
    assertEquals(route.parseArguments("/", null).isEmpty(), true);
    assertEquals(route.parseArguments(null, null).isEmpty(), true);
    assertEquals(route.parseArguments("", "").isEmpty(), true);
  }

  @Test
  public void testTemplateMismatch() {

    Route route = new Route("test");
    assertEquals(route.parseArguments("/", "?").isEmpty(), true);
    assertEquals(route.parseArguments("?", "/").isEmpty(), true);
  }

  @Test
  public void testQuestionMarkParser() {

    Route route = new Route("test");
    assertEquals(route.parseArguments("?key1=val1&key2=val2&key3=val3", "?key1=&key2=&key3="),
        createMapResult1());
    assertEquals(route.parseArguments("?key1=val1&key2=val2&key3=val3&", "?key1=&key2=&key3="),
        createMapResult1());
    assertEquals(route.parseArguments("?key1=val1&key2=val2&key3=val3", "?key1=&key2=&key3=&"),
        createMapResult1());
    assertEquals(route.parseArguments("?key1=val1&key2=val2&&key3=val3", "?key1=&key2=&key3="),
        createMapResult1());
    assertEquals(route.parseArguments("?key1=val1&key2=val2&key3=val3", "?key1=&&key2=&key3="),
        createMapResult1());
    assertEquals(
        route.parseArguments("?key1=  val1&  key2=val2&key3=val3  ", "?key1=&key2=&key3="),
        createMapResult1());
    assertEquals(
        route.parseArguments("?key1=val1&key2=val2&=val3", "?key1=&key2=&key3=").isEmpty(), true);
    assertEquals(route.parseArguments("?key1=val1&key2=val2&key3=val3", "?key1=&key2=").isEmpty(),
        true);
    assertEquals(route.parseArguments("?key1=val1&key2=val2", "?key1=&key2=&key3=").isEmpty(), true);
  }

  @Test
  public void testSlashParser() {

    Route route = new Route("test");
    assertEquals(route.parseArguments("/val1/val2/val3", "/key1/key2/key3"), createMapResult1());
    assertEquals(route.parseArguments("/val1/val2/val3/", "/key1/key2/key3"), createMapResult1());
    assertEquals(route.parseArguments("/val1/val2/val3", "/key1/key2/key3/"), createMapResult1());
    assertEquals(route.parseArguments("/val1/val2/val3/", "/key1/key2/key3/"), createMapResult1());
    assertEquals(route.parseArguments("/val1/  val2/val3  ", "/key1/key2/key3"), createMapResult1());
    assertEquals(route.parseArguments("/val1//val3", "/key1/key2/key3").isEmpty(), true);
    assertEquals(route.parseArguments("/val1/val2/val3", "/key1//key3").isEmpty(), true);
    assertEquals(route.parseArguments("/val1//val3", "/key1//key3"), createMapResult2());
    assertEquals(route.parseArguments("/val1/val2/val3", "/key1/key2").isEmpty(), true);
  }

  private Map<String, String> createMapResult1() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("key1", "val1");
    map.put("key2", "val2");
    map.put("key3", "val3");
    return map;
  }

  private Map<String, String> createMapResult2() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("key1", "val1");
    map.put("key3", "val3");
    return map;
  }
}
