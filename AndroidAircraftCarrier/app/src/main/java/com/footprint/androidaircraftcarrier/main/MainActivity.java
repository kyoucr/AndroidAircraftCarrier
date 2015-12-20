package com.footprint.androidaircraftcarrier.main;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.footprint.androidaircraftcarrier.R;
import com.footprint.androidaircraftcarrier.app.ShellActivity;
import com.footprint.androidaircraftcarrier.app.ShellFragment;
import com.footprint.androidaircraftcarrier.main.fragments.ExploreFragment;
import com.footprint.androidaircraftcarrier.main.fragments.HomeFragment;
import com.footprint.androidaircraftcarrier.main.fragments.PlanningFragment;
import com.footprint.androidaircraftcarrier.main.fragments.SourceCodeFragment;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.WeakHashMap;

public class MainActivity extends ShellActivity implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private Shimmer shimmer;

    private WeakHashMap<String, ShellFragment> fragmentHashMap = new WeakHashMap<>();
    private Handler mainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置ToolBar
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //设置抽屉DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mToolbar.setNavigationIcon(R.drawable.ic_action);//放在这里设置才有效果

        //设置导航栏NavigationView的点击事件
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            String className = "";
            ShellFragment fragmentToDisplay = null;

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);//点击了把它设为选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉

                switch (menuItem.getItemId()) {
                    case R.id.item_home:
                        mToolbar.setTitle(R.string.shell_home);
                        className = HomeFragment.class.getCanonicalName();
                        if (fragmentHashMap.get(className) == null) {
                            fragmentToDisplay = (ShellFragment) HomeFragment.instantiate(MainActivity.this, className);
                            fragmentHashMap.put(className, fragmentToDisplay);
                        }
                        break;
                    case R.id.item_explore:
                        mToolbar.setTitle(R.string.shell_explore);
                        className = ExploreFragment.class.getCanonicalName();
                        if (fragmentHashMap.get(className) == null) {
                            fragmentToDisplay = (ShellFragment) ExploreFragment.instantiate(MainActivity.this, className);
                            fragmentHashMap.put(className, fragmentToDisplay);
                        }
                        break;
                    case R.id.item_code:
                        mToolbar.setTitle(R.string.shell_code);
                        className = SourceCodeFragment.class.getCanonicalName();
                        if (fragmentHashMap.get(className) == null) {
                            fragmentToDisplay = (ShellFragment) SourceCodeFragment.instantiate(MainActivity.this, className);
                            fragmentHashMap.put(className, fragmentToDisplay);
                        }
                        break;
                    case R.id.item_plan:
                        mToolbar.setTitle(R.string.shell_plan);
                        className = PlanningFragment.class.getCanonicalName();
                        if (fragmentHashMap.get(className) == null) {
                            fragmentToDisplay = (ShellFragment) PlanningFragment.instantiate(MainActivity.this, className);
                            fragmentHashMap.put(className, fragmentToDisplay);
                        }
                        break;
                }
                fragmentToDisplay = fragmentHashMap.get(className);

                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content, fragmentToDisplay).commitAllowingStateLoss();
                    }
                }, 300);//可以有效的改善体验——Fragment的替换更加流畅

                return true;
            }
        });

        configShimmer();
    }

    private void configShimmer() {
        shimmer = new Shimmer();
        shimmer.setRepeatCount(2)
                .setDuration(1500);
    }

    private void setStatusBar() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getColor(R.color.theme_red));
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_avatar:
                AnimatorSet avatarSet = new AnimatorSet();
                avatarSet.playTogether(
                        ObjectAnimator.ofFloat(v, "rotation", 0, 2160),
                        ObjectAnimator.ofFloat(v, "scaleX", 1, 0.5f, 1.5f, 1),
                        ObjectAnimator.ofFloat(v, "scaleY", 1, 0.5f, 1.5f, 1),
                        ObjectAnimator.ofFloat(v, "alpha", 1, 0.25f, 1)
                );
                avatarSet.setDuration(3 * 1000).start();
                break;
            case R.id.textview_username:
                AnimatorSet nameSet = new AnimatorSet();
                nameSet.playTogether(
                        ObjectAnimator.ofFloat(v, "translationX", 0, -180, 0),
                        ObjectAnimator.ofFloat(v, "translationY", 0, -180, 0),
                        ObjectAnimator.ofFloat(v, "scaleX", 1, 2.5f, 1),
                        ObjectAnimator.ofFloat(v, "scaleY", 1, 2.5f, 1),
                        ObjectAnimator.ofFloat(v, "alpha", 0f, 1, 1)
                );
                nameSet.setDuration(3 * 1000).start();
                break;
            case R.id.textview_label:
                shimmer.start((ShimmerTextView) v);
                break;
            case R.id.layout_drawer_header:

                break;
            default:
                break;
        }
    }
}
