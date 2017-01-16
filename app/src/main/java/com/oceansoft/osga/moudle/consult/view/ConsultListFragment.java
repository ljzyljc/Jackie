package com.oceansoft.osga.moudle.consult.view;

import android.view.View;
import android.widget.AdapterView;

import com.oceansoft.osga.moudle.base.adapter.AbsAdapter;
import com.oceansoft.osga.moudle.base.view.views.AbsBaseMapFragment;
import com.oceansoft.osga.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osga.widget.listview.AbsListFragment;

/**
 * Created by TempCw on 2017/1/15.
 */

public class ConsultListFragment extends AbsListFragment<ConsultMatter> {


    @Override
    protected AbsAdapter<ConsultMatter> getAdapter() {
        return null;
    }

    @Override
    protected void sendRequest() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
