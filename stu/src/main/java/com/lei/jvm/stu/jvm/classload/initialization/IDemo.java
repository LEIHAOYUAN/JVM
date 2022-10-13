package com.lei.jvm.stu.jvm.classload.initialization;

/**
 * @Author leihaoyuan
 * @Date 2022/1/27 9:58
 * @Version 1.0
 * @Description 接口的初始化
 * 接口的加载过程与类加载过程稍有不同，针对接口需要做一些特殊说明：接口也有初始化过程，
 * 这点与类是一致的，上面的代码都是用静态语句块“static{}”来输出初始化信息的，而接口中不能使
 * 用“static{}”语句块，但编译器仍然会为接口生成“<clinit>()”类构造器 [2] ，用于初始化接口中所定义的
 * 成员变量。
 * 接口与类真正有所区别的是前面讲述的六种“有且仅有”需要触发初始化场景中的第三种：
 * 当一个类在初始化时，要求其父类全部都已经初始化过了，但是一个接口在初始化时，并不要求其父
 * 接口全部都完成了初始化，只有在真正使用到父接口的时候（如引用接口中定义的常量）才会初始化。
 */
public interface IDemo {
    int aa = 0;
}