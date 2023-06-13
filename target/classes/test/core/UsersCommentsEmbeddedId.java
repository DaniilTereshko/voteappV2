package test.core;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsersCommentsEmbeddedId {
    private static final long serialVersionUID = 1L;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "comments_id")
    private Long commentId;

    public UsersCommentsEmbeddedId() {
    }

    public UsersCommentsEmbeddedId(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersCommentsEmbeddedId that = (UsersCommentsEmbeddedId) o;

        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        return getCommentId() != null ? getCommentId().equals(that.getCommentId()) : that.getCommentId() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getCommentId() != null ? getCommentId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsersCommentsEmbeddedId{" +
                "userId=" + userId +
                ", commentId=" + commentId +
                '}';
    }
}
