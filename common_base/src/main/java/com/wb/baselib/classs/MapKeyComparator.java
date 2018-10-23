package com.wb.baselib.classs;

import java.util.Comparator;

/**
 * map
 */
class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}