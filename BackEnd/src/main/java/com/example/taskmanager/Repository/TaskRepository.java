package com.example.taskmanager.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

    @Query("SELECT s FROM Task s WHERE s.id = ?1")
        Optional<Task> findTaskById (int id);

    @Query("SELECT s FROM Task s WHERE s.tasks = ?1")
        Optional<Task> findTaskByTasks (String tasks);
}
