package com.ganeshgc.socialmediablog_app.service.impl;


import com.ganeshgc.socialmediablog_app.model.RoleEntity;
import com.ganeshgc.socialmediablog_app.model.UserEntity;
import com.ganeshgc.socialmediablog_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class SocialMediaBlogCustomizedUserDetailService implements UserDetailsService {

  private UserRepository userRepository;

    public SocialMediaBlogCustomizedUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        //load user by username or email from the db
        UserEntity userEntity=userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("Username or email address not found : " + usernameOrEmail));
       //fetch all the roles of a user
        Set<RoleEntity> userRoles=userEntity.getRoles();
        //convert user roles into grantedAuthorities
        Set<GrantedAuthority> grantedAuthoritySet=userRoles.stream()
                .map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        //create spring security user
        User user=new User(userEntity.getUsername(),userEntity.getPassword(),grantedAuthoritySet);
        return user;
    }
}
