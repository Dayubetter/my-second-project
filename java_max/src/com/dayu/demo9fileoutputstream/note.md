### FileOutputStream 文件字节输出流
- 以内存为基准，把内存中的数据以字节的形式写出到文件中去

| 构造器                                                  | 说明                      |
|------------------------------------------------------|-------------------------|
| `public FileOutputStream(File file)`                 | 创建字节输出流管道与源文件对象接通       |
| `public FileOutputStream(String filepath)`           | 创建字节输出流管道与源文件路径接通       |
| `public FileOutputStream(File file, boolean append)` | 创建字节输出流管道与源文件对象接通，可追加数据 |
| `public FileOutputStream(String filepath, boolean append)`  | 创建字节输出流管道与源文件路径接通，可追加数据 |

| 方法                                                   | 说明                                                       |
|------------------------------------------------------|----------------------------------------------------------|
| `public void write(int b)`                           | 写一个字节出去                                                  |
| `public void write(byte[] b)`                        | 写一个字节数组出去                                                |
| `public void write(byte[] b, int off, int len)` | 写一个字节数组的一部分出去 把数据写入字节输出流管道，把数据写入到内存中，指定写入数据的起始位置和写入数据的长度 |
| `public void flush()`                                | 刷新缓冲区，把数据写入到文件                                           |
| `public void close()`                                | 关闭字节输出流管道，把数据写入到文件，并释放资源                                 |