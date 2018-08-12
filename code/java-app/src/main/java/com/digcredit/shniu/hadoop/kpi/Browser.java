package com.digcredit.shniu.hadoop.kpi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * Created by shniu on 2017/8/8.
 */
public class Browser {

    public static class BrowserMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        IntWritable one = new IntWritable(1);
        Text browser = new Text();
        Kpi kpi = new Kpi();

        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            kpi = Kpi.parse(value.toString());
            if (kpi.getValidate()) {
                browser.set(kpi.getUserAgent());
                context.write(browser, one);
            }
        }
    }

    public static class BrowserReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        IntWritable resCount = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            Integer sum = 0;

            for (IntWritable i : values) {
                sum += i.get();
            }

            resCount.set(sum);
            context.write(key, resCount);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: browser input output");
        }

        Job job = Job.getInstance(conf, "Browser kpi");
        job.setJarByClass(Browser.class);
        job.setMapperClass(BrowserMapper.class);
        job.setReducerClass(BrowserReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        for (int i = 0; i < otherArgs.length - 1; ++i) {
            FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
        }

        FileOutputFormat.setOutputPath(job, new Path(otherArgs[otherArgs.length - 1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
