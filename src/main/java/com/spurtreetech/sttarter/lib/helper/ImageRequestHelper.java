package com.spurtreetech.sttarter.lib.helper;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.spurtreetech.sttarter.lib.R;

/**
 * Created by rahul on 19/08/15.
 */
public class ImageRequestHelper {

    public static void setImageToImageView(String url, final ImageView mImageView) {

        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        mImageView.setImageResource(R.drawable.ic_launcher);
                    }
                });

        // Access the RequestQueue through your singleton class.
        RequestQueueHelper.addToRequestQueue(request, "");
    }

}
