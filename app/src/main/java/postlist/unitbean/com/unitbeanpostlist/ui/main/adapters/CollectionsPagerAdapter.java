package postlist.unitbean.com.unitbeanpostlist.ui.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import postlist.unitbean.com.unitbeanpostlist.ui.base.fragments.BaseFragment;

public class CollectionsPagerAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragments = new ArrayList<>();
    private String tabTitles[] = new String[] { "Posts", "Settings", "Profile" };

    public CollectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addPage(BaseFragment fragment){
        fragments.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
