package fr.mncc.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import fr.mncc.sandbox.client.assets.AppConstants;
import fr.mncc.sandbox.client.assets.AppResourceBundle;
import fr.mncc.sandbox.client.widgets.button.Button;
import fr.mncc.sandbox.client.widgets.input.Input;
import fr.mncc.sandbox.client.widgets.label.Label;

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
        RootPanel.get().add(new MainLayout());
    }
}
