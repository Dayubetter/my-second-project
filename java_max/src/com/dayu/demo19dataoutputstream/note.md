### DataOutputStream 数据输出流
- 允许把数据和类型一并写出去

|构造器|说明|
|:---|:---|
|public DataOutputStream(OutputStream out)|创建一个数据输出流，以写入数据的格式，写入到指定的OutputStream|

|方法| 说明                               |
|:---|:---------------------------------|
|public final void writeByte(int v) throws IOException| 将byte类型的数据写入基础的字节输出流             |
|public final void writeInt(int v) throws IOException| 将int类型数据写入基础的字节输出流               |
|public final void writeUTF(String v) throws IOException| 将字符串数据String类型数据以UTF-8写入基础的字节输出流 |
|public void write(int/byte[]/byte[]一部分) throws IOException|支持写字节数据出去|