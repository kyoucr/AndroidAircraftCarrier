package com.footprint.androidaircraftcarrier.animation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/5/15.
 */
public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * 本演示代码的显示页数 (看作向导步骤).
     */
    private static final int NUM_PAGES = 5;

    /**
     * Pager控件，用来处理动画并允许整页横向滑动来展示上一个和下一个“向导步骤”
     */
    private ViewPager mPager;

    /**
     * Pager适配器，用于提供ViewPager控件的具体页面。
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // 初始化 ViewPager 和 PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // 如果用户当前正在看第一步（也就是第一页），那就要让系统来处理返回按钮。
            //这个是结束（finish()）当前活动并弹出回退栈。
            super.onBackPressed();
        } else {
            // 否则，返回前一页
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * 一个简单的Pager控件适配器，它顺序地展示了5个ScreenSlidePageFragment对象
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}