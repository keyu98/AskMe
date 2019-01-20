package site.keyu.askme.pojo;

/**
 * @Author:Keyu
 */
public class CommentViewObject {
    private User user;

    private Comment comment;

    private long like;

    private long dislike;

    private boolean haslike = false;

    private boolean hasdislike = false;

    public boolean isHaslike() {
        return haslike;
    }

    public void setHaslike(boolean haslike) {
        this.haslike = haslike;
    }

    public boolean isHasdislike() {
        return hasdislike;
    }

    public void setHasdislike(boolean hasdislike) {
        this.hasdislike = hasdislike;
    }

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }

    public long getDislike() {
        return dislike;
    }

    public void setDislike(long dislike) {
        this.dislike = dislike;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }


}
