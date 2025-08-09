package To_do_list.service;

import To_do_list.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    void saveTask(String name,String description,boolean completed,int priority);
    void deleteTask(Long id);
    void changeName(Long id,String name);
    void changeDescription(Long id,String description);
    void changeCompleted(Long id,boolean completed);
    void changePriority(Long id,int priority);
    TaskEntity displayTaskById(Long id);
    List<TaskEntity> displayAllTask();
}
