package com.dayu;

import org.junit.jupiter.api.Test;

public class UUIDTest {

    @Test
    public void UUIDTest() {
        for (int i = 0; i < 100; i++) {
            System.out.println(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
    }
}
