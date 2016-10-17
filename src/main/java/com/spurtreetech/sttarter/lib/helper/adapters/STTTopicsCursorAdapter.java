package com.spurtreetech.sttarter.lib.helper.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.spurtreetech.sttarter.lib.R;
import com.spurtreetech.sttarter.lib.helper.Keys;
import com.spurtreetech.sttarter.lib.helper.PreferenceHelper;
import com.spurtreetech.sttarter.lib.helper.STTGeneralRoutines;
import com.spurtreetech.sttarter.lib.helper.STTarter;
import com.spurtreetech.sttarter.lib.helper.models.TopicMeta;
import com.spurtreetech.sttarter.lib.helper.volley.CircularNetworkImageView;
import com.spurtreetech.sttarter.lib.provider.STTProviderHelper;
import com.spurtreetech.sttarter.lib.provider.topics.TopicsColumns;
import com.spurtreetech.sttarter.lib.provider.topics.TopicsCursor;

public class STTTopicsCursorAdapter extends CursorRecyclerAdapter<DiscoverTopicsViewHolder> implements View.OnClickListener {

    STTProviderHelper ph;
    STTGeneralRoutines gr;
    Gson gson;
    public STTTopicsCursorAdapter(Cursor c) {
        super(c);
        ph = new STTProviderHelper();
        gr = new STTGeneralRoutines();
        gson = new Gson();
    }

    @Override
    public void onBindViewHolder(DiscoverTopicsViewHolder holder, Cursor cursor) {
        holder.topicMeta = gson.fromJson(cursor.getString(cursor.getColumnIndex(TopicsColumns.TOPIC_META)), TopicMeta.class);

        holder.topicName = cursor.getString(cursor.getColumnIndex(TopicsColumns.TOPIC_NAME));
        if(URLUtil.isValidUrl(holder.topicMeta.getImage()) && holder.topicMeta.getImage() != null) {
            try {
                holder.topicImageView.setImageUrl(holder.topicMeta.getImage(),STTarter.getInstance().getImageLoader());
                holder.topicImageView.setErrorImageResId(R.drawable.ic_launcher);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            //ImageRequestHelper.setImageToImageView(holder.topicMeta.getImage(), holder.topicImageView);
        }
        else {
            holder.topicImageView.setImageResource(R.drawable.ic_launcher);
        }

        holder.topicTitleTextView.setText(holder.topicMeta.getName());
        // TODO select the latest message for the topic name
        //holder.latestMsgTextView.setText(ph.getLatestMessage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TOPIC_NAME))));
        holder.latestMsgTextView.setText(holder.topicMeta.getGroup_desc());
        Log.d("STTTopicsCursorAdapter", "topic checked for status - " + holder.topicName);

        TopicsCursor tc = ph.getTopicData(holder.topicName);
        if(tc != null && tc.getCount() > 0) {
            tc.moveToFirst();
            holder.subscribed = (tc.getBooleanOrNull(TopicsColumns.TOPIC_IS_SUBSCRIBED)) ? 1 : 0;
        } else {
            holder.subscribed = 0;
        }

        if (holder.subscribed == 1){
            holder.clickButton.setVisibility(View.GONE);
            holder.subscribedText.setVisibility(View.VISIBLE);
        }
        else {
            holder.clickButton.setBackgroundResource(R.drawable.ic_navigation_check);
            holder.subscribedText.setVisibility(View.GONE);
        }

        //holder.clickButton.setBackgroundResource((holder.subscribed == 1 ? R.drawable.ic_navigation_check : R.drawable.ic_navigation_close));

        holder.clickButton.setOnClickListener(this);

        /*holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        // Drag From Left
        //holder.swipeLayout.addRevealListener(R.id.bottom_wrapper1, this);
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.bottom_wrapper1));

        // Drag From Right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.bottom_wrapper));


        // Handling different events when swiping
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });*/

        /*viewHolder.swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((((SwipeLayout) v).getOpenStatus() == SwipeLayout.Status.Close)) {
                    //Start your activity

                    Toast.makeText(mContext, " onClick : " + item.getName() + " \n" + item.getEmailId(), Toast.LENGTH_SHORT).show();
                }

            }
        });*/

       /* holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext, " onClick : " + item.getName() + " \n" + item.getEmailId(), Toast.LENGTH_SHORT).show();
            }
        });*/


        /*holder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Clicked on Map " , Toast.LENGTH_SHORT).show();
            }
        });


        holder.tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Clicked on Share ", Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Clicked on Edit  " , Toast.LENGTH_SHORT).show();
            }
        });


        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*mItemManger.removeShownLayouts(holder.swipeLayout);
                studentList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, studentList.size());
                mItemManger.closeAllItems();*//*
                Toast.makeText(view.getContext(), "Deleted ", Toast.LENGTH_SHORT).show();
            }
        });*/


        // mItemManger is member in RecyclerSwipeAdapter Class
      //  mItemManger.bindView(holder.itemView, position);

        holder.clickButton.setTag(holder);
    }

    @Override
    public DiscoverTopicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic_discover, parent, false);
        return new DiscoverTopicsViewHolder(v);
    }

    @Override
    public void onClick(View view) {

        Log.d("STTTopicsCursorAdapter", "subscribe button clicked");
        // TODO subscribe/unsubscribe to the topic and remove from db on unsubscribing
        DiscoverTopicsViewHolder tempVH = (DiscoverTopicsViewHolder) view.getTag();
        if(tempVH.subscribed == 1) {
            // TODO unsubscribe
            STTarter.getInstance().unsubscribe(tempVH.topicName);
            Log.d("STTTopicsCursorAdapter", "unsubscribed");
            tempVH.subscribed = 0;
            gr.unsubscribeTopic(tempVH.topicName, PreferenceHelper.getSharedPreference().getString(Keys.USER_ID, ""));
            Log.d("STTTopicsCursorAdapter", "updated? : " + ph.updateTopicSubscribe(tempVH.topicName, 0));
        } else {
            // TODO subscribe
            STTarter.getInstance().subscribe(tempVH.topicName);
            Log.d("STTTopicsCursorAdapter", "subscribed");
            tempVH.subscribed = 1;
            gr.subscribeTopic(tempVH.topicName, PreferenceHelper.getSharedPreference().getString(Keys.USER_ID, ""));
            Log.d("DiscoverTopicsCursor", "updated? : " + ph.updateTopicSubscribe(tempVH.topicName, 1));
        }

        if (tempVH.subscribed == 1){
            tempVH.clickButton.setVisibility(View.GONE);
            tempVH.subscribedText.setVisibility(View.VISIBLE);
        }
        else {
            tempVH.clickButton.setBackgroundResource(R.drawable.ic_navigation_check);
            tempVH.subscribedText.setVisibility(View.GONE);
        }

       // tempVH.clickButton.setBackgroundResource((tempVH.subscribed == 1 ? R.drawable.ic_navigation_check : R.drawable.ic_navigation_close));
    }

}


