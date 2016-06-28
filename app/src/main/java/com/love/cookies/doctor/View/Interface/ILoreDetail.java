package com.love.cookies.doctor.View.Interface;

import com.love.cookies.doctor.Model.Bean.LoreDetailBean;

/**
 * Created by xiekun on 2015/11/30 0030.
 *
 * 知识详情Activity View接口
 */
public interface ILoreDetail {
    void initLoreDetail(LoreDetailBean loreDetailBean);
    void setEmptyView();
    void setFailedView();
    void showLoading();
    void hideLoading();
}
