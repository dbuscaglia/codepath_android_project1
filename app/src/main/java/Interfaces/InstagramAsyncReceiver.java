package Interfaces;

import java.util.ArrayList;

import danbuscaglia.instagramclone.models.InstagramImageModel;

/**
 * Created by danbuscaglia on 9/19/15.
 *
 * TODO: could generealize this interface for the future.
 */
public interface InstagramAsyncReceiver {

    void handleDataCallback(ArrayList<InstagramImageModel> feed);
}
