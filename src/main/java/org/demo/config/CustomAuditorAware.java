package org.demo.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuditorAware implements AuditorAware<UserDetails> {

	@Override
	public UserDetails getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || ! authentication.isAuthenticated()) {
			return null;
		}
		
		Object principal = authentication.getPrincipal();
		return (principal instanceof UserDetails) ? (UserDetails) principal: null;
	}
}
