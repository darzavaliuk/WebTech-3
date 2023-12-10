package by.bsuir.cinema.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.cinema.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public CustomUserDetailsServiceImpl() {
	}

	public CustomUserDetailsServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		by.bsuir.cinema.domain.User user = userDao.read(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
		return new User(user.getLogin(), user.getPassword(), true, true, true, true, authorities);
	}
}
