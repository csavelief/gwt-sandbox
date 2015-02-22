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

public final class KeyTest {

  @Test
  public void testNull() {

    Key key = new Key(null);
    assertEquals("", key.getKey());

    key.setKey(null);
    assertEquals("", key.getKey());
  }

  @Test
  public void testEmpty() {

    Key key = new Key("");
    assertEquals("", key.getKey());

    key.setKey("");
    assertEquals("", key.getKey());
  }

  @Test
  public void testString() {

    Key key = new Key("key");
    assertEquals("key", key.getKey());

    key.setKey("key");
    assertEquals("key", key.getKey());
  }

  @Test
  public void testEquals() {

    Key key1 = new Key("key1");
    Key key2 = new Key("key2");

    assertTrue(key1.equals(key1));
    assertTrue(key2.equals(key2));
    assertFalse(key1.equals(null));
    assertFalse(key2.equals(null));
    assertFalse(key1.equals(key2));
    assertFalse(key2.equals(key1));
  }

  @Test
  public void testHashCode() {

    Key key1 = new Key("key1");
    Key key2 = new Key("key2");

    assertTrue(key1.hashCode() == key1.hashCode());
    assertTrue(key2.hashCode() == key2.hashCode());
    assertFalse(key1.hashCode() == key2.hashCode());
  }
}
