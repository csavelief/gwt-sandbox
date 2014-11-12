package fr.mncc.sandbox.client.assets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface AppResourceBundle extends ClientBundle {

    public static final AppResourceBundle INSTANCE =  GWT.create(AppResourceBundle.class);

    @Source("styles/app.css")
    AppCss css();

    @Source("images/1x1-pixel.png")
    ImageResource pixel();

    @Source("files/licence.txt")
    TextResource licence();
}
