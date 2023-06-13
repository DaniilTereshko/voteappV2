package test.core;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "user_information", schema = "test_db")
public class UserInformation implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "user_information_id_seq", sequenceName = "user_information_id_seq", schema = "test_db", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "user_information_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "money")
    private BigDecimal money;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserInformation() {
    }

    public UserInformation(Long id, BigDecimal money, User user) {
        this.id = id;
        this.money = money;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInformation that = (UserInformation) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getMoney() != null ? !getMoney().equals(that.getMoney()) : that.getMoney() != null) return false;
        return getUser() != null ? getUser().equals(that.getUser()) : that.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getMoney() != null ? getMoney().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "id=" + id +
                ", money=" + money +
                ", userId=" + user +
                '}';
    }
}
