package To_do_list.service.impl;

import To_do_list.entity.TaskEntity;
import To_do_list.exception.IdNotFoundException;
import To_do_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements To_do_list.service.TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void saveTask(String name, String description, boolean completed, int priority) {
        TaskEntity taskEntity = new TaskEntity(name, description, completed, priority);
        taskRepository.save(taskEntity);
    }

    @Override
    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }else {
            throw new IdNotFoundException();
        }
    }

    @Override
    public void changeName(Long id, String name) {
        taskRepository.findById(id).ifPresentOrElse(x -> {x.setName(name); taskRepository.save(x);}, () -> {throw new IdNotFoundException();});
    }

    @Override
    public void changeDescription(Long id, String description) {
        taskRepository.findById(id).ifPresentOrElse(x -> {x.setDescription(description); taskRepository.save(x);}, () -> {throw new IdNotFoundException();});
    }

    @Override
    public void changeCompleted(Long id, boolean completed) {
        taskRepository.findById(id).ifPresentOrElse(x -> {x.setCompleted(completed); taskRepository.save(x);}, () -> {throw new IdNotFoundException();});
    }

    @Override
    public void changePriority(Long id, int priority) {
        taskRepository.findById(id).ifPresentOrElse(x -> {x.setPriority(priority); taskRepository.save(x);}, () -> {throw new IdNotFoundException();});
    }

    @Override
    public TaskEntity displayTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    @Override
    public List<TaskEntity> displayAllTask() {
        return taskRepository.findAll();
    }
}
