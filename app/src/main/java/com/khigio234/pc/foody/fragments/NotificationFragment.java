package com.khigio234.pc.foody.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khigio234.pc.foody.R;

/**
 * Created by PC on 8/8/2016.
 */
public class NotificationFragment extends Fragment {

    //region Properties

    public static final String ARG_INSTANCE = "com.khigio234.pc.foody";

    //endregion

    //region Constructor

//    public static NotificationFragment newInstance(int instance) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_INSTANCE, instance);
//        NotificationFragment fragment = new NotificationFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public NotificationFragment() {
    }


    //endregion

    //region Lifecycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //endregion
}
