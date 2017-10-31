package org.demo.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.demo.constant.Attributes;
import org.demo.vo.CurrentUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;


@EnableWebSecurity
@Order(SecurityProperties.IGNORED_ORDER)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {
	public CustomSecurityConfig() {
		super();
	}
	
	private @Autowired UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/js/**", "/img/**", "/css/**", "/api/**").permitAll().anyRequest().authenticated()
				.and()
					.formLogin().loginPage("/user/login").usernameParameter("username")
					.successHandler(new LoginSuccessHanlder()).failureUrl("/user/login?error=true")
					.permitAll()
				.and()
					.logout().logoutUrl("/user/logout").logoutSuccessUrl("/user/login")
				.and()
					.csrf().disable();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

//	private class LoginFailedHandler extends SimpleUrlAuthenticationFailureHandler {
//		
//		@Override
//		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//				AuthenticationException exception) throws IOException, ServletException {
//			request.getSession().setAttribute(Attributes.AUTHORIZED_FAIL, "User name or password is not correct.");
//			
//			response.sendRedirect("/user/login?error");
//		}
//	}

	private class LoginSuccessHanlder extends SimpleUrlAuthenticationSuccessHandler {
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
				throws IOException, ServletException {
			
			UserDetails user = (UserDetails) auth.getPrincipal();		
			Set<String> authorities = AuthorityUtils.authorityListToSet(user.getAuthorities());
			
			CurrentUserVo currentUser = new CurrentUserVo();
			currentUser.setUsername(user.getUsername());
			currentUser.setAuthorities(authorities);
			
			HttpSession session = request.getSession();
			session.setAttribute(Attributes.CURRENT_USER, currentUser);
			
			SavedRequest savedRequest = requestCache.getRequest(request, response);
			if (savedRequest == null) {
				response.sendRedirect("/user/dashboard");
				return;
			}
			
			String targetUrlParameter = getTargetUrlParameter();
			if (isAlwaysUseDefaultTargetUrl()
					|| (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
				requestCache.removeRequest(request, response);
				super.onAuthenticationSuccess(request, response, auth);
				return;
			}
			clearAuthenticationAttributes(request);
			String targetUrl = savedRequest.getRedirectUrl();
			getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}

		private RequestCache requestCache = new HttpSessionRequestCache();
	}
}
