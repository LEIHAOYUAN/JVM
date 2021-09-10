package com.lei.stu.visitor;

import java.util.LinkedList;
import java.util.List;

public class ObjectStructure {

    private List<Element> elements = new LinkedList<>();

    public void attach(Element element) {
        elements.add(element);
    }

    public void detach(Element element) {
        elements.remove(element);
    }

    public void accept(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }

}