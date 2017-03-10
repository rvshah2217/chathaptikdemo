package com.app.chatdemo.fragment;

import android.content.Context;
import android.util.Log;

import com.app.chatdemo.fragment.chats.ChatsFragmentInterface;
import com.app.chatdemo.fragment.chatdetails.ChatDetailsFragmentInterface;
import com.app.chatdemo.model.ChatDetailsModel;
import com.app.chatdemo.model.FavTotalModel;

import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class PresenterChat implements ChatsFragmentInterface, ChatDetailsFragmentInterface {

    private ChatsFragmentInterface historyFragmentInterface;
    private InteractorChat interactorChat;
    private ChatDetailsFragmentInterface summaryFragmentInterface;
    private String TAG = "ChatHistoryPresenter";

    public PresenterChat(Context context, ChatsFragmentInterface historyFragmentInterface) {
        this.historyFragmentInterface = historyFragmentInterface;
        interactorChat = new InteractorChat(context, this);
    }

    public PresenterChat(Context context, ChatDetailsFragmentInterface summaryFragmentInterface) {
        this.summaryFragmentInterface = summaryFragmentInterface;
        interactorChat = new InteractorChat(context, this);
    }


    public void getChatResponse(Context context) {
        interactorChat.getChatDetailResponse(context);
    }


    @Override
    public void showProgressDialog(String message, boolean indeterminate, boolean isCancelable) {
        if (historyFragmentInterface != null) {
            historyFragmentInterface.showProgressDialog(message, indeterminate, isCancelable);
        }
    }

    @Override
    public void hideProgressDialog() {
        if (historyFragmentInterface != null) {
            historyFragmentInterface.hideProgressDialog();
        }
    }

    @Override
    public void setChatData(Context contexts) {
        if (historyFragmentInterface != null) {
            historyFragmentInterface.setChatData(contexts);
            Log.i(TAG, "chatHistoryInterface: ");
        }
    }


    public void getChatDetail(Context context) {
        interactorChat.getChatDetailSummeryResponse(context);
    }

    @Override
    public void setChatSummeryData(Context context, List<ChatDetailsModel> chatSummeryDbDetailsList, List<FavTotalModel> favTotalModelsList) {
        if (summaryFragmentInterface != null) {
            summaryFragmentInterface.setChatSummeryData(context, chatSummeryDbDetailsList, favTotalModelsList);
            Log.i(TAG, "chatSummeryInterface: " + chatSummeryDbDetailsList);
        }

    }
}
