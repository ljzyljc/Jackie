package com.oceansoft.osga.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.oceansoft.osga.R;
import com.oceansoft.osga.config.BaseApplication;


/**
 * 对话框工具类
 *
 * @author chenw
 */
public class DialogUtil {

    public static ProgressDialog pd;

    public static Dialog alertDialog = null;

    public static void showLoadDialog(Context mContext) {
        try {
            if (null == alertDialog) {
                View contentView = View.inflate(mContext, R.layout.dialog_progressbar, null);
                alertDialog = new Dialog(mContext, R.style.AppDialog);
                alertDialog.setContentView(contentView);
                setWidthHeight(contentView.findViewById(R.id.layout));
                alertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setWidthHeight(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int width = view.getMeasuredWidth();
        if (width < view.getMeasuredHeight()) {
            width = view.getMeasuredHeight();
        }
        view.getLayoutParams().height = width;
        view.getLayoutParams().width = width + DensityUtil.px2dip(BaseApplication.getInstance(), 100);
    }

    public static void showLoadDialog(Context mContext, final String message) {
        showLoadDialog(mContext, message, null);
    }

    public static void showLoadDialog(Context mContext, final String message, final OnCancelListener listener) {
        if (null == alertDialog) {
            alertDialog = new Dialog(mContext, R.style.AppDialog);
            alertDialog.setCancelable(true);
            View contentView = View.inflate(mContext, R.layout.dialog_progressbar, null);
            alertDialog.setContentView(contentView);
            alertDialog.setOnCancelListener(new OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    alertDialog = null;
                    if (null != listener) {
                        listener.onCancel(dialog);
                    }
                }
            });
            ((TextView) contentView.findViewById(R.id.loadingtips)).setText(message);
            alertDialog.show();
        }
    }

    public static void showSystemLoadDialog(Context mContext) {
        if (null == alertDialog) {
            alertDialog = new Dialog(mContext, R.style.AppDialog);
            alertDialog.setContentView(R.layout.dialog_progressbar);
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            alertDialog.show();
        }
    }

    public static void showNormalLoadDiaglog(Context mContext, String text, Boolean cancelable) {
        View contentView = View.inflate(mContext, R.layout.dialog_progressbar, null);
        if (alertDialog == null) {
            alertDialog = new Dialog(mContext, R.style.AppDialog);
            alertDialog.setCancelable(cancelable);
            alertDialog.setContentView(contentView);
            ((TextView) contentView.findViewById(R.id.loadingtips)).setText(text);
            alertDialog.show();
        }
    }

    public static void closeLoadDialog() {
        try {
            if (alertDialog != null) {
                alertDialog.setOnCancelListener(null);
                alertDialog.dismiss();
                alertDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showListDialog(Context mContext, final ListDialogListner onItemClickListner, BaseAdapter adapter) {
        View contentView = View.inflate(mContext, R.layout.listview_layout, null);
        ListView listView = (ListView) contentView.findViewById(R.id.list);
        listView.setSelector(R.drawable.item_selector);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemClickListner.onItemClick(adapterView, view, i, l);
            }
        });
        if (alertDialog != null) {
            closeLoadDialog();
        }
        alertDialog = new Dialog(mContext, R.style.ThemeDialog);
        alertDialog.setCancelable(true);
        //        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setContentView(contentView);
        alertDialog.show();
    }

    /**
     * 对话框计数器
     */
    public static int loadingDialogCounter = 0;

    public interface ListDialogListner {
        void onItemClick(AdapterView<?> adapterView, View view, int i, long l);
    }
}
