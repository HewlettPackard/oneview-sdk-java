/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
