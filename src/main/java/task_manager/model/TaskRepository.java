package task_manager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //
   List<Task> findAllByUserId(Long id);

   List<Task> findAllByTime(LocalDateTime time);

   List<Task> findAllByTimeBetween(LocalDateTime time1, LocalDateTime time2);
}
