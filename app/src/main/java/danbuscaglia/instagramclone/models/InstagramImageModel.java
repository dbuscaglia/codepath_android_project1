package danbuscaglia.instagramclone.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import danbuscaglia.instagramclone.utils.InstagramDateUtils;

/**
 * Created by danbuscaglia on 9/18/15.
 */
public class InstagramImageModel {

    private String url;
    private String videoUrl;
    private String userProfilePic_url;

    private String user_name;
    private String caption;
    private String timestamp;

    private int likes;
    private int imageHeight;

    private ArrayList<InstagramCommentModel> comments;

    public static final int MAX_COMMENTS = 2;

    public String getUrl() {
        return url;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getCaption() {
        return caption;
    }

    public int getLikes() {
        return likes;
    }

    public String getLikesString() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedLikes = formatter.format(likes);
        return formattedLikes;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public InstagramImageModel(JSONObject imagedata) throws JSONException {

        this.user_name = imagedata.getJSONObject("user").getString("username");
        try {
            this.caption = imagedata.getJSONObject("caption").getString("text");
            this.timestamp = imagedata.getJSONObject("caption").getString("created_time");
        } catch (JSONException e) {
            this.caption = "";
            this.timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
        }

        this.url = imagedata.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
        this.imageHeight = imagedata.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
        this.likes = imagedata.getJSONObject("likes").getInt("count");
        this.userProfilePic_url = imagedata.getJSONObject("user").getString("profile_picture");
        //if (imagedata.has("videos")) {
        //    this.videoUrl = imagedata.getJSONObject("videos").getString("url");
        //} else {
        //    this.videoUrl = null;
        //}
        this.extract_comments(imagedata.getJSONObject("comments").getJSONArray("data"));
    }

    public String getUserProfilePic_url() {
        return userProfilePic_url;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    private void extract_comments(JSONArray comment_array) {
        this.comments = new ArrayList<InstagramCommentModel>();

        //for (int i=0; i < this.MAX_COMMENTS; i++) {
        //    comments.add(this._extract_comment(comment_array.get(i)))
        //}
        InstagramCommentModel comment = new InstagramCommentModel("testuser",
                                                                  "this is to test formatting");
        comments.add(comment);
    }


    public ArrayList<InstagramCommentModel> getComments() {
        return comments;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getFormmatedInstagramTimestamp() {
        return InstagramDateUtils.igFormattedDate(this.timestamp);
    }
}
