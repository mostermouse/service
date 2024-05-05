package org.delivery.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreCategory {

    //중식
    CHINESE_FOOD("中食" , "中食"),
    //양식
    WESTERN_FOOD("洋食" , "洋食"),
    //한식
    KOREAN_FOOD("韓食" , "韓食"),
    //일식
    JAPANESE_FOOD("和食" , "和食"),
    //치킨
    CHICKEN("チーキン" , "チーキン"),
    //피자
    PIZZA("ピザ" , "ピザ"),
    //햄버거
    HAMBURGER("ハンバーガー" , "ハンバーガー"),
    //커피
    COFFEE_TEA("コーヒー＆茶" , "コーヒー＆茶"),
    ;
    private String display;
    private String description;
}
