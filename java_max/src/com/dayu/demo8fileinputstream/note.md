### FileInputStream 文件字节输入流
- 作用：以内存为基准，可以把磁盘文件中的数据以字节的形式读入到内存中去
构造器：
- public FileInputStream(File file)      创建字节输入流管道与源文件接通
- public FileInputStream(String path)    创建字节输入流管道与源文件接通
方法名称:
- public int read()             每次读取一个字节返回，如果发现没有数据可读会返回-1
- public int read(byte[] buffer)     每次用一个字节数组去读取数据，返回字节数组读取了多个个字节，如果发现没有数据可读会返回-1