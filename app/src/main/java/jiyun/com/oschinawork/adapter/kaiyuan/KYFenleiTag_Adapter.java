package jiyun.com.oschinawork.adapter.kaiyuan;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.fragment.kaiyuansoftware.tag.KY_FenLeiTag;
import jiyun.com.oschinawork.fragment.kaiyuansoftware.tag.KY_SecondTag;
import jiyun.com.oschinawork.modle.bean.TagBean;

/**
 * Created by Administrator on 2017/4/16.
 */

public class KYFenleiTag_Adapter extends BaseAdapter<TagBean.SoftwareTypeBean> {
    private SharedPreferences mShared =  App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
    private SharedPreferences.Editor mEditor;
    private FragmentManager fragmentManager = App.activity.getSupportFragmentManager();
    private FragmentTransaction transaction;


    public KYFenleiTag_Adapter(Context context, List<TagBean.SoftwareTypeBean> datas) {
        super(context, R.layout.ky_tag_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final TagBean.SoftwareTypeBean Bean) {
        holder.setText(R.id.KY_TagTextView, Bean.getName());
       holder.setOnclickListener(R.id.FenLei_Erji_LinearLayout, new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mEditor = mShared.edit();
               mEditor.putString("two_tag",Bean.getTag());
               mEditor.commit();
               transaction = fragmentManager.beginTransaction();
               transaction.replace(R.id.Faxian_FramLayout, new KY_SecondTag());
               //这是把这个添加到回退站
               transaction.addToBackStack(null);
               transaction.commit();

           }
       });
    }
}
