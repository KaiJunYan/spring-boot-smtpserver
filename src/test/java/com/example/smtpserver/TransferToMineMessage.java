package com.example.smtpserver;

import org.apache.commons.mail.util.MimeMessageParser;
import org.apache.commons.mail.util.MimeMessageUtils;

import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Header;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class TransferToMineMessage {

    public static void main(String[] args) {
        File file = new File("src/test/java/com/example/smtpserver/mail.txt");
        InputStream mailFileInputStream = null;
        try {
            //查看inputstream 类型
            System.out.println(" inputstream 类型:" + mailFileInputStream.getClass().getName());

            mailFileInputStream = new FileInputStream(file);
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props);

            //获得MimeMessage对象
//            MimeMessage message = new MimeMessage(session, mailFileInputStream);

            //使用工具类获得MimeMessage对象
            MimeMessage message = MimeMessageUtils.createMimeMessage(session, mailFileInputStream);
            MimeMessageParser parser = new MimeMessageParser(message);
            parser.parse();

            //使用工具类获得附件
            List<DataSource> attachmentList = parser.getAttachmentList();
            for (int i = 0; i < attachmentList.size(); i++) {
                attachmentList.get(i).getInputStream();
                //同下方的bodyPart.getInputStream() 可以直接进行分离，写入到文件中获得附件内容
            }

            //使用工具类保存邮件到eml文件
            File file1 = new File("src/test/java/com/example/smtpserver/test.Eml");
            MimeMessageUtils.writeMimeMessage(message, file1);

            // 邮件Header
            System.out.println("==================邮件标题部分开始=================");
            Enumeration allHeaders = message.getAllHeaders();
            while (allHeaders.hasMoreElements()) {
                Header o = (Header) allHeaders.nextElement();
                System.out.println(o.getName() + ":" + o.getValue());
            }
            System.out.println("==================邮件标题部分结束=================");

            //邮件Content 包含附件和正文
            System.out.println("==================邮件正文、附件部分=================");
            MimeMultipart content = (MimeMultipart) message.getContent();
            System.out.println("==================正文、附件头部===================");
            for (int i = 0; i < content.getCount(); i++) {
                BodyPart bodyPart = content.getBodyPart(i);
                Enumeration headers = bodyPart.getAllHeaders();
                while (headers.hasMoreElements()) {
                    Header o = (Header) headers.nextElement();
                    System.out.println(o.getName() + ":" + o.getValue());
                }
                System.out.println("==================正文、附件内容=================");
                //需要转化成对应的文件
                String fileName = bodyPart.getFileName() == null ? "正文" : bodyPart.getFileName();
                System.out.println("============正在执行正文、附件:" + fileName);
                File fileAttach = new File("src/test/java/com/example/smtpserver/" + fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(fileAttach);
                if (!fileAttach.exists()) {
                    fileAttach.createNewFile();
                }
                InputStream in = bodyPart.getInputStream();
                int data;
                while ((data = in.read()) != -1) {
                    fileOutputStream.write(data);
                }
                in.close();
                fileOutputStream.close();
                System.out.println("==================正文、附件内容结束=================");

                //not work
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bodyPart.getInputStream()));
//                String line = null;
//                while ((line = bufferedReader.readLine()) != null) {
//                    System.out.println(line);
//                    writer.write(line);
//                }
//                writer.flush();
//                writer.close();
//                bufferedReader.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
