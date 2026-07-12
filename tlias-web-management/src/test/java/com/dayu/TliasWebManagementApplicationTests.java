package com.dayu;

import com.dayu.pojo.Result;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ApplicationContext applicationContext; // SpringIOC容器

    @Autowired
    private Gson gson;

    @Test
    public void testGson() {
        System.out.println(gson.toJson(Result.success("hello gson")));
    }
    @Test
    public void testScope() {
        for (int i = 0; i < 10; i++) {
            Object deptController = applicationContext.getBean("deptController");
            System.out.println(deptController);
        }
    }
}
