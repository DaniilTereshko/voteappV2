package test.core;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "comments", schema = "test_db")
public class Comment implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @SequenceGenerator(name = "comments_id_seq", sequenceName = "comments_id_seq", schema = "test_db", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "comments_id_seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Lob
    @Column(name = "description")
    private String description;

    public Comment() {
    }

    public Comment(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (getId() != null ? !getId().equals(comment.getId()) : comment.getId() != null) return false;
        return getDescription() != null ? getDescription().equals(comment.getDescription()) : comment.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
