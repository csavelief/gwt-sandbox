/**
 * Copyright (c) 2012 MNCC
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
package fr.mncc.gwttoolbox.ajax.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.junit.client.GWTTestCase;

public class JsonParserTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "fr.mncc.gwttoolbox.ajax.ajax";
  }

  public void testFromJson() {
    Contact contactTemplate = createContact();
    Contact contact =
        JsonParser
            .fromJson("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"phone\":\"+33666666666\", \"birthdate\":\"1984-09-15\"}");
    assertEquals(contactTemplate.getFirstName(), contact.getFirstName());
    assertEquals(contactTemplate.getLastName(), contact.getLastName());
    assertEquals(contactTemplate.getBirthdate(), contact.getBirthdate());
    assertEquals(contactTemplate.getPhone(), contact.getPhone());
  }

  public void testToJson() {
    String json = JsonParser.toJson(createContact());
    assertEquals(
        "{\"firstName\":\"John\", \"lastName\":\"Doe\", \"phone\":\"+33666666666\", \"birthdate\":\"1984-09-15\"}",
        json);
  }

  public void testInvariant() {
    Contact contactTemplate = createContact();
    String json = JsonParser.toJson(contactTemplate);
    Contact contact = JsonParser.fromJson(json);
    assertEquals(contactTemplate.getFirstName(), contact.getFirstName());
    assertEquals(contactTemplate.getLastName(), contact.getLastName());
    assertEquals(contactTemplate.getBirthdate(), contact.getBirthdate());
    assertEquals(contactTemplate.getPhone(), contact.getPhone());
  }

  private Contact createContact() {
    Contact contact = (Contact) Contact.createObject();
    contact.setFirstName("John");
    contact.setLastName("Doe");
    contact.setPhone("+33666666666");
    contact.setBirthdate("1984-09-15");
    return contact;
  }

  static class Contact extends JavaScriptObject {

    protected Contact() {

    }

    public final native String getFirstName() /*-{
      return this.firstName;
    }-*/;

    public final native void setFirstName(String firstName) /*-{
      this.firstName = firstName;
    }-*/;

    public final native String getLastName() /*-{
      return this.lastName;
    }-*/;

    public final native void setLastName(String lastName) /*-{
      this.lastName = lastName;
    }-*/;

    public final native String getBirthdate() /*-{
      return this.birthdate;
    }-*/;

    public final native void setBirthdate(String birthdate) /*-{
      this.birthdate = birthdate;
    }-*/;

    public final native String getPhone() /*-{
      return this.phone;
    }-*/;

    public final native void setPhone(String phone) /*-{
      this.phone = phone;
    }-*/;
  }
}
