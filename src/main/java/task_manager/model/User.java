package task_manager.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Task> tasks = new HashSet<Task>();

    public User(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User that = (User) obj;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "[id=" + this.id + ", name=" + this.name + ", username=" + this.username + ", password=" + this.password + "]";
    }
}
