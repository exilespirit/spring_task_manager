package task_manager.model;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<User, Long> {
}
