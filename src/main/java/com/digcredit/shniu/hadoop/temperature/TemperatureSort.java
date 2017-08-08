package com.digcredit.shniu.hadoop.temperature;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class TemperatureSort {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class TemperatureSortMapper
            extends Mapper<LongWritable, Text, KeyPair, Text> {

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            // 处理读取的一行数据
            String line = value.toString();

            // 每一行数据由制表符分割，所以需要分割
            String[] yearAndTemp = line.split("\t");

            // 只处理符合条件的数据
            if (yearAndTemp.length == 2) {

                try {
                    // 解析年份
                    Date date = sdf.parse(yearAndTemp[0]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int year = calendar.get(1);

                    // 解析温度
                    String t = yearAndTemp[1];
                    t = t.substring(0, t.indexOf("C"));

                    // 创建复合对象键值keyPair
                    KeyPair keyPair = new KeyPair();
                    keyPair.setYear(year);
                    keyPair.setTemperature(Integer.parseInt(t));

                    context.write(keyPair, new Text(t));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class TemperatureSortReducer extends Reducer<KeyPair, Text, KeyPair, Text> {

        public void reduce(KeyPair keyPair, Iterable<Text> value, Context context)
                throws IOException, InterruptedException {
            // 直接输出
            for (Text text : value) {
                context.write(keyPair, text);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        // 起个Job
        Job job = Job.getInstance(conf, "TemperatureSort");
        job.setJarByClass(TemperatureSort.class);
        job.setMapperClass(TemperatureSortMapper.class);
        job.setReducerClass(TemperatureSortReducer.class);
        job.setMapOutputKeyClass(KeyPair.class);
        job.setMapOutputValueClass(Text.class);

        job.setNumReduceTasks(3);
        job.setPartitionerClass(Partition.class);
        job.setSortComparatorClass(Sort.class);
        job.setGroupingComparatorClass(Group.class);

        // 输入输出文件路径
        FileInputFormat.addInputPath(job, new Path("/user/docker/yearTemp/input"));
        FileOutputFormat.setOutputPath(job, new Path("/user/docker/yearTemp/output"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
