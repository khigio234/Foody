package com.khigio234.pc.foody;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.khigio234.pc.core.view.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by PC on 8/2/2016.
 */
public class AppBindingAdapter {

    @BindingAdapter(value = {"items"})
    public static <T> void setAdapter(RecyclerView recyclerView, List<T> items) {
        if (recyclerView.getAdapter() instanceof BaseRecyclerViewAdapter) {
            BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.setData(items);
            }
        }
    }
}
