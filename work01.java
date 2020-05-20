package cn.tedu.work;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.Iterator;

/**
 * rdd形式直接读入文件并修改
 */
public class work01 {
    public static void main(String[] args) throws AnalysisException {

        SparkSession session = SparkSession.builder().appName("work01").master("local").getOrCreate();
        JavaRDD<String> rdd = session.read().textFile("E:/桌面/任务/任务/zpsjj/jobs3.csv").javaRDD();
        //"未融资","A轮","B轮","C轮","D轮及以上","上市公司","不需要融资"

        final String[] arr = new String[]{"未融资","A轮","B轮","C轮","D轮及以上","上市公司","不需要融资","天使轮","战略投资","其他"};
       // final List<String> list = new ArrayList<String>();
        final String[] tmp = new String[1];
        JavaRDD<String> rdd1 = rdd.flatMap(new FlatMapFunction<String, String>() {

        //                          未融资 A轮 B轮 C轮 D轮及以上 上市公司 不需要融资

            @Override
            public Iterator<String> call(String s) throws Exception {
                for(int i=0;i<arr.length;i++){
                    if(s.contains(arr[i])){
                        tmp[0] =s;
                     //   list.add(s);
                        break;
                    }
                    else {
                        s=s+tmp[0];
                    }
                }
                return Arrays.asList(s).iterator();
            }
        });
//        JavaRDD<String> rdd2 = rdd1.flatMap(new FlatMapFunction<String, String>() {
//            @Override
//            public Iterator<String> call(String s) throws Exception {
//                for (String s1 : list) {
//                    if (s == s1) {
//                        s.replace(s, "");
//                    }
//                }
//                return Arrays.asList(s).iterator();
//            }
//        });
//        JavaRDD<String> distinct = rdd2.distinct();
//        JavaRDD<String> rdd3 = distinct.flatMap(new FlatMapFunction<String, String>() {
//            int j = 0;
//
//            @Override
//            public Iterator<String> call(String s) throws Exception {
//                j++;
//                String[] strs = s.split(",");
//                for (String s1 : strs) {
//                    s1.trim();
//                }
//                return Arrays.asList(j + "    " + s).iterator();
//            }
//
//        });
//
        rdd1.saveAsTextFile("E:/桌面/test03");

   }
}

