package org.delivery.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {
    REGISTERED("登録"),
    UNREGISTERED("キャンセル"),
    ;
    private String description;
}
