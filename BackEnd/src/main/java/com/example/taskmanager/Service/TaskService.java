package com.example.taskmanager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.taskmanager.Model.Task;
import com.example.taskmanager.Repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository repository;

    public boolean addTask(Task tasks){
       Optional<Task> task = repository.findTaskByTasks(tasks.getTasks());
        if(task.isPresent()){
            throw new IllegalStateException("Already added");
        }
        repository.save(tasks);
        return true;
    }

    public boolean removeTask(int id){
        Task task = repository.findTaskById(id).get();
        if(task == null ){
            throw new IllegalStateException("There is no task to delete");
        }
        repository.delete(task);
        return true;
    }

    public List<Task> getTask() {
        return repository.findAll();
    }
}
