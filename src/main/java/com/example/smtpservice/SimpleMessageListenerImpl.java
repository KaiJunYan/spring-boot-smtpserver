package com.example.smtpservice;

import com.example.eml.EmlUtils;
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
        //查看inputstream 类型
        //inputstream 类型:org.subethamail.smtp.io.ReceivedHeaderStream
        System.out.println(" inputstream 类型:" + data.getClass().getName());


        //封装到model    EmlUtils.parseInputStream(data); 前操作InputStream会导致无法保存附件
//        EmailModel model = new EmailModel();
//        model.setFrom(from);
//        model.setTo(recipient);
//        String mailContent = convertStreamToString(data);
//        model.setSubject(getSubjectFromStr(mailContent));
//        model.setEmailStr(mailContent);
//        System.out.println("=========== 邮件内容 ==========");
//        System.out.println(model);

        //保存邮件附件
        try {
            EmlUtils.parseInputStream(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}