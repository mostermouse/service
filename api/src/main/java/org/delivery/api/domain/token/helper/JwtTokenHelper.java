package org.delivery.api.domain.token.helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {
    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;
    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {

        var expriedLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);
        var expriedAt = Date.from(
                expriedLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expriedAt)
                .compact();
        return TokenDto.builder()
                .token(jwtToken)
                .expriedAt(expriedLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {

        var expriedLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);
        var expriedAt = Date.from(
                expriedLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expriedAt)
                .compact();
        return TokenDto.builder()
                .token(jwtToken)
                .expriedAt(expriedLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();


        try {
            var reslut = parser.parseClaimsJws(token);
            return new HashMap<String, Object>(reslut.getBody());

        } catch (Exception e) {
            if (e instanceof SignatureException) {
                //トークンが無効な場合
                throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);
            } else if (e instanceof ExpiredJwtException) {
                //期限切れのトークン
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);
            } else {
                //その他エラー
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION, e);
            }
        }
    }
}
