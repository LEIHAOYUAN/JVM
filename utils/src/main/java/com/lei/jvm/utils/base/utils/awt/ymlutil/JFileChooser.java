package com.lei.jvm.utils.base.utils.awt.ymlutil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;

//文件选择器演示

public class JFileChooser extends JFrame {

    //文件选择器
    private javax.swing.JFileChooser chooser;

    //选择文件按钮
    private JButton button;
    //用于设定文件对话框作用(打开还是保存文件)
    private JComboBox comboBox;

    public JFileChooser() {

        //调用父类构造函数
        super("选择待转换properties文件");

        //得到容器
        Container contentPane = getContentPane();

        //设置布局管理器为Flowlayout
        contentPane.setLayout(new FlowLayout());
        //初始化文件选择器
        chooser = new javax.swing.JFileChooser();

        //初始化组合框
        comboBox = new JComboBox();
        //增加组合框列表内容
        comboBox.addItem(StandardCharsets.UTF_8.name());
        comboBox.addItem(StandardCharsets.ISO_8859_1.name());
        comboBox.addItem(StandardCharsets.US_ASCII.name());
        //增加组件到容器
        contentPane.add(comboBox);

        //初始化按钮
        button = new JButton("选择文件");
        contentPane.add(button);

        //按钮事件处理
        button.addActionListener(e -> {
            // 选择器选择编码格式
            String codedFormat = comboBox.getSelectedItem().toString();
            //文件选择器返回状态
            int state;
            //移去所有文件过滤器
            chooser.setCurrentDirectory(new File("E:\\lang"));
            chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
            //增加文件过滤器,接爱gif文件
            chooser.addChoosableFileFilter(new MyFileFilter("properties", "语言资源文件"));
            //显示保存文件对话框
            state = chooser.showOpenDialog(null);
            //得到选择的文件
            File file = chooser.getSelectedFile();
            //选择了文件并点击了打开可保存按钮
            if (file != null && state == javax.swing.JFileChooser.APPROVE_OPTION) {
                //显示提示信息
                String tips = PropertiesUtil.transfer(file.getPath(), codedFormat);
                JOptionPane.showMessageDialog(null, tips);
                //点击了撤销按钮
            } else if (state == javax.swing.JFileChooser.CANCEL_OPTION) {

            } else if (state == javax.swing.JFileChooser.ERROR_OPTION) {
                //显示提示信息
                JOptionPane.showMessageDialog(null, "错误!");
            }
        });
        //设置窗口大小
        this.setSize(800, 200);
        //设置窗口可见
        this.setVisible(true);
        //关闭窗口时退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}