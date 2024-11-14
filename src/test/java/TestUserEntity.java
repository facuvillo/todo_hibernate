import com.toDoHibernate.persistence.dao.UserDAO;
import com.toDoHibernate.persistence.dao.UserDAOImpl;
import com.toDoHibernate.persistence.entities.User;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestUserEntity{

    @Test
    void FindUserTest() {

        UserDAO userDAO = new UserDAOImpl();

        List<User> users = userDAO.findAll();

        System.out.println(users);

    }

    @Test
    void findByEmail() {

        UserDAO userDAO = new UserDAOImpl();

        User user = userDAO.findByEmail("user1@example.com");

        System.out.println(user);

    }
}
