package com.training.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.dao.IUserDao;
import com.training.entity.UserInfoEntity;
import com.training.service.UserInfoService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


	@Autowired
	UserInfoService userInfoService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

			
			UserInfoEntity user = userInfoService.login(username);
			if(user == null) {
				throw new UsernameNotFoundException("User not found");
			}
//			GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole());
//			UserDetails userDetails = new User(username, StringUtils.EMPTY, Arrays.asList(authority));
			return new CustomUserDetails(user);
	}

	
}
