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
package fr.mncc.gwttoolbox.crypto.shared.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Rc4Test {

  @Test
  public void testReset() {
    Rc4 rc4 = new Rc4();
    assertEquals(false, rc4.reset(null));
    assertEquals(false, rc4.reset(""));
    assertEquals(true, rc4.reset("key"));
  }

  @Test
  public void testWithNull() {
    Rc4 rc4 = new Rc4();
    assertEquals("", rc4.code("Hello", null));
    assertEquals("", rc4.code("Hello", ""));
    assertEquals("", rc4.code(null, "key"));
    assertEquals("", rc4.code("", "key"));
    assertEquals("", rc4.code(null, null));
    assertEquals("", rc4.code("", ""));
  }

  @Test
  public void testCodeVector1() {
    Rc4 rc4 = new Rc4();
    assertEquals(rc4.code("Hello", "key"), "430958814b");
    assertEquals(rc4.code("Hello"), "c71e262424");
    assertEquals(rc4.code("HelloHello", "key"), "430958814bc71e262424");
  }

  @Test
  public void testDecodeVector1() {
    Rc4 rc4 = new Rc4();
    assertEquals("Hello", rc4.decode("430958814b", "key"));
    assertEquals("Hello", rc4.decode("c71e262424"));
    assertEquals("HelloHello", rc4.decode("430958814bc71e262424", "key"));
  }

  @Test
  public void testCodeVector2() {
    Rc4 rc4 = new Rc4();
    assertEquals("5f0451cd55fa1229236b6a09792a7cdde91b95", rc4.code("The quick brown fox", "key"));
    assertEquals("46c1948e8f45d3c7cb5c9e5bea7c5896e2c8f5c39c57b898", rc4
        .code(" jumps over the lazy dog"));
    assertEquals(
        "5f0451cd55fa1229236b6a09792a7cdde91b9546c1948e8f45d3c7cb5c9e5bea7c5896e2c8f5c39c57b898",
        rc4.code("The quick brown fox jumps over the lazy dog", "key"));
  }

  @Test
  public void testDecodeVector2() {
    Rc4 rc4 = new Rc4();
    assertEquals("The quick brown fox", rc4.decode("5f0451cd55fa1229236b6a09792a7cdde91b95", "key"));
    assertEquals(" jumps over the lazy dog", rc4
        .decode("46c1948e8f45d3c7cb5c9e5bea7c5896e2c8f5c39c57b898"));
    assertEquals("The quick brown fox jumps over the lazy dog", rc4.decode(
        "5f0451cd55fa1229236b6a09792a7cdde91b9546c1948e8f45d3c7cb5c9e5bea7c5896e2c8f5c39c57b898",
        "key"));
  }
}
