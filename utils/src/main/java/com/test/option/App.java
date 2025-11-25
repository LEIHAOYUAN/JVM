package com.test.option;

import java.util.List;

/**
 * @author ryan
 */
public class App {
    public static void main(String[] args) {

    }

    public static List<OptionValue> convert(List<Item> items) {
        // TODO 实现将items转换为OptionValue的逻辑

        return null;
    }

    public static class Item {
        public String parentId;
        public String optionId;
        public String optionValueId;
        public String name;
        public Double price;
        public Integer quantity = 1;
        public Integer seq = 0;
    }

    public static class OptionValue {
        public String id;
        public String parentId;
        public Integer seq;
        public String name;
        public Double quantity;
        public Double chargeQuantity;
        public Double price;
        public List<OptionValue> nestValues;
    }
}
