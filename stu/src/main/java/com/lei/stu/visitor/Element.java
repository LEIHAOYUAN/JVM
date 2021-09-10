package com.lei.stu.visitor;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/10/23 17:26
 */
public abstract class Element {
    public abstract void accept(Visitor visitor);
}
