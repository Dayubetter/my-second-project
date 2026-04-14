### BufferedInputStream缓冲字节输入流
- 作用：可以提高字节输入流读取数据的性能
- 原理：缓冲字节输入流自带了8kb缓冲池；缓冲字节输出流也自带了8kb缓冲池

| 构造器                                          |说明|
|:---------------------------------------------|:----|
| public BufferedInputStream(InputStream is)   |创建一个 BufferedInputStream，它将包装指定的原始输入流|
| public BufferedOutputStream(OutputStream os) |创建一个 BufferedOutputStream，它将包装指定的原始输出流|

### BufferedOutputStream缓冲字节输出流
### BufferedReader缓冲字符输入流
### BufferedWriter缓冲字符输出流