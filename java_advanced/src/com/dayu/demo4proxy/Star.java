package com.dayu.demo4proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Star implements StarService{
    private String name;

    @Override
    public void sing(String name) {
        System.out.println(this.name + "正在唱：" + name);
    }

    @Override
    public String dance() {
        System.out.println(this.name + "正在跳舞");
        return "谢谢";
    }
}
