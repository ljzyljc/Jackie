package com.oceansoft.osga.moudle.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oceansoft.osga.R;
import com.oceansoft.osga.WebViewUI;
import com.oceansoft.osga.config.Config;
import com.oceansoft.osga.moudle.home.bean.NewsBean;
import com.oceansoft.osga.moudle.home.view.NewsDetailUI;
import com.oceansoft.osga.utils.NetWorkUtil;
import com.oceansoft.osga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TempCw on 2017/1/14.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<NewsBean.DataBean> mList;
    private LayoutInflater inflater;

    public ViewPagerAdapter(Context context, List<NewsBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;

    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        View view=null;
        if (view==null) {
             view = inflater.from(context).inflate(R.layout.news_top_pic_cell, null);
        }
        ImageView imageView= (ImageView) view.findViewById(R.id.img_pic);
        TextView textView= (TextView) view.findViewById(R.id.img_title);
        final NewsBean.DataBean  dataBean=mList.get(position);
        if (TextUtils.isEmpty(dataBean.getImgUrl())){
            imageView.setImageResource(R.drawable.img_default);
        }else{
            Glide.with(context).load(dataBean.getImgUrl()).placeholder(R.drawable.img_default)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        textView.setText(dataBean.getTitle());
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetWorkUtil.isNetWorkAvailable(context)&&dataBean.getId()>=0){
                    Intent intent=new Intent(context, NewsDetailUI.class);
                    intent.putExtra("title","警务资讯");
                    //FIXME:修改成常量
//                    intent.putExtra("url","https://gaapi.jl.gov.cn:443/econsole/api/news/"+dataBean.getId());
                    intent.putExtra("url", Config.HOST+"econsole/api/news/"+dataBean.getId());
                    intent.putExtra("type",dataBean.getPubDept());
                    intent.putExtra("imgUrl", TextUtils.isEmpty(dataBean.getImgUrl()) ? "" : dataBean.getImgUrl());
                    intent.putExtra("summary", dataBean.getSummary());
                    context.startActivity(intent);

                }else {
                    ToastUtil.showToast(context,R.string.network_invailable);
                }
            }
        });

        return view;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
