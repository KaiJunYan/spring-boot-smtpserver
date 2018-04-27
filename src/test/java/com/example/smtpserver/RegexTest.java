package com.example.smtpserver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        Pattern SUBJECT_PATTERN = Pattern.compile("Subject: (.*?)-");
        String line = "Subject: File attachment-MIME-Version";
        Matcher matcher = SUBJECT_PATTERN.matcher(line);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }

        String filetext = "//@张小名: 25分//@李小花: 43分//@王力: 100分";
        Pattern p = Pattern.compile("\\@(.*?)\\:");//正则表达式，取=和|之间的字符串，不包括=和|
        Matcher m = p.matcher(filetext);
        while (m.find()) {
//            m.group(0) 是包含那两个字符，m.group(1)  不包含那两个字符。
            System.out.println(m.group(1));

        }

    }
}
