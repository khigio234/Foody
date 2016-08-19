package com.khigio234.pc.foody.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.view.BaseRecyclerViewAdapter;
import com.khigio234.pc.core.view.ViewHolder;
import com.khigio234.pc.core.viewmodel.RestaurantViewModel;
import com.khigio234.pc.foody.BR;
import com.khigio234.pc.foody.R;

import java.util.List;

/**
 * Created by PC on 8/18/2016.
 */
public class NewsFeedListAdapter extends BaseRecyclerViewAdapter<RestaurantViewModel, List<Restaurant>> {

    //region Override method

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_restaurant, parent, false);
        return new ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = ((ViewHolder) holder).getViewDataBinding();

        viewDataBinding.setVariable(BR.restaurant, mData.get(position));
        viewDataBinding.setVariable(BR.viewModel, mBaseViewModel);

        viewDataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    //endregion
}
