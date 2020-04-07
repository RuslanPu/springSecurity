package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.dao.UserDetailsDao;
import web.model.Role;
import web.model.User;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDetailsDao.getUserByName(email);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(email);
            builder.password(user.getPassword());
            String[] authorities = user.getAuthorities()
                    .stream().map(a -> a.getAuthority()).toArray(String[]::new);

            builder.authorities(authorities);

        } else {
            throw new UsernameNotFoundException("User not found!");
        }
        return userDetailsDao.getUserByName(email);
    }
}
