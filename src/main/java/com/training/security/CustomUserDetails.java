package com.training.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.training.entity.UserInfoEntity;

public class CustomUserDetails implements UserDetails {

	private UserInfoEntity user;
	public CustomUserDetails(UserInfoEntity user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
//		grantedAuths.add(new SimpleGrantedAuthority(user.getUserRole()));
//		return grantedAuths;
		return Collections.singleton(new SimpleGrantedAuthority(user.getUserRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return StringUtils.EMPTY;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
