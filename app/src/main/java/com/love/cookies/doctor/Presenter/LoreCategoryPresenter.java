package com.love.cookies.doctor.Presenter;

import com.love.cookies.doctor.App.DoctorApplication;
import com.love_cookies.cookie_library.Application.ActivityCollections;
import com.love_cookies.cookie_library.Utils.ToastUtils;

import com.love.cookies.doctor.Model.Bean.LoreCategoryBean;
import com.love.cookies.doctor.Model.Biz.Interface.CallBack;
import com.love.cookies.doctor.Model.Biz.LoreCategoryBiz;
import com.love.cookies.doctor.R;
import com.love.cookies.doctor.View.Interface.ILoreCategory;

/**
 * Created by xiekun on 2015/11/30 0030.
 *
 * 知识条目Presenter
 */
public class LoreCategoryPresenter {

    private LoreCategoryBiz loreCategoryBiz;
    private ILoreCategory iLoreCategory;

    public LoreCategoryPresenter(ILoreCategory iLoreCategory) {
        loreCategoryBiz = new LoreCategoryBiz();
        this.iLoreCategory = iLoreCategory;
    }

    public void getLoreCategoryDate(final int page, int id) {
        if(DoctorApplication.getInstance().checkNetwork()) {
            iLoreCategory.showLoading();
            loreCategoryBiz.getLoreCategoryDate(page, id, new CallBack<LoreCategoryBean>() {
                @Override
                public void getSuccess(LoreCategoryBean result) {
                    if(result.getYi18().size() == 0 && page == 1) {
                        iLoreCategory.setEmptyView();
                    } else {
                        iLoreCategory.initLoreCategoryDate(result);
                    }
                    iLoreCategory.hideLoading();
                }

                @Override
                public void getFailed(String msg) {
                    ToastUtils.show(ActivityCollections.getInstance().currentActivity(), R.string.loading_failed);
                    iLoreCategory.setFailedView();
                    iLoreCategory.hideLoading();
                }
            });
        }
    }

}
