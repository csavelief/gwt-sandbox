package fr.mncc.sandbox.client.assets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.LocalizableResource;

/*
 * Application-wide constants
 *      Extend Constants to create a collection of constant values of a variety of types that can be accessed by calling
 *      methods (called constant accessors) on an interface.
 *
 * Method Annotations
 *
 * @DefaultBooleanValue(boolean val)
 *      Sets the default value for a method which returns a boolean.
 * @DefaultDoubleValue(double val)
 *      Sets the default value for a method which returns a double.
 * @DefaultFloatValue(float val)
 *      Sets the default value for a method which returns a float.
 * @DefaultIntValue(int val)
 *      Sets the default value for a method which returns a int.
 * @DefaultStringArrayValue({String str, ...})
 *      Sets the default value for a method which returns a String array.
 * @DefaultStringMapValue({String key, String value, ...})
 *      Sets the default value for a method which returns a Map<String,String> or a raw map (which will still be a
 *      String=>String map). The number of supplied values must be even, and the first entry of each pair is the key and
 *      the second is the value.
 * @DefaultStringValue(String str)
 *      Sets the default value for a method which returns a String.
 */
@LocalizableResource.DefaultLocale("en")
public interface AppConstants extends Constants {

    public static final AppConstants INSTANCE = GWT.create(AppConstants.class);

    public String menuCompany();

    public String menuHome();

    public String menuAbout();

    public String menuServices();

    public String menuContact();

    public String pageTitle();

    public String pageSubtitle();

    public String howToUseThisLayout();

    public String nowLetsSpeakSomeLatin();

    public String loremIpsum();
}