class DiscoverTopicsViewHolder extends RecyclerView.ViewHolder
{
    public TextView topicTitleTextView, latestMsgTextView,subscribedText;
    public ImageButton clickButton;
    public CircularNetworkImageView topicImageView;
    public int subscribed;
    public TopicMeta topicMeta;
    public String topicName;
    public LinearLayout containerLayout;

    /*SwipeLayout swipeLayout;
    TextView tvDelete;
    TextView tvEdit;
    TextView tvShare;
    ImageButton btnLocation;*/

    public DiscoverTopicsViewHolder (View itemView)
    {
        super(itemView);
        this.containerLayout = (LinearLayout) itemView.findViewById(R.id.containerLayout);
        this.topicImageView = (CircularNetworkImageView) itemView.findViewById(R.id.topicImageView);
        this.topicTitleTextView = (TextView) itemView.findViewById(R.id.topicTitleTextView);
        this.latestMsgTextView = (TextView) itemView.findViewById(R.id.latestMsgTextView);
        this.clickButton = (ImageButton) itemView.findViewById(R.id.clickButton);
        this.subscribedText = (TextView) itemView.findViewById(R.id.subscribe);

        /*this.swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
        this.tvDelete = (TextView) itemView.findViewById(R.id.tvDelete);
        this.tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
        this.tvShare = (TextView) itemView.findViewById(R.id.tvShare);
        this.btnLocation = (ImageButton) itemView.findViewById(R.id.btnLocation);*/


        itemView.setTag(this);
    }
}