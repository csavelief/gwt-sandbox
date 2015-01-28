package fr.mncc.sandbox.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.mncc.sandbox.client.assets.AppResourceBundle;

public class MainLayout extends Composite {

    @UiTemplate("MainLayout.ui.xml")
    interface MyUiBinder extends UiBinder<Widget, MainLayout> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField HTMLPanel layout;
    @UiField DivElement menu;
    @UiField AnchorElement menuLink;

    public MainLayout() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        layout.getElement().setId("app-layout");
        menu.setId("app-menu");

        AppResourceBundle.INSTANCE.appCss().ensureInjected();

        DOM.setEventListener(menuLink, new EventListener() {

            @Override public void onBrowserEvent(Event event) {
                if (event.getTypeInt() == Event.ONCLICK) {
                    final String active = "active";
                    toggleClass(layout.getElement(), active);
                    toggleClass(menu, active);
                    toggleClass(menuLink, active);
                }
            }
        });
        DOM.sinkEvents(menuLink, Event.ONCLICK);
    }

    @Override
    protected void onUnload() {
        DOM.setEventListener(menuLink, null);
        super.onUnload();
    }

    private void toggleClass(Element element, String className) {
        StringBuilder classesOut = new StringBuilder();
        String[] classesIn = element.getClassName().split("/\\s+/");
        int length = classesIn.length;
        boolean classNameFound = false;

        for (int i=0; i < length; i++) {
            if (classesIn[i].equals(className)) {
                classNameFound = true;
                break;
            }
            classesOut.append(classesIn[i]);
            classesOut.append(" ");
        }

        // The className is not found
        if (!classNameFound) {
            classesOut.append(className);
        }

        element.setClassName(classesOut.toString());
    }
}
