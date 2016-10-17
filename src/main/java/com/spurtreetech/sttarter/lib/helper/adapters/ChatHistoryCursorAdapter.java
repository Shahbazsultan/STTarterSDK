package com.spurtreetech.sttarter.lib.helper.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.spurtreetech.sttarter.lib.R;
import com.spurtreetech.sttarter.lib.helper.STTarter;
import com.spurtreetech.sttarter.lib.helper.models.TopicMeta;
import com.spurtreetech.sttarter.lib.helper.utils.DateTimeHelper;
import com.spurtreetech.sttarter.lib.helper.volley.CircularNetworkImageView;
import com.spurtreetech.sttarter.lib.provider.STTProviderHelper;
import com.spurtreetech.sttarter.lib.provider.messages.MessagesCursor;
import com.spurtreetech.sttarter.lib.provider.topics.TopicsColumns;

public class ChatHistoryCursorAdapter extends CursorRecyclerAdapter<ChatHistoryViewHolder> implements View.OnClickListener {

    ChatInitiateListener cil;
    Gson gson = new Gson();
    STTProviderHelper ph;
    public ChatHistoryCursorAdapter(Context context, Cursor cursor, ChatInitiateListener chatInitiateListener) {
        //super(context, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        super(cursor);
        this.cil = chatInitiateListener;
        ph = new STTProviderHelper();
    }

    @Override
    public void onClick(View v) {
        Log.d(getClass().getSimpleName(), ((TextView)v.findViewById(R.id.name)).getText().toString());
        Log.d(getClass().getSimpleName() + ">", ((TextView) v.findViewById(R.id.channel)).getText().toString());

        TextView channelTextView = ((TextView)v.findViewById(R.id.channel));

        cil.chatClicked(channelTextView.getText().toString(), (TopicMeta) v.getTag(),channelTextView.getTag().toString());
    }

    @Override
    public void onBindViewHolder(ChatHistoryViewHolder holder, Cursor cursor) {
        holder.channel.setText(cursor.getString(cursor.getColumnIndex(TopicsColumns.TOPIC_NAME)));
        holder.channel.setTag(cursor.getString(cursor.getColumnIndex(TopicsColumns.TOPIC_GROUP_MEMBERS)));

        STTProviderHelper sttProviderHelper = new STTProviderHelper();
        int count = 0;
        count = sttProviderHelper.getUnreadMessageCountForTopic(cursor.getString(cursor.getColumnIndex(TopicsColumns.TOPIC_NAME)));

        if (count > 0){
            holder.unreadMessageCount.setVisibility(View.VISIBLE);
            holder.unreadMessageCount.setText(count+"");
        }
        else {
            holder.unreadMessageCount.setVisibility(View.GONE);
        }


        Log.d("Topic Meta",cursor.getString(cursor.getColumnIndex(TopicsColumns.TOPIC_META)).toString());

        holder.tm = gson.fromJson(cursor.getString(cursor.getColumnIndex(TopicsColumns.TOPIC_META)), TopicMeta.class);
        if(URLUtil.isValidUrl(holder.tm.getImage()) && holder.tm.getImage() != null) {
        try{
            holder.groupIconImageView.setImageUrl(holder.tm.getImage(), STTarter.getInstance().getImageLoader());
            holder.groupIconImageView.setErrorImageResId(R.drawable.ic_launcher);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
            //ImageRequestHelper.setImageToImageView(holder.tm.getImage(), holder.groupIconImageView);
        }
        else {
            holder.groupIconImageView.setImageResource(R.drawable.ic_launcher);
        }
        holder.name.setText(holder.tm.getName());
        holder.topicList.setTag(holder.tm);
        //holder.latestMsgTextView.setText();
        MessagesCursor mc = ph.getLastMessageForTopic(cursor.getString(cursor.getColumnIndex(TopicsColumns.TOPIC_NAME)));
        if(mc!=null && mc.getCount()>0) {
            mc.moveToFirst();
            holder.description.setText(mc.getMessageText());
            holder.sentFrom.setText(mc.getMessageFrom());

            String tmstmp = DateTimeHelper.getTimeOrDateString(mc.getUnixTimestamp().getTime());

            Log.d(">>>>>>>>>>",tmstmp);

            holder.timeStamp.setText(tmstmp);

        } else {
            holder.description.setText("");
            holder.timeStamp.setText("");
            holder.sentFrom.setText("");
        }
    }


    @Override
    public ChatHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_topiclist, parent, false);
        //((TextView)v.findViewById(R.id.name)).setOnClickListener(this);
        v.setOnClickListener(this);
        return new ChatHistoryViewHolder(v);
    }

    public interface ChatInitiateListener {
        void chatClicked(String chatId, TopicMeta topicMeta,String groupMembers);
    }
}

class ChatHistoryViewHolder extends RecyclerView.ViewHolder
{
    public TextView channel, name, description,sentFrom,timeStamp,unreadMessageCount;
    public int position;
    public TopicMeta tm;
    public LinearLayout topicList;
    public CircularNetworkImageView groupIconImageView;

    public ChatHistoryViewHolder (View itemView) {
        super(itemView);
        this.channel = (TextView) itemView.findViewById(R.id.channel);
        this.name = (TextView) itemView.findViewById(R.id.name);
        this.timeStamp = (TextView) itemView.findViewById(R.id.timeStamp);
        this.sentFrom = (TextView) itemView.findViewById(R.id.sentFrom);
        description = (TextView) itemView.findViewById(R.id.description);
        topicList = (LinearLayout) itemView.findViewById(R.id.topicListLayout);
        groupIconImageView = (CircularNetworkImageView) itemView.findViewById(R.id.groupIconImageView);
        unreadMessageCount = (TextView) itemView.findViewById(R.id.unreadCount);
        this.position = this.getPosition();
    }
}
