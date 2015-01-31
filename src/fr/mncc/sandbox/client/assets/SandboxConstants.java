/**
 * Copyright (c) 2015 MNCC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author http://www.mncc.fr
 */
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
public interface SandboxConstants extends Constants {

    public static final SandboxConstants INSTANCE = GWT.create(SandboxConstants.class);

    public String landingPageTitle();

    public String landingPageDescription ();

    public String sideMenuTitle();

    public String sideMenuDescription();

    public String menuCompany();

    public String menuHome();

    public String menuAbout();

    public String menuServices();

    public String menuContact();

    public String pageTitle();

    public String pageSubtitle();

    public String howToUseThisLayout();

    public String nowLetsSpeakSomeLatin();

    public String tryResizingYourBrowser();

    public String instructions();

    public String loremIpsum();

    public String phaellusEgetEnim();
}
