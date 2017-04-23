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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.modle.bean.TweetNewBean;

import static android.R.attr.port;
import static jiyun.com.oschinawork.R.id.MinImageView;

/**
 * Created by Administrator on 2017/4/16.
 */

public class TweetAdapter extends BaseAdapter<TweetNewBean.TweetBean> {
    private String SystemDate;
    public TweetAdapter(Context context, List<TweetNewBean.TweetBean> datas) {
        super(context, R.layout.item_zuixindongtan, datas);
    }

    @Override
    public void convert(ViewHolder holder, TweetNewBean.TweetBean Bean) {
        ImageView view = (ImageView) holder.itemView.findViewById(R.id.item_newsdongtan_author_head);
//      你要找对类型  不要照片来个settext
        holder.setText(R.id.item_newsdongtan_author_body, Bean.getBody());
        holder.setText(R.id.item_newsdongtan_author_name, Bean.getAuthor());
//        holder.setText(R.id.TweetTime, Bean.getPubDate());
//        Glide.with(App.activity)
//                .load(Bean.getPortrait())
//                .into(view);
        Glide.with(App.activity).load(Bean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(App.activity.getResources(), resource);
                ciDrawable.setCircular(true);
                view.setImageDrawable(ciDrawable);
            }
        });
        SystemDate = getDate(System.currentTimeMillis(), "yyyy-MM-dd");//得到当前年月日

        long todayLIngCheng = getMorning(new Date()).getTime(); //今天的凌晨时间
        long newSTime = getDate(Bean.getPubDate(), "yyyy-MM-dd HH:mm:ss");//发布的时间
        long systemLong = new Date(System.currentTimeMillis()).getTime();//当前的时间
        long newsDate = getDate(Bean.getPubDate(), "yyyy-MM-dd");//获取到发布的日期
        long D_Date = getDate(SystemDate, "yyyy-MM-dd");//当前日期
        if (newSTime != 0) {
            long poortime = systemLong - newSTime;//发布的时间距离现在的时间相差多少毫秒
            long poor_s = poortime / 1000;  //现在就是相差多少秒
            if (poor_s < 60) {
                holder.setText(R.id.item_newsdongtan_author_date, poor_s + "秒前");
            } else if (poor_s < 3600) {
                holder.setText(R.id.item_newsdongtan_author_date, poor_s / 60 + "分钟前");
            } else if (newSTime > todayLIngCheng) {
                holder.setText(R.id.item_newsdongtan_author_date, poor_s / 3600 + "小时前");
            } else if (Integer.parseInt(getDate((D_Date - newsDate), "d")) == 1) {
                holder.setText(R.id.item_newsdongtan_author_date, "昨天");
            } else if (Integer.parseInt(getDate((D_Date - newsDate), "d")) == 2) {
                holder.setText(R.id.item_newsdongtan_author_date, "前天");
            } else {
                holder.setText(R.id.item_newsdongtan_author_date, Integer.parseInt(getDate((D_Date - newsDate), "d")) + "天前");
            }

        }
    }
    //获取今天凌晨时间

    private Date getMorning(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    private String getDate(long time, String geshi) {
        SimpleDateFormat sdf = new SimpleDateFormat(geshi);
        Date date = new Date(time);
        return sdf.format(date);
    }

    private Long getDate(String strTime, String geshi) {
        SimpleDateFormat sdf = new SimpleDateFormat(geshi);
        try {
            return sdf.parse(strTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
