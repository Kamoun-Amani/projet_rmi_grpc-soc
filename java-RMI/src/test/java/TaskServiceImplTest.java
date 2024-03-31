import com.common.TaskService;
import com.server.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceImplTest {
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        try {
            taskService = new TaskServiceImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddTask() throws RemoteException {
        taskService.addTask("Task 1");
        List<String> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Task 1", tasks.get(0));
    }

    @Test
    public void testRemoveTask() throws RemoteException {
        taskService.addTask("Task 1");
        taskService.addTask("Task 2");
        taskService.removeTask("Task 1");
        List<String> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Task 2", tasks.get(0));
    }

    @Test
    public void testGetAllTasks() throws RemoteException {
        taskService.addTask("Task 1");
        taskService.addTask("Task 2");
        List<String> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0));
        assertEquals("Task 2", tasks.get(1));
    }
}
