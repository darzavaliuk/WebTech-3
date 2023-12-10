package by.bsuir.cinema.service;

import java.util.List;

import by.bsuir.cinema.domain.Role;

public interface RoleService extends Service {

	List<Role> getRoleList();

	void createRole(Role role);

	Role readRole(int id);

	void updateRole(Role role);

	void deleteRole(Role role);

	public boolean isAnyFilmContainGenre(int id);
}
