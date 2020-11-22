package com.df.sla.cpu;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SarCpuReducer extends Reducer<Text, FloatWritable, Text, Text>
{
	private Text result = new Text();
	public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException
	{
		int count = 0;
		float sum = 0.0f;
		for (FloatWritable val : values)
		{
			sum += val.get();
			count++;
		}
		float aggregate = sum / count;
		
		result.set(aggregate + "%");
		context.write(key, result);
	}
}