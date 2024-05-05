package org.delivery.db.storemenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreMenuStatus {
    REGISTERED("登録"),
    UNREGISTERED("キャンセル"),
    ;
    private String description;

}
