package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}