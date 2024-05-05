package org.delivery.db.storeuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserStatus {
    REGISTERED("登録"),
        UNREGISTERED("キャンセル"),
            ;
    private String description;
}
