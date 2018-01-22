package com.practice.conf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MyCustomizer implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED,
				"/static/401.html");
		ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,
				"/static/404.html");
		ErrorPage error500Page = new ErrorPage(
				HttpStatus.INTERNAL_SERVER_ERROR, "/static/500.html");
		container.addErrorPages(error401Page, error404Page, error500Page);
	}

}
