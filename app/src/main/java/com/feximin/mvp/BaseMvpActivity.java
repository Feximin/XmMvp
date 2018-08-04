package com.feximin.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMvpActivity<P extends BaseMvpPresenter> extends AppCompatActivity implements BaseMvpView {

    protected P mHostPresenter;
    protected final List<BaseMvpPresenter> mAllPresenterList = new ArrayList<>(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInitViews(savedInstanceState);
        setContentView();
        initViews(savedInstanceState);
        postInitViews(savedInstanceState);
        mHostPresenter = generateHostPresenter();
        mAllPresenterList.add(mHostPresenter);
        final List<BaseMvpPresenter> childPresenterList = generateChildPresenter();
        if (childPresenterList != null){
            mAllPresenterList.addAll(childPresenterList);
        }
        onInitViewsCompleted();
    }

    protected void beforeInitViews(Bundle savedInstanceState){}
    protected abstract void initViews(Bundle savedInstanceState);
    protected void postInitViews(Bundle savedInstanceState){}

    protected void onInitViewsCompleted(){}

    abstract P generateHostPresenter();

    protected void setContentView(){
        setContentView(getLayoutResId());
    }
    protected abstract int getLayoutResId();

    public final P getHostPresenter(){
        return mHostPresenter;
    }


    @Override
    public Activity getActivity(){
        return this;
    }

    @Override
    public void release(){

    }


    public List<BaseMvpPresenter> generateChildPresenter(){
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (BaseMvpPresenter mp : mAllPresenterList){
            mp.detachView();
        }
    }
}
