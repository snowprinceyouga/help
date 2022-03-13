package ru.edu.helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class HelpdeskUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User userInfo = userRepository.findByLogin(username);

        if (userInfo == null){
            throw new UsernameNotFoundException("User with login: " + username + " not found!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole()));

        return new org.springframework.security.core.userdetails.User(userInfo.getLogin(), userInfo.getPassword(), authorities);
    }
}
