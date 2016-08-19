package com.khigio234.pc.foody.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.view.BaseRecyclerViewAdapter;
import com.khigio234.pc.core.view.ViewHolder;
import com.khigio234.pc.core.viewmodel.CategoryViewModel;
import com.khigio234.pc.foody.R;

import java.util.List;

/**
 * Created by PC on 8/9/2016.
 */
public class CategoryListAdapter extends BaseRecyclerViewAdapter<CategoryViewModel, List<Category>> {

    //region Override methods

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_category, parent, false);
        return new ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = ((ViewHolder) holder).getViewDataBinding();

        viewDataBinding.setVariable(com.khigio234.pc.foody.BR.category, mData.get(position));
        viewDataBinding.setVariable(com.khigio234.pc.foody.BR.viewModel, mBaseViewModel);

        viewDataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    //endregion
}
