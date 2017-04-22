package jiyun.com.oschinawork.adapter.kaiyuan;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

/**
 * Created by Administrator on 2017/4/16.
 */
import java.util.List;

import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.modle.bean.KY_TuiJianBean;
import jiyun.com.oschinawork.modle.bean.RuanJianBean;

public class KYTuiJianAdapter extends BaseAdapter<KY_TuiJianBean.SoftwareBean> {
    public KYTuiJianAdapter(Context context, List<KY_TuiJianBean.SoftwareBean> datas) {
        super(context, R.layout.kaiyuan_newfragment_item, datas);
    }

    @Override
    public void convert(ViewHolder holder,KY_TuiJianBean.SoftwareBean bean) {
        holder.setText(R.id.kaiyuan_newItem1, bean.getName());
        holder.setText(R.id.kaiyuan_newItem2, bean.getDescription());
    }
}
