package com.oceansoft.osga;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;


import com.oceansoft.osga.config.BaseApplication;
import com.oceansoft.osga.moudle.consult.view.ConsultFragment;
import com.oceansoft.osga.moudle.download.DownloadService;
import com.oceansoft.osga.moudle.home.view.HomeFragment;
import com.oceansoft.osga.moudle.matters.view.MattersFragment;
import com.oceansoft.osga.moudle.usercenter.total.view.views.UserFragment;
import com.oceansoft.osga.mvp.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {
    private List<TabItem> tabItems;
    private FragmentTabHost fragmentTabHost;
//    private String url="http://gaapi.jl.gov.cn:80/econsole/upload/app/ea46e738fddca12282db7c51f36448f1.apk";
//
//    private DownloadService.DownloadBinder downloadBinder;
//    private ServiceConnection connection=new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            downloadBinder= (DownloadService.DownloadBinder) iBinder;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent=new Intent(this,DownloadService.class);
//        startService(intent);
//        bindService(intent,connection,BIND_AUTO_CREATE);
//        Toast.makeText(this,"启动服务",Toast.LENGTH_SHORT).show();
////        downloadBinder.startDownLoad(url);


        //初始化tab页基本信息
        initTabItemList();
        //绑定
        initTabView();
        ((BaseApplication)getApplication()).checkUpgrade(true,true);
//        if (downloadBinder==null){
//            Log.i("jc","downLoad为空");
//        }else{
//            downloadBinder.startDownLoad(url);
//        }


    }

    private void initTabView() {
        fragmentTabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        for(int i=0;i<tabItems.size();i++){
            TabItem tabItem=tabItems.get(i);
            TabHost.TabSpec tabSpec=fragmentTabHost.newTabSpec(tabItem.getTabTextRes()).setIndicator(tabItem.getTabIndicator());
            fragmentTabHost.addTab(tabSpec,tabItem.getFragmentClazz(),tabItem.getBundle());
            //设置背景
            fragmentTabHost.getTabWidget().getChildTabViewAt(i).setBackgroundColor(getResources().getColor(R.color.tabbar_bottom_bg));
            fragmentTabHost.setOnTabChangedListener(this);
            if (i==0){
                tabItem.setChecked(true);
            }

        }
    }

    private void initTabItemList() {
        tabItems=new ArrayList<>();
        this.tabItems.add(new TabItem(MainActivity.this,
                HomeFragment.class,
                R.drawable.icon_menu_index,
                R.drawable.icon_menu_index_a,
                R.string.home));
        this.tabItems.add(new TabItem(MainActivity.this,
                MattersFragment.class,
                R.drawable.icon_menu_center,
                R.drawable.icon_menu_center_a,
                R.string.matter));
        this.tabItems.add(new TabItem(MainActivity.this,
                ConsultFragment.class,
                R.drawable.icon_menu_matter_n,
                R.drawable.icon_menu_matter_p,
                R.string.consult));
        this.tabItems.add(new TabItem(MainActivity.this,
                UserFragment.class,
                R.drawable.icon_menu_personal_center,
                R.drawable.icon_menu_personal_center_a,
                R.string.user));
    }
    private int index=0;
    @Override
    public void onTabChanged(String tabId) {

        for (int i=0;i<tabItems.size();i++){
            TabItem tabItem=tabItems.get(i);
            if (tabItem.getTabTextRes().equals(tabId)){
                tabItem.setChecked(true);
                index=i;
            }else{
                tabItem.setChecked(false);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(connection);
    }
}
