package me.umalik.hmcts;

import me.umalik.hmcts.domain.Status;
import me.umalik.hmcts.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TaskController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService service;

    @Test
    void getTasks_returnMessageFromService() throws Exception
    {
        List<Task> listOfTasks = new ArrayList<>(1);
        Task theTask = new Task(
                "Demo Test Data",
                "Notice of change in representation.  Change the solicitor details.",
                Status.CANCELLED,
                LocalDateTime.now().plusDays(10));
        listOfTasks.add(theTask);

        String listOfTasksAsJsonStr = "[";

        StringBuilder taskAsJsonStr = new StringBuilder();

        for(Task task : listOfTasks)
        {
            taskAsJsonStr.append( toJsonString(task) );
        }

        listOfTasksAsJsonStr = listOfTasksAsJsonStr + taskAsJsonStr.toString() + "]";

        when( service.allTasks() ).thenReturn( listOfTasks );

        this.mockMvc.perform( get("/tasks") )
                .andDo( print() )
                .andExpect( status().isOk() )
                .andExpect( content().string( listOfTasksAsJsonStr ) );
    }

    private String toJsonString(Task task)
    {
        StringBuilder jsonStr = new StringBuilder("{");

        jsonStr.append("\"id\":");
        jsonStr.append(task.getId());
        jsonStr.append(",");

        jsonStr.append("\"title\":");
        jsonStr.append("\""+ task.getTitle() +"\"");
        jsonStr.append(",");

        jsonStr.append("\"description\":");
        jsonStr.append("\""+ task.getDescription() +"\"");
        jsonStr.append(",");

        jsonStr.append("\"status\":");
        jsonStr.append("\""+ task.getStatus() +"\"");
        jsonStr.append(",");

        jsonStr.append("\"dueDate\":");
        jsonStr.append("\""+ task.getDueDate() +"\"");

        jsonStr.append("}");

        return jsonStr.toString();
    }
}
