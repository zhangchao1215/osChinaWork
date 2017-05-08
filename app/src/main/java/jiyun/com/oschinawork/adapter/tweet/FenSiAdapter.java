package jiyun.com.oschinawork.adapter.tweet;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.modle.bean.tweet.FenSiBean;

/**
 * Created by Administrator on 2017/4/26.
 */

public class FenSiAdapter extends BaseAdapter<FenSiBean.FriendBean> {
    public FenSiAdapter(Context context,  List<FenSiBean.FriendBean> datas) {
        super(context, R.layout.item_dongtanxiangqing_pinglun, datas);
    }

    @Override
    public void convert(ViewHolder holder, FenSiBean.FriendBean Bean) {
        holder.setText(R.id.pinglunList_Name,Bean.getName());
        holder.setText(R.id.pinglunlist_content, Bean.getFrom());
        final ImageView imageView = holder.getView(R.id.pinglunlist_head);
        Glide.with(App.activity).load(Bean.getPortrait())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
