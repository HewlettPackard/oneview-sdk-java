/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil
{
    private static final String SLASH = "\\";
    private static final String REPLACEDWITH = "";

    /*
     * 
     */
    public static String convertIntToString(int number)
    {
        return Integer.toString(number);
    }

    /*
     * 
     */
    public String replaceQuotesWithEmpty(final String inStr)
    {
        System.out.println("StringUtil - input string : " + inStr);
        String newStr = inStr.replaceAll(SLASH, REPLACEDWITH);
        String pattern = "^\"|\"$";
        String returnStr = inStr.replaceAll(pattern, "");
        System.out.println("StringUtil - replaced string : " + returnStr);
        return returnStr;
    }

    public String replaceQuotesAndBackSlash(final String inStr)
    {

        String SLASHQ = "\\\\{1}?\"";
        String REPLACEDWITH = "";
        String pattern = "^\"|\"$";
        String SLASHN = "\\\\n";
        String QUOTE = "\"";
        String returnStr = inStr.replaceAll(SLASHN, " ");
        returnStr = returnStr.replaceAll(SLASHQ, QUOTE);
        returnStr = returnStr.replaceAll(pattern, REPLACEDWITH);

        return returnStr;
    }

    public String replaceQuotesBackSlashWithQuote(final String inStr)
    {

        String SLASHQ = "\\\\{1}?\"";
        String QUOTE = "\"";

        String returnStr = inStr.replaceAll(SLASHQ, QUOTE);
        System.out.println("StringUtil - replaced string : " + returnStr);
        return returnStr;
    }

}
