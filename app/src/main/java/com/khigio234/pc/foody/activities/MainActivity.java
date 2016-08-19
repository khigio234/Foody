package com.khigio234.pc.foody.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.khigio234.pc.foody.R;
import com.khigio234.pc.foody.fragments.BaseNavigationFragment;
import com.khigio234.pc.foody.fragments.CategoryFragment;
import com.khigio234.pc.foody.fragments.MoreFragment;
import com.khigio234.pc.foody.fragments.NewsFeedFragment;
import com.khigio234.pc.foody.fragments.NotificationFragment;
import com.khigio234.pc.foody.fragments.SearchFragment;
import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseNavigationFragment.FragmentNavigation {

    //region Properties

    private BottomBar mBottomBar;
    private FragNavController mNavController;

    private final int INDEX_NEWS_FEED = FragNavController.TAB1;
    private final int INDEX_CATEGORY = FragNavController.TAB2;
    private final int INDEX_SEARCH = FragNavController.TAB3;
    private final int INDEX_NOTIFICATION = FragNavController.TAB4;
    private final int INDEX_MORE = FragNavController.TAB5;

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new ArrayList<>(5);

        fragments.add(new NewsFeedFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new SearchFragment());
        fragments.add(new NotificationFragment());
        fragments.add(new MoreFragment());

        mNavController = new FragNavController(getSupportFragmentManager(), R.id.container, fragments);

        mBottomBar = BottomBar.attach(this, savedInstanceState,
                Color.parseColor("#FFFFFF"),
                ContextCompat.getColor(this, R.color.colorAccent),
                0.3f);

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.bb_menu_news_feed:
                        mNavController.switchTab(INDEX_NEWS_FEED);
                        break;
                    case R.id.bb_menu_category:
                        mNavController.switchTab(INDEX_CATEGORY);
                        break;
                    case R.id.bb_menu_search:
                        mNavController.switchTab(INDEX_SEARCH);
                        break;
                    case R.id.bb_menu_notification:
                        mNavController.switchTab(INDEX_NOTIFICATION);
                        break;
                    case R.id.bb_menu_more:
                        mNavController.switchTab(INDEX_MORE);
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                mNavController.clearStack();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mNavController.getCurrentStack().size() > 1) {
            mNavController.pop();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //endregion


    @Override
    public void pushFragment(Fragment fragment) {
        mNavController.push(fragment);

        Log.d("tt","ccc");
    }

}
