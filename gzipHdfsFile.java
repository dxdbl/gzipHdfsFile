package com.hdfs;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.scene.effect.Reflection;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

public class gzipHdfsFile {
    static String hdfsFileDir = "";

    public static void gzip(String codecClassName) throws Exception{

        String gzipFileDir = hdfsFileDir.concat(".gz");
        //压缩文件
        Class<?> codecClass = Class.forName(codecClassName);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass,conf);

        //指定压缩文件输出路径
        FSDataOutputStream outputStream = fs.create(new Path(gzipFileDir));
        //指定被压缩的文件路径
        FSDataInputStream in = fs.open(new Path(hdfsFileDir));
        //创建压缩输出流
        CompressionOutputStream out = codec.createOutputStream(outputStream);
        IOUtils.copyBytes(in, out, conf);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }
    public static void main(String[] args) throws Exception {
        //获取文件路径参数，生成输出路径参数
        hdfsFileDir = args[0];
        //执行文件压缩
        gzip("org.apache.hadoop.io.compress.GzipCodec");
    }
}
