package org.delivery.api.domain.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.controller.model.UserRegisterRequest;
import org.delivery.api.domain.controller.model.UserResponse;
import org.delivery.api.domain.converter.UserConverter;
import org.delivery.api.domain.server.UserService;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;

    /*사용자에 대한 가입처리 로직
    1.repuest -> entity
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
}
