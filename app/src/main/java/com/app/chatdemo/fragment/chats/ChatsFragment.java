package com.app.chatdemo.fragment.chats;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.chatdemo.R;
import com.app.chatdemo.adapter.RecyclerViewChatsAdapter;
import com.app.chatdemo.fragment.PresenterChat;
import com.app.chatdemo.fragment.chatdetails.RefreshFragmentInterface;
import com.app.chatdemo.common.database.MessagesStore;
import com.app.chatdemo.model.MessagesDisplayModel;
import com.app.chatdemo.common.networkcalls.NetworkStatus;

import java.util.ArrayList;

/**
 * Created by swapna on 4/3/17.
 */
public class ChatsFragment extends Fragment implements ChatsFragmentInterface,RefreshFragmentInterface {

     RecyclerView mRecyclerView;
    RecyclerViewChatsAdapter mRecyclerViewHistoryAdapter;
    PresenterChat presenterChat;
     LinearLayoutManager mLayoutManager;
    private String TAG="ChatHistoryFragment";
     MessagesStore messagesStore;

    public static ChatsFragment newInstance(String tab_id) {
        ChatsFragment fragment = new ChatsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_id", tab_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_chat_recycler, container, false);

        messagesStore=new MessagesStore(getActivity());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.chatRecyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList=messagesStore.getAllUserChatData();
        if(messagesDisplayModelArrayList!=null&&messagesDisplayModelArrayList.size()>0)
        {
            mRecyclerViewHistoryAdapter =new RecyclerViewChatsAdapter(getActivity());
            mRecyclerView.setAdapter(mRecyclerViewHistoryAdapter);
        }
        else
        {
            presenterChat = new PresenterChat(getActivity(),this);
            checkInternetConnectivityAndProceed();
        }

        return rootView;
    }
    private void checkInternetConnectivityAndProceed() {
        if(NetworkStatus.isConnected(getContext())) {
            presenterChat.getChatResponse(getActivity());
        }

    }

    @Override
    public void showProgressDialog(String message, boolean indeterminate, boolean isCancelable) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setChatData(Context contexts) {

        mRecyclerViewHistoryAdapter =new RecyclerViewChatsAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerViewHistoryAdapter);
    }


    @Override
    public void refreshData() {

    }
}
