package com.hebust;

import com.hebust.filter.CrosFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@MapperScan("com.hebust.mapper")
@SpringBootApplication
public class AssistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistanceApplication.class, args);
    }

    /**
     * 配置跨域访问的过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/*");
        bean.setFilter(new CrosFilter());
        return bean;
    }
}
