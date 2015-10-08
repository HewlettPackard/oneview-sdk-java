/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.util;

public class StringUtil {
    // private static final String SLASH = "\\";
    // private static final String REPLACEDWITH = "";

    public static String convertIntToString(final int number) {
        return Integer.toString(number);
    }

    public static String replaceQuotesWithEmpty(final String inStr) {

        // final String newStr = inStr.replaceAll(SLASH, REPLACEDWITH);
        final String pattern = "^\"|\"$";
        final String returnStr = inStr.replaceAll(pattern, "");
        return returnStr;
    }

    public static String replaceQuotesAndBackSlash(final String inStr) {

        final String SLASHQ = "\\\\{1}?\"";
        final String REPLACEDWITH = "";
        final String pattern = "^\"|\"$";
        final String SLASHN = "\\\\n";
        final String QUOTE = "\"";
        String returnStr = inStr.replaceAll(SLASHN, " ");
        returnStr = returnStr.replaceAll(SLASHQ, QUOTE);
        returnStr = returnStr.replaceAll(pattern, REPLACEDWITH);
        return returnStr;
    }

    public static String replaceQuotesBackSlashWithQuote(final String inStr) {

        final String SLASHQ = "\\\\{1}?\"";
        final String QUOTE = "\"";
        final String returnStr = inStr.replaceAll(SLASHQ, QUOTE);
        return returnStr;
    }

}
