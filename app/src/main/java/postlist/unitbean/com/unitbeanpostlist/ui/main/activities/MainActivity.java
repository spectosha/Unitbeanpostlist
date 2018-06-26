package postlist.unitbean.com.unitbeanpostlist.ui.main.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.arellomobile.mvp.presenter.InjectPresenter;

import postlist.unitbean.com.unitbeanpostlist.R;
import postlist.unitbean.com.unitbeanpostlist.ui.base.activities.BaseActivity;
import postlist.unitbean.com.unitbeanpostlist.ui.base.adapter.BaseAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.base.fragments.BaseFragment;
import postlist.unitbean.com.unitbeanpostlist.ui.main.adapters.CollectionsPagerAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.adapters.NavigationAdapter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.presenters.MainPresenter;
import postlist.unitbean.com.unitbeanpostlist.ui.main.views.MainView;

public class MainActivity extends BaseActivity implements MainView, BaseAdapter.OnItemClickListener {

    @InjectPresenter
    MainPresenter presenter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private Toolbar toolbar;

    RecyclerView recyclerView;
    NavigationAdapter adapter;

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

        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.rvNavigation);
        adapter = new NavigationAdapter(presenter);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        collectionsPagerAdapter = new CollectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(collectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
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
        viewPager.setCurrentItem(position-1);
    }
}