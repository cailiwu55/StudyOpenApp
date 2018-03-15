package test.md;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clw on 2017/12/27.
 */

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private FloatingActionButton mActionButton;
    private ActionMode mActionMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        mTabLayout = findViewById(R.id.tabs);
        mViewpager = findViewById(R.id.viewpager);
        mActionButton = findViewById(R.id.fab);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyFragment.newInstance("fragment0"));
        fragmentList.add(MyFragment.newInstance("fragment1"));
        fragmentList.add(ListFragment.newInstance("fragment2"));
        List<String> titleList = new ArrayList<>();
        titleList.add("PAGE0");
        titleList.add("PAGE1");
        titleList.add("PAGE2");

        mViewpager.setAdapter(new MyAdapter(getSupportFragmentManager(), fragmentList, titleList));
        mTabLayout.setupWithViewPager(mViewpager);

        mActionButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false;
                }
                mActionMode = startSupportActionMode(mCallback);
                return true;
            }
        });
    }

    class MyAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragmentList;
        private List<String> mTitleList;

        public MyAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
            super(fm);
            mFragmentList = fragmentList;
            mTitleList = titleList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }

    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //选项菜单
        getMenuInflater().inflate(R.menu.a, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.a, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                Snackbar.make(mViewpager, "app_bar_search is click.", Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void showSnake(String msg) {
        Snackbar.make(mViewpager, msg, Snackbar.LENGTH_SHORT).show();
    }

    //上下文操作模式

    private ActionMode.Callback mCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context_mode, menu);
            mode.setTitle("标题");
            mode.setSubtitle("副标题");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_selectall:
                    showSnake("selectall");
                    mode.finish();
                    break;
                case R.id.menu_delete:
                    showSnake("delete");
                    mode.finish();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };
}
