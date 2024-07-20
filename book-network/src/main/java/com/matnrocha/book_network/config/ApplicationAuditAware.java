package com.matnrocha.book_network.config;

import com.matnrocha.book_network.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * the Spring with @EnableJpaAuditing is able to fill @CreatedDate and @LastModifiedDate, but not @CreatedBy and @LastModifiedBy.
 * This class is and AuditorAware using the User Id
 */


public class ApplicationAuditAware implements AuditorAware<Integer> {
    /**
     * Return the user Id if available
     * @return Integer userId
     */
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        User userPrincipal = (User) auth.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());
    }
}
