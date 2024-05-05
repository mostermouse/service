package org.delivery.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    REGISTERED("登録"),
    UNREGISTERED("キャンセル")
    ;

    private final String description;
}
