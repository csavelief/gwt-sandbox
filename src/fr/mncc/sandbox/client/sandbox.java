package fr.mncc.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import fr.mncc.sandbox.client.assets.AppResourceBundle;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class sandbox implements EntryPoint {

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {

        // Load application-wide css
        AppResourceBundle.INSTANCE.pureCss().ensureInjected();

        // Add widget to document's body for display
        RootPanel.get().add(new SideMenuLayout());
    }
}
