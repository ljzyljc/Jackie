package com.oceansoft.osga.mvp.view;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.utils.ActivityCollector;
import com.oceansoft.osga.utils.StatusColorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackie on 2017/1/10.
 *
 *运行时权限调用
 * 在Activity中调用
 *   requeseRunTimePermission(new String[]{Manifest.permission.CALL_PHONE,
 Manifest.permission.WRITE_EXTERNAL_STORAGE}
 , new PermissionListener() {
@Override
public void onGranted() {
Toast.makeText(TestActivity.this,"所有权限都同意了",Toast.LENGTH_SHORT).show();
}

@Override
public void onDenied(List<String> deniedPermission) {
for (String permission: deniedPermission){
Toast.makeText(TestActivity.this, "被拒绝权限："+permission, Toast.LENGTH_SHORT).show();
}
}
});
 *
 *
 *
 *  //如果在没有Activity的界面如何执行
 public void test(){
 BaseActivity.requeseRunTimePermission(new String[]{Manifest.permission.CALL_PHONE},
 new PermissionListener() {
@Override
public void onGranted() {
//当同意的时候
}

@Override
public void onDenied(List<String> deniedPermission) {

//当拒绝的时候
}
});

 }
 */

public class BaseActivity extends FragmentActivity{
    private static PermissionListener mListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        //设置状态栏的颜色和标题栏一致
        StatusColorUtil.applyKitKatTranslucency(BaseActivity.this);
    }
    //设置导航栏
    public void bindNavagation(int id, int text, final Activity activity){
        DefaultNavigation.Builder builder=new
                DefaultNavigation.Builder(activity, (ViewGroup) findViewById(id));
        builder.setCenterText(text)
                .setLeftImg(R.drawable.btn_node_pre_xml)
                .setLeftImgOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.finish();
                    }
                })
                .create();

    }
    //运行时权限调用方法
    public static void requeseRunTimePermission(String []permissions,PermissionListener permissionListener){
        Activity topActivity=ActivityCollector.getTopActivity();
        if (topActivity==null){
            return;
        }
        mListener=permissionListener;
        List<String> permissionList=new ArrayList<>();
        for (String permission:permissions) {
            //如果没有允许该权限，就添加到该List中
            if (ActivityCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
            if (!permissionList.isEmpty()){
                ActivityCompat.requestPermissions(topActivity,permissionList.toArray(new String[permissionList.size()]),1);
            }else {
                mListener.onGranted();
            }
        }
    //    grantResults (授权的结果)


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    List<String> deniedPermissionList=new ArrayList<>();
                    for (int i=0;i<grantResults.length;i++){
                        int grantResult=grantResults[i];
                        String permission=permissions[i];
                        if (grantResult!=PackageManager.PERMISSION_GRANTED){
                            deniedPermissionList.add(permission);
                        }
                    }
                    if (deniedPermissionList.isEmpty()){
                        mListener.onGranted();
                    }else{
                        mListener.onDenied(deniedPermissionList);
                    }
                }
                break;
            default:
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    //运行时权限监听接口
    public interface PermissionListener {
        void onGranted();
        void onDenied(List<String> deniedPermission);

    }


}
