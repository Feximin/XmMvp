package com.feximin.mvp;

/**
 * Created by Feximin on 2018/8/4.
 */
public class SampleLoginContractor {

    static class Presenter extends BaseMvpPresenter<View>{

        public Presenter(View t) {
            super(t);
        }

        void login(String name, String pd){

        }
    }

    interface View extends BaseMvpView{
        void onLoginSuccess();
        void onLoginFailed(int errCode);
    }

}
