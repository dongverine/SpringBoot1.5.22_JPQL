package com.dongverine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Slf4j
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /*
        SpringBoot1.5 에서는 extends WebMvcConfigurerAdapter를 한다. 2.0에서는 없어진 추상클래스
        SpringBoot2.x 에서는 implements WebMvcConfigurer 한다.
    */
    @Value("${resources.location_1}")
    private String resourcesLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("resourcesLocation : {}",resourcesLocation);
        registry.addResourceHandler("/exp/**")
                .addResourceLocations("file:"+resourcesLocation);

    }

//    @Override
//    public void addViewControllers(final ViewControllerRegistry registry) {
//        //registry.addViewController("/exp/c.jsp").setViewName("List");
//    }

//    @Bean
//    public ExpViewResolver getCustomInternalResourceViewResolver() {
//        ExpViewResolver resolver = new ExpViewResolver();
//        resolver.setPrefix("/custom/");
//        resolver.setSuffix(".jsp");
//        resolver.setOrder(0);
//        return resolver;
//    }

//    @Bean
//    public ExpUrlBasedViewResolver getCustomUrlBasedViewResolver() {
//        ExpUrlBasedViewResolver resolver = new ExpUrlBasedViewResolver();
//        resolver.setOrder(0);
//        return resolver;
//    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/board/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(1);
        return resolver;
    }
}
