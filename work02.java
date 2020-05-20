package cn.tedu.work;

import com.csvreader.CsvReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class work02 {
    public static void main(String[] args) throws IOException {
        File file = new File("E:/桌面/任务/任务/zpsjj/jobs6.csv");
        InputStream in = new FileInputStream(file);
        OutputStream out = new FileOutputStream("E:/桌面/job/job06");
        CsvReader cr = new CsvReader(in, Charset.forName("utf-8"));
        //输出约束信息
        cr.readHeaders();
        int a = 0;
        // System.out.println(cr.getHeader(12)+"第一次");
        //  System.out.println(Arrays.toString(cr.getHeaders())+"第二次");
        //输出一行数据
        while(cr.readRecord()){
        cr.readRecord();
        String rawRecord = cr.getRawRecord();
        int columnCount = cr.getColumnCount();
        //System.out.println(columnCount+"第三次");

        for (int i = 0; i < columnCount; i++) {

            String str = cr.get(i);

            Pattern p = Pattern.compile("\\s+|\t+|\n|\r");
            Matcher m = p.matcher(str);
            String s = m.replaceAll("；");
            byte[] bus = (s+',').getBytes();

            out.write(bus);
            //System.out.println(s+"第"+j+"次");
            }
        out.write("\n".getBytes());
        }
    }
}
