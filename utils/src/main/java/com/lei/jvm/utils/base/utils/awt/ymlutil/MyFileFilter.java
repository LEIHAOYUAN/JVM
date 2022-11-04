package com.lei.jvm.utils.base.utils.awt.ymlutil;

import javax.swing.filechooser.FileFilter;
import java.io.File;

//文件过滤器

//Download by http://www.srcfans.com

public class MyFileFilter extends FileFilter {

    String ends; //文件后缀

    String description; //文件描述文字

    //构造函数
    public MyFileFilter(String ends, String description) {
        //设置文件后缀
        this.ends = ends;
        //设置文件描述文字
        this.description = description;
    }

    //重载FileFilter中的accept方法
    public boolean accept(File file) {
        //如果是目录,则返回true
        if (file.isDirectory()) {
            return true;
        }
        //得到文件名称
        String fileName = file.getName();
        //把文件后缀与可接受后缀转成大写后比较
        if (fileName.toUpperCase().endsWith(ends.toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }

    //返回文件描述文字
    public String getDescription() {
        return description;
    }

}