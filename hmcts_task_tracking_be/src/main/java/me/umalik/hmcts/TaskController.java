package me.umalik.hmcts;

import java.util.List;

import me.umalik.hmcts.db.TaskRepository;
import me.umalik.hmcts.domain.Task;
import me.umalik.hmcts.domain.Status;
import me.umalik.hmcts.utils.TaskNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private final TaskRepository repository;

    TaskController(TaskRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/tasks")
    @CrossOrigin(origins = "*")
    List<Task> all()
    {
        return repository.findAll();
    }

    @PostMapping("/tasks")
    @CrossOrigin(origins = "*")
    Task newTask(@RequestBody Task task)
    {
        return repository.save(task);
    }

    @GetMapping("/tasks/{id}")
    @CrossOrigin(origins = "*")
    Task oneTask(@PathVariable Long id)
    {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PutMapping("/tasks/{id}")
    @CrossOrigin(origins = "*")
    Task replaceTask(@RequestBody Task newTask, @PathVariable Long id)
    {
        return repository.findById(id)
            .map(task -> {
                task.setTitle(newTask.getTitle());
                task.setDescription(newTask.getDescription());
                task.setStatus(newTask.getStatus());
                task.setDueDate(newTask.getDueDate());
                return repository.save(task);
            })
            .orElseGet(() -> {
                return repository.save(newTask);
            });
    }

    @PatchMapping("/tasks/{id}/{taskStatus}")
    @CrossOrigin(origins = "*")
    Task updateTaskStatus(@PathVariable Long id, @PathVariable Status taskStatus)
    {
        return repository.findById(id)
            .map(task -> {
                task.setStatus(taskStatus);
                return repository.save(task);
            })
            .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @DeleteMapping("/tasks/{id}")
    @CrossOrigin(origins = "*")
    void deleteTask(@PathVariable Long id)
    {
        repository.deleteById(id);
    }
}