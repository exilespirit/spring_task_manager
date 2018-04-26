package task_manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TaskID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "Type")
    private String type;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column (name = "Time")
    private Timestamp time;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTime() {return this.time; }

    public void setTime(Timestamp time) { this.time = time; }

    @Override
    public String toString() {
        return "[id=" + this.id + ", name=" + this.type + ", username=" + this.user + ", password=" + this.title +", description=" + this.description + "]";
    }


}
