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

import org.junit.Test;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public final class KeyValueMapTest {

  @Test
  public void testOf() {

    KeyValueMap<String, String> keyValueMap = KeyValueMap.of("key1=value1&key2=value2");
    assertEquals(2, keyValueMap.size());
    assertEquals("value1", keyValueMap.get("key1"));
    assertEquals("value2", keyValueMap.get("key2"));

    keyValueMap = KeyValueMap.of("&key1=value1&key2=value2");
    assertEquals(2, keyValueMap.size());
    assertEquals("value1", keyValueMap.get("key1"));
    assertEquals("value2", keyValueMap.get("key2"));

    keyValueMap = KeyValueMap.of("key1=value1&key2=value2&");
    assertEquals(2, keyValueMap.size());
    assertEquals("value1", keyValueMap.get("key1"));
    assertEquals("value2", keyValueMap.get("key2"));
  }

  @Test
  public void testKeyValueMapFromNullKeyValueMap() {
    try {
      new KeyValueMap<String, String>((KeyValueMap<String, String>) null);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testKeyValueMapFromKeyValueMap() {
    assertEquals(createKeyValueMap1(), new KeyValueMap<String, String>(createKeyValueMap1()));
    assertEquals(createKeyValueMap2(), new KeyValueMap<String, String>(createKeyValueMap2()));
    assertEquals(createKeyValueMap3(), new KeyValueMap<String, String>(createKeyValueMap3()));
  }

  @Test
  public void testKeyValueMapFromNullIterable() {
    try {
      new KeyValueMap<String, String>((Iterable<KeyValuePair<String, String>>) null);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testKeyValueMapFromIterable() {
    assertEquals(createKeyValueMap1(), new KeyValueMap<String, String>(createKeyValueMap1()
        .asKeyValuePairs()));
    assertEquals(createKeyValueMap2(), new KeyValueMap<String, String>(createKeyValueMap2()
        .asKeyValuePairs()));
    assertEquals(createKeyValueMap3(), new KeyValueMap<String, String>(createKeyValueMap3()
        .asKeyValuePairs()));
  }

  @Test
  public void testKeyValueMapFromNullIterator() {
    try {
      new KeyValueMap<String, String>((Iterator<KeyValuePair<String, String>>) null);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testKeyValueMapFromIterator() {
    assertEquals(createKeyValueMap1(), new KeyValueMap<String, String>(createKeyValueMap1()
        .asKeyValuePairs().iterator()));
    assertEquals(createKeyValueMap2(), new KeyValueMap<String, String>(createKeyValueMap2()
        .asKeyValuePairs().iterator()));
    assertEquals(createKeyValueMap3(), new KeyValueMap<String, String>(createKeyValueMap3()
        .asKeyValuePairs().iterator()));
  }

  @Test
  public void testKeyValueMapFromVararg() {
    assertEquals(createKeyValueMap1(), new KeyValueMap<String, String>(
        new KeyValuePair<String, String>("key1", "value1"), new KeyValuePair<String, String>(
            "key2", "value2")));
    assertEquals(createKeyValueMap2(), new KeyValueMap<String, String>(
        new KeyValuePair<String, String>("value1", "key1"), new KeyValuePair<String, String>(
            "value2", "key2")));
    assertEquals(createKeyValueMap3(), new KeyValueMap<String, String>(
        new KeyValuePair<String, String>("key1", "value1"), new KeyValuePair<String, String>(
            "key2", null)));
  }

  @Test
  public void testGet() {

    KeyValueMap<String, String> keyValueMap = createKeyValueMap1();
    assertEquals("value1", keyValueMap.get("key1"));
    assertEquals("value2", keyValueMap.get("key2"));

    keyValueMap = createKeyValueMap2();
    assertEquals("key1", keyValueMap.get("value1"));
    assertEquals("key1", keyValueMap.get("value1"));

    keyValueMap = createKeyValueMap3();
    assertEquals("value1", keyValueMap.get("key1"));
    assertEquals(null, keyValueMap.get("key2"));
  }

  @Test
  public void testPut() {

    KeyValueMap<String, String> keyValueMap = new KeyValueMap<String, String>();
    keyValueMap.put("key2", null);
    keyValueMap.put("key1", "value1");

    assertEquals(createKeyValueMap3(), keyValueMap);

    try {
      keyValueMap.put(null, "value2");
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testSize() {

    KeyValueMap<String, String> keyValueMap = createKeyValueMap1();
    assertEquals(2, keyValueMap.size());

    keyValueMap.remove("key1");
    assertEquals(1, keyValueMap.size());
  }

  @Test
  public void testRemove() {

    KeyValueMap<String, String> keyValueMap = createKeyValueMap1();
    assertEquals(2, keyValueMap.size());
    assertEquals("value1", keyValueMap.get("key1"));
    assertEquals("value2", keyValueMap.get("key2"));

    keyValueMap.remove("key1");
    assertEquals(1, keyValueMap.size());
    assertEquals("value2", keyValueMap.get("key2"));
  }

  @Test
  public void testClear() {

    KeyValueMap<String, String> keyValueMap = createKeyValueMap1();
    assertEquals(2, keyValueMap.size());
    assertEquals("value1", keyValueMap.get("key1"));
    assertEquals("value2", keyValueMap.get("key2"));

    keyValueMap.clear();
    assertEquals(0, keyValueMap.size());
  }

  @Test
  public void testKeys() {
    KeyValueMap<String, String> keyValueMap = createKeyValueMap1();
    assertEquals(2, keyValueMap.size());

    for (String key : keyValueMap.keys()) {
      assertTrue(key.equals("key1") || key.equals("key2"));
    }
  }

  @Test
  public void testValues() {
    KeyValueMap<String, String> keyValueMap = createKeyValueMap1();
    assertEquals(2, keyValueMap.size());

    for (String value : keyValueMap.values()) {
      assertTrue(value.equals("value1") || value.equals("value2"));
    }
  }

  @Test
  public void testContains() {

    KeyValueMap<String, String> keyValueMap = createKeyValueMap1();
    assertTrue(keyValueMap.contains(keyValueMap));
    assertTrue(keyValueMap.contains(createKeyValueMap1()));
    assertTrue(createKeyValueMap1().contains(keyValueMap));
    assertFalse(keyValueMap.contains(createKeyValueMap2()));
    assertFalse(createKeyValueMap2().contains(keyValueMap));
    assertFalse(keyValueMap.contains(createKeyValueMap3()));
    assertFalse(createKeyValueMap3().contains(keyValueMap));

    assertTrue(keyValueMap.contains(keyValueMap.asKeyValuePairs()));
    assertTrue(keyValueMap.contains(createKeyValueMap1().asKeyValuePairs()));
    assertTrue(createKeyValueMap1().contains(keyValueMap.asKeyValuePairs()));
    assertFalse(keyValueMap.contains(createKeyValueMap2().asKeyValuePairs()));
    assertFalse(createKeyValueMap2().contains(keyValueMap.asKeyValuePairs()));
    assertFalse(keyValueMap.contains(createKeyValueMap3().asKeyValuePairs()));
    assertFalse(createKeyValueMap3().contains(keyValueMap.asKeyValuePairs()));
  }

  @Test
  public void testAsKeyValuePairs() {

    KeyValueMap<String, String> keyValueMap = createKeyValueMap1();
    List<KeyValuePair<String, String>> keyValuePairs = keyValueMap.asKeyValuePairs();

    for (KeyValuePair<String, String> pair : keyValuePairs) {
      assertEquals(pair.getValue(), keyValueMap.get(pair.getKey()));
    }

    for (String key : keyValueMap.keys()) {
      boolean found = false;

      for (KeyValuePair<String, String> pair : keyValuePairs) {
        if (key.equals(pair.getKey())) {
          assertEquals(keyValueMap.get(key), pair.getValue());
          found = true;
          break;
        }
      }

      assertTrue(found);
    }
  }

  @Test
  public void testAsJson() {

    KeyValueMap<?, ?> keyValueMap = createKeyValueMap1();
    assertTrue(keyValueMap.asJson().equals("{\"key1\":\"value1\",\"key2\":\"value2\"}")
        || keyValueMap.asJson().equals("{\"key2\":\"value2\",\"key1\":\"value1\"}"));

    keyValueMap = createKeyValueMap3();
    assertTrue(keyValueMap.asJson().equals("{\"key1\":\"value1\",\"key2\":\"\"}")
        || keyValueMap.asJson().equals("{\"key2\":\"\",\"key1\":\"value1\"}"));
  }

  @Test
  public void testAsString() {

    KeyValueMap<?, ?> keyValueMap = createKeyValueMap1();
    assertTrue(keyValueMap.asString().equals("key1=value1&key2=value2")
        || keyValueMap.asString().equals("key2=value2&key1=value1"));

    keyValueMap = createKeyValueMap3();
    assertTrue(keyValueMap.asString().equals("key1=value1&key2=")
        || keyValueMap.asString().equals("key2=&key1=value1"));
  }

  @Test
  public void testEquals() {
    KeyValueMap<?, ?> keyValueMap = createKeyValueMap1();
    assertTrue(keyValueMap.equals(keyValueMap));
    assertTrue(keyValueMap.equals(createKeyValueMap1()));
    assertTrue(createKeyValueMap1().equals(keyValueMap));
    assertFalse(keyValueMap.equals(createKeyValueMap2()));
    assertFalse(createKeyValueMap2().equals(keyValueMap));
    assertFalse(keyValueMap.equals(createKeyValueMap3()));
    assertFalse(createKeyValueMap3().equals(keyValueMap));
    assertFalse(keyValueMap.equals(null));
  }

  @Test
  public void testHashCode() {
    KeyValueMap<?, ?> keyValueMap = createKeyValueMap1();
    assertTrue(keyValueMap.hashCode() == keyValueMap.hashCode());
    assertTrue(keyValueMap.hashCode() == createKeyValueMap1().hashCode());
    assertFalse(keyValueMap.hashCode() == createKeyValueMap2().hashCode());
    assertFalse(keyValueMap.hashCode() == createKeyValueMap3().hashCode());
  }

  private KeyValueMap<String, String> createKeyValueMap1() {
    return new KeyValueMap<String, String>(new KeyValuePair<String, String>("key1", "value1"),
        new KeyValuePair<String, String>("key2", "value2"));
  }

  private KeyValueMap<String, String> createKeyValueMap2() {
    return new KeyValueMap<String, String>(new KeyValuePair<String, String>("value1", "key1"),
        new KeyValuePair<String, String>("value2", "key2"));
  }

  private KeyValueMap<String, String> createKeyValueMap3() {
    return new KeyValueMap<String, String>(new KeyValuePair<String, String>("key1", "value1"),
        new KeyValuePair<String, String>("key2", (String) null));
  }

  @SuppressWarnings("deprecation")
  private KeyValueMap<String, Serializable> createKeyValueMap4() {
    return new KeyValueMap<String, Serializable>(new KeyValuePair<String, Serializable>("date",
        new Date(12, 0, 16, 22, 22, 38)), new KeyValuePair<String, Serializable>("time", new Time(
        22, 22, 38)), new KeyValuePair<String, Serializable>("timestamp", new Timestamp(12, 0, 16,
        22, 22, 38, 0)), new KeyValuePair<String, Serializable>("string", "test"),
        new KeyValuePair<String, Serializable>("integer", 1),
        new KeyValuePair<String, Serializable>("long", 1L), new KeyValuePair<String, Serializable>(
            "float", 1.1f), new KeyValuePair<String, Serializable>("double", 1.1d),
        new KeyValuePair<String, Serializable>("booleanFalse", false),
        new KeyValuePair<String, Serializable>("booleanTrue", true));
  }
}
