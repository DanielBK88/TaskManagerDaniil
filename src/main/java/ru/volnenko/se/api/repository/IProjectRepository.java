package ru.volnenko.se.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.volnenko.se.entity.Project;

/**
 * @author Denis Volnenko
 */
public interface IProjectRepository extends JpaRepository<Project, String> {
}

