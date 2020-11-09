package com.yuxing.trainee.gateway.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.Collections;
import java.util.List;

/**
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 {@link ResponseStatusExceptionHandler} 执行
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({ServerProperties.class, ResourceProperties.class})
public class GlobalExceptionConfiguration {

	private final ServerProperties serverProperties;
	private final ResourceProperties resourceProperties;
	private final ApplicationContext applicationContext;
	private final List<ViewResolver> viewResolvers;
	private final ServerCodecConfigurer serverCodecConfigurer;

	public GlobalExceptionConfiguration(ServerProperties serverProperties,
										 ResourceProperties resourceProperties,
										 ObjectProvider<List<ViewResolver>> viewResolversProvider,
										 ServerCodecConfigurer serverCodecConfigurer,
										 ApplicationContext applicationContext) {
		this.serverProperties = serverProperties;
		this.applicationContext = applicationContext;
		this.resourceProperties = resourceProperties;
		this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
		this.serverCodecConfigurer = serverCodecConfigurer;
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes) {
		JsonExceptionHandler exceptionHandler = new JsonExceptionHandler(
				errorAttributes,
				this.resourceProperties,
				this.serverProperties.getError(),
				this.applicationContext);
		exceptionHandler.setViewResolvers(this.viewResolvers);
		exceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
		exceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
		return exceptionHandler;
	}
}