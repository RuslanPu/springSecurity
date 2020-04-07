package web.dao;

import web.model.Role;
import web.model.User;

public interface UserDetailsDao {

    User getUserByName(String username);

}
