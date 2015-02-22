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

import java.util.Date;
import java.util.Random;

/**
 * George Marsaglia's Xorshift : http://en.wikipedia.org/wiki/Xorshift
 */
public final class SecureRandom extends Random {

  private long seed_;

  public SecureRandom() {
    super(new Date().getTime());
  }

  public SecureRandom(long seed) {
    super(seed);
  }

  @Override
  synchronized public void setSeed(long seed) {
    this.seed_ = seed;
    super.setSeed(seed);
  }

  @Override
  protected int next(int bits) {
    long next = seed_;
    next ^= (next << 21);
    next ^= (next >>> 35);
    next ^= (next << 4);
    seed_ = next;
    next &= ((1L << bits) - 1);
    return (int) next;
  }
}
