package com.ibagroup.web.security;

import com.ibagroup.common.dao.postgres.model.User;
import com.ibagroup.common.dao.postgres.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String filterUsername) throws UsernameNotFoundException {
        String username = null;
        String password = null;
        List<GrantedAuthority> authorities;

        Optional<User> userOptional = userRepository.findUserByUsername(filterUsername);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User details not found for the user: " + filterUsername);
        } else {
            User user = userOptional.get();
            username = user.getUsername();
            password = user.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(username, password,authorities);
    }

}
