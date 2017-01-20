package com.oceansoft.osga.moudle.usercenter.register.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterAutoCodeInfo;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterResultInfo;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterSuccessInfo;
import com.oceansoft.osga.moudle.usercenter.register.presenter.RegisterPresenter;
import com.oceansoft.osga.mvp.view.impl.AbsMvpMapActivity;
import com.oceansoft.osga.utils.DialogUtil;
import com.oceansoft.osga.utils.ToastUtil;

/**
 * Created by TempCw on 2017/1/12.
 */

public class RegisterUI extends AbsMvpMapActivity implements View.OnClickListener{
    private RegisterPresenter registerPresenter;
    EditText et_mobile;
    EditText et_captcha;
    Button bu_captcha;
    EditText et_password;
    EditText et_confirm_password;
    Button bu_register;
    private static final int what_countdownGetValidateCode = 0x10;
    private final static int REGETVALIDATE_SECONDS = 120;
    private int RegetValidate_Seconds=REGETVALIDATE_SECONDS;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case what_countdownGetValidateCode:

                   bu_captcha.setClickable(false);
                   bu_captcha.setEnabled(false);
                   bu_captcha.setText(String.format("%d秒后重新获取", RegetValidate_Seconds));
                   RegetValidate_Seconds=RegetValidate_Seconds-1;
                   if (RegetValidate_Seconds<0){
                       sendEmptyMessage(0);
                       return;
                   }
                   removeMessages(what_countdownGetValidateCode);
                   sendEmptyMessageDelayed(what_countdownGetValidateCode,1000);
                   break;
               default:
                    bu_captcha.setClickable(true);
                   bu_captcha.setEnabled(true);
                   RegetValidate_Seconds=REGETVALIDATE_SECONDS;
                   bu_captcha.setText("获取验证码");
                   break;
           }
        }
    };


    @Override
    public void bindPresenter() {
        registerPresenter=new RegisterPresenter(RegisterUI.this);
        putPresenter(registerPresenter, new IRegisterView() {

            @Override
            public void onStartLoading() {
                DialogUtil.showLoadDialog(RegisterUI.this);
            }

            @Override
            public void registerSuccess(RegisterResultInfo appUser) {
                Log.i("jc",appUser.getStatusCode()+String.valueOf(appUser.isSucc())+"成功");

            }

            @Override
            public void registerFaild(String errrorMessage) {
                Log.i("jc",errrorMessage+"注册失败");
                ToastUtil.showToast(RegisterUI.this,errrorMessage);
            }

            @Override
            public void getAutoCode(RegisterAutoCodeInfo s) {
                Log.i("jc",s.getStatusCode()+String.valueOf(s.isSucc())+"--");
                mHandler.removeMessages(what_countdownGetValidateCode);
                mHandler.sendEmptyMessage(what_countdownGetValidateCode);
            }

            @Override
            public void getAutoCodeFaild(String error) {
                Log.i("jc","获取验证码失败"+error);
                ToastUtil.showToast(RegisterUI.this,error);
            }

            @Override
            public void onFinish() {
                DialogUtil.closeLoadDialog();
            }

        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        bindNavagation(R.id.relativelayout,R.string.register,RegisterUI.this);



    }


    private void initView() {
         et_mobile= (EditText) findViewById(R.id.et_mobile);
         et_captcha= (EditText) findViewById(R.id.et_captcha);
         bu_captcha= (Button) findViewById(R.id.bu_captcha);
         et_password= (EditText) findViewById(R.id.et_password);
         et_confirm_password= (EditText) findViewById(R.id.et_password);
         bu_register= (Button) findViewById(R.id.bu_register);

        bu_captcha.setOnClickListener(this);
        bu_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bu_captcha:
                //获取验证码
                registerPresenter.getAuthCode("13182676063","1");
                break;
            case R.id.bu_register:
                //注册
                registerPresenter.register("13182676063","123456","120987");
                break;
            default:
                break;
        }
    }
}
