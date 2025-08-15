package me.umalik.hmcts;

import java.util.List;

import me.umalik.hmcts.domain.Task;
import me.umalik.hmcts.domain.Status;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private final TaskService service;

    TaskController(TaskService service)
    {
        this.service = service;
    }

    @GetMapping("/tasks")
    @CrossOrigin(origins = "*")
    List<Task> allTasks()
    {
        return service.allTasks();
    }

    @PostMapping("/tasks")
    @CrossOrigin(origins = "*")
    Task newTask(@RequestBody Task task)
    {
        return service.saveTask(task);
    }

    @GetMapping("/tasks/{id}")
    @CrossOrigin(origins = "*")
    Task oneTask(@PathVariable Long id)
    {
        return service.findTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    @CrossOrigin(origins = "*")
    Task updateTask(@RequestBody Task newTask, @PathVariable Long id)
    {
        return service.replaceTask(newTask, id);
    }

    @PatchMapping("/tasks/{id}/{taskStatus}")
    @CrossOrigin(origins = "*")
    Task updateStatus(@PathVariable Long id, @PathVariable Status taskStatus)
    {
        return service.updateTaskStatus(id, taskStatus);
    }

    @DeleteMapping("/tasks/{id}")
    @CrossOrigin(origins = "*")
    void deleteTask(@PathVariable Long id)
    {
        service.deleteTask(id);
    }
}