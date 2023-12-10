package by.bsuir.cinema.service.impl;

import java.util.List;

import by.bsuir.cinema.dao.RoleDao;
import by.bsuir.cinema.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsuir.cinema.domain.Role;
import by.bsuir.cinema.domain.User;
import by.bsuir.cinema.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
    RoleDao roleDao;

	@Autowired
    UserDao userDao;

	public RoleServiceImpl() {
	}

	public RoleServiceImpl(RoleDao roleDao, UserDao userDao) {
		this.roleDao = roleDao;
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<Role> getRoleList() {
		return roleDao.readAll("id");
	}

	@Override
	public void createRole(Role role) {
		roleDao.create(role);
	}

	@Override
	public Role readRole(int id) {
		return roleDao.read(id);

	}

	@Override
	public void updateRole(Role role) {
		roleDao.update(role);

	}

	@Override
	public void deleteRole(Role role) {
		roleDao.delete(role);
	}

	@Override
	public boolean isAnyFilmContainGenre(int id) {
		List<User> users = userDao.readAllUsersWhereRoleIdPresent(id);
		return users.size() != 0;
	}
}
