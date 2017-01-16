package com.oceansoft.osga.moudle.consult.view;

import com.oceansoft.osga.moudle.base.view.views.BaseView;
import com.oceansoft.osga.moudle.consult.bean.ConsultMatter;

import java.util.List;

/**
 * Created by TempCw on 2017/1/15.
 */

public interface IConsultView extends BaseView {
    void hideLoading();

    void showLoading();

    void update();

    void loadMore();

    void showError();

    void load(ConsultMatter consultMatter);

    void loadFail();



}
