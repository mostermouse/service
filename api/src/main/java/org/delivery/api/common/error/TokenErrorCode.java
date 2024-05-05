package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{

    INVALID_TOKEN(400, 2000, "無効なトークン"),
    EXPIRED_TOKEN(400,2001,"期限切れのトークン"),
    TOKEN_EXCEPTION(400 , 2002 , "トークン不明なエラー"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400,2003,"認証ヘッダートークンなし"),
            ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
