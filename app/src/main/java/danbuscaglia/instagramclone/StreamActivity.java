package danbuscaglia.instagramclone;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Interfaces.InstagramAsyncReceiver;
import adapters.StreamAdapter;
import client.InstagramClient;
import danbuscaglia.instagramclone.models.InstagramImageModel;

public class StreamActivity extends AppCompatActivity implements InstagramAsyncReceiver {

    private SwipeRefreshLayout swipeContainer;
    private InstagramClient igClient;
    private StreamAdapter instagramStreamAdapter;
    private ArrayList<InstagramImageModel> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_activiry);
        this.initStream();

    }

    private void loadFeed() {
        if (this.igClient == null) {
            this.igClient = new InstagramClient(this);
        }
        this.igClient.getPopular();
    }

    private void initStream() {
        // Construct the data source
        ArrayList<InstagramImageModel> photos = new ArrayList<InstagramImageModel>();
        // Create the adapter to convert the array to views
        StreamAdapter streamAdapter = new StreamAdapter(this, photos);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvItems);
        listView.setAdapter(streamAdapter);
        this.instagramStreamAdapter = streamAdapter;
        this.photos = photos;
        this.loadFeed();
        this.swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFeed();
            }
        });

        // Configure the refreshing colors
        this.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stream_activiry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void handleDataCallback(ArrayList<InstagramImageModel> feed) {
        /**
         * handle instagram callback
         */
        this.photos.clear();
        for (InstagramImageModel item : feed) {
            this.photos.add((InstagramImageModel) item);
        }
        this.instagramStreamAdapter.notifyDataSetChanged();
        this.swipeContainer.setRefreshing(false);
    }
}
