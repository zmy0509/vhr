package org.qingqiao.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.qingqiao.vhr.bean.Hr;
import org.qingqiao.vhr.service.Impl.HrService;
import org.qingqiao.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private HrService hrService;
    @Autowired
    private MyFilterInvocation myFilterInvocation;
    @Autowired
    private MyDecisionManager myDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(myDecisionManager);
                        object.setSecurityMetadataSource(myFilterInvocation);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    //设置响应的类型,和编码格式
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    //获取当前登录用户的信息
                    Hr hr = (Hr) authentication.getPrincipal();
                    //为了保证安全,用空字符串覆盖掉密码
                    hr.setPassword("");
                    //调用我们自己写好的工具类OK方法
                    //status表示状态码
                    //hr是我们登录的用户信息
                    ResponseBean ok = ResponseBean.ok("登陆成功",hr);
                    //使用jackson 转换json字符串
                    ObjectMapper objectMapper = new ObjectMapper();
                    //转换
                    String s = objectMapper.writeValueAsString(ok);
                    //将json数据响应给调用者
                    httpServletResponse.getWriter().println(s);
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    ResponseBean error = ResponseBean.error(e.getMessage());
                    if (e instanceof LockedException){
                        error.setMsg("账户被锁定,请联系管理员!");
                    }else if (e instanceof CredentialsExpiredException){
                        error.setMsg("密码过期,请联系管理员!");
                    }else if (e instanceof AccountExpiredException){
                        error.setMsg("账户过期,请联系管理员!");
                    }else if (e instanceof DisabledException){
                        error.setMsg("账户禁用,请联系管理员!");
                    }else if (e instanceof BadCredentialsException){
                        error.setMsg("用户名或密码错误,请重新输入");
                    }
                    //使用jackson 转换json字符串
                    ObjectMapper objectMapper = new ObjectMapper();
                    String s = objectMapper.writeValueAsString(error);
                    httpServletResponse.getWriter().println(s);
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    ObjectMapper objectMapper = new ObjectMapper();
                    ResponseBean ok = ResponseBean.ok("注销成功");
                    String s = objectMapper.writeValueAsString(ok);
                    httpServletResponse.getWriter().println(s);
                })
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
    }
}
