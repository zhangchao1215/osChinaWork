package jiyun.com.oschinawork.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.BlogWebActivity;
import jiyun.com.oschinawork.config.ConfigFragment;
import jiyun.com.oschinawork.modle.bean.BlogListBean;

/**
 * Created by Administrator on 2017/4/13.
 */

public class BlogAdapter extends BaseAdapter<BlogListBean.BlogBean> {
    public BlogAdapter(Context context, List<BlogListBean.BlogBean> datas) {
        super(context, R.layout.news_recycler_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final BlogListBean.BlogBean blogBean) {
        holder.setText(R.id.contentTitle, blogBean.getTitle());
        holder.setText(R.id.contentBody, blogBean.getBody());
        holder.setOnclickListener(R.id.Recycler_Layout_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BlogWebActivity.class);
                intent.putExtra("BlogId",blogBean.getId());
                App.activity.startActivity(intent);
            }
        });
    }
}
