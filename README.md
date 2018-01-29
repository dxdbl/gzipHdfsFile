# gzipHdfsFile

压缩HDFS目录下的文件
利用java来进行

最后拷贝到hadoop集群上运行，运行命令

输入：文件名完整路径：/mahf/test.txt

输出：压缩后文件名完整路径：/mahf/test.txt.gz

hadoop jar gzipHdfsFile.jar 文件完整路径(包含文件名!)

例如：hadoop jar gzipHdfsFile.jar /mahf/test.txt
