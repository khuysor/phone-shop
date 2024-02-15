package com.huysor.ecommerce.phoneshop.services.util;

import java.util.List;

public class GeneralUntils {
    public static List<Integer>toInteger(List<String> list){
        return list.stream().map(str->str.length()).toList();
    }
    public static List<Integer>getEvenNumber(List<Integer>list){
        return list.stream().filter(x->x%2==0).toList();
    }

}
