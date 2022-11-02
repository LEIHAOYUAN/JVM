package com.lei.jvm.stu.extend;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/2 18:09
 */
public abstract class AbsParent {

    protected List<String> resource = Lists.newArrayList();

    public abstract void addResource();




}
