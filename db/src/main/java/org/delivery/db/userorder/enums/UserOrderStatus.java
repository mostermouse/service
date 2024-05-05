package org.delivery.db.userorder.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderStatus {
    REGISTERED("登録"),
    UNREGISTERED("キャンセル"),

    //注文
    ORDER("注文"),
    //確認
    ACCEPT("確認"),
    //料理中
    COOKING("料理中"),
    //出前中
    DELIVERY("出前中"),
    //完了
    RECEIVE("完了"),
    ;
    /*UserOrderStatus(String description){
     this.description = description;
    }*/
    private String description;
}
