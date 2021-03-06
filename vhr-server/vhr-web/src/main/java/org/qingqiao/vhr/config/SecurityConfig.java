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
                    //?????????????????????,???????????????
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    //?????????????????????????????????
                    Hr hr = (Hr) authentication.getPrincipal();
                    //??????????????????,??????????????????????????????
                    hr.setPassword("");
                    //????????????????????????????????????OK??????
                    //status???????????????
                    //hr??????????????????????????????
                    ResponseBean ok = ResponseBean.ok("????????????",hr);
                    //??????jackson ??????json?????????
                    ObjectMapper objectMapper = new ObjectMapper();
                    //??????
                    String s = objectMapper.writeValueAsString(ok);
                    //???json????????????????????????
                    httpServletResponse.getWriter().println(s);
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    ResponseBean error = ResponseBean.error(e.getMessage());
                    if (e instanceof LockedException){
                        error.setMsg("???????????????,??????????????????!");
                    }else if (e instanceof CredentialsExpiredException){
                        error.setMsg("????????????,??????????????????!");
                    }else if (e instanceof AccountExpiredException){
                        error.setMsg("????????????,??????????????????!");
                    }else if (e instanceof DisabledException){
                        error.setMsg("????????????,??????????????????!");
                    }else if (e instanceof BadCredentialsException){
                        error.setMsg("????????????????????????,???????????????");
                    }
                    //??????jackson ??????json?????????
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
                    ResponseBean ok = ResponseBean.ok("????????????");
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
