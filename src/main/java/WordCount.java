/*
 * Created by gpf on 17-5-31.
 */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;


public class WordCount {

    static SparkConf conf = new SparkConf().setAppName("word count").setMaster("spark://mynamenode:7077");

    public static void main(String[] args) {
        String logFile = args[0];

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {return s.contains("a"); }
        }).count();
        long numBs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {return s.contains("b"); }
        }).count();

        System.out.println("Lines with a: " +numAs + ", lines with b: " + numBs);

        sc.stop();
    }
}
