package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ActuatorConfig implements WebMvcConfigurer {
    @Bean
    public ActuatorConfigurer actuatorConfigurer(WebMvcEndpointHandlerMapping mapping) {
        ActuatorConfigurer configurer = new ActuatorConfigurer(mapping, interceptor());
        configurer.configure();
        return configurer;
    }

    @Bean
    public Interceptor interceptor() {
        return new Interceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor()).addPathPatterns("/**/*");
    }

    public static class ActuatorConfigurer {
        private final WebMvcEndpointHandlerMapping mapping;
        private final Interceptor interceptor;

        public ActuatorConfigurer(WebMvcEndpointHandlerMapping mapping, Interceptor interceptor) {
            this.mapping = mapping;
            this.interceptor = interceptor;
        }

        void configure() {
            System.out.println("**** ADDING INTERCEPTOR " + interceptor + " TO MAPPING " + mapping + " ****");
            this.mapping.setInterceptors(this.interceptor);
        }
    }

    public static class Interceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Object handler) throws Exception {
            System.out.println("**** PRE-HANDLING " + request.getRequestURI() + " ****");
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request,
                               HttpServletResponse response,
                               Object handler,
                               ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Object handler,
                                    Exception ex) throws Exception {

        }
    }
}
