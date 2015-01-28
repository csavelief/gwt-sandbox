package fr.mncc.sandbox.client.assets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocalizableResource;
import com.google.gwt.i18n.client.Messages;

/**
 * Application-wide messages
 *      Extend Messages to create a collection of formatted messages that can accept parameters.
 *
 * Method Annotations
 *
 * @DefaultMessage(String message)
 *      Specifies the message string to be used for the default locale for this method, with all of the options above.
 *      If an @AlternateMessage annotation is present, this is the default text used when more specific forms do not
 *      apply — for count messages in English, this would be the plural form instead of the singular form.
 * @AlternateMessage({String form, String message, ...})
 *      Specifies the text for alternate forms of the message. The supplied array of strings must be in pairs, with the
 *      first entry the name of an alternate form appropriate for the default locale, and the second being the message
 *      to use for that form. See the Plural Forms and Select Forms examples below.
 *
 * Parameter Annotations
 *
 * @Example(String example)
 *      An example for this variable. Many translation tools will show this to the translator instead of the placeholder
 *      — i.e., Hello {0} with @Example("John") will show as Hello John with "John" highlighted to indicate it should
 *      not be translated.
 * @Optional
 *      Indicates that this parameter need not be present in all translations. If this annotation is not supplied, it is
 *      a compile-time error if the translated string being compiled does not include the parameter.
 * @PluralCount
 *      Indicates that this parameter is used to select which form of text to use (ie, 1 widget vs. 2 widgets).
 *      The argument annotated must be int, short, an array, or a list (in the latter cases the size of the list is used
 *      as the count).
 */
@LocalizableResource.DefaultLocale("en")
public interface AppMessages extends Messages {

    public static final AppMessages INSTANCE = GWT.create(AppMessages.class);

    @DefaultMessage("Bonjour {0}!")
    public String helloWorld(String name);
}
