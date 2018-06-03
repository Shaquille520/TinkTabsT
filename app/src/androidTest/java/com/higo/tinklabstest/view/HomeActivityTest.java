package com.higo.tinklabstest.view;

import android.support.annotation.LayoutRes;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.higo.tinklabstest.features.home.HomeActivity;
import com.higo.tinklabstest.features.home.HomeContract;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;


/**
 * Created by sharkliu on 2018/6/2
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(
            HomeActivity.class);
    @Before
    public void setUp(){

    }

}
