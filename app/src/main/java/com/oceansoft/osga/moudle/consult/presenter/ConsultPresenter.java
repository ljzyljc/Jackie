package com.oceansoft.osga.moudle.consult.presenter;

import android.content.Context;
import android.util.Log;

import com.oceansoft.osga.moudle.base.presenter.BasePresenter;
import com.oceansoft.osga.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osga.moudle.consult.model.ConsultModel;
import com.oceansoft.osga.moudle.consult.view.IConsultView;

/**
 * Created by TempCw on 2017/1/15.
 */

public class ConsultPresenter extends BasePresenter<ConsultModel,IConsultView> {
    private ConsultModel consultModel;
    public ConsultPresenter(Context context) {
        super(context);
        consultModel=new ConsultModel(getContext());
    }
    public void getConsultList(int pageNum){
        consultModel.getMatterList(pageNum,new ConsultModel.OnLoadLister() {
            @Override
            public void onSuccess(ConsultMatter consultMatter) {
                getView().load(consultMatter);
                Log.i("jc","  getView().load(consultMatter);cg");
            }

            @Override
            public void onFailor(String errorMessage) {
                getView().loadFail();
                Log.i("jc",errorMessage+"加载失败");
            }
        });

    }

    @Override
    public void Onunsubscribe() {
        getModel().unsubscribe();
    }
}
