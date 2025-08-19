package com.example.taskmanager.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.Model.Task;
import com.example.taskmanager.Service.TaskService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "first/api/taskmanager")
public class TaskController {

    private final TaskService service;

    @GetMapping()
    public ResponseEntity<Map<String, String>> welcome() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Welcome to my task manager");
        return ResponseEntity.ok(body);
    }

    @GetMapping("/alltasks")
    public ResponseEntity<List<Task>> getTask(){
        return ResponseEntity.ok(service.getTask());
    }

    
    @PostMapping("/tasks")
    public ResponseEntity<String> addTask(@RequestBody Task tasks) {
        if(service.addTask(tasks)){
            return ResponseEntity.ok("Task has added!");
        }
        return new ResponseEntity<>("Cannot be add task!", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/delete={id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id){
        if(service.removeTask(id)){
            return ResponseEntity.ok("Task has removed!");
        }
        return new ResponseEntity<>("Cannot be removed!", HttpStatus.NOT_ACCEPTABLE);
    }
    
}
