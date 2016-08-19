package com.khigio234.pc.foody.fragments;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.khigio234.pc.core.view.BaseFragment;
import com.khigio234.pc.core.viewmodel.BaseViewModel;

/**
 * Created by PC on 8/10/2016.
 */
public class BaseNavigationFragment<B extends ViewDataBinding, V extends BaseViewModel> extends BaseFragment<B, V> {

    //region Properties

    public static final String ARG_INSTANCE = "com.khigio234.foodysd.fragments.argsInstance";

    FragmentNavigation mFragmentNavigation;
    int mInt = 0;

    //endregion

    //region Lifecycle

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARG_INSTANCE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNavigation) {
            mFragmentNavigation = (FragmentNavigation) context;
        }
    }

    //endregion

    //region Interface

    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);
    }

    //region

}
