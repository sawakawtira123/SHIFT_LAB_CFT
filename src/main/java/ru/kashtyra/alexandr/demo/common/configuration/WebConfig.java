package ru.kashtyra.alexandr.demo.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import ru.kashtyra.alexandr.demo.common.UriPath;

import java.lang.reflect.Method;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiAwareRequestMappingHandlerMapping();
    }

    private static class ApiAwareRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

        @Override
        protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
            Class<?> beanType = method.getDeclaringClass();
            if (AnnotationUtils.findAnnotation(beanType, RestController.class) != null) {
                PatternsRequestCondition apiPattern = new PatternsRequestCondition(UriPath.API_PATH_PREFIX)
                        .combine(mapping.getPatternsCondition());

                mapping = new RequestMappingInfo(mapping.getName(), apiPattern, mapping.getMethodsCondition(),
                        mapping.getParamsCondition(), mapping.getHeadersCondition(), mapping.getConsumesCondition(),
                        mapping.getProducesCondition(), mapping.getCustomCondition());
            }
            super.registerHandlerMethod(handler, method, mapping);
        }
    }
}