package com.training.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.training.common.util.CommonUtil;
import com.training.dao.IUserDao;
import com.training.entity.UserInfoEntity;
import com.training.service.UserInfoService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	@Autowired
	UserInfoService userInfoService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UsernamePasswordAuthenticationToken usernamePassAuthToken = null;

		UserInfoEntity userInfo = userInfoService.login(username, password);
		if (userInfo!=null) {
			//CommonUtil.setSessionAttribute("userInfo", userInfo);
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			grantedAuths.add(new SimpleGrantedAuthority(userInfo.getUserRole()));
			usernamePassAuthToken = new UsernamePasswordAuthenticationToken(username, StringUtils.EMPTY, grantedAuths);
		}
		return usernamePassAuthToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}