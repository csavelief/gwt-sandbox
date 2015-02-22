/**
 * Copyright (c) 2014 MNCC
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

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public interface Entity extends HasKey, IsSerializable, Serializable {

  void setKey(String key);

  String getKind();

  void setKind(String kind);

  String getAncestorKey();

  void setAncestorKey(String key);

  String getAncestorKind();

  void setAncestorKind(String kind);

  Object getProperty(String propertyName);

  void setProperty(String propertyName, Object propertyValue);

  void removeProperty(String propertyName);

  boolean containsProperty(String propertyName);

  Iterable<String> getProperties();

  String getPropertyAsString(String propertyName);

  int getPropertyAsInt(String propertyName);

  long getPropertyAsLong(String propertyName);

  double getPropertyAsDouble(String propertyName);

  float getPropertyAsFloat(String propertyName);

  boolean getPropertyAsBoolean(String propertyName);

  Date getPropertyAsDate(String propertyName);

  Time getPropertyAsTime(String propertyName);

  Timestamp getPropertyAsTimestamp(String propertyName);
}
