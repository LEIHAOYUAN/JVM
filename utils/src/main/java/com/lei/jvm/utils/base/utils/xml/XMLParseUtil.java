package com.lei.jvm.utils.base.utils.xml;

import com.alibaba.fastjson.JSON;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/12 14:39
 *  https://blog.csdn.net/lk7688535/article/details/52401752
 *
 */
@Slf4j
@UtilityClass
public class XMLParseUtil {


    public static void main(String[] args) {
        xPath();
    }

    /**
     <?xml version="1.0" encoding="utf-8"?>
     <classroom grade="primary5">
     <persons>
     <teacher id="1">
     <property name="name" value="laosi"/>
     <property name="age" value="24"/>
     </teacher>

     <students>
     <student id="1">
     <property name="name" value="lisi"/>
     <property name="age" value="12"/>
     </student>
     <student id="2">
     <property name="name" value="zhangsan"/>
     <property name="age" value="12"/>
     </student>
     <student id="3">
     <property name="name" value="wangwu"/>
     <property name="age" value="12"/>
     </student>
     </students>
     </persons>
     </classroom>
     */
    public static void xPath(){
        String xml = "<classroom grade=\"primary5\">\n" +
                "\t<persons>\n" +
                "\t\t<teacher id=\"1\">\n" +
                "\t\t\t<property name=\"name\" value=\"laosi\"/>\n" +
                "\t\t\t<property name=\"age\" value=\"24\"/>\n" +
                "\t\t</teacher>\n" +
                "\t\t\n" +
                "\t\t<students>\n" +
                "\t\t\t<student id=\"1\">\n" +
                "\t\t\t\t<property name=\"name\" value=\"lisi\"/>\n" +
                "\t\t\t\t<property name=\"age\" value=\"12\"/>\n" +
                "\t\t\t</student>\n" +
                "\t\t\t<student id=\"2\">\n" +
                "\t\t\t\t<property name=\"name\" value=\"zhangsan\"/>\n" +
                "\t\t\t\t<property name=\"age\" value=\"12\"/>\n" +
                "\t\t\t</student>\n" +
                "\t\t\t<student id=\"3\">\n" +
                "\t\t\t\t<property name=\"name\" value=\"wangwu\"/>\n" +
                "\t\t\t\t<property name=\"age\" value=\"12\"/>\n" +
                "\t\t\t</student>\n" +
                "\t\t</students>\n" +
                "\t</persons>\n" +
                "</classroom>";
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            log.error("xml字符串解析，失败 --> {}", e.getMessage(),e);
            return;
        }
        Node node = doc.selectSingleNode("/classroom/persons/teacher");
        log.info("获取节点={}", node.getText());

    }

    /**
     * (多层)xml格式字符串转换为map
     *
     * @param xml xml字符串
     * @return 第一个为Root节点，Root节点之后为Root的元素，如果为多层，可以通过key获取下一层Map
     */
    public static Map<String, Object> multilayerXmlToMap(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            log.error("xml字符串解析，失败 --> {}", e);
        }
        Map<String, Object> map = new HashMap<>();
        if (null == doc) {
            return map;
        }
        // 获取根元素
        Element rootElement = doc.getRootElement();
        recursionXmlToMap(rootElement,map);
        return map;
    }

    /**
     * multilayerXmlToMap核心方法，递归调用
     *
     * @param element 节点元素
     * @param outmap 用于存储xml数据的map
     */
    @SuppressWarnings("unchecked")
    private static void recursionXmlToMap(Element element, Map<String, Object> outmap) {
        // 得到根元素下的子元素列表
        List<Element> list = element.elements();
        int size = list.size();
        if (size == 0) {
            // 如果没有子元素,则将其存储进map中
            outmap.put(element.getName(), element.getTextTrim());
        } else {
            // innermap用于存储子元素的属性名和属性值
            Map<String, Object> innermap = new HashMap<>();
            // 遍历子元素
            list.forEach(childElement -> recursionXmlToMap(childElement, innermap));
            outmap.put(element.getName(), innermap);
        }
    }


}
