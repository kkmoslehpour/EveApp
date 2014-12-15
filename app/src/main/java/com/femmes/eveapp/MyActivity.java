package com.femmes.eveapp;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;

public class MyActivity extends FragmentActivity {
    private static final String[] CONTENT = new String[] { "Women", "Men", "Kids", "Jewelry", "Beauty", "Lifestyle" };
    ViewPager pager;
    FragmentPagerAdapter adapter;
    private boolean isFirstOrLastPage;
    private int currentPageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawCustomActionBar();
        setContentView(R.layout.simple_tabs);

        adapter = new GoogleMusicAdapter(getSupportFragmentManager());

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//////////////////////////////////////////////////////////////////////////////////////////
            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(MyActivity.this,
//                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }
            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(currentPageIndex!=position){
                    isFirstOrLastPage = false;
                    currentPageIndex=position;
                    return;
                }
                if(positionOffset == 0 && positionOffsetPixels == 0){
                    if(isFirstOrLastPage){
                        //DO SOMETHING
                        if(position==0) {pager.setCurrentItem(CONTENT.length-1, true); currentPageIndex=position;}
                        else if(position==CONTENT.length-1) {pager.setCurrentItem(0, true); currentPageIndex=position;}
                    }else{
                        isFirstOrLastPage = true;
                    }
                }
            }
            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        indicator.setViewPager(pager);
    }

    // ACTION BAR
    public void drawCustomActionBar(){
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        View customNav = LayoutInflater.from(this).inflate(R.layout.actionbar_layout, null);
        actionBar.setCustomView(customNav, lp1);
    }

    //NAVIGATION TABS
    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }

    }
}

