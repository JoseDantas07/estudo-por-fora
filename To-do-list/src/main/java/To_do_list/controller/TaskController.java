package To_do_list.controller;

import To_do_list.entity.TaskEntity;
import To_do_list.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @PostMapping
    public void saveTask(String name,String description,boolean completed,int priority){
        taskService.saveTask(name, description, completed, priority);
    }

    @GetMapping()
    public List<TaskEntity> displayAllTasks(){
      return taskService.displayAllTask();
    }

    @GetMapping("/{id}")
    public TaskEntity displayTaskById(@PathVariable Long id){
       return taskService.displayTaskById(id);
    }

    @PutMapping("/{id}/name")
    public void changeName(@PathVariable Long id,@RequestParam String name){
        taskService.changeName(id,name);
    }

    @PutMapping("/{id}/description")
    public void changeDescription(@PathVariable Long id,@RequestParam String description){
        taskService.changeDescription(id,description);
    }

    @PutMapping("/{id}/completed")
    public void changeCompleted(@PathVariable Long id,@RequestParam boolean completed){
        taskService.changeCompleted(id,completed);
    }

    @PutMapping("/{id}/priority")
    public void changePriority(@PathVariable Long id,@RequestParam int priority){
        taskService.changePriority(id,priority);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
