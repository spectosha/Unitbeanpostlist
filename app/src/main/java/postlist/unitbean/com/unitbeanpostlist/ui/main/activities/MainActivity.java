package postlist.unitbean.com.unitbeanpostlist.ui.main.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.activities.BaseActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.base.adapter.BaseAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.base.fragments.BaseFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.bottomsheetposts.fragments.BottomSheetPostsFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.countdown.fragments.CountdownFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.main.adapters.CollectionsPagerAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.adapters.NavigationAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.presenters.MainPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.views.MainView;
import postlist.unitbean.com.unitbeanpostlist.ui.postlist.fragments.PostListFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.timer.fragments.TimerFragment;

public class MainActivity extends BaseActivity implements MainView, BaseAdapter.OnItemClickListener {

    @InjectPresenter
    MainPresenter presenter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private Toolbar toolbar;

    RecyclerView recyclerView;
    NavigationAdapter adapter;

    AppBarLayout appBarLayout;
    ViewPager viewPager;
    CollectionsPagerAdapter collectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        collectionsPagerAdapter = new CollectionsPagerAdapter(getSupportFragmentManager());
        collectionsPagerAdapter.addPage(new PostListFragment());
        collectionsPagerAdapter.addPage(new CountdownFragment());
        collectionsPagerAdapter.addPage(new BottomSheetPostsFragment());

        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.rvNavigation);
        adapter = new NavigationAdapter(presenter);
        adapter.setOnItemClickListener(this);
        adapter.selectItem(1);
        recyclerView.setAdapter(adapter);

        presenter.addFragment(new PostListFragment());
        presenter.addFragment(new TimerFragment());

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(collectionsPagerAdapter);
        viewPager.getCurrentItem();

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        appBarLayout = findViewById(R.id.app_bar_layout);
        ImageView collapsingBarImage = findViewById(R.id.collapsing_bar_image);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offset = ((float) verticalOffset / -488 / 2) + 1;
                collapsingBarImage.setScaleX(offset);
                collapsingBarImage.setScaleY(offset);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                appBarLayout.setExpanded(false);
                appBarLayout.setActivated(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void closeNavigationView() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void chooseFragmentLayout(BaseFragment fragment) {

    }

    @Override
    public void onClick(View view, int position) {
        adapter.selectItem(position);
        viewPager.setCurrentItem(position-1);
        closeNavigationView();

    }
}