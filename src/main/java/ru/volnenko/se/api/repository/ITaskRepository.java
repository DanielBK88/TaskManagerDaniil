package ru.volnenko.se.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volnenko.se.entity.Task;

/**
 * @author Denis Volnenko
 */
public interface ITaskRepository extends JpaRepository<Task, String> {
    
}
