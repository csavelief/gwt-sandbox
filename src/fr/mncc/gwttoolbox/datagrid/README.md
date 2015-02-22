gwt-datagrid
============

NOT FOR PRODUCTION !

Thin wrapper around the GWT DataGrid API in order to increase our productivity.

Dependencies
============

None.

Example
=======

```java
public class ContactDTO extends Entity {

    public ContactDTO() { super("ContactDTO"); }
    public String getName() { return getAsString("name"); }
    public void setName(String name) { put("name", name); }
    public int getAge() { return getAsInt("age"); }
    public void setAge(int age) { put("age", age); }
    public String getProfilePictureAsImageBase64() { return getAsString("imageBase64"); }
    public void setProfilePictureAsImageBase64(String imageBase64) { put("imageBase64", imageBase64); }
}
```

```java
public class ContactList extends Grid<ContactDTO> {

    class ProfilePictureCell extends ImageColumn<ContactDTO> {

        public ProfilePictureCell() { super(null /* constraint on height only */, "60px"); }
        @Override public String getValue(ContactDTO contact) { return "data:" + contact.getProfilePictureAsImageBase64(); }
    }

    class NameCell extends TextColumnReadOnly<ContactDTO> {

        public NameCell() {}
        @Override public String getColumnHeader() { return "Name"; }
        @Override public String getValue(ContactDTO contact) { return contact.getName(); }
    }

    class AgeCell extends TextColumnReadOnly<ContactDTO> {

        public AgeCell() {}
        @Override public String getColumnHeader() { return "Age"; }
        @Override public String getValue(ContactDTO contact) { return "" + contact.getAge(); }
    }

    public ContactList() {
        super(500 /* paging by group of 500 rows */);
        addColumn((fr.mncc.gwttoolbox.datagrid.client.columns.Column)(new ProfilePictureCell()));
        addColumn((fr.mncc.gwttoolbox.datagrid.client.columns.Column)(new NameCell()));
        addColumn((fr.mncc.gwttoolbox.datagrid.client.columns.Column)(new AgeCell()));
        addAsyncDataProvider(); // Tell the grid that the list of ContactDTO comes from a webservice
    }

    @Override
    protected void onRangeChanged(int start, int length) {
        // Here, call your webservice to get a list of ContactDTO.
        // Use start and length for paging the result list.
        // Then call Grid.update() to display the list of ContactDTO.
    }
}
```