/*
 * ************************************************************
 * 文件：_20_匹配有效括号.java
 * 模块：MyApplication.app
 * 项目：MyApplication
 * 当前修改时间：2020年12月19日 15:03:06
 * 上次修改时间：2020年12月19日 15:03:06
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.leetcode.栈;

import java.util.HashMap;
import java.util.Stack;

public class _20_匹配有效括号 {

    public boolean isValid1(String string) {
        Stack<Character> stack = new Stack<>();
//        String[] strings = {};
//        int length = strings.length;
        int len = string.length();
        for (int i = 0; i< len;i++) {
            char c = string.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                //绝对是右括号
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '{' && c != '}') return false;
                if (left == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
        /*
        思路：
        如果遇到的是左括号，则直接入栈，当遇到右括号，出栈
         */

    }

    //优化

    private static HashMap<Character, Character> map = new HashMap<>();

    //静态方法的初始化；
    static {
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
    }

    public _20_匹配有效括号() {

    }

    public boolean isValid2(String string) {
        Stack<Character> stack = new Stack<>();
        int len = string.length();
        for (int i = 0; i< len;i++) {
            char c = string.charAt(i);
            if (map.containsKey(c)) { //判断左括号
                stack.push(c);
            } else {
                //绝对是右括号
                if (stack.isEmpty()) return false;
                if (c != map.get(stack.pop())) return false;
            }
        }
        return stack.isEmpty();
        /*
        思路：
        如果遇到的是左括号，则直接入栈，当遇到右括号，出栈
         */

    }
}
