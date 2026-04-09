package com.dayu.demo2genericity;


public interface Data<T> {
    void add(T t);
    void remove(T t);
    void update(T t);
    T query(int id);
}
