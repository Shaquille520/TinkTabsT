package com.higo.tinklabstest.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.higo.tinklabstest.data.guide.GuideDataResource;
import com.higo.tinklabstest.data.guide.GuideLocalDataResource;
import com.higo.tinklabstest.data.guide.GuideRemoteDataResource;
import com.higo.tinklabstest.data.guide.GuideRepository;
import com.higo.tinklabstest.utils.AppExecutors;

/**
 * Enables injection of production implementations for
 * {@link } at compile time.
 */
public class Injection {

   public static  GuideRepository provideGuideRepository(){
       AppExecutors appExecutors=new AppExecutors();
       GuideLocalDataResource localDataResource=GuideLocalDataResource.getInstance(appExecutors,BaseApplication.getInstance().getDaoSession());
       GuideRemoteDataResource remoteDataResource=GuideRemoteDataResource.getInstance(appExecutors );
       return GuideRepository.getInstance(localDataResource,remoteDataResource);
   };
}
