package com.feximin.mvp;

import android.app.Activity;

/**
 * Created by Feximin on 2018/8/4.
 */
public interface BaseMvpView {
    Activity getActivity();
    void release();
}
