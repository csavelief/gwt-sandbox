/**
 * Copyright (c) 2011 MNCC
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
package fr.mncc.gwttoolbox.router.client;

import com.google.gwt.core.client.GWT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Route {

    private String name_;

    private Route() {

    }

    public Route(String name) {
        if (name == null || name.isEmpty()) {
            GWT.log("name should be neither null nor an empty string");
            name_ = "";
        } else if (!name.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
            GWT.log("name must match ^[a-zA-Z][a-zA-Z0-9]*$");
            name_ = "";
        } else {
            name_ = name;
        }
    }

    public String getName() {
        return name_;
    }

    public void enter(String arguments) {

    }

    public void leave() {

    }

    /**
     * Parser for arguments
     *
     * @param arguments must be either "?key1=val1&key2=val2&…&keyn=valn" or "/val1/val2/…/valn"
     * @param template  must be either "?key1=&key2=&...&keyn=" or "/key1/key2/.../keyn"
     * @return key-value map
     */
    protected Map<String, String> parseArguments(String arguments, String template) {

        if (template == null) {
            GWT.log("template must not be null");
            return new HashMap<String, String>();
        }

        if (arguments == null) {
            GWT.log("arguments must not be null");
            return new HashMap<String, String>();
        }

        template = template.trim();
        arguments = arguments.trim();

        if (template.isEmpty() && arguments.isEmpty()) {
            return new HashMap<String, String>();
        } else if (template.startsWith("?") && arguments.startsWith("?")) {
            return parseArgumentsWithQuestionMark(arguments.substring(1).trim(),
                template.substring(1)
                    .trim());
        } else if (template.startsWith("/") && arguments.startsWith("/")) {
            return parseArgumentsWithSlash(arguments.substring(1).trim(),
                template.substring(1).trim());
        }

        GWT.log("unknown template/arguments format");
        return new HashMap<String, String>();
    }

    /**
     * Parser for arguments
     *
     * @param arguments must be "key1=val1&key2=val2&…&keyn=valn"
     * @param template  must be "key1=&key2=&...&keyn="
     * @return key-value map
     */
    protected Map<String, String> parseArgumentsWithQuestionMark(String arguments,
        String template) {

        String[] argumentsTokens = arguments.split("&");
        if (argumentsTokens == null) {
            GWT.log(logAnErrorOccurred(arguments));
            return new HashMap<String, String>();
        }

        String[] templateTokens = template.split("&");
        if (templateTokens == null) {
            GWT.log(logAnErrorOccurred(template));
            return new HashMap<String, String>();
        }

        argumentsTokens = removeNullOrEmpty(argumentsTokens);
        templateTokens = removeNullOrEmpty(templateTokens);

        if (templateTokens.length != argumentsTokens.length) {
            GWT.log(logSizeMismatch(arguments, template));
            return new HashMap<String, String>();
        }

        final Map<String, String> map = new HashMap<String, String>();
        for (String token : argumentsTokens) {

            if (token == null) {
                GWT.log(logNullToken(arguments));
                continue;
            }

            token = token.trim();
            final String[] tokenTokens = token.split("=");
            if (tokenTokens.length != 2) {
                GWT.log(logAnErrorOccurred(token));
                return new HashMap<String, String>();
            }

            final String key = tokenTokens[0] == null ? "" : tokenTokens[0].trim();
            if (key.isEmpty()) {
                GWT.log(logMissingKey(arguments));
                return new HashMap<String, String>();
            }

            final String value = tokenTokens[1] == null ? "" : tokenTokens[1].trim();
            if (value.isEmpty()) {
                GWT.log(logMissingValue(arguments));
            }

            map.put(key, value);
        }

        for (String token : templateTokens) {

            if (token == null) {
                GWT.log(logNullToken(template));
                continue;
            }

            token = token.trim();
            final String[] tokenTokens = token.trim().split("=");
            if (tokenTokens.length != 1) {
                GWT.log(logAnErrorOccurred(token));
                return new HashMap<String, String>();
            }

            final String key = tokenTokens[0] == null ? "" : tokenTokens[0].trim();
            if (key.isEmpty()) {
                GWT.log(logMissingKey(template));
                return new HashMap<String, String>();
            }

            if (!map.containsKey(key)) {
                GWT.log(logMissingKey(arguments));
                return new HashMap<String, String>();
            }
        }
        return map;
    }

    /**
     * Parser for arguments
     *
     * @param arguments must be "val1/val2/…/valn"
     * @param template  must be "key1/key2/.../keyn"
     * @return key-value map
     */
    protected Map<String, String> parseArgumentsWithSlash(String arguments, String template) {

        String[] argumentsTokens = arguments.split("/");
        if (argumentsTokens == null) {
            GWT.log(logAnErrorOccurred(arguments));
            return new HashMap<String, String>();
        }

        String[] templateTokens = template.split("/");
        if (templateTokens == null) {
            GWT.log(logAnErrorOccurred(template));
            return new HashMap<String, String>();
        }

        argumentsTokens = removeNullOrEmpty(argumentsTokens);
        templateTokens = removeNullOrEmpty(templateTokens);

        if (templateTokens.length != argumentsTokens.length) {
            GWT.log(logSizeMismatch(arguments, template));
            return new HashMap<String, String>();
        }

        final Map<String, String> map = new HashMap<String, String>();
        for (int i = 0, len = templateTokens.length; i < len; i++) {

            final String key = templateTokens[i] == null ? "" : templateTokens[i].trim();
            if (key.isEmpty()) {
                GWT.log(logMissingKey(template));
                return new HashMap<String, String>();
            }

            final String value = argumentsTokens[i] == null ? "" : argumentsTokens[i].trim();
            if (value.isEmpty()) {
                GWT.log(logMissingValue(arguments));
                return new HashMap<String, String>();
            }

            map.put(key, value);
        }
        return map;
    }

    protected String[] removeNullOrEmpty(String[] array) {
        ArrayList<String> removed = new ArrayList<String>();
        for (String str : array) {
            if (str != null && !str.trim().isEmpty())
                removed.add(str);
        }
        return removed.toArray(new String[0]);
    }

    private String logAnErrorOccurred(String string) {
        return "an error occurred while parsing \"" + string + "\"";
    }

    private String logMissingKey(String string) {
        return "missing key found while parsing \"" + string + "\"";
    }

    private String logMissingValue(String string) {
        return "missing value found while parsing \"" + string + "\"";
    }

    private String logNullToken(String string) {
        return "null token found while parsing \"" + string + "\"";
    }

    private String logSizeMismatch(String arguments, String template) {
        return "the number of arguments do not match between arguments \"" + arguments
            + "\" and template \"" + template + "\"";
    }
}
