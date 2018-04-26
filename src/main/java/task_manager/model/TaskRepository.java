package task_manager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //

    //@Query("SELECT f FROM Tasks f WHERE LOWER(f.name) = LOWER(:name)")
   // Task findByName(@Param("name") String name);
}
