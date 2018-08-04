package com.feximin.mvp;

import android.app.Activity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Feximin on 2018/8/4.
 */
public class BaseMvpPresenter<V extends BaseMvpView> {

    protected V view;
    protected final Activity mActivity;
    protected boolean mReleased;
    protected CompositeDisposable mCompositeDisposable;

    public BaseMvpPresenter(V t){
        this.view = t;
        this.mActivity = view.getActivity();
        onAttach();
    }

    protected void onAttach(){

    }

    public void addDisposableToComposite(Disposable disposable){
        if (mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        if (disposable != null){
            mCompositeDisposable.add(disposable);
        }
    }



    public void detachView(){
        if (mReleased){
            return;
        }
        mReleased = true;
        if (mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
        view.release();
        view = null;
    }

    public boolean isViewAttached(){
        return !mReleased;
    }

    public boolean isViewDetached(){
        return mReleased;
    }

    public V getView(){
        return this.view;
    }
}
