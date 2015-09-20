package danbuscaglia.instagramclone.utils;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import android.text.format.DateUtils;

public class InstagramDateUtils {
    /**
     * Borrowed from Hasham Ali, very useful module.
     *
     */
    private static DateFormat _df = new SimpleDateFormat("K:mm a | MMMMMMMMM dd, yyyy");

    public static String formattedDate(String timestamp) {
        return _df.format(new Date(Long.parseLong(timestamp) * 1000L));
    }

    public static String igFormattedDate(String timestamp) {
        /**
         * From the group discussion board.
         */
        CharSequence relativeDateTimeString =  DateUtils.getRelativeTimeSpanString(
                Long.parseLong(timestamp) * 1000, System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS);
        return relativeDateTimeString.toString().replaceAll("[^0-9]+", "s");
    }

}
