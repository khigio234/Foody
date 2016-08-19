package com.khigio234.pc.foody.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khigio234.pc.core.viewmodel.CategoryViewModel;
import com.khigio234.pc.foody.App;
import com.khigio234.pc.foody.BR;
import com.khigio234.pc.foody.R;
import com.khigio234.pc.foody.adapters.CategoryListAdapter;
import com.khigio234.pc.foody.commons.GridSpacingItemDecoration;
import com.khigio234.pc.foody.databinding.FragmentCategoryBinding;

/**
 * Created by PC on 8/8/2016.
 */
public class CategoryFragment extends BaseNavigationFragment<FragmentCategoryBinding, CategoryViewModel> {

    //region Properties

    public static final String ARG_INSTANCE = "com.khigio234.pc.foody";

    private CategoryListAdapter mCategoryListAdapter;

    //endregion

    //region Constructor

//    public static CategoryFragment newInstance(int instance) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_INSTANCE, instance);
//        CategoryFragment fragment = new CategoryFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public CategoryFragment() {
    }


    //endregion

    //region Lifecycle


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.sharedComponent().inject(this);

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setBindingContentView(inflater, container, R.layout.fragment_category, BR.viewModel);

        View view = mViewDataBinding.getRoot();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_category);

        mCategoryListAdapter = new CategoryListAdapter();
        mCategoryListAdapter.setBaseViewModel(mBaseViewModel);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mCategoryListAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //endregion

    /**
     * Converting dp to pixel
     */

    private int dpToPx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
