package org.delivery.api.domain.userorder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {
    @NotNull
    private Long storeId;
    //注文
    //特定ユーザーが、特定メニューを注文
    //特定のユーザー=ログインしたセッションに含まれるユーザー
    //特定メニューid
    @NotNull
    private List<Long> storeMenuIdList;

}
