package com.yuxing.trainee.gateway.infrastructure.exception;

import com.yuxing.trainee.common.core.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.Map;

/**
 * 网关异常处理
 *
 * @author yuxing
 */
@Slf4j
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {


    public JsonExceptionHandler(ErrorAttributes errorAttributes,
                                ResourceProperties resourceProperties,
                                ErrorProperties errorProperties,
                                ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

        /**
         * 获取异常属性
         */
        @Override
        protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
            int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            Throwable error = super.getError(request);
            if (error instanceof NotFoundException) {
                String msg = request.uri().getPath() + " not found";
                log.error(msg, error);
                return Result.failed(HttpStatus.NOT_FOUND.value(), msg).toMap();
            }
            return response(code, this.buildMessage(request, error));
        }

        /**
         * 指定响应处理方法为JSON处理的方法
         */
        @Override
        protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
            return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
        }


        /**
         * 根据code获取对应的HttpStatus
         */
        @Override
        protected int getHttpStatus(Map<String, Object> errorAttributes) {
            int statusCode = (int) errorAttributes.get("code");
            return HttpStatus.valueOf(statusCode).value();
        }

        /**
         * 构建异常信息
         */
        private String buildMessage(ServerRequest request, Throwable throwable) {
            StringBuilder message = new StringBuilder("Failed to handle request [");
            message.append(request.methodName());
            message.append(" ");
            message.append(request.uri());
            message.append("]");
            if (throwable != null) {
                message.append(": ");
                message.append(throwable.getMessage());
            }
            return message.toString();
        }

        /**
         * 构建返回的JSON数据格式
         *
         * @param status 状态码
         * @param msg    异常信息
         * @return JSON数据格式
         */
        public static Map<String, Object> response(int status, String msg) {
            Map<String, Object> map = Result.failed(status, msg).toMap();
            log.error(map.toString());
            return map;
        }
    }