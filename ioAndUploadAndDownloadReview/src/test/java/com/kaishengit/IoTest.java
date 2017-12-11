package com.kaishengit;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

/**
 * @author Tonglin
 */
public class IoTest {

    @Test
    public void copyImage() {

        try {
            InputStream inputStream = new FileInputStream("e:/temp/1.jpg");
            OutputStream outputStream = new FileOutputStream("e:/upload/3.jpg");

            /*byte[] bytes = IOUtils.toByteArray(inputStream);

            outputStream.write(bytes);*/

            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            byte[] bytes = new byte[10];

            while (bufferedInputStream.read(bytes) != -1) {

                outputStream.write(bytes);
            }



            inputStream.close();
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void copyText() {

        try {
            FileReader fileReader = new FileReader(new File("e:/temp/1.txt"));
            FileWriter fileWriter = new FileWriter("e:/upload/2.txt");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder stringBuilder = new StringBuilder();
            String str;

            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }

            fileWriter.write(stringBuilder.toString());

            fileReader.close();
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readTextFromFile() {

        File file = new File("e:/temp","1.txt");
        System.out.println(file.getName());


        try {

            String str = "hello,inputStream1";

            char[] chars1 = str.toCharArray();

            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(chars1);

            fileWriter.flush();
            fileWriter.close();


            FileReader fileReader = new FileReader(file);

            char[] chars = new char[100];

            while (fileReader.read(chars) != -1) {
                System.out.println(chars);
            }

            fileReader.close();


            /*InputStream inputStream = new FileInputStream(file);


            StringBuilder stringBuilder = new StringBuilder();
            int data;
            while ((data = inputStream.read()) != -1) {

                stringBuilder.append((char) data);
            }

            System.out.println(new String(stringBuilder.toString().getBytes("UTF-8")));*/
          /*  FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[10];
            fileOutputStream.write(bytes,0,bytes.length);
            System.out.println(bytes);*/



            //inputStream.close();
           /* fileOutputStream.flush();
            fileOutputStream.close();*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
