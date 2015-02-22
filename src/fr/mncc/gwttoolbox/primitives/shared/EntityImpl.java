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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import static fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty;
import static fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull;

public final class EntityImpl implements Entity {

  private Key key_ = new Key();
  private Key ancestorKey_ = new Key();
  private KeyValueMap<String, String> keyValueMap_ = new KeyValueMap<String, String>();
  private String kind_;
  private String ancestorKind_;

  protected EntityImpl() {
    setKind("Entity");
    setAncestorKind("");
  }

  public EntityImpl(String kind) {
    setKind(kind);
    setAncestorKind("");
  }

  public EntityImpl(String kind, String ancestorKind, String ancestorKey) {
    setKind(kind);
    setAncestorKind(ancestorKind);
    setAncestorKey(ancestorKey);
  }

  public EntityImpl(String kind, String key) {
    setKind(kind);
    setKey(key);
    setAncestorKind("");
  }

  public EntityImpl(String kind, String key, String ancestorKind, String ancestorKey) {
    setKind(kind);
    setKey(key);
    setAncestorKind(ancestorKind);
    setAncestorKey(ancestorKey);
  }

  @Override
  public String getKey() {
    return key_.getKey();
  }

  @Override
  public void setKey(String key) {
    key_.setKey(key);
  }

  @Override
  public String getKind() {
    return kind_;
  }

  @Override
  public void setKind(String kind) {
    kind_ = checkNeitherNullNorEmpty(kind);
  }

  @Override
  public String getAncestorKey() {
    return ancestorKey_.getKey();
  }

  @Override
  public void setAncestorKey(String key) {
    ancestorKey_.setKey(key);
  }

  @Override
  public String getAncestorKind() {
    return ancestorKind_;
  }

  @Override
  public void setAncestorKind(String kind) {
    ancestorKind_ = checkNotNull(kind);
  }

  @Override
  public Object getProperty(String propertyName) {
    if (!keyValueMap_.contains(propertyName) || keyValueMap_.get(propertyName) == null) {
      return null;
    }
    return Strings.objectFromString(keyValueMap_.get(propertyName));
  }

  @Override
  public String getPropertyAsString(String propertyName) {
    Object object = getProperty(propertyName);
    return object == null ? "" : object.toString();
  }

  @Override
  public int getPropertyAsInt(String propertyName) {
    Object object = getProperty(propertyName);
    return object instanceof Integer ? (Integer) object : object instanceof Long ? ((Long) object)
        .intValue() : object instanceof Double ? ((Double) object).intValue()
        : object instanceof Float ? ((Float) object).intValue() : object instanceof String
            ? Strings.parseInt((String) object) : 0;
  }

  @Override
  public long getPropertyAsLong(String propertyName) {
    Object object = getProperty(propertyName);
    return object instanceof Long ? (Long) object : object instanceof Integer ? ((Integer) object)
        .longValue() : object instanceof Double ? ((Double) object).longValue()
        : object instanceof Float ? ((Float) object).longValue() : object instanceof String
            ? Strings.parseLong((String) object) : 0L;
  }

  @Override
  public double getPropertyAsDouble(String propertyName) {
    Object object = getProperty(propertyName);
    return object instanceof Double ? (Double) object : object instanceof Float ? ((Float) object)
        .doubleValue() : object instanceof Long ? ((Long) object).doubleValue()
        : object instanceof Integer ? ((Integer) object).doubleValue() : object instanceof String
            ? Strings.parseDouble((String) object) : 0.0d;
  }

  @Override
  public float getPropertyAsFloat(String propertyName) {
    Object object = getProperty(propertyName);
    return object instanceof Float ? (Float) object : object instanceof Double ? ((Double) object)
        .floatValue() : object instanceof Long ? ((Long) object).floatValue()
        : object instanceof Integer ? ((Integer) object).floatValue() : object instanceof String
            ? Strings.parseFloat((String) object) : 0.0f;
  }

  @Override
  public boolean getPropertyAsBoolean(String propertyName) {
    Object object = getProperty(propertyName);
    return object instanceof Boolean ? (Boolean) object : object instanceof String ? Strings
        .parseBoolean((String) object) : false;
  }

  @Override
  public Date getPropertyAsDate(String propertyName) {
    Object object = getProperty(propertyName);
    return object instanceof Timestamp ? new Date(((Timestamp) object).getTime())
        : object instanceof Time ? new Date(((Time) object).getTime()) : object instanceof Date
            ? (Date) object : new Date(0);

  }

  @Override
  public Time getPropertyAsTime(String propertyName) {
    Object object = getProperty(propertyName);
    return object instanceof Timestamp ? new Time(((Timestamp) object).getTime())
        : object instanceof Time ? (Time) object : object instanceof Date ? new Time(
            ((Date) object).getTime()) : new Time(0);
  }

  @Override
  public Timestamp getPropertyAsTimestamp(String propertyName) {
    Object object = getProperty(propertyName);
    return object instanceof Timestamp ? (Timestamp) object : object instanceof Time
        ? new Timestamp(((Time) object).getTime()) : object instanceof Date ? new Timestamp(
            ((Date) object).getTime()) : new Timestamp(0);
  }

  @Override
  public void setProperty(String propertyName, Object propertyValue) {
    if (propertyValue == null) {
      keyValueMap_.put(propertyName, null);
    } else {
      keyValueMap_.put(propertyName, Strings.objectToString(propertyValue));
    }
  }

  @Override
  public void removeProperty(String propertyName) {
    keyValueMap_.remove(propertyName);
  }

  @Override
  public boolean containsProperty(String propertyName) {
    return keyValueMap_.contains(propertyName);
  }

  @Override
  public Iterable<String> getProperties() {
    return keyValueMap_.keys();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof EntityImpl))
      return false;
    if (o == this)
      return true;

    final EntityImpl entity = (EntityImpl) o;
    return kind_.equals(entity.kind_) && key_.equals(entity.key_)
        && ancestorKind_.equals(entity.ancestorKind_) && ancestorKey_.equals(entity.ancestorKey_)
        && keyValueMap_.equals(entity.keyValueMap_);
  }

  @Override
  public int hashCode() {
    final int seed = 37;
    int hashCode = 1;
    hashCode = seed * hashCode + kind_.hashCode();
    hashCode = seed * hashCode + key_.hashCode();
    hashCode = seed * hashCode + ancestorKind_.hashCode();
    hashCode = seed * hashCode + ancestorKey_.hashCode();
    hashCode = seed * hashCode + keyValueMap_.hashCode();
    return hashCode;
  }

  @Override
  public String toString() {

    String separator = "";
    String properties = "{";
    for (String key : keyValueMap_.keys()) {
      Object value = Strings.objectFromString(keyValueMap_.get(key));
      properties +=
          (separator + "\"" + key + "\":\"" + (value == null ? "" : value.toString()) + "\"");
      separator = ",";
    }
    properties = properties + "}";

    return "{\"kind\":\"" + kind_ + "\",\"key\":\"" + key_.getKey() + "\",\"ancestorKind\":\""
        + ancestorKind_ + "\",\"ancestorKey\":\"" + ancestorKey_.getKey() + "\",\"properties\":"
        + properties + "}";
  }
}
