package com.love.cookies.doctor.Presenter;

import com.love.cookies.doctor.App.DoctorApplication;
import com.love_cookies.cookie_library.Application.ActivityCollections;
import com.love_cookies.cookie_library.Utils.ToastUtils;

import com.love.cookies.doctor.Model.Bean.AskCategoryBean;
import com.love.cookies.doctor.Model.Biz.AskCategoryBiz;
import com.love.cookies.doctor.Model.Biz.Interface.CallBack;
import com.love.cookies.doctor.R;
import com.love.cookies.doctor.View.Interface.IAskCategory;

/**
 * Created by xiekun on 2015/12/1 0001.
 *
 * 健康一问条目Presenter
 */
public class AskCategoryPresenter {

    private AskCategoryBiz askCategoryBiz;
    private IAskCategory iAskCategory;

    public AskCategoryPresenter(IAskCategory iAskCategory) {
        askCategoryBiz = new AskCategoryBiz();
        this.iAskCategory = iAskCategory;
    }

    public void getAskCategoryDate(final int page, int id) {
        if(DoctorApplication.getInstance().checkNetwork()) {
            iAskCategory.showLoading();
            askCategoryBiz.getAskCategoryDate(page, id, new CallBack<AskCategoryBean>() {
                @Override
                public void getSuccess(AskCategoryBean result) {
                    if(result.getYi18().size() == 0 && page == 1) {
                        iAskCategory.setEmptyView();
                    } else {
                        iAskCategory.initAskCategoryDate(result);
                    }
                    iAskCategory.hideLoading();
                }

                @Override
                public void getFailed(String msg) {
                    ToastUtils.show(ActivityCollections.getInstance().currentActivity(), R.string.loading_failed);
                    iAskCategory.setFailedView();
                    iAskCategory.hideLoading();
                }
            });
        }
    }

}
