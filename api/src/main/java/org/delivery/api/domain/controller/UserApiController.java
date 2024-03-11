package org.delivery.api.domain.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.business.UserBusiness;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    private final UserBusiness userBusiness;
}
