package by.bsuir.cinema.dao;

import java.util.List;
import java.util.Map;

import by.bsuir.cinema.domain.User;

public interface UserDao extends BaseDao<User> {

	/*
	 * User read(String login);
	 * 
	 * User read(String email);
	 */

	User readAllWhereEq(Map<String,Object> map);

	List<User> readAllUsersWhereRoleIdPresent(int genreId);

	User read(String login);
}
