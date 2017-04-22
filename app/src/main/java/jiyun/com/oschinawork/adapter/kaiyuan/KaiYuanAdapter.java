package jiyun.com.oschinawork.adapter.kaiyuan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.config.ConfigFragment;
import jiyun.com.oschinawork.fragment.kaiyuansoftware.KY_FenLeiFragment;
import jiyun.com.oschinawork.fragment.kaiyuansoftware.tag.KY_FenLeiTag;
import jiyun.com.oschinawork.modle.bean.RuanJianBean;

import static android.R.attr.id;
import static jiyun.com.oschinawork.R.id.Faxian_FramLayout;

/**
 * Created by Administrator on 2017/4/16.
 */

public class KaiYuanAdapter extends BaseAdapter<RuanJianBean.SoftwareTypeBean> {
    private SharedPreferences mShared =  App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
    private SharedPreferences.Editor mEditor;
    private FragmentManager  fragmentManager = App.activity.getSupportFragmentManager();
    private FragmentTransaction transaction;

    public KaiYuanAdapter(Context context, List<RuanJianBean.SoftwareTypeBean> datas) {
        super(context, R.layout.ruanjian_fenlei_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final RuanJianBean.SoftwareTypeBean Bean) {
        holder.setText(R.id.Fenlei_TextItem, Bean.getName());
        holder.setOnclickListener(R.id.KYFeiLei_Linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor = mShared.edit();
                mEditor.putString("tag1", Bean.getTag());
                mEditor.commit();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.Faxian_FramLayout, new KY_FenLeiTag());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });
    }
}
