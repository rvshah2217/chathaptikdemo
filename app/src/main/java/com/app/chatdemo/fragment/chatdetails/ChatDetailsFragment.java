package com.app.chatdemo.fragment.chatdetails;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.chatdemo.R;
import com.app.chatdemo.adapter.RecyclerViewDetailsAdapter;
import com.app.chatdemo.fragment.PresenterChat;
import com.app.chatdemo.model.ChatDetailsModel;
import com.app.chatdemo.common.database.MessagesStore;
import com.app.chatdemo.model.FavTotalModel;
import com.app.chatdemo.model.MessagesDisplayModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class ChatDetailsFragment extends Fragment  implements ChatDetailsFragmentInterface,RefreshFragmentInterface {

    private RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManager;
    private String TAG = "SummeryFragment";
    private RecyclerViewDetailsAdapter mRecyclerViewMessagesAdapter;
     PresenterChat presenterChat;


    public static ChatDetailsFragment newInstance(String tab_id) {
        ChatDetailsFragment fragment = new ChatDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_id", tab_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_chat_detail_recycler, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.chatRecyclerviewcount);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);



        MessagesStore messagesStore = new MessagesStore(getActivity());
        ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList = messagesStore.getAllUserChatData();
        if(messagesDisplayModelArrayList!=null&&messagesDisplayModelArrayList.size()>0)
        {
            List<ChatDetailsModel> chatSummeryDbDetailsList =  messagesStore.getChatDetail();
            Log.i(TAG, "onClick: "+chatSummeryDbDetailsList.toString());

            List<FavTotalModel> favTotalModelsList =  messagesStore.getFavDetail();
            Log.i(TAG, "onClick: "+favTotalModelsList.toString());

            mRecyclerViewMessagesAdapter = new RecyclerViewDetailsAdapter(getContext(), chatSummeryDbDetailsList, favTotalModelsList);
            mRecyclerView.setAdapter(mRecyclerViewMessagesAdapter);
        }
        else
        {
            presenterChat = new PresenterChat(getActivity(),this);
            presenterChat.getChatResponse(getActivity());
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void setChatSummeryData(Context context, List<ChatDetailsModel> chatSummeryDbDetailsList, List<FavTotalModel> favTotalModelsList) {
        Log.i(TAG, "setChatSummeryData: "+chatSummeryDbDetailsList.size()+favTotalModelsList);
        mRecyclerViewMessagesAdapter = new RecyclerViewDetailsAdapter(getContext(), chatSummeryDbDetailsList,favTotalModelsList);
        mRecyclerView.setAdapter(mRecyclerViewMessagesAdapter);
    }

    @Override
    public void refreshData() {
        Log.i(TAG, "refreshData: ");
        MessagesStore messagesStore = new MessagesStore(getActivity());
        ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList = messagesStore.getAllUserChatData();
        if(messagesDisplayModelArrayList!=null&&messagesDisplayModelArrayList.size()>0)
        {
            List<ChatDetailsModel> chatSummeryDbDetailsList =  messagesStore.getChatDetail();
            Log.i(TAG, "onClick: "+chatSummeryDbDetailsList.toString());

            List<FavTotalModel> favTotalModelsList =  messagesStore.getFavDetail();
            Log.i(TAG, "onClick: "+favTotalModelsList.toString());

            mRecyclerViewMessagesAdapter = new RecyclerViewDetailsAdapter(getContext(), chatSummeryDbDetailsList, favTotalModelsList);
            mRecyclerView.setAdapter(mRecyclerViewMessagesAdapter);
        }
        else
        {
            presenterChat = new PresenterChat(getActivity(),this);
            presenterChat.getChatResponse(getActivity());
        }
    }
    

}
