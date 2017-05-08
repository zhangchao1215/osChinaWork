package jiyun.com.oschinawork.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/4/26.
 */

public class MyContentLinearLayoutManager extends LinearLayoutManager {
    public MyContentLinearLayoutManager(Context context) {
        super(context);
    }
    public MyContentLinearLayoutManager(Context context,int orientation,boolean reverseLayout){
        super(context,orientation,reverseLayout);
    }

    public MyContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
