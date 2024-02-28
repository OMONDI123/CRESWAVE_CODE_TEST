package com.example.demo.web.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.model.UserModel;
/**
 * @author austine
 *
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserModel) {
        	System.out.println("Loged In User Id==========>>>>"+Optional.of(((UserModel) principal).getId()));
            return Optional.of(((UserModel) principal).getId());
            
        } else {
            // Handle other cases if needed
            return Optional.empty();
        }
    }
}
