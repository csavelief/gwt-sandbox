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

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public final class KeyValuePairTest {

  @Test
  public void testKeyValuePair() {
    try {
      new KeyValuePair<String, String>(null, "value");
      assertEquals(false, true);
    } catch (NullPointerException e) {
      assertEquals(true, true);
    }
  }

  @Test
  public void testGetKey() {

    KeyValuePair<?, ?> keyValuePair = createKeyValuePair1();
    assertEquals("key", keyValuePair.getKey());

    keyValuePair = createKeyValuePair2();
    assertEquals("value", keyValuePair.getKey());

    keyValuePair = createKeyValuePair3();
    assertEquals("key", keyValuePair.getKey());
  }

  @Test
  public void testGetValue() {

    KeyValuePair<?, ?> keyValuePair = createKeyValuePair1();
    assertEquals("value", keyValuePair.getValue());

    keyValuePair = createKeyValuePair2();
    assertEquals("key", keyValuePair.getValue());

    keyValuePair = createKeyValuePair3();
    assertEquals(null, keyValuePair.getValue());
  }

  @Test
  public void testAsString() {
    assertEquals("key=value", createKeyValuePair1().asString());
  }

  @Test
  public void testAsJson() {
    assertEquals("{\"key\":\"value\"}", createKeyValuePair1().asJson());
  }

  @Test
  public void testEquals() {
    KeyValuePair<?, ?> keyValuePair = createKeyValuePair1();
    assertTrue(keyValuePair.equals(keyValuePair));
    assertTrue(keyValuePair.equals(createKeyValuePair1()));
    assertTrue(createKeyValuePair1().equals(keyValuePair));
    assertFalse(keyValuePair.equals(createKeyValuePair2()));
    assertFalse(createKeyValuePair2().equals(keyValuePair));
    assertFalse(keyValuePair.equals(createKeyValuePair3()));
    assertFalse(createKeyValuePair3().equals(keyValuePair));
    assertFalse(keyValuePair.equals(null));
  }

  @Test
  public void testHashCode() {
    KeyValuePair<?, ?> keyValuePair = createKeyValuePair1();
    assertTrue(keyValuePair.hashCode() == keyValuePair.hashCode());
    assertTrue(keyValuePair.hashCode() == createKeyValuePair1().hashCode());
    assertFalse(keyValuePair.hashCode() == createKeyValuePair2().hashCode());
    assertFalse(keyValuePair.hashCode() == createKeyValuePair3().hashCode());
  }

  private KeyValuePair<String, String> createKeyValuePair1() {
    return new KeyValuePair<String, String>("key", "value");
  }

  private KeyValuePair<String, String> createKeyValuePair2() {
    return new KeyValuePair<String, String>("value", "key");
  }

  private KeyValuePair<String, String> createKeyValuePair3() {
    return new KeyValuePair<String, String>("key", null);
  }
}
