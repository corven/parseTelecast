package cos.test.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PagerChannelsAdapter extends FragmentPagerAdapter{
    private static final String FAVORITES = "Избранное",
        CHANNELS = "Каналы";

    private List<Fragment> channels;

    public PagerChannelsAdapter(FragmentManager fm, List<Fragment> channels) {
        super(fm);

        this.channels = channels;
    }

    @Override
    public Fragment getItem(int position) {
        return channels.get(position);
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return FAVORITES;
            case 1:
                return CHANNELS;
        }
        return null;
    }
}
