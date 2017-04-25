package jiyun.com.oschinawork.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.WebActivity;
import jiyun.com.oschinawork.modle.bean.NewsListBean;

/**
 * Created by Administrator on 2017/4/12.
 */

public class NewsConterAdapter extends BaseAdapter<NewsListBean.NewsBean> {
    public NewsConterAdapter(Context context, List<NewsListBean.NewsBean> datas) {
        super(context, R.layout.news_recycler_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final NewsListBean.NewsBean newsBean) {
        holder.setText(R.id.contentTitle,newsBean.getTitle());
        holder.setText(R.id.contentBody,newsBean.getBody());
        holder.setOnclickListener(R.id.Recycler_Layout_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("id",newsBean.getId());
                context.startActivity(intent);
            }
        });
    }
}
