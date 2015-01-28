package fr.mncc.sandbox.client.assets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface AppResourceBundle extends ClientBundle {

    public static final AppResourceBundle INSTANCE =  GWT.create(AppResourceBundle.class);

    @Source("styles/pure/0.5.0/pure.gss")
    PureCss pureCss();

    @Source({"styles/app.gss", "styles/pure/0.5.0/pure.gss"})
    AppCss appCss();

    @Source("images/1x1-pixel.png")
    ImageResource pixel();

    @Source("images/9069037713_1752f5daeb.jpg")
    ImageResource img1();

    @Source("images/9069585985_80da8db54f.jpg")
    ImageResource img2();

    @Source("images/9086701425_fda3024927.jpg")
    ImageResource img3();

    @Source("images/9121446012_c1640e42d0.jpg")
    ImageResource img4();

    @Source("files/LICENSE.md")
    TextResource license();

    @Source("files/README.md")
    TextResource readme();
}
