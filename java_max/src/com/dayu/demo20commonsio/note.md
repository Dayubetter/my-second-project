### commons-io-2.11.0.jar框架
创建lib，放进去 Add as  library
类中导包使用
- FileUtils类提供的部分方法

```java
public static void copyFile(File srcFile, File destFile) throws IOException // 拷贝文件
public static void copyDirectory(File srcDir, File destDir) throws IOException // 拷贝文件夹
public static void deleteDirectory(File directory) throws IOException // 删除文件夹
public static void readFileToString(File file, String encoding) throws IOException // 读取数据
public static void writeStringToFile(File file, String data, String charname, boolean append) throws IOException // 写入数据
```

- IOUtils类提供的部分方法
```java
public static int copy(InputStream input, OutputStream output) throws IOException // 拷贝文件
public static int copy(Reader input, Writer output) throws IOException // 拷贝文件

```