package test.core;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks", schema = "test_db")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", schema = "test_db",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Size(max = 35)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "task")
    private Set<User> users = new HashSet<>();

    public Task() {
    }

    public Task(Long id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task1 = (Task) o;

        if (getId() != null ? !getId().equals(task1.getId()) : task1.getId() != null) return false;
        if (getName() != null ? !getName().equals(task1.getName()) : task1.getName() != null) return false;
        return getUsers() != null ? getUsers().equals(task1.getUsers()) : task1.getUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }
}
