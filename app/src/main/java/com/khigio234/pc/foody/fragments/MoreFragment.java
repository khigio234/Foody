package com.khigio234.pc.foody.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.khigio234.pc.foody.R;
import com.khigio234.pc.foody.adapters.MoreListAdapter;

import java.util.ArrayList;

/**
 * Created by PC on 8/8/2016.
 */
public class MoreFragment extends BaseNavigationFragment {

    //region Properties

    public static final String ARG_INSTANCE = "com.khigio234.pc.foody";

    private Context mContext;
    private ListView mListView;
    private ArrayList mList;

    public static String[] sListAction = {"Thêm nhà hàng", "Mời bạn bè", "Góp ý", "Cài đặt ứng dụng"};
    public static int[] sListImage = {R.drawable.ic_add_restaurant_more,
            R.drawable.ic_add_friends_more,
            R.drawable.ic_feed_back_more,
            R.drawable.ic_setting_more};

    //endregion

    //region Constructor

//    public static MoreFragment newInstance(int instance) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_INSTANCE, instance);
//        MoreFragment fragment = new MoreFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public MoreFragment() {
    }


    //endregion

    //region Lifecycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        mContext = this.getContext();
        mListView = (ListView) view.findViewById(R.id.list_item_more);
        mListView.setAdapter(new MoreListAdapter(this, sListAction, sListImage));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //endregion
}
