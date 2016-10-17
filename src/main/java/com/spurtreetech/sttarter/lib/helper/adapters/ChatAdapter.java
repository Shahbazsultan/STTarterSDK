package com.spurtreetech.sttarter.lib.helper.adapters;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spurtreetech.sttarter.lib.R;
import com.spurtreetech.sttarter.lib.helper.utils.DateTimeHelper;
import com.spurtreetech.sttarter.lib.provider.messages.MessagesColumns;

import java.util.Date;

/**
 * Created by RahulT on 29-06-2015.
 */
public class ChatAdapter extends CursorRecyclerAdapter<ChatViewHolder> {

    int mLayout;
    Cursor cursor;
    Date date;

    public ChatAdapter(int layout, Cursor c) {
        super(c);
        mLayout = layout;
        this.cursor = c;
        date = new Date();
    }
    public ChatAdapter(Cursor c) {
        this(R.layout.item_chat_message, c);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, Cursor cursor) {

        holder.message.setText(cursor.getString(cursor.getColumnIndex(MessagesColumns.MESSAGE_TEXT)));
        Log.d("ChatAdapter", "message by sender? : " + cursor.getString(cursor.getColumnIndex(MessagesColumns.IS_SENDER)));
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        if(cursor.getString(cursor.getColumnIndex(MessagesColumns.IS_SENDER)).equals("1")) {
            holder.userInfo.setText(cursor.getString(cursor.getColumnIndex(MessagesColumns.MESSAGE_FROM)));

            holder.userInfo.setTextColor(Color.WHITE);
            holder.timeStamp.setTextColor(Color.WHITE);
            holder.message.setTextColor(Color.WHITE);
            holder.dividerLine.setBackgroundColor(Color.WHITE);

            String tmstmp = DateTimeHelper.getTimeOrDateString(Long.parseLong(cursor.getString(cursor.getColumnIndex(MessagesColumns.UNIX_TIMESTAMP))));

            holder.timeStamp.setText(tmstmp);
            holder.container.setBackgroundResource(R.drawable.chatboxorange);
            holder.mainContainer.setHorizontalGravity(Gravity.RIGHT);
        } else {
            holder.userInfo.setText(cursor.getString(cursor.getColumnIndex(MessagesColumns.MESSAGE_FROM)));
            String tmstmp = DateTimeHelper.getTimeOrDateString(Long.parseLong(cursor.getString(cursor.getColumnIndex(MessagesColumns.UNIX_TIMESTAMP))));

            holder.userInfo.setTextColor(Color.rgb(255, 171 ,64));
            holder.timeStamp.setTextColor(Color.rgb(114, 114, 114));
            holder.message.setTextColor(Color.rgb(114, 114, 114));
            holder.dividerLine.setBackgroundColor(Color.rgb(196, 196, 196));

            holder.timeStamp.setText(tmstmp);
            holder.container.setBackgroundResource(R.drawable.chatboxwhite);
            holder.mainContainer.setHorizontalGravity(Gravity.LEFT);
        }
        //holder.time.setText(date.toString());
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(mLayout, parent, false);

        return new ChatViewHolder(v);
    }
}

class ChatViewHolder extends RecyclerView.ViewHolder
{
    public TextView message, time, userInfo,timeStamp;
    public LinearLayout container, mainContainer;
    public View dividerLine;

    public ChatViewHolder (View itemView)
    {
        super(itemView);
        message = (TextView) itemView.findViewById(R.id.message);
        time = (TextView) itemView.findViewById(R.id.time);
        userInfo = (TextView) itemView.findViewById(R.id.userInfo);
        container = (LinearLayout) itemView.findViewById(R.id.chatBoxLayout);
        timeStamp = (TextView) itemView.findViewById(R.id.timeStamp);
        dividerLine = (View) itemView.findViewById(R.id.divider_line);
        mainContainer = (LinearLayout) itemView;
    }
}