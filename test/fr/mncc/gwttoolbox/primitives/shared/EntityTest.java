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

import org.junit.Test;

import static junit.framework.Assert.*;

public final class EntityTest {

  @Test
  public void testGetKey() {

  }

  @Test
  public void testSetKey() {

  }

  @Test
  public void testGetKind() {

  }

  @Test
  public void testSetKind() {

  }

  @Test
  public void testGetProperty() {

  }

  @Test
  public void testSetNoProperty() {
    Entity entity = new EntityImpl("test");
    assertEquals(entity.getProperty("test_no_property"), null);
  }

  @Test
  public void testSetNullProperty() {
    Entity entity = new EntityImpl("test");
    entity.setProperty("test_property_null", null);
    assertEquals(entity.getProperty("test_property_null"), null);
  }

  @Test
  public void testSetLongProperty() {
    Entity entity = new EntityImpl("test");
    entity.setProperty("test_long", 1L);
    assertEquals(entity.getProperty("test_long"), 1L);
  }

  @Test
  public void testSetIntProperty() {
    Entity entity = new EntityImpl("test");
    entity.setProperty("test_int", 1);
    assertEquals(entity.getProperty("test_int"), 1);
  }

  @Test
  public void testSetDoubleProperty() {
    Entity entity = new EntityImpl("test");
    entity.setProperty("test_double", 1.1d);
    assertEquals(entity.getProperty("test_double"), 1.1d);
  }

  @Test
  public void testSetFloatProperty() {
    Entity entity = new EntityImpl("test");
    entity.setProperty("test_float", 1.1f);
    assertEquals(entity.getProperty("test_float"), 1.1f);
  }

  @Test
  public void testSetStringProperty() {
    Entity entity = new EntityImpl("test");
    entity.setProperty("test_string", "1.1");
    assertEquals(entity.getProperty("test_string"), "1.1");
  }

  @Test
  public void testRemoveProperty() {

  }

  @Test
  public void testContainsProperty() {

  }

  @Test
  public void testGetProperties() {

  }

  @Test
  public void testEquals() {

    Entity entity1 = new EntityImpl("test");
    entity1.setProperty("test", 1);

    Entity entity2 = new EntityImpl("test");
    entity2.setProperty("test", 1);

    Entity entity3 = new EntityImpl("test");
    entity3.setProperty("test", 2);

    assertTrue(entity1.equals(entity1));
    assertTrue(entity1.equals(entity2));
    assertFalse(entity1.equals(entity3));
  }

  @Test
  public void testHashCode() {

    Entity entity1 = new EntityImpl("test");
    entity1.setProperty("test", 1);

    Entity entity2 = new EntityImpl("test");
    entity2.setProperty("test", 1);

    Entity entity3 = new EntityImpl("test");
    entity3.setProperty("test", 2);

    assertEquals(entity1.hashCode(), entity1.hashCode());
    assertEquals(entity1.hashCode(), entity2.hashCode());
    assertFalse(entity1.hashCode() == entity3.hashCode());
  }

  @Test
  public void testToString() {

    Entity entity1 = new EntityImpl("test");
    entity1.setProperty("test", 1);

    Entity entity2 = new EntityImpl("test");
    entity2.setProperty("test", 1);

    Entity entity3 = new EntityImpl("test");
    entity3.setProperty("test", 2);

    assertEquals(entity1.toString(), entity1.toString());
    assertEquals(entity1.toString(), entity2.toString());
    assertFalse(entity1.toString().equals(entity3.toString()));
  }

}
