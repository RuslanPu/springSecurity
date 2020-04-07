package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    public EntityManager entityManager;


    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUser() {
        return entityManager.createQuery("select user from User user").getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        if(user != null) {
            Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :paramId");
            query.setParameter("paramId",user.getId());
            query.executeUpdate();

        }

    }

    @Override
    public User getUserById(Long id) {
         return entityManager.find(User.class, id);
    }

    @Override
    public boolean unicEmail(String email) {
        List<String> listQuery =  entityManager.createQuery("select U.email from User U where U.email = :paramEmail")
                .setParameter("paramEmail", email)
                .getResultList();
        if (listQuery.size() > 0) {
            return false;
        } else {
            return true;
        }

    }
}
