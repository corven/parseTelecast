package cos.test.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cos.test.R;
import cos.test.adapter.PagerChannelsAdapter;
import cos.test.fragment.FragmentChannels;
import cos.test.fragment.FragmentFavorites;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerChannelsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();
    }

    private void initViewPager() {
        viewPager = (ViewPager)findViewById(R.id.pagerChannels);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentFavorites());
        fragments.add(new FragmentChannels());

        adapter = new PagerChannelsAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

    }
}
