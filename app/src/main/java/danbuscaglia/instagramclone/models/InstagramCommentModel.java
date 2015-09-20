package danbuscaglia.instagramclone.models;

/**
 * Created by danbuscaglia on 9/19/15.
 */
public class InstagramCommentModel {
    private String user_name;
    private String comment_text;

    public InstagramCommentModel(String user_name, String comment_text) {
        this.user_name = user_name;
        this.comment_text = comment_text;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getComment_text() {
        return comment_text;
    }

}
