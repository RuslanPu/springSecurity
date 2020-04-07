package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDAO;
import web.dao.UserDAOImpl;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void addRole(Role role) {
      roleDao.add(role);
    }

    @Override
    public boolean unicEmail(String email) {
        return userDAO.unicEmail(email);
    }

    @Transactional
    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Transactional
    @Override
    public void add(User user, String[] checkboxRoles) {
        List<Role> roles = new ArrayList<>();
        parseString(roles, checkboxRoles);
        user.setRoles(roles);
        userDAO.add(user);
    }

    @Transactional
    @Override
    public void edit(User user, String[] checkedRoles) {
        List<Role> roles = new ArrayList<>();
        parseString(roles,checkedRoles);
        user.setRoles(roles);
        userDAO.edit(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    void parseString(List<Role> roles, String[] checkboxRoles) {
        for (int i = 0; i < checkboxRoles.length; i ++) {
            if (checkboxRoles[i].equals("ROLE_USER")) {
                roles.add(roleDao.getRoleById(10L));
            }
            if (checkboxRoles[i].equals("ROLE_ADMIN")) {
                roles.add(roleDao.getRoleById(11L));
            }
        }
     }
}
