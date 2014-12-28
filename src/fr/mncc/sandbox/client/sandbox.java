package fr.mncc.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import fr.mncc.sandbox.client.assets.AppConstants;
import fr.mncc.sandbox.client.assets.AppResourceBundle;
import fr.mncc.sandbox.client.widgets.button.Button;
import fr.mncc.sandbox.client.widgets.input.Input;
import fr.mncc.sandbox.client.widgets.label.Label;

import static fr.mncc.sandbox.client.widgets.button.Button.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class sandbox implements EntryPoint {

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {

        // Load css
        AppResourceBundle.INSTANCE.css().ensureInjected();

        // Create an application-wide event bus
        SimpleEventBus eventBus = new SimpleEventBus();

        // Create widgets
        final Input input = new fr.mncc.sandbox.client.widgets.input.Input(eventBus, "demo-input");
        final Button button = new fr.mncc.sandbox.client.widgets.button.Button();
        final Label label = new fr.mncc.sandbox.client.widgets.label.Label("demo-label", "");

        // React to widgets' events

        // Update label on input ONCHANGE event
        eventBus.addHandler(Input.ChangeEvent.TYPE, new Input.ChangeEventHandler() {

            @Override
            public void onChange(String text) {
                if (label != null) {
                    label.setText(text);
                }
            }
        });

        // Display alert on button ONCLICK event
        button.SetOnClickCallback(new Button.SimpleCallback() {

            @Override
            public void onSuccess(Void result) {
                Window.alert(AppConstants.INSTANCE.helloWorld());
            }
        });

        // Add widgets to document's body for display
        HorizontalPanel panel = new HorizontalPanel();
        panel.add(input);
        panel.add(label);
        panel.add(button);

        RootPanel.get().add(panel);
    }
}
