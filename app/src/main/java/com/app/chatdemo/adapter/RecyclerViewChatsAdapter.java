package com.app.chatdemo.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.chatdemo.ChatApplication;
import com.app.chatdemo.model.MessagesDisplayModel;
import com.app.chatdemo.common.Constants;
import com.app.chatdemo.R;
import com.app.chatdemo.common.database.MessagesStore;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by swapna on 4/3/17.
 */
public class RecyclerViewChatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    MessagesStore messagesStore;
    // List<MessageModel> messageModels;
    Context context;
    ArrayList<MessagesDisplayModel> messages;
    // List<MessageModel> messages;
    ImageLoader imageLoader;
    private String TAG = "RecyclerView";

    public RecyclerViewChatsAdapter(Context context) {
        this.context = context;
        // this.messages = messages;
        messagesStore = new MessagesStore(context);
        ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList = messagesStore.getAllUserChatData();
        Log.i(TAG, "onClick: " + messagesDisplayModelArrayList.toString());

        this.messages = messagesDisplayModelArrayList;

        imageLoader = ChatApplication.getImageLoader();
        //Toast.makeText(context,""+messages.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case Constants.HomeAdapterRowViewType.USER:
                view = inflater
                        .inflate(R.layout.my_chat_item_view, viewGroup, false);

                return new ViewHolderMeUser(view);

            case Constants.HomeAdapterRowViewType.OTHERS:

                view = inflater
                        .inflate(R.layout.user_chat_item_view, viewGroup, false);
                return new ViewHolderOtherUser(view);
            default:
                view = inflater
                        .inflate(R.layout.my_chat_item_view, viewGroup, false);

                return new ViewHolderMeUser(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final MessagesDisplayModel messagesDisplayModel = messages.get(position);

        switch (holder.getItemViewType()) {
            case Constants.HomeAdapterRowViewType.USER:
                final ViewHolderMeUser viewHolderMeUser = (ViewHolderMeUser) holder;
                viewHolderMeUser.mTxtMyName.setText(messagesDisplayModel.getName());
                viewHolderMeUser.mTxtChatMsg.setText(messagesDisplayModel.getMessages());

                String date = dateFormate(messagesDisplayModel.getTimeStamp());
                viewHolderMeUser.myTimeStamp.setText(date);

                viewHolderMeUser.addToFavouriteImg.setTag(messagesDisplayModel.getMyFav());

                if(messagesDisplayModel.getMyFav()==0)
                {
                    viewHolderMeUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_empty));

                }
                else
                {
                    viewHolderMeUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_fill));

                }


                viewHolderMeUser.addToFavouriteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tagOfImageView = (int) viewHolderMeUser.addToFavouriteImg.getTag();

                        if (tagOfImageView == 1) {
                            viewHolderMeUser.addToFavouriteImg.setTag(0);
                            viewHolderMeUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_empty));

                            messagesDisplayModel.setMyFav(0);

                            Log.i(TAG, "onClick: " + messagesDisplayModel.toString());

                        } else {
                            viewHolderMeUser.addToFavouriteImg.setTag(1);
                            viewHolderMeUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_fill));

                            messagesDisplayModel.setMyFav(1);

                            Log.i(TAG, "onClick: " + messagesDisplayModel.toString());
                        }
                        messagesStore.updateHandler(messagesDisplayModel);

                    }
                });

                break;
            case Constants.HomeAdapterRowViewType.OTHERS:
                final ViewHolderOtherUser viewHolderOtherUser = (ViewHolderOtherUser) holder;
                viewHolderOtherUser.mTxtUserName.setText(messagesDisplayModel.getName());
                viewHolderOtherUser.mTxtUserChatMsg.setText(messagesDisplayModel.getMessages());
                date = dateFormate(messagesDisplayModel.getTimeStamp());
                viewHolderOtherUser.userTimeStamp.setText(date);


                viewHolderOtherUser.addToFavouriteImg.setTag(messagesDisplayModel.getMyFav());

                if(messagesDisplayModel.getMyFav()==0)
                {
                    viewHolderOtherUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_empty));

                }
                else
                {
                    viewHolderOtherUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_fill));

                }

                viewHolderOtherUser.addToFavouriteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tagOfImageView = (int) viewHolderOtherUser.addToFavouriteImg.getTag();

                        if (tagOfImageView == 1) {
                            viewHolderOtherUser.addToFavouriteImg.setTag(0);
                            viewHolderOtherUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_empty));

                            messagesDisplayModel.setMyFav(0);

                            Log.i(TAG, "onClick: " + messagesDisplayModel.toString());
                        } else {
                            viewHolderOtherUser.addToFavouriteImg.setTag(1);
                            viewHolderOtherUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_fill));

                            messagesDisplayModel.setMyFav(1);

                            Log.i(TAG, "onClick: " + messagesDisplayModel.toString());
                        }

                        messagesStore.updateHandler(messagesDisplayModel);
                    }
                });

                break;
            default:

        }
    }

    private String dateFormate(String messageTime) {

        Date futureDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = null;
        try {
            futureDate = dateFormat.parse(messageTime);
            formattedTime = output.format(futureDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedTime;
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getMe();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderMeUser extends RecyclerView.ViewHolder {
        private AppCompatImageView addToFavouriteImg;
        private AppCompatTextView mTxtMyName;
        private AppCompatTextView mTxtChatMsg;
        private AppCompatTextView myTimeStamp;

        public ViewHolderMeUser(View itemView) {
            super(itemView);
            addToFavouriteImg = (AppCompatImageView) itemView.findViewById(R.id.addToFavouriteImg);
            mTxtMyName = (AppCompatTextView) itemView.findViewById(R.id.mTxtMyName);
            mTxtChatMsg = (AppCompatTextView) itemView.findViewById(R.id.mTxtChatMsg);
            myTimeStamp = (AppCompatTextView) itemView.findViewById(R.id.myTimeStamp);

        }
    }

    public class ViewHolderOtherUser extends RecyclerView.ViewHolder {
        // private AppCompatImageView mImageViewCategoryItem;
        private AppCompatTextView mTxtUserName;
        private AppCompatTextView mTxtUserChatMsg;
        private AppCompatTextView userTimeStamp;
        private AppCompatImageView addToFavouriteImg;

        public ViewHolderOtherUser(View itemView) {
            super(itemView);
            addToFavouriteImg = (AppCompatImageView) itemView.findViewById(R.id.addToFavouriteImg);
            mTxtUserName = (AppCompatTextView) itemView.findViewById(R.id.mTxtUserName);
            mTxtUserChatMsg = (AppCompatTextView) itemView.findViewById(R.id.mTxtUserChatMsg);
            userTimeStamp = (AppCompatTextView) itemView.findViewById(R.id.name);
        }
    }


}
