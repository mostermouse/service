package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);
        log.info("INIT URI : {}" , req.getRequestURI());

        chain.doFilter(req, res);

        //requset 정보

        var hearderNames = req.getHeaderNames();
        var heardValues = new StringBuilder();

        hearderNames.asIterator().forEachRemaining(headKey -> {
            var headerValue = req.getHeader(headKey);
            //authorization-token : ??? , user-agent : ???
            heardValues.append(headKey).append(" : ").append(headerValue).append(" , ");

        });
        var requstBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();


        log.info(">>>>> uri : {}, method : {}, header : {} , body : {}", uri, method, heardValues, requstBody);


        //response 정보
        var responseHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues.append(headerKey).append(" : ").append(headerValue).append(" , ");
        });

        var responseBody = new String(res.getContentAsByteArray());
        log.info("<<<<< uri : {}, method : {}, header : {} , body : {}", uri, method, responseHeaderValues, responseBody);

        res.copyBodyToResponse();
    }
}
