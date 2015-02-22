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

public class Projection2 implements IsSerializable, Serializable {

  private String propertyName_ = "";
  private Class<?> clazz_ = null;

  protected Projection2() {

  }

  protected Projection2(String propertyName, Class<?> clazz) {
    propertyName_ = propertyName;
    clazz_ = clazz;
  }

  public static Projection2 of(String propertyName, Class<?> clazz) {
    return new Projection2(propertyName, clazz);
  }

  public String getPropertyName() {
    return propertyName_;
  }

  public Class<?> getClazz() {
    return clazz_;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("propertyName_", propertyName_).add("clazz_", clazz_)
        .omitNullValues().toString();
  }
}
