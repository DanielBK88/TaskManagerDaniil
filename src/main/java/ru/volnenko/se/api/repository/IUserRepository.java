package ru.volnenko.se.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volnenko.se.entity.TMUser;

public interface IUserRepository extends JpaRepository<TMUser, String> {

}
