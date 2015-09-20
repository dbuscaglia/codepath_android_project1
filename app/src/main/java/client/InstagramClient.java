package client;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Interfaces.InstagramAsyncReceiver;
import danbuscaglia.instagramclone.models.InstagramImageModel;

/**
 * Created by danbuscaglia on 9/18/15.
 */
public class InstagramClient extends JsonHttpResponseHandler {

    public final String _CLIENT_ID = "e421c0867da24bb2adbec280f70409a8";
    public final String _CLIENT_SECRET = "a801f35006df49feb277c0a12b0fba78";

    public final String _POPULAR_URL = "https://api.instagram.com/v1/media/popular?client_id=" +
                                        this._CLIENT_ID;

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Log.i("DEBUG", response.toString());
        JSONArray photosJSON = null;
        ArrayList<InstagramImageModel> feed = new ArrayList<>();
        try {

            photosJSON = response.getJSONArray("data");
            for (int i = 0; i < photosJSON.length(); i++) {
                JSONObject photo = photosJSON.getJSONObject(i);
                InstagramImageModel instagramPhoto = new InstagramImageModel(photo);
                feed.add(instagramPhoto);
            }
            this._receiver.handleDataCallback(feed);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                          JSONObject errorResponse) {
        Log.i("DEBUG", errorResponse.toString());
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }

    private AsyncHttpClient _http;
    private InstagramAsyncReceiver _receiver;

    public InstagramClient(InstagramAsyncReceiver caller) {
        this._http = new AsyncHttpClient();
        this._receiver = caller;
    }
    public void getPopular() {
        this._http.get(this._POPULAR_URL, null, this);
    }

}
