package com.arcsoft.docserver.aqapserver.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xqh3622
 */
public class ChineseAndEnglish {
    // GENERAL_PUNCTUATION 判断中文的"号

    // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号

    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
    /**
     * 是否是中文
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {

        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;

        }

        return false;
    }
    /**
     * 是否是英文
     * @param charaString
     * @return
     */
    public static boolean isEnglish(String charaString){
        return charaString.matches("^[a-zA-Z]*");
    }
    public static boolean isChinese(String str){

        String regEx = "[\\u4e00-\\u9fa5]+";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        if(m.find()) {
            return true;
        } else {
            return false;
        }

    }
    public static void main(String[] args) {

        System.out.println(isChinese('员'));

        System.out.println(isChinese('s'));

        System.out.println(isEnglish("程序员之家"));

        System.out.println(isEnglish("robert"));

        System.out.println(isChinese(" 程序员论坛"));
        System.out.println();
    }

    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<>();

        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
                if(map.containsKey(s.charAt(0))) {
                    return false;
                }
        for(int i =0 ;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
               if(stack.peek().equals(map.get(s.charAt(i)))){
                   stack.pop();
               } else{
                   return  false;
               }
            }else{
                stack.push(s.charAt(i));
            }
       }
        return true;
    }
}