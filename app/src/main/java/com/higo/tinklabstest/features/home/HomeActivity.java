package com.higo.tinklabstest.features.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

import com.higo.tinklabstest.R;
import com.higo.tinklabstest.base.BaseActivity;
import com.higo.tinklabstest.entity.CityGuide;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.labNavigator)
    TabLayout labNavigator;

    @BindView(R.id.viewPagger)
    ViewPager viewPager;

    public MyPagerAdapter myPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initTabLayout();
    }

    private void initTabLayout() {
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        labNavigator.setupWithViewPager(viewPager);
        labNavigator.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        labNavigator.setBackgroundColor(Color.parseColor("#F3F3F3"));
        labNavigator.setTabTextColors(Color.parseColor("#a2a2a2"),Color.parseColor("#001835"));
    }

    private final class MyPagerAdapter extends FragmentStatePagerAdapter {

        private String[] mTitles = new String[]{getResources().getString(R.string.tab_city_guide), getResources().getString(R.string.tab_shop),getResources().getString(R.string.tab_eat)};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                   fragment = CityGuideFragment.newInstance();
                    break;
                case 1:
                    fragment = ShopFragment.newInstance();
                    break;
                case 2:
                    fragment = EatFragment.newInstance();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}
