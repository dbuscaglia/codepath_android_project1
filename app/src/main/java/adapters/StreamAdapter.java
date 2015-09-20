package adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.util.ArrayList;

import danbuscaglia.instagramclone.R;
import danbuscaglia.instagramclone.models.InstagramCommentModel;
import danbuscaglia.instagramclone.models.InstagramImageModel;

/**
 * Created by danbuscaglia on 9/15/15.
 */
public class StreamAdapter extends ArrayAdapter<InstagramImageModel> {
    private static class ViewHolder {
        ImageView picture;
        ImageView posterPicture;
        TextView caption;
        TextView posterName;
        TextView tvLikes;
        TextView tvTimePosted;
    }


    public StreamAdapter(Context context, ArrayList<InstagramImageModel> stream_items) {
        super(context, 0, stream_items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InstagramImageModel so = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.stream_item, parent, false);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.tvTimePosted = (TextView) convertView.findViewById(R.id.tvTimePosted);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tvCaption);
            viewHolder.posterPicture = (ImageView) convertView.findViewById(R.id.ivPosterImage);
            viewHolder.posterName = (TextView) convertView.findViewById(R.id.tvPosterName);
            viewHolder.tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Lookup view for data population
        viewHolder.picture.setImageResource(0);
        Picasso.with(getContext()).load(so.getUrl()).into(viewHolder.picture);
        viewHolder.caption.setText(so.getCaption());
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(0)
                .cornerRadiusDp(30)
                .oval(false)
                .build();


        Picasso.with(getContext())
                .load(so.getUserProfilePic_url())
                .fit()
                .transform(transformation)
                .into(viewHolder.posterPicture);
        viewHolder.posterName.setText(so.getUser_name());
        viewHolder.tvLikes.setText(so.getLikesString());
        viewHolder.tvTimePosted.setText(so.getFormmatedInstagramTimestamp());
        // Return the completed view to render on screen
        viewHolder.caption.setText(so.getCaption());
        return convertView;
    }

}
