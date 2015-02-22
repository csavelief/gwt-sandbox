/**
 * Copyright (c) 2013 MNCC
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
package fr.mncc.gwttoolbox.appengine.shared;

import java.io.Serializable;

import com.google.common.base.Objects;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Clause2 implements IsSerializable, Serializable {

  private boolean isAnd_ = true;
  private Clause2 clauseLeft_ = null;
  private Clause2 clauseRight_ = null;

  protected Clause2() {

  }

  protected Clause2(boolean isAnd, Clause2 clauseLeft, Clause2 clauseRight) {
    isAnd_ = isAnd;
    clauseLeft_ = clauseLeft;
    clauseRight_ = clauseRight;
  }

  public static Clause2 and(Clause2 clauseLeft, Clause2 clauseRight) {
    return new Clause2(true, clauseLeft, clauseRight);
  }

  public static Clause2 or(Clause2 clauseLeft, Clause2 clauseRight) {
    return new Clause2(false, clauseLeft, clauseRight);
  }

  public boolean isAnd() {
    return isAnd_;
  }

  public boolean isOr() {
    return !isAnd();
  }

  public Clause2 getLeftClause() {
    return clauseLeft_;
  }

  public Clause2 getRightClause() {
    return clauseRight_;
  }

  public boolean isNode() {
    return !isLeaf();
  }

  public boolean isLeaf() {
    return clauseLeft_ == null && clauseRight_ == null;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("isAnd_", isAnd_).add("clauseLeft_", clauseLeft_).add(
        "clauseRight_", clauseRight_).omitNullValues().toString();
  }
}
