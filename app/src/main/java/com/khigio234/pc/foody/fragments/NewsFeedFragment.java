package com.khigio234.pc.foody.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khigio234.pc.core.viewmodel.RestaurantViewModel;
import com.khigio234.pc.foody.App;
import com.khigio234.pc.foody.BR;
import com.khigio234.pc.foody.R;
import com.khigio234.pc.foody.adapters.NewsFeedListAdapter;
import com.khigio234.pc.foody.databinding.FragmentNewsFeedBinding;

/**
 * Created by PC on 8/8/2016.
 */
public class NewsFeedFragment extends BaseNavigationFragment<FragmentNewsFeedBinding, RestaurantViewModel> {

    //region Properties

    private NewsFeedListAdapter mNewsFeedListAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    //endregion

    //region Constructor

    public NewsFeedFragment() {
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

        setBindingContentView(inflater, container, R.layout.fragment_news_feed, BR.viewModel);

        View view = mViewDataBinding.getRoot();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_news_feed);

        mNewsFeedListAdapter = new NewsFeedListAdapter();
        mNewsFeedListAdapter.setBaseViewModel(mBaseViewModel);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mNewsFeedListAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //endregion
}