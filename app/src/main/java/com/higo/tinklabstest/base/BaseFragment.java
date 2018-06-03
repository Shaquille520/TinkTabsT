package com.higo.tinklabstest.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.higo.tinklabstest.R;

/**
 * @author sharkliu
 * @version 1.0
 */

public class BaseFragment extends Fragment {

    private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
}
