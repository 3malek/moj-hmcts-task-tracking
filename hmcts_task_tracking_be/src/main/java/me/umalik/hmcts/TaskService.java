package me.umalik.hmcts;

import org.springframework.stereotype.Service;
import me.umalik.hmcts.db.TaskRepository;
import me.umalik.hmcts.domain.Status;
import me.umalik.hmcts.domain.Task;
import me.umalik.hmcts.utils.TaskNotFoundException;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    TaskService(TaskRepository repository)
    {
        this.repository = repository;
    }

    List<Task> allTasks()
    {
        return repository.findAll();
    }

    Task saveTask(Task task)
    {
        return repository.save(task);
    }

    Task findTaskById(Long id)
    {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    Task replaceTask(Task newTask, Long id)
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

    Task updateTaskStatus(Long id, Status taskStatus)
    {
        return repository.findById(id)
                .map(task -> {
                    task.setStatus(taskStatus);
                    return repository.save(task);
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    void deleteTask(Long id)
    {
        repository.deleteById(id);
    }
}