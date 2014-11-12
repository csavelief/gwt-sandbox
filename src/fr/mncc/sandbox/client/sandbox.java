package fr.mncc.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import fr.mncc.sandbox.client.assets.AppResourceBundle;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class sandbox implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // Load css
        AppResourceBundle.INSTANCE.css().ensureInjected();

        // Test widgets
        RootPanel.get().add(new fr.mncc.sandbox.client.widgets.button.Button());
    }
}
