package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDetailsDaoImp implements UserDetailsDao{

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public User getUserByName(String email) {

        User user = new User();

         return user = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.email = :paramMail", User.class)
                .setParameter("paramMail", email)
                .getSingleResult();

    }


}
