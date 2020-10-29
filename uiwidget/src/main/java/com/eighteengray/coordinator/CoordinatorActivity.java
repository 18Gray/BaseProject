package com.eighteengray.coordinator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.eighteengray.basecomponent.baseactivity.BaseActivity;
import com.eighteengray.materialdesign.R;
import java.util.ArrayList;
import java.util.List;



//可折叠顶部的基类Activity，可设置titlebar/content/tablayou/viewpager和fragment
public class CoordinatorActivity extends BaseActivity {

    // 左边抽屉
    DrawerLayout dl_main;
    NavigationView nv_main;
    ActionBarDrawerToggle actionBarDrawerToggle;

    // 上部内容
    CoordinatorLayout coordinator_layout;
    AppBarLayout appbar_layout;
    CollapsingToolbarLayout collapsing_toolbar_layout;
    Toolbar toolbar;
    View layout_content_coordinator;


    // 中部悬停按钮
    TabLayout tab_layout;

    // 下部滑动列表
    NestedScrollView nested_scroll_view;
    ViewPager view_pager;
    List<Fragment> fragmentList = new ArrayList<>();
    FragmentAdapter fragmentAdapter;
    List<String> titles;
    ContentFragment contentFragment1;
    ContentFragment contentFragment2;
    ContentFragment contentFragment3;

    FloatingActionButton float_action_button;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        initView();
    }

    private void initView(){
        dl_main = (DrawerLayout) findViewById(R.id.dl_main);
        nv_main = (NavigationView) findViewById(R.id.nv_main);

        coordinator_layout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        appbar_layout = (AppBarLayout) findViewById(R.id.appbar_layout);
        collapsing_toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        layout_content_coordinator = findViewById(R.id.layout_content_coordinator);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        view_pager = (ViewPager) findViewById(R.id.view_pager);

        float_action_button = (FloatingActionButton) findViewById(R.id.float_action_button);
    }

    private void bindView()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.text));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.text));
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dl_main.openDrawer(Gravity.LEFT);
            }
        });

        appbar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i <= -layout_content_coordinator.getHeight() / 2) {
                    collapsing_toolbar_layout.setTitle("1111");
                } else {
                    collapsing_toolbar_layout.setTitle("2222");
                }
            }
        });

        titles = new ArrayList<>();
        titles.add("Page One");
        titles.add("Page Two");
        titles.add("Page Three");
        tab_layout.addTab(tab_layout.newTab().setText(titles.get(0)));
        tab_layout.addTab(tab_layout.newTab().setText(titles.get(1)));
        tab_layout.addTab(tab_layout.newTab().setText(titles.get(2)));

        contentFragment1 = new ContentFragment();
        contentFragment2 = new ContentFragment();
        contentFragment3 = new ContentFragment();

        fragmentList.add(contentFragment1);
        fragmentList.add(contentFragment2);
        fragmentList.add(contentFragment3);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        view_pager.setAdapter(fragmentAdapter);
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));

        tab_layout.setupWithViewPager(view_pager);
        tab_layout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(view_pager));

        float_action_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "弹出菜单", Toast.LENGTH_SHORT).show();
            }
        });

        nv_main.setItemIconTintList(null);
        View view = nv_main.getHeaderView(0);
        view.findViewById(R.id.iv_navigation_header).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        nv_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                menuItem.setChecked(true);
                dl_main.closeDrawers();
                return true;
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl_main, R.string.app_name, R.string.app_name);
        dl_main.setDrawerListener(actionBarDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.home){
            dl_main.openDrawer(Gravity.LEFT);
        } /*else if(item.getItemId() == R.id.homeAsUp) {
            dl_main.openDrawer(Gravity.LEFT);
        }*/ else if(item.getItemId() == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "跳转搜索页", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.action_notification) {
            Toast.makeText(getApplicationContext(), "跳转消息页", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "跳转设置页", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        if(actionBarDrawerToggle != null){
            actionBarDrawerToggle.syncState();
        }
    }


}
