package com.oceansoft.osga.moudle.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.home.bean.GridItem;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: dlm
 * @time: 14-6-12 下午2:05
 */
public class HomeGridAdapter extends AbsDragAdapter<GridItem.DataBean> {
    private int row_height = 0;
    private int deleteIndex = -1;

    /**
     * 不可移动的下标，从该下标后面的元素都不可以移动
     */
    private int unmoveIndex = Integer.MAX_VALUE;

    public int getUnMoveIndex() {
        return unmoveIndex;
    }

    public HomeGridAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final GridItem.DataBean gridItem = getItem(i);
        if (view == null)
            view = mInflater.inflate(R.layout.home_adapter_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        TextView name = (TextView) view.findViewById(R.id.name);
        ImageView badge = (ImageView) view.findViewById(R.id.badge);
        ImageView img_delete = (ImageView) view.findViewById(R.id.img_del);

        name.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.third_app_icon_default);

        //删除
//        img_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gridItem.setSelected(0);
//                deleteIndex = -1;
//                ThirdAppHelper.getInstance().remove(gridItem.getUrl());
//
//                int deleteindex = -1;
//                for (int position = 0; position < mList.size(); position++) {
//                    if (mList.get(position).getIcon() == R.drawable.icon_add_gray) {// 添加
//                        deleteindex = position;
//                        break;
//                    }
//                }
//
//                while (mList.size() - 1 >= deleteindex)
//                    mList.remove(mList.size() - 1);
//                mList.remove(i);
//                GridItem add_more = new GridItem("添加更多", R.drawable.icon_add_gray, AppManagerUIListFragment.class);
//                add_more.setDeleteble(false);
//                mList.add(add_more);
//                int null_count = 4 - mList.size() % 4;
//                if (null_count != 4) {
//                    for (int i = 0; i < null_count; i++) {
//                        GridItem item = new GridItem();
//                        item.setDeleteble(false);
//                        mList.add(item);
//                    }
//                }
//                notifyDataSetChanged();
//            }
//        });

        if (row_height > 0) {
            view.setMinimumHeight(row_height);
        }
        if (!TextUtils.isEmpty(gridItem.getTitle())) {
            name.setText(gridItem.getTitle());
        } else {
            name.setText("");
            name.setVisibility(View.GONE);
        }
//        if (gridItem.getRemoteVersion() == gridItem.getVersion() || gridItem.getStatus() == 0) {
//            badge.setVisibility(View.GONE);
//        } else if (gridItem.getRemoteVersion() > gridItem.getVersion() && gridItem.getStatus() == 1) {
//            badge.setVisibility(View.VISIBLE);
//        } else {
//            badge.setVisibility(View.VISIBLE);
//        }
        if (deleteIndex == i) {
            img_delete.setVisibility(View.VISIBLE);
        } else {
            img_delete.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(gridItem.getTitle()) && !gridItem.getTitle().equals("添加更多")) {
            if (TextUtils.isEmpty(gridItem.getIcon())) {
                imageView.setImageResource(R.drawable.third_app_icon_default);
                if(gridItem.getIcon() == null)
                    imageView.setImageResource(R.drawable.third_app_icon_default);
            } else {
                Glide.with(mContext).load(gridItem.getIcon())
                        .placeholder(R.drawable.third_app_icon_default)   //加载默认图片
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            }
        } else {
//            if (gridItem.getIcon() != R.drawable.icon_add_gray)
//                imageView.setVisibility(View.GONE);
//            else {
//                imageView.setImageResource(gridItem.getIcon());
//                unmoveIndex = i;
//            }
        }

        if (i == mHidePosition) {
            view.setVisibility(View.INVISIBLE);
        } else
            view.setVisibility(View.VISIBLE);
        return view;
    }

    //    设置行高
    public void setRowHeight(int height) {
        row_height = height;
        this.notifyDataSetChanged();
    }

    public int getDeleteIndex() {
        return deleteIndex;
    }

    public void setDeleteIndex(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public View inflateView(int position) {
        View temview=null;
        if (temview==null) {
            temview = mInflater.inflate(R.layout.home_adapter_item, null);
        }

        ImageView imageView = (ImageView) temview.findViewById(R.id.img);
        TextView name = (TextView) temview.findViewById(R.id.name);

        GridItem.DataBean gridItem = getItem(position);
        Log.i("jc","-----"+gridItem.getIcon());

        name.setText(gridItem.getTitle());
        Glide.with(mContext).load(gridItem.getIcon()).placeholder(R.drawable.third_app_icon_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        return temview;
    }
}
