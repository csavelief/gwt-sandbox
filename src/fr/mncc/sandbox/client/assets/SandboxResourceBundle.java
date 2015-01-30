package fr.mncc.sandbox.client.assets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import fr.mncc.gwttoolbox.client.assets.GwtToolboxResourceBundle;

public interface SandboxResourceBundle extends GwtToolboxResourceBundle {

    public static final SandboxResourceBundle INSTANCE = GWT.create(SandboxResourceBundle.class);

    @Source({"styles/SideMenuLayout.gss",
        "../../../gwttoolbox/client/assets/styles/pure/0.5.0/pure.gss"}) SideMenuLayoutCss SideMenuLayoutCss();

    @Source("images/9069037713_1752f5daeb.jpg") ImageResource img1();

    @Source("images/9069585985_80da8db54f.jpg") ImageResource img2();

    @Source("images/9086701425_fda3024927.jpg") ImageResource img3();

    @Source("images/9121446012_c1640e42d0.jpg") ImageResource img4();

    @Source("files/LICENSE.md") TextResource license();

    @Source("files/README.md") TextResource readme();
}
