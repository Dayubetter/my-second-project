package com.dayu.demo1junit;

import org.junit.Assert;
import org.junit.Test;

// 测试类：junit单元测试框架，对业务类中的业务方法进行正确性测试
public class StringUtilTest {
    // 测试方法:必须是公开的，无参数，无返回值
    // 测试方法必须@Test注解

    @Test
    public void testPrintNumbers() {
        // 测试步骤
        StringUtil.printNumber("张三abc"); // 5
        // 测试用例
        StringUtil.printNumber("张三");
        StringUtil.printNumber("张三张三");
        StringUtil.printNumber(null);
        StringUtil.printNumber("");
    }
    @Test
    public void testGetMaxIndex() {
        // 测试步骤
        int index = StringUtil.getMaxIndex("abcdefg"); // 6
        // 测试用例
        int index1 = StringUtil.getMaxIndex("张三");
        int index2 = StringUtil.getMaxIndex("");
        int index3 = StringUtil.getMaxIndex(null);
        System.out.println( index);
        System.out.println(index1);
        System.out.println(index2);
        System.out.println(index3);
        // 做断言:断言结果是否与预期结果一致
        Assert.assertEquals("测试失败，最大索引有问题",6, index);
        Assert.assertEquals("测试失败，最大索引有问题",1, index1);
        Assert.assertEquals("测试失败，最大索引有问题",-1, index2);
        Assert.assertEquals("测试失败，最大索引有问题",-1, index3);
    }
}
