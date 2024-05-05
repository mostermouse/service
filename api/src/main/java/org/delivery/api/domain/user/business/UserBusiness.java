package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@Business
@RequiredArgsConstructor
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /*
    ユーザーの購読処理ロジック

    1.repuest - >エンティティ
    2.enitity -> save
    3. save Entity -> response
    4.response return
    * */

    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
        /*return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Request null"));
*/
    }

    /*1. email、passwordを持ってユーザーをチェック
     *2. user entity ログイン確認
     *3. トークン生成
     *4. token response*/
    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;


    }

    public UserResponse me(
            Long userId
    ) {
        //var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        //var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var userEntity = userService.getUserWithThrow(userId);
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
