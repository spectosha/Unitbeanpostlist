package postlist.unitbean.com.unitbeanpostlist.ui.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.ui.postlist.fragments.PostListFragment;

public class CollectionsPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> pages = new ArrayList<>();

    public CollectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public void addPage(Fragment fragment){
        pages.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
