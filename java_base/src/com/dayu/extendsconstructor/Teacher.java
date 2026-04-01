package com.dayu.extendsconstructor;

public class Teacher extends People{
    private String skill;

    public Teacher(String name,String skill,char sex) {
        // 可以把子类继承自父类的这部分的数据也完成初始化
        super(name,sex);
         this.skill=skill;
    }

    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
}
