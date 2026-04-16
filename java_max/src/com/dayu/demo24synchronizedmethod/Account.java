package com.dayu.demo24synchronizedmethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String cardId; // 卡号
    private double money;  // 金额

    public synchronized void drawMoney(double money) {
        // 拿到当前谁来取钱
        String name = Thread.currentThread().getName();
        // 判断余额是否充足
        if (this.money >= money) {
            System.out.println(name + "取钱成功，取钱金额：" + money);
            this.money -= money;
            System.out.println("余额为：" + this.money);
        }else {
            System.out.println(name + "取钱失败，余额不足");
        }
    }
}
