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

import fr.mncc.gwttoolbox.crypto.shared.algorithms.CubeHash;
import fr.mncc.gwttoolbox.crypto.shared.algorithms.RandomString;
import fr.mncc.gwttoolbox.crypto.shared.algorithms.Rc4;

import java.io.Serializable;

public final class TokenContainer implements Serializable {

  private String token_;

  public TokenContainer() {
    token_ = "";
  }

  /**
   * Set object with a given secret.
   * 
   * @param token
   */
  public TokenContainer(String token) {
    token_ = token == null ? "" : token;
  }

  /**
   * Create a secret from a given key : "TOKEN_HASH-RC4(TOKEN, secret)".
   * 
   * @param key
   */
  public TokenContainer init(String key) {
    if (key == null || key.isEmpty())
      key = "key";

    final String token = new RandomString().getLettersAndDigits();
    token_ = new CubeHash().hash(token) + "-" + new Rc4(key).code(token);
    return this;
  }

  /**
   * Check if the current secret is valid.
   * 
   * @param key
   * @return true if token is valid, false otherwise
   */
  public boolean isValid(String key) {
    if (key == null || key.isEmpty())
      key = "key";

    // Check token syntax
    int separator = token_.indexOf("-");
    if (separator < 0) {
      return false;
    }

    // Split token hash and coded token
    final String hash = token_.substring(0, separator);
    final String secret = new Rc4(key).decode(token_.substring(separator + 1));

    // Check if token is valid
    return hash.equals(new CubeHash().hash(secret));
  }

  public String getToken() {
    return token_;
  }

  @Override
  public int hashCode() {
    return token_.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TokenContainer))
      return false;
    if (obj == this)
      return true;

    final TokenContainer secret = (TokenContainer) obj;
    return token_.equals(secret.getToken());
  }
}
