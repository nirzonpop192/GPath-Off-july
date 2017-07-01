package com.siddiquinoor.restclient.parse;

/**
 * Created by pop on 3/2/2017.
 */

public class Parse {

    /**
     * this method convert the string into long
     * @param string string value
     * @return converted  long
     */
    public static long StringToLongNullCheck(String string) {

        long lgMaxValue = -1;
        if (string != null) {
            if (string.equals("null") || string.length() == 0) {
                lgMaxValue = 0;
            } else {
                lgMaxValue = (long) Double.parseDouble(string);
            }
        }

        return lgMaxValue;
    }

    /**
     * this method convert the string into double
     * @param string string value
     * @return converted  double
     */

    public static double StringToDoubleNullCheck(String string) {

        double lgMaxValue = -1;
        if (string != null) {
            if (string.equals("null") || string.length() == 0) {
                lgMaxValue = 0;
            } else {
                lgMaxValue = Double.parseDouble(string);
            }
        }

        return lgMaxValue;
    }

    public static String removeNewLineFromImage(String str) {
        return str.replace("\\n", "").replace("\\r", "");
    }

    public static String removeSlashFromImage(String str) {
        return str.replace("\\", "");

    }
}
