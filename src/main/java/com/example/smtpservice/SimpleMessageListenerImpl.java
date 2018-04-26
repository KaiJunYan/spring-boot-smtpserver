package com.example.smtpservice;

import org.subethamail.smtp.helper.SimpleMessageListener;

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleMessageListenerImpl implements SimpleMessageListener {

    private static final Pattern SUBJECT_PATTERN = Pattern.compile("Subject: (.*?)-");
    private static final String LINE_SEPARATOR = "   ";//System.getProperty("line.separator");
    public static final String UTF8 = "UTF-8";

    public SimpleMessageListenerImpl() {
    }

    @Override
    public boolean accept(String from, String recipient) {
        return true;
    }

    @Override
    public void deliver(String from, String recipient, InputStream data) {

        EmailModel model = new EmailModel();
        model.setFrom(from);
        model.setTo(recipient);
        String mailContent = convertStreamToString(data);
        model.setSubject(getSubjectFromStr(mailContent));
        model.setEmailStr(mailContent);
        System.out.println("=========== 邮件内容 ==========");
        System.out.println(model);
        //TODO 传递到其他地方

    }

    private String convertStreamToString(InputStream is) {
        final long lineNbToStartCopy = 4; // Do not copy the first 4 lines (received part)
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(UTF8)));
        StringBuilder sb = new StringBuilder();

        String line;
        long lineNb = 0;
        try {
            while ((line = reader.readLine()) != null) {
                if (++lineNb > lineNbToStartCopy) {
                    sb.append(line).append(LINE_SEPARATOR);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getSubjectFromStr(String data) {
        try {
            BufferedReader reader = new BufferedReader(new StringReader(data));

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = SUBJECT_PATTERN.matcher(line);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

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
        while(m.find()) {
//            m.group(0) 是包含那两个字符，m.group(1)  不包含那两个字符。
            System.out.println(m.group(1));

        }

    }
}