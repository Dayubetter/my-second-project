### InputStremReader字符输入转换流
// 解决思路：先获取文件的原始字节流，再将其按照真实的字符集编码转换成字符输入流，这样字符输入流中的字符就不乱码了

| 构造器                                      | 描述                                               |
|:-----------------------------------------|:-------------------------------------------------|
| public InputStreamReader(InputStream is) | 创建一个使用默认字符集的InputStreamReader，与直接用FileReader效果一样 |
| public InputStreamReader(InputStream is, String charsetName) | 创建一个使用指定字符集的InputStreamReader |