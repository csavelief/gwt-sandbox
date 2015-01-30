package fr.mncc.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import fr.mncc.sandbox.client.assets.SandboxResourceBundle;
import fr.mncc.sandbox.client.layouts.sidemenu.SideMenuLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class sandbox implements EntryPoint {

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {

        // Layout document's body
        RootPanel.get().add(new SideMenuLayout());
    }
}
