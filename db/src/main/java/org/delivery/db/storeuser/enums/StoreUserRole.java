package org.delivery.db.storeuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserRole {
    MASTER("マスター"),
    ADMIN("管理者"),
    USER("一般ユーザー"),
    ;
    private String description;
}
