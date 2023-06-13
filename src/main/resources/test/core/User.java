package test.core;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "test_db")

@NamedQuery(name = "User.findAll", query = "SELECT u From User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", schema = "test_db",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 35)
    @Column(name = "username")
    @Pattern(regexp = "", message = "Invalid Username")
    private String username;
    @Size(max = 35)
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserInformation> userInformationId = new HashSet<>();
    @JoinTable(name = "user_task",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "task_id"), schema = "test_db")
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Task> task = new HashSet<>();

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<UserInformation> getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(UserInformation userInformation) {
        this.userInformationId.add(userInformation);
        userInformation.setUser(this);
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task.add(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
