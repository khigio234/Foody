package com.khigio234.pc.foody.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khigio234.pc.foody.R;
import com.khigio234.pc.foody.fragments.MoreFragment;

/**
 * Created by PC on 8/29/2016.
 */
public class MoreListAdapter extends BaseAdapter {

    //region Properties

    private Context mContext;
    private String [] mResults;
    private int [] mImageId;
    private static LayoutInflater sInflater = null;

    //endregion

    //region Constructor

    public MoreListAdapter(MoreFragment fragment, String[] listAction, int[] listImage) {
        mContext = fragment.getContext();
        mResults = listAction;
        mImageId = listImage;
        sInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //endregion

    //region Holder

    public class Holder {
        TextView mTextView;
        ImageView mImageView;
    }

    //endregion

    //region Override methods

    @Override
    public int getCount() {
        return mResults.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        View rowView;
        rowView = sInflater.inflate(R.layout.list_item_more, null);

        holder.mTextView = (TextView) rowView.findViewById(R.id.tv_item_more);
        holder.mImageView = (ImageView) rowView.findViewById(R.id.img_item_more);
        holder.mTextView.setText(mResults[position]);
        holder.mImageView.setImageResource(mImageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "" + mResults[position], Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }

    //endregion
}
