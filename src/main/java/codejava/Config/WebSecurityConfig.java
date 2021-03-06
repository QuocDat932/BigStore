package codejava.Config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.util.UrlPathHelper;

import codejava.Constant.*;
import codejava.Constant.RoleConst;
import codejava.Jwt.JwtAuthenticationFilter;
import codejava.Services.impl.*;
import codejava.oauth2.CustomOAuth2UserService;
import codejava.oauth2.Oauth2LoginSuccess;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserServiceImpl customUserService;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomOAuth2UserService customoauthserv;
	@Autowired
	private Oauth2LoginSuccess Oauth2LoginSuccessHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		/*
		  	Su dung doan code nay de chan cac request tuong ung voi role ADMIN
		  	Khi xay dung chuc nang cho phia admin thi dung doan code nay de khong cho
		     cac user thong thuong duoc goi api admin va truy cap trang admin
		 http.authorizeRequests().antMatchers("/admin/**").hasAuthority(RoleConst.ROLE_ADMIN);
		 */
		http.authorizeRequests().antMatchers("/admin/**","/api/admin/**").hasAuthority(RoleConst.ROLE_ADMIN);
		http.authorizeRequests().and().rememberMe()
					.tokenRepository(this.persistentTokenRepository())
					.tokenValiditySeconds(30*60);
		/*login Google, FaceBook*/
		http.oauth2Login().loginPage("/login").userInfoEndpoint().userService(customoauthserv)
		.and().successHandler(Oauth2LoginSuccessHandler).and().logout().
		logoutSuccessHandler(new LogoutSuccessHandler() {
			
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				System.out.println("user " + authentication.getName());
				UrlPathHelper helper = new UrlPathHelper();
				String context = helper.getContextPath(request);
				response.sendRedirect(context);
			}
		}).permitAll() 
		;
	
		http.cors()
				.and().authorizeRequests()
				.antMatchers("/admin/**","/", "/home","/index", "/sanpham/**", "/replace/**","/cart/**", "/api/**","/register","/home/login","/type/**","/home/cart/**").permitAll() // Cho phep tat ca truy cap link nay
				.anyRequest().authenticated(); // Cac link con lai thi phai xac thuc
	
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/home/*", "/home/css/*", "/home/fonts/*", "/home/images/*", "/home/js/**","/home/video/*","/layout/**","/home/pay/*","/common/**");
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}
	@Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
	

	
}