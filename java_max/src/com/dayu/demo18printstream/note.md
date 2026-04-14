### 打印流
PrintStream/PrintWriter
- 作用：打印流可以实现更方便，更高效的打印数据出去，能实现打印啥出去就是啥出去

PrintStream提供的打印数据的方案

|构造器|说明|
|:---|:---|
|public PrintStream(OutputStream/File/String out)| 打印流直接通向字节输出流/文件/文件路径|
|public PrintStream(String fileName, Charset charset)| 可以指定写出去的字符编码|
|public PrintStream(OutputStream out, boolean autoFlush) | 可以设置打印数据时是否自动刷新缓冲区|
|public PrintStream(OutputStream out, boolean autoFlush, String encoding) | 可以同时指定自动刷新缓冲区，字符编码|