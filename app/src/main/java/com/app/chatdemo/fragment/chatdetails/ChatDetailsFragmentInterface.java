package com.app.chatdemo.fragment.chatdetails;

import android.content.Context;

import com.app.chatdemo.model.ChatDetailsModel;
import com.app.chatdemo.model.FavTotalModel;

import java.util.List;

/**
 * Created by swapna on 6/3/17.
 */
public interface ChatDetailsFragmentInterface {
    void setChatSummeryData(Context context, List<ChatDetailsModel> chatSummeryDbDetailsList, List<FavTotalModel> favTotalModelsList);
}
