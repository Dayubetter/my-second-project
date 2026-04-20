package com.dayu.demo2reflection;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class SaveObjectFrameWork {
    // 保存任意对象的静态方法
    public static void saveObject(Object obj) throws Exception{
        PrintStream printStream = new PrintStream(new FileOutputStream("java_advanced/src/com/dayu/demo2reflection/object.txt", true));
        // obj 学生老师狗
        // 只有反射可以知道对象有多少个字段
        // 1。 获取Class对象
        Class c1 = obj.getClass();
        String simpleName = c1.getSimpleName();
        printStream.println("==================" + simpleName + "==================" );
        // 2. 获取Class对象的所有对象
        Field[] fields = c1.getDeclaredFields();
        // 3.遍历字段
        for (Field field : fields) {
            // 4. 获取字段的值
            // 4.1 获取字段的名称
            field.setAccessible(true);
            String fieldName = field.getName();
            // 4.2 获取字段的值
            Object fieldValue = field.get(obj) +  "";
            // 5. 把字段名和字段值，保存到文件中
            printStream.println(fieldName + ":" + fieldValue);
        }
        printStream.close();
    }
}
