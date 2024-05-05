
package org.delivery.db.userordermenu.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderMenuStatus {
    REGISTERED("登録"),
    UNREGISTERED("キャンセル"),
    ;
    /*UserOrderStatus(String description){
     this.description = description;
    }*/
    private String description;
}
