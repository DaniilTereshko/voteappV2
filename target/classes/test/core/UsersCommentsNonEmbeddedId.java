package test.core;

import java.io.Serializable;

public class UsersCommentsNonEmbeddedId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long commentsId;

    public UsersCommentsNonEmbeddedId() {
    }

    public UsersCommentsNonEmbeddedId(Long userId, Long commentsId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersCommentsNonEmbeddedId that = (UsersCommentsNonEmbeddedId) o;

        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        return getCommentsId() != null ? getCommentsId().equals(that.getCommentsId()) : that.getCommentsId() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getCommentsId() != null ? getCommentsId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsersCommentsNonEmbeddedId{" +
                "userId=" + userId +
                ", commentsId=" + commentsId +
                '}';
    }
}
