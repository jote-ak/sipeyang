/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sprimaudi.zkspring.filter;

import java.util.Date;

/**
 * @author User
 */
public class FilterUtil {

    private static String KNOWN_FILTER_ITEM_TYPE = "teks-tanggal-angka-referensi";
    private static String KNOWN_FILTER_OPERATION = "like-in-lt-lte-gt-gte-eq-equalsIgnoreCase-"
            + "contains-containsIgnoreCase-isNull-isNotNull-between-startsWith-startsWithIgnoreCase-endsWith-endsWithIgnoreCase";
    //========================
    //Filter Type
    //========================
    public static String FTYPE_TEXT = "teks";
    public static String FTYPE_TANGGAL = "tanggal-angka-referensi";
    public static String FTYPE_ANGKA = "angka";
    public static String FTYPE_REFERENSI = "referensi";
    //========================
    //Operators
    //========================
    public static String FOPR_LIKE = "like";
    public static String FOPR_CONTAINS = "contains";
    public static String FOPR_CONTAINSIGNORECASE = "containsIgnoreCase";
    public static String FOPR_STARTSWITH = "startsWith";
    public static String FOPR_STARTSWITHIGNORECASE = "startsWithIgnoreCase";
    public static String FOPR_ENDSWITH = "endsWith";
    public static String FOPR_ENDSWITHIGNORECASE = "endsWithIgnoreCase";
    public static String FOPR_EQ = "eq";
    public static String FOPR_EQUALSIGNORECASE = "equalsIgnoreCase";
    public static String FOPR_LT = "lt";
    public static String FOPR_LTE = "lte";
    public static String FOPR_GT = "gt";
    public static String FOPR_GTE = "gte";
    public static String FOPR_BETWEEN = "between";
    public static String FOPR_IN = "in";
    public static String FOPR_ISNULL = "isNull";
    public static String FOPR_ISNOTNULL = "isNotNull";

    public static Fentry newItem(String alias, String stringValue, Date dateValue, Number numberValue, boolean range, boolean endRange, String type) {
        Fentry fi = new Fentry(alias, stringValue, dateValue, numberValue, range, endRange, type);
        return fi;
    }

    public static boolean validateItemType(String type) {
        return KNOWN_FILTER_ITEM_TYPE.contains(type);
    }
}
