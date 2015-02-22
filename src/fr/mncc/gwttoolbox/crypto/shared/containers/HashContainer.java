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

import java.io.Serializable;

import fr.mncc.gwttoolbox.crypto.shared.algorithms.CubeHash;
import fr.mncc.gwttoolbox.crypto.shared.algorithms.RandomString;
import fr.mncc.gwttoolbox.crypto.shared.algorithms.SecureRandom;

public final class HashContainer implements Serializable {

  private String salt_;
  private int numberOfIterations_;
  private String hash_;

  public HashContainer() {
    this("", null, 0);
  }

  public HashContainer(String hash, String salt, int numberOfIterations) {
    salt_ = salt == null || salt.isEmpty() ? new RandomString().getLettersAndDigits() : salt;
    numberOfIterations_ = numberOfIterations < 1 ? 1000 : numberOfIterations;
    hash_ = hash == null ? "" : hash;
  }

  public HashContainer init(String string, String salt, int numberOfIterations) {
    salt_ = salt == null || salt.isEmpty() ? new RandomString().getLettersAndDigits() : salt;
    numberOfIterations_ = numberOfIterations < 1 ? 1000 : numberOfIterations;
    hash_ = computeHash(string == null ? "" : string, salt_, numberOfIterations_);
    return this;
  }

  public HashContainer init(String string) {
    return init(string, new RandomString().getLettersAndDigits(), 1000);
  }

  public String getSalt() {
    return salt_;
  }

  public int getNumberOfIterations() {
    return numberOfIterations_;
  }

  public String getHash() {
    return hash_;
  }

  public String getHash(String nonce) {
    return computeHash(hash_, nonce == null ? "" : nonce, getNumberOfIterations());
  }

  /**
   * Compute H(n) such as H(0) = H(string) and H(n) = H(H(n-1) + salt) with n set to
   * numberOfIterations.
   * 
   * @param string a string to hash. Must not be null.
   * @param salt a random string. Must not be null.
   * @param numberOfIterations a random integer. Must be strictly above 0.
   * @return hashed string.
   */
  private String computeHash(String string, String salt, int numberOfIterations) {

    final CubeHash cubeHash = new CubeHash();

    // Compute H(0)
    string = string == null ? cubeHash.hash("") : cubeHash.hash(string);
    final SecureRandom secureRandom = new SecureRandom(string.hashCode());

    // Compute H(1), H(2), ..., H(n)
    for (int i = 0; i < numberOfIterations; i++) {
      salt = cubeHash.hash(shuffle(salt, secureRandom));
      string = cubeHash.hash(string + salt);
    }
    return string;
  }

  private String shuffle(String oldSalt, SecureRandom rng) {
    final byte[] newSalt = oldSalt.getBytes();
    for (int i = newSalt.length; i > 1; i--) {
      int rndValue = rng.nextInt(i - 1);
      byte rndChar = newSalt[rndValue];
      newSalt[rndValue] = newSalt[i - 1];
      newSalt[i - 1] = rndChar;
    }
    return new String(newSalt);
  }

  @Override
  public int hashCode() {
    final int seed = 37;
    int hashCode = 1;
    hashCode = seed * hashCode + numberOfIterations_;
    hashCode = seed * hashCode + salt_.hashCode();
    return seed * hashCode + hash_.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof HashContainer))
      return false;
    if (obj == this)
      return true;

    final HashContainer hash = (HashContainer) obj;
    return numberOfIterations_ == hash.numberOfIterations_ && salt_.equals(hash.salt_)
        && hash_.equals(hash.getHash());
  }
}
