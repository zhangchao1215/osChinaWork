package jiyun.com.oschinawork.adapter.huodong;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.modle.bean.XianXiaHuoDongBean;

/**
 * Created by Administrator on 2017/4/22.
 */

public class HuoDongAdapter extends BaseAdapter<XianXiaHuoDongBean.EventBean> {
    public HuoDongAdapter(Context context, List<XianXiaHuoDongBean.EventBean> datas) {
        super(context, R.layout.activity_huodong_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, XianXiaHuoDongBean.EventBean eventBean) {
        ImageView view = (ImageView) holder.itemView.findViewById(R.id.HuoDong_Image);

        holder.setText(R.id.HuoDong_TextName, eventBean.getTitle());
        holder.setText(R.id.HuoDong_TextBody, eventBean.getCity());
        holder.setText(R.id.HuoDong_TextTime, eventBean.getStartTime());
        Glide.with(context).load(eventBean.getCover())
                .into(view);

    }
}
