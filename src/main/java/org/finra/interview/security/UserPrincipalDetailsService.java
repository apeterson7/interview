package org.finra.interview.security;

import lombok.extern.log4j.Log4j2;
import org.finra.interview.models.User;
import org.finra.interview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Log4j2
@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String username)throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        if (userPrincipal != null) {
            log.info("loadUserByUsername: found match, returning "
                    + userPrincipal.getUsername() + ":" + userPrincipal.getPassword() + ":"
                    + userPrincipal.getAuthorities().toString());
            return userPrincipal;
        }

        log.info("loadUserByUsername: did not find match, throwing UsernameNotFoundException");
        throw new UsernameNotFoundException(username);
    }

    public UserDetails getLoggedInUser(){

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken))
        {
            if(auth.getDetails() !=null)
                System.out.println(auth.getDetails().getClass());
            if( auth.getDetails() instanceof UserDetails)
            {
                System.out.println("UserDetails");
            }
            else
            {
                System.out.println("!UserDetails");
            }
        }
        return null;
    }
}