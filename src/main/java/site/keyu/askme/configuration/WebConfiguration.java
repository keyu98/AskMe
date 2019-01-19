package site.keyu.askme.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import site.keyu.askme.interceptor.NeedLoginInterceptor;
import site.keyu.askme.interceptor.PassportInterceptor;

/**
 * @Author:Keyu
 * 配置拦截器
 */
@Component
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    NeedLoginInterceptor needLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(needLoginInterceptor).addPathPatterns("/user/*");
        super.addInterceptors(registry);
    }
}
