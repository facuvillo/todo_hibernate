import com.toDoHibernate.persistence.dao.ListDAO;
import com.toDoHibernate.persistence.dao.ListDAOImple;
import com.toDoHibernate.persistence.entities.ListTasks;
import org.junit.jupiter.api.Test;

public class TestListsTasks {


    @Test
    void testListTask() {
        ListDAO listDAO = new ListDAOImple();
        ListTasks list = listDAO.findByIdEager(1L);
        System.out.println(list.toString());
    }
}
