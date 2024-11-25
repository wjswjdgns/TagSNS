package cronWeb.cronWeb_Spring;

import cronWeb.cronWeb_Spring.argumentresolver.LoginMemberArgumentResolver;
import cronWeb.cronWeb_Spring.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // ArgumentResolver 추가
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    // 인터셉터를 추가한다. (조건을 붙여서)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/new/member" ,"/signup","/css/**","/js/**","/img/**", "/*.ico");
    }

}
