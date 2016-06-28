package com.love.cookies.doctor.Presenter;

import com.love.cookies.doctor.App.DoctorApplication;
import com.love_cookies.cookie_library.Application.ActivityCollections;
import com.love_cookies.cookie_library.Utils.ToastUtils;

import com.love.cookies.doctor.Model.Bean.EffectBean;
import com.love.cookies.doctor.Model.Biz.EffectMainBiz;
import com.love.cookies.doctor.Model.Biz.Interface.CallBack;
import com.love.cookies.doctor.R;
import com.love.cookies.doctor.View.Interface.IEffectMain;

/**
 * Created by xiekun on 2015/11/26 0026.
 *
 * 疗效Presenter
 */
public class EffectMainPresenter {

    private EffectMainBiz effectMainBiz;
    private IEffectMain iEffectMain;

    public EffectMainPresenter(IEffectMain iEffectMain) {
        effectMainBiz = new EffectMainBiz();
        this.iEffectMain = iEffectMain;
    }

    public void getEffectDate() {
        if(DoctorApplication.getInstance().checkNetwork()) {
            iEffectMain.showLoading();
            effectMainBiz.getEffectDate(new CallBack<EffectBean>() {
                @Override
                public void getSuccess(EffectBean result) {
                    if(result.getYi18().size() > 0) {
                        iEffectMain.initEffectDate(result);
                    } else {
                        iEffectMain.setEmptyView();
                    }
                    iEffectMain.hideLoading();
                }

                @Override
                public void getFailed(String msg) {
                    ToastUtils.show(ActivityCollections.getInstance().currentActivity(), R.string.loading_failed);
                    iEffectMain.setFailedView();
                    iEffectMain.hideLoading();
                }
            });
        }
    }

}
