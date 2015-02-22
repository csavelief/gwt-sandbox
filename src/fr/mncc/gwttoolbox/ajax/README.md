gwt-ajax
========

Simple wrapper around GWT RequestBuilder API

Dependencies
============

None.

How to build gwt-ajax ?
=======================

* Download & install [Gradle 1.7](http://www.gradle.org/) or above
* Go to your local gwt-ajax directory
* Type "gradle" in your command-line
* The generated jar will be written in the [bin/](https://github.com/MNCC/gwt-ajax/tree/master/bin) directory

How to get started ?
====================

Add the following line to your *.gwt.xml file :

```xml
	<inherits name='fr.mncc.gwttoolbox.ajax.ajax'/>
```

Example : How to call a webservice using JSON ?
===============================================

```java
public final class Contact extends JavaScriptObject {

    public static Contact create() {
        return (Contact) JavaScriptObject.createObject().cast();
    }

    protected Contact() { }
    public final native long getId() /*-{ return this.id; }-*/;
    public final native void setId(long id) /*-{ this.id = id; }-*/;
    public final native String getName() /*-{ return this.name; }-*/;
    public final native void setName(String name) /*-{ this.name = name; }-*/;
    public final native int getAge() /*-{ return this.age; }-*/;
    public final native void setAge(int age) /*-{ this.age = age; }-*/;
}
```

```java
public final class ContactList<Contact> extends JavaScriptObject {

    protected ContactList() { }
    public final native String getErrorText() /*-{ return this.error_text; }-*/;
    public final native JsArray<Contact> getContacts() /*-{ return this.contact_list; }-*/;
}
```

```java
Json.get("http://www.mywebsite.com/contacts/list", "date=2013-07-19", new AsyncCallback<ContactList<Contact>>() {

    @Override
    public void onFailure(Throwable caught) {
        // Process errors here
    }

    @Override
    public void onSuccess(ContactList<Contact> contacts) {
        // Do whatever you need to do with the contact list here
    }
});
```

Example : How to call an external webservice using JSONP ?
==========================================================

```java
Jsonp.get("http://www.mywebsite.com/contacts/list?date=2013-07-19", new AsyncCallback<ContactList<Contact>>() {

    @Override
    public void onFailure(Throwable caught) {
        // Process errors here
    }

    @Override
    public void onSuccess(ContactList<Contact> contacts) {
        // Do whatever you need to do with the contact list here
    }
});
```