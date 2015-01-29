package fr.mncc.sandbox.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.mncc.sandbox.client.assets.AppResourceBundle;

public class SideMenuLayout extends Composite {

    @UiTemplate("SideMenuLayout.ui.xml")
    interface MyUiBinder extends UiBinder<Widget, SideMenuLayout> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField HTMLPanel layout;
    @UiField DivElement menu;
    @UiField AnchorElement menuLink;

    public SideMenuLayout() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        AppResourceBundle.INSTANCE.appCss().ensureInjected();

        DOM.setEventListener(menuLink, new EventListener() {

            @Override public void onBrowserEvent(Event event) {
                if (event.getTypeInt() == Event.ONCLICK) {
                    toggleClass(layout.getElement(), AppResourceBundle.INSTANCE.appCss().active());
                    toggleClass(menu, AppResourceBundle.INSTANCE.appCss().active());
                    toggleClass(menuLink, AppResourceBundle.INSTANCE.appCss().active());
                    event.preventDefault();
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
        String elementClasses = element.getClassName();
        if (elementClasses == null) {
            element.setClassName(className);
        }
        else if (elementClasses.contains(className)) {
            element.setClassName(elementClasses.replace(className, ""));
        }
        else {
            element.setClassName(elementClasses + " " + className);
        }
    }
}
