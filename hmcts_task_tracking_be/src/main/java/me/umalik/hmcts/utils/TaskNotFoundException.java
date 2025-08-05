package me.umalik.hmcts.utils;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id)
    {
        super("Could not find task " + id);
    }
}
