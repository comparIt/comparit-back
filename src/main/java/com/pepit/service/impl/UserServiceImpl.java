package com.pepit.service.impl;

import com.pepit.constants.Roles;
import com.pepit.converters.UserConverter;
import com.pepit.dto.UserDto;
import com.pepit.model.User;
import com.pepit.repository.UserRepository;
import com.pepit.security.Hashing;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(this.fromRoleListToCommaSeparatedRoles(user)));
    }

    @Override
    public UserDto create(UserDto userDto) {
        return createUser(userDto,Roles.ROLE_CUSTOMER);
    }

    @Override
    public User getUserByToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
    }

    @Override
    public UserDto getUserDtoByToken() {
        return userConverter.entityToDto(getUserByToken());
    }

    @Override
    public User updateToSupplier(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Can't find user"));
        user.getRoles().add(Roles.ROLE_SUPPLIER);
        return userRepository.save(user);
    }

    @Override
    public UserDto createSupplier(UserDto userDto) {
        return createUser(userDto,Roles.ROLE_SUPPLIER);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("Can't find user"));
    }


    private String fromRoleListToCommaSeparatedRoles(User user){
        return user.getRoles().stream().map(Enum::name).collect(Collectors.joining(","));
    }

    private UserDto createUser(UserDto userDto, Roles roles){
        User user = userConverter.dtoToEntity(userDto);
        user.setPassword(this.bCryptPasswordEncoder.encode(Hashing.sha256(userDto.getPassword())));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setCreatedAt(LocalDateTime.now());
            user.setRoles(Collections.singletonList(
                    roles
            ));
        }
        this.userRepository.save(user);
        return userConverter.entityToDto(user);
    }


}
