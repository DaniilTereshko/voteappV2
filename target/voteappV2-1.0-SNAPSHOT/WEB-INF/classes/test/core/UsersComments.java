package test.core;

import jakarta.persistence.*;

import java.io.Serializable;
@IdClass(UsersCommentsNonEmbeddedId.class)
@Entity
@Table(name = "users_comments", schema = "test_db")
public class UsersComments implements Serializable {
    private static final long serialVersionUID = 3L;
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "comments_id")
    private Long commentsId;

    public UsersComments() {
    }

    public UsersComments(Long userId, Long commentsId) {
        this.userId = userId;
        this.commentsId = commentsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(Long commentsId) {
        this.commentsId = commentsId;
    }
}
