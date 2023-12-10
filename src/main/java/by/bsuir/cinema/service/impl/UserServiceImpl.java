package by.bsuir.cinema.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.bsuir.cinema.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsuir.cinema.domain.User;
import by.bsuir.cinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    UserDao userDao;

	public UserServiceImpl() {
	}

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getUserList() {
		return userDao.readAll("id");
	}

	@Override
	public void createUser(User user) {
		userDao.create(user);
	}

	@Override
	public User readUser(int id) {
		return userDao.read(id);
	}

	@Override
	public User readUser(String login) {
		return userDao.read(login);
	}

	@Override
	public User readUser(String parametre, Object value) {
		return readUser(new HashMap() {
			{
				put(parametre, value);
			}
		});
	}

	@Override
	public User readUser(Map<String, Object> map) {
		return userDao.readAllWhereEq(map);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

}
