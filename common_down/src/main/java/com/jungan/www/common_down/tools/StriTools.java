package com.jungan.www.common_down.tools;

import java.util.StringTokenizer;

public class StriTools {
    /**
     * 将逗号分隔的字符串 转换成数组
     * @param str
     * @return
     */
    public static String[] convertStrToArray(String str){
        StringTokenizer st = new StringTokenizer(str,",");//把","作为分割标志，然后把分割好的字符赋予StringTokenizer对象。
        String[] strArray = new String[st.countTokens()];//通过StringTokenizer 类的countTokens方法计算在生成异常之前可以调用此 tokenizer 的 nextToken 方法的次数。
        int i=0;
        while(st.hasMoreTokens()){//看看此 tokenizer 的字符串中是否还有更多的可用标记。
            strArray[i++] = st.nextToken().trim();//返回此 string tokenizer 的下一个标记。
        }
        return strArray;
    }
}
