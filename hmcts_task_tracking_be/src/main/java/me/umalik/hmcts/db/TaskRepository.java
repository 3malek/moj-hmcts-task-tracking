package me.umalik.hmcts.db;

import me.umalik.hmcts.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
