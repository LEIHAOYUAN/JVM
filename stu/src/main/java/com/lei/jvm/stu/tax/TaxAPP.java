package com.lei.jvm.stu.tax;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
public class TaxAPP {

    public static void main(String[] args) {
        List<Tax> data = JSON.parseArray(JSON_DATA, Tax.class);
        String SQL = "UPDATE tax_categories SET updated_time=NOW(),tax_driver_code=''{0}'' WHERE tax_driver_code='''' AND tax_category=''{1}'' AND tax_sub_category=''{2}'';";
        List<String> result = Lists.newArrayList();
        for (Tax tax : data) {
            if (tax == null || StrUtil.isBlank(tax.tax_category) || StrUtil.isBlank(tax.tax_sub_category) || StrUtil.isBlank(tax.tax_driver_code)) {
                log.warn("参数非法={}", JSON.toJSONString(tax));
                continue;
            }
            result.add(MessageFormat.format(SQL, tax.tax_driver_code, tax.tax_category, tax.tax_sub_category));
        }
        result.forEach(System.out::println);
    }


    public static class Tax {
        public String tax_category;
        public String tax_sub_category;
        public String tax_driver_code;
    }

    public static final String JSON_DATA = """
                [
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Food, Prepared",
                                "tax_driver_code": "1"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Grocery item",
                                "tax_driver_code": "10"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Meal kit, Prepared (non-frozen) - ready to eat",
                                "tax_driver_code": "100"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Meal kit, Prepared (frozen)",
                                "tax_driver_code": "1000"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Meal Kits, Unprepared",
                                "tax_driver_code": "101"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Ingredients, Unprepared",
                                "tax_driver_code": "102"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Dessert, Prepared",
                                "tax_driver_code": "1002"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Dessert, Unprepared",
                                "tax_driver_code": "10005"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Bakery, Prepared",
                                "tax_driver_code": "100000"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Bakery, Unprepared",
                                "tax_driver_code": "100001"
                              },
                              {
                                "tax_category": "Food",
                                "tax_sub_category": "Candy",
                                "tax_driver_code": "100002"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Alcoholic beverage, excluding Wine",
                                "tax_driver_code": "10006"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Wine",
                                "tax_driver_code": "10007"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Bottled water",
                                "tax_driver_code": "100003"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Carbonated water, sweetened",
                                "tax_driver_code": "10008"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Carbonated water, unsweetened",
                                "tax_driver_code": "10009"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Mineral water",
                                "tax_driver_code": "100005"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Seltzer, sweetened",
                                "tax_driver_code": "100007"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Seltzer, unsweetened",
                                "tax_driver_code": "100008"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Cocktail mix",
                                "tax_driver_code": "100009"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Carbonated beverages, sweetened",
                                "tax_driver_code": "100010"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Carbonated beverages, unsweetened",
                                "tax_driver_code": "10010"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Soft drink",
                                "tax_driver_code": "1003"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Fountain drink",
                                "tax_driver_code": "10011"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Sports drink",
                                "tax_driver_code": "100011"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Heated beverage",
                                "tax_driver_code": "100012"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Prepared beverage, other",
                                "tax_driver_code": "100013"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Milk",
                                "tax_driver_code": "10012"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "fruit juice (containing <50% juice)",
                                "tax_driver_code": "100014"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "fruit juice (containing >50% <70% juice)",
                                "tax_driver_code": "100015"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "fruit juice (containing 70% + juice)",
                                "tax_driver_code": "100016"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "vegetable juice (containing <50% juice)",
                                "tax_driver_code": "100017"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "vegetable juice (containing >50% <70% juice)",
                                "tax_driver_code": "1000001"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "vegetable juice (containing 70% + juice)",
                                "tax_driver_code": "1000002"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Iced coffee (cup)",
                                "tax_driver_code": "1000003"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Iced tea, sweetened (cup)",
                                "tax_driver_code": "1004"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Iced tea, unsweetened (cup)",
                                "tax_driver_code": "103"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Iced coffee (bottled, can)",
                                "tax_driver_code": "1005"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Iced tea, sweetened (bottled, can)",
                                "tax_driver_code": "1006"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Iced tea, unsweetened (bottled, can)",
                                "tax_driver_code": "1007"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Lemonade, sweetened",
                                "tax_driver_code": "1008"
                              },
                              {
                                "tax_category": "Beverage",
                                "tax_sub_category": "Lemonade, unsweetened",
                                "tax_driver_code": "10014"
                              },
                              {
                                "tax_category": "Other",
                                "tax_sub_category": "Giftcard",
                                "tax_driver_code": "FEE-GC"
                              },
                              {
                                "tax_category": "Other",
                                "tax_sub_category": "Membership",
                                "tax_driver_code": "FEE-MEM"
                              },
                              {
                                "tax_category": "Other",
                                "tax_sub_category": "Delivery",
                                "tax_driver_code": "FEE-DEL"
                              },
                              {
                                "tax_category": "Other",
                                "tax_sub_category": "Service Fee (When ordering on the web or APP)",
                                "tax_driver_code": "FEE-SERV"
                              },
                              {
                                "tax_category": "Other",
                                "tax_sub_category": "Fast Past Fee (Allows customers to get order faster)",
                                "tax_driver_code": "FEE-FP"
                              }
                            ]
            """;

}
