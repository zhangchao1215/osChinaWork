package jiyun.com.oschinawork.adapter.huodong;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.HuoDongWebView;
import jiyun.com.oschinawork.modle.bean.XianXiaHuoDongBean;

/**
 * Created by Administrator on 2017/4/22.
 */

public class HuoDongAdapter extends BaseAdapter<XianXiaHuoDongBean.EventBean> {
    public HuoDongAdapter(Context context, List<XianXiaHuoDongBean.EventBean> datas) {
        super(context, R.layout.activity_huodong_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final XianXiaHuoDongBean.EventBean eventBean) {
        ImageView view = (ImageView) holder.itemView.findViewById(R.id.HuoDong_Image);

        holder.setText(R.id.HuoDong_TextName, eventBean.getTitle());
        holder.setText(R.id.HuoDong_TextBody, eventBean.getCity());
        holder.setText(R.id.HuoDong_TextTime, eventBean.getStartTime());
        Glide.with(context).load(eventBean.getCover())
                .into(view);
        holder.setOnclickListener(R.id.HD_LinearLayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HuoDongWebView.class);
                intent.putExtra("hd_detail", eventBean.getId());
                App.activity.startActivity(intent);

            }
        });
    }
}
