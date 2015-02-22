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
package fr.mncc.gwttoolbox.appengine.server.nextgen.implementations;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import fr.mncc.gwttoolbox.appengine.server.nextgen.interfaces.GaeEntity;
import fr.mncc.gwttoolbox.primitives.shared.EntityImpl;
import fr.mncc.gwttoolbox.primitives.shared.Strings;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public final class GaeEntityImpl implements GaeEntity {

  private final Entity entity_;

  protected GaeEntityImpl() {
    entity_ = null;
  }

  public GaeEntityImpl(Entity entity) {
    entity_ = fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entity);
  }

  public GaeEntityImpl(fr.mncc.gwttoolbox.primitives.shared.Entity entity) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entity);

    final long key = Strings.parseLong(entity.getKey());
    final long ancestorKey = Strings.parseLong(entity.getAncestorKey());
    if (entity.getAncestorKind().isEmpty()) {
      if (key < 0) {
        throw new Error("Key must be > 0.");
      } else if (key == 0) {
        entity_ = new Entity(entity.getKind());
      } else {
        entity_ = new Entity(entity.getKind(), key);
      }
    } else {
      if (ancestorKey <= 0) {
        throw new Error("Ancestor key must be > 0.");
      }
      if (key == 0) {
        entity_ =
            new Entity(entity.getKind(), KeyFactory
                .createKey(entity.getAncestorKind(), ancestorKey));
      } else {
        entity_ =
            new Entity(entity.getKind(), key, KeyFactory.createKey(entity.getAncestorKind(),
                ancestorKey));
      }
    }

    for (String propertyName : entity.getProperties()) {

      // Property starts with __ or ends with __ : reserved AppEngine keywords
      if (propertyName.startsWith("__") || propertyName.endsWith("__")) {
        continue;
      }

      Object propertyValue = entity.getProperty(propertyName);
      if (propertyValue == null) {
        continue;
      }

      // DataStore limits String objects to 500 characters
      if (propertyValue instanceof String && ((String) propertyValue).length() >= 500) {
        propertyValue = new Text((String) propertyValue);
      }
      // DataStore is not able to store Timestamp
      else if (propertyValue instanceof Timestamp) {
        propertyValue = new Date(((Timestamp) propertyValue).getTime());
      }
      // DataStore is not able to store Time objects
      else if (propertyValue instanceof Time) {
        propertyValue = new Date(((Time) propertyValue).getTime());
      }

      entity_.setProperty(propertyName, propertyValue);
    }
  }

  public GaeEntityImpl(String kind) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);

    entity_ = new Entity(kind);
  }

  public GaeEntityImpl(String kind, String ancestorKind, long ancestorKey) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorKey > 0);

    entity_ = new Entity(kind, KeyFactory.createKey(ancestorKind, ancestorKey));
  }

  public GaeEntityImpl(String kind, long key) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(key > 0);

    entity_ = new Entity(kind, key);
  }

  public GaeEntityImpl(String kind, long key, String ancestorKind, long ancestorKey) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(key > 0);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorKey > 0);

    entity_ = new Entity(kind, key, KeyFactory.createKey(ancestorKind, ancestorKey));
  }

  @Override
  public Entity unbox() {
    return entity_;
  }

  @Override
  public EntityImpl convert() {

    EntityImpl entity =
        entity_.getParent() == null ? new EntityImpl(entity_.getKind(), Long.toString(entity_
            .getKey().getId(), 10)) : new EntityImpl(entity_.getKind(), Long.toString(entity_
            .getKey().getId(), 10), entity_.getParent().getKind(), Long.toString(entity_
            .getParent().getId(), 10));

    for (String propertyName : entity_.getProperties().keySet()) {
      Object propertyValue = entity_.getProperty(propertyName);
      if (propertyValue instanceof Text) {
        entity.setProperty(propertyName, ((Text) propertyValue).getValue());
      } else if (propertyValue instanceof Date) {
        entity.setProperty(propertyName, new Timestamp(((Date) propertyValue).getTime()));
      } else {
        entity.setProperty(propertyName, propertyValue);
      }
    }
    return entity;
  }

  @Override
  public String getKey() {
    return Long.toString(entity_.getKey().getId(), 10);
  }

  @Override
  public void setKey(String key) {
    throw new Error("Not available for this implementation of Entity.");
  }

  @Override
  public String getKind() {
    return entity_.getKind();
  }

  @Override
  public void setKind(String kind) {
    throw new Error("Not available for this implementation of Entity.");
  }

  @Override
  public String getAncestorKey() {
    return entity_.getParent() == null ? "" : Long.toString(entity_.getParent().getId(), 10);
  }

  @Override
  public void setAncestorKey(String key) {
    throw new Error("Not available for this implementation of Entity.");
  }

  @Override
  public String getAncestorKind() {
    return entity_.getParent() == null ? "" : entity_.getParent().getKind();
  }

  @Override
  public void setAncestorKind(String kind) {
    throw new Error("Not available for this implementation of Entity.");
  }

  @Override
  public Object getProperty(String propertyName) {
    Object object = entity_.getProperty(propertyName);
    return object instanceof Text ? ((Text) object).getValue() : object;
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
    entity_.setProperty(propertyName, propertyValue instanceof Timestamp ? new Date(
        ((Timestamp) propertyValue).getTime()) : propertyValue instanceof String
        && ((String) propertyValue).length() >= 500 ? new Text((String) propertyValue)
        : propertyValue);
  }

  @Override
  public void removeProperty(String propertyName) {
    entity_.removeProperty(propertyName);
  }

  @Override
  public boolean containsProperty(String propertyName) {
    return entity_.getProperty(propertyName) != null;
  }

  @Override
  public Iterable<String> getProperties() {
    return entity_.getProperties().keySet();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GaeEntityImpl))
      return false;
    if (o == this)
      return true;

    final GaeEntityImpl entity = (GaeEntityImpl) o;
    return entity_.equals(entity.entity_);
  }

  @Override
  public int hashCode() {
    return entity_.hashCode();
  }

  @Override
  public String toString() {
    return convert().toString();
  }
}
