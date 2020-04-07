package web.dao;

import web.model.Role;

public interface RoleDao {
    void add(Role role);
    Role getRoleById(Long id);
}
