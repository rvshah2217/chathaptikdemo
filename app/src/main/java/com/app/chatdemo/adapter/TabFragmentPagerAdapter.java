package com.app.chatdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.app.chatdemo.fragment.chats.ChatsFragment;
import com.app.chatdemo.fragment.chatdetails.ChatDetailsFragment;

/**
 * Created by swapna on 4/3/17.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Chats", "Chat Data" };
     Context context;

    public TabFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                //ChatHistoryFragment tab1 = new ChatHistoryFragment();
            return ChatsFragment.newInstance("one");
            case 1:
               // ChatSummeryFragment tab2 = new ChatSummeryFragment();
                return ChatDetailsFragment.newInstance("two");
            default :
                return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
