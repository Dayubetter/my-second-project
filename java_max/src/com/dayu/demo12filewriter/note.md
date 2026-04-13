### FileWriter 文件字符输出流
- 以内存为基准，把内存中的数据一字符的形式写出到文件中去。

| 构造器                                                     |说明|
|:--------------------------------------------------------|:---|
| `public FileWriter(File file) throws IOException`       |创建一个字符输出流，以写入数据到指定的File对象表示的文件中|
| `public FileWriter(String filepath) throws IOException` |创建一个字符输出流，以写入数据到指定的文件名所表示的文件中|
|`public FileWriter(File file, boolean append) throws IOException`|创建一个字符输出流，以写入数据到指定的File对象表示的文件中，并指定是否追加数据|
|`public FileWriter(String filepath, boolean append) throws IOException`|创建一个字符输出流，以写入数据到指定的文件名所表示的文件中，并指定是否追加数据|

| 方法                                                 | 说明      |
|:---------------------------------------------------|:--------|
| `public void write(int c) throws IOException`      | 写入一个字符  |
| `public void write(String str) throws IOException` | 写入一个字符串 |
| `public void write(char[] cbuf) throws IOException` | 写入一个字符数组 |
| `public void write(char[] cbuf, int off, int len) throws IOException` | 写入一个字符数组的指定部分 |
| `public void write(String str, int off, int len) throws IOException` | 写入一个字符串的指定部分 |

！[注意] 
> - 字符输出流写出数据后，必须刷新流，或者关闭流，写出去的数据才能生效

| 方法                                                 | 说明      |
|:---------------------------------------------------|:--------|
|`public void flush() throws IOException`|刷新流,将内存中缓存的数据立即写到文件中去生效|
|`public void close() throws IOException`|关闭流,将内存中缓存的数据写出去，并释放资源|