package com.app.chatdemo.fragment.chats;

import android.content.Context;

/**
 * Created by swapna on 4/3/17.
 */
public interface ChatsFragmentInterface {

    void showProgressDialog(String message, boolean indeterminate, boolean isCancelable);

    void hideProgressDialog();

    void setChatData(Context contexts);
}
