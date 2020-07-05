package ru.volnenko.se.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volnenko.se.entity.role.UserRole;

public interface IRoleRepository extends JpaRepository<UserRole, String> {

}
