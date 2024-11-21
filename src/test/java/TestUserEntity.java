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

    @Test
    void createUser() {

        UserDAO userDAO = new UserDAOImpl();
        // Long id, String nickname, String mail, String password
        User user = new User(null,"usuario1","ejemplo@example.com","s12345678");
        userDAO.create(user);

        User userDB = userDAO.findByEmail("ejemplo@example.com");
        System.out.println(userDB);

    }

    @Test
    void updateUser() {
        UserDAO userDAO = new UserDAOImpl();

    }
}
