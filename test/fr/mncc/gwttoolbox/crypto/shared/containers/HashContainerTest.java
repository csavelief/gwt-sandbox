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
package fr.mncc.gwttoolbox.crypto.shared.containers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class HashContainerTest {

  @Test
  public void testNull() {

    HashContainer hashContainer1 = new HashContainer().init(null);
    String salt1 = hashContainer1.getSalt();
    int numberOfIterations1 = hashContainer1.getNumberOfIterations();
    String hash1 = hashContainer1.getHash();
    String hashPlus1 = hashContainer1.getHash("0123456789");

    HashContainer hashContainer2 = new HashContainer(hash1, salt1, numberOfIterations1);
    String salt2 = hashContainer2.getSalt();
    int numberOfIterations2 = hashContainer2.getNumberOfIterations();
    String hash2 = hashContainer2.getHash();
    String hashPlus2 = hashContainer2.getHash("0123456789");

    assertEquals(hashContainer1, hashContainer2);
    assertEquals(salt1, salt2);
    assertEquals(numberOfIterations1, numberOfIterations2);
    assertEquals(hash1, hash2);
    assertEquals(hashPlus1, hashPlus2);
  }

  @Test
  public void testEmptyString() {

    HashContainer hashContainer1 = new HashContainer().init("");
    String salt1 = hashContainer1.getSalt();
    int numberOfIterations1 = hashContainer1.getNumberOfIterations();
    String hash1 = hashContainer1.getHash();
    String hashPlus1 = hashContainer1.getHash("0123456789");

    HashContainer hashContainer2 = new HashContainer(hash1, salt1, numberOfIterations1);
    String salt2 = hashContainer2.getSalt();
    int numberOfIterations2 = hashContainer2.getNumberOfIterations();
    String hash2 = hashContainer2.getHash();
    String hashPlus2 = hashContainer2.getHash("0123456789");

    assertEquals(hashContainer1, hashContainer2);
    assertEquals(salt1, salt2);
    assertEquals(numberOfIterations1, numberOfIterations2);
    assertEquals(hash1, hash2);
    assertEquals(hashPlus1, hashPlus2);
  }

  @Test
  public void testVector1() {
    HashContainer hashContainer1 = new HashContainer().init("Hello");
    String salt1 = hashContainer1.getSalt();
    int numberOfIterations1 = hashContainer1.getNumberOfIterations();
    String hash1 = hashContainer1.getHash();
    String hashPlus1 = hashContainer1.getHash("0123456789");

    HashContainer hashContainer2 = new HashContainer(hash1, salt1, numberOfIterations1);
    String salt2 = hashContainer2.getSalt();
    int numberOfIterations2 = hashContainer2.getNumberOfIterations();
    String hash2 = hashContainer2.getHash();
    String hashPlus2 = hashContainer2.getHash("0123456789");

    assertEquals(hashContainer1, hashContainer2);
    assertEquals(salt1, salt2);
    assertEquals(numberOfIterations1, numberOfIterations2);
    assertEquals(hash1, hash2);
    assertEquals(hashPlus1, hashPlus2);
  }

  @Test
  public void testVector2() {

    HashContainer hashContainer1 = new HashContainer().init("hello");
    String salt1 = hashContainer1.getSalt();
    int numberOfIterations1 = hashContainer1.getNumberOfIterations();
    String hash1 = hashContainer1.getHash();
    String hashPlus1 = hashContainer1.getHash("0123456789");

    HashContainer hashContainer2 = new HashContainer(hash1, salt1, numberOfIterations1);
    String salt2 = hashContainer2.getSalt();
    int numberOfIterations2 = hashContainer2.getNumberOfIterations();
    String hash2 = hashContainer2.getHash();
    String hashPlus2 = hashContainer2.getHash("0123456789");

    assertEquals(hashContainer1, hashContainer2);
    assertEquals(salt1, salt2);
    assertEquals(numberOfIterations1, numberOfIterations2);
    assertEquals(hash1, hash2);
    assertEquals(hashPlus1, hashPlus2);
  }

  @Test
  public void testVector3() {

    HashContainer hashContainer1 =
        new HashContainer().init("The quick brown fox jumps over the lazy dog");
    String salt1 = hashContainer1.getSalt();
    int numberOfIterations1 = hashContainer1.getNumberOfIterations();
    String hash1 = hashContainer1.getHash();
    String hashPlus1 = hashContainer1.getHash("0123456789");

    HashContainer hashContainer2 = new HashContainer(hash1, salt1, numberOfIterations1);
    String salt2 = hashContainer2.getSalt();
    int numberOfIterations2 = hashContainer2.getNumberOfIterations();
    String hash2 = hashContainer2.getHash();
    String hashPlus2 = hashContainer2.getHash("0123456789");

    assertEquals(hashContainer1, hashContainer2);
    assertEquals(salt1, salt2);
    assertEquals(numberOfIterations1, numberOfIterations2);
    assertEquals(hash1, hash2);
    assertEquals(hashPlus1, hashPlus2);
  }
}
