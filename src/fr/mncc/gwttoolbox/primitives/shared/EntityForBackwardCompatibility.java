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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * For backward compatibility with a previous version of the Entity class. DO NOT USE ANYMORE !
 */
@Deprecated
public class EntityForBackwardCompatibility implements Entity, HasId, HasTimestamp, IsSerializable {

  private EntityImpl entity_ = null;

  protected EntityForBackwardCompatibility() {
    entity_ = new EntityImpl();
  }

  public EntityForBackwardCompatibility(Entity entity) {
    entity_ =
        new EntityImpl(entity.getKind(), entity.getKey(), entity.getAncestorKind(), entity
            .getAncestorKey());
    for (String propertyName : entity.getProperties()) {
      entity_.setProperty(propertyName, entity.getProperty(propertyName));
    }
  }

  public EntityForBackwardCompatibility(String kind) {
    entity_ = new EntityImpl(kind);
  }

  public EntityForBackwardCompatibility(String kind, long id) {
    entity_ = new EntityImpl(kind, "" + id);
  }

  // public EntityForBackwardCompatibility(String kind, long id, List<String> properties) {
  // super(kind, "" + id);
  // }

  // private static boolean listEquals(List<String> l1, List<String> l2) {
  // TODO
  // }

  // private Map<String, Object> mapOf(List<String> list) {
  // TODO
  // }

  // public Map<String, Object> diff(Entity entity, int flags) {
  // TODO
  // }

  // public Map<String, Object> gcd(Entity entity) {
  // TODO
  // }

  @Override
  public long getId() {
    return Strings.parseLong(entity_.getKey());
  }

  public void setId(long id) {
    entity_.setKey("" + id);
  }

  @Override
  public Timestamp getTimestamp() {
    return getPropertyAsTimestamp("__timestamp__");
  }

  public void setTimestamp() {
    put("__timestamp__", new Date());
  }

  public Set<String> keySet() {
    return entity_.getProperties() instanceof Set ? (Set<String>) entity_.getProperties()
        : new HashSet<String>();
  }

  public void put(String propertyName, Object propertyValue) {
    entity_.setProperty(propertyName, propertyValue);
  }

  public void remove(String propertyName) {
    entity_.removeProperty(propertyName);
  }

  public Object getAsObject(String propertyName) {
    return entity_.getProperty(propertyName);
  }

  @Override
  public String getPropertyAsString(String propertyName) {
    return entity_.getPropertyAsString(propertyName);
  }

  @Override
  public int getPropertyAsInt(String propertyName) {
    return entity_.getPropertyAsInt(propertyName);
  }

  @Override
  public long getPropertyAsLong(String propertyName) {
    return entity_.getPropertyAsLong(propertyName);
  }

  @Override
  public double getPropertyAsDouble(String propertyName) {
    return entity_.getPropertyAsDouble(propertyName);
  }

  @Override
  public float getPropertyAsFloat(String propertyName) {
    return entity_.getPropertyAsFloat(propertyName);
  }

  @Override
  public boolean getPropertyAsBoolean(String propertyName) {
    return entity_.getPropertyAsBoolean(propertyName);
  }

  @Override
  public Date getPropertyAsDate(String propertyName) {
    return entity_.getPropertyAsDate(propertyName);
  }

  @Override
  public Time getPropertyAsTime(String propertyName) {
    return entity_.getPropertyAsTime(propertyName);
  }

  @Override
  public Timestamp getPropertyAsTimestamp(String propertyName) {
    return entity_.getPropertyAsTimestamp(propertyName);
  }

  @Override
  public String getKey() {
    return entity_.getKey();
  }

  @Override
  public void setKey(String key) {
    entity_.setKey(key);
  }

  @Override
  public String getKind() {
    return entity_.getKind();
  }

  @Override
  public void setKind(String kind) {
    entity_.setKey(kind);
  }

  @Override
  public String getAncestorKey() {
    return entity_.getAncestorKey();
  }

  @Override
  public void setAncestorKey(String key) {
    entity_.setAncestorKey(key);
  }

  @Override
  public String getAncestorKind() {
    return entity_.getAncestorKind();
  }

  @Override
  public void setAncestorKind(String kind) {
    entity_.setAncestorKind(kind);
  }

  @Override
  public Object getProperty(String propertyName) {
    return entity_.getProperty(propertyName);
  }

  @Override
  public void setProperty(String propertyName, Object propertyValue) {
    entity_.setProperty(propertyName, propertyValue);
  }

  @Override
  public void removeProperty(String propertyName) {
    entity_.removeProperty(propertyName);
  }

  @Override
  public boolean containsProperty(String propertyName) {
    return entity_.containsProperty(propertyName);
  }

  @Override
  public Iterable<String> getProperties() {
    return entity_.getProperties();
  }
}
