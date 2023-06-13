package org.example.dao.classes.db.hibernate.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "genres")
public class GenreEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "genres_seq", sequenceName = "genres_id_seq",schema = "app", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "genres_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    public GenreEntity() {
    }

    public GenreEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreEntity that = (GenreEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
