package me.umalik.hmcts.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Task {

    private @Id @GeneratedValue Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime dueDate;

    Task() {}

    public Task(String title, String description, Status status, LocalDateTime dueDate)
    {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate.truncatedTo(ChronoUnit.SECONDS);
    }

    public Long getId() {
        return this.id;
    }

    public Status getStatus() {
        return status;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof Task))
            return false;
        Task task = (Task) o;
        return Objects.equals(this.id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString()
    {
        return "Task {" + "id=" + this.id +
                ", title='" + this.title + '\'' +
                ", description='" + this.description + '\'' +
                ", status='" + this.status + '\'' +
                ", dueDate='" + this.dueDate + '\'' +
                '}';
    }
}