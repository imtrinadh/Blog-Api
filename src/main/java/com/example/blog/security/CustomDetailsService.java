package com.example.blog.security;

import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       User user= userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username or email:"+usernameOrEmail));
        return new org.springframework.security.core.userdetails
                .User(user.getEmail(),user.getPassword(),mapRolesofAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesofAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
