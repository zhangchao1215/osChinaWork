package jiyun.com.oschinawork.fragment.searchFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ComponentInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.modle.bean.db.SearchMyanger;

import static android.R.attr.id;

/**
 * Created by Administrator on 2017/4/17.
 */

public class Search_FromActivity extends BaseActivity {
    @BindView(R.id.Search_FromEdit)
    EditText SearchFromEdit;
    @BindView(R.id.Search_FromText)
    TextView SearchFromText;
    @BindView(R.id.Search_FromLayout)
    FrameLayout SearchFromLayout;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private String name;
    private SearchMyanger manger;
    @Override
    protected int getLayoutId() {
        return R.layout.search_fromlayout;
    }

    @Override
    protected void init() {
        manger = new SearchMyanger(getApplicationContext());
        fragmentManager = this.getSupportFragmentManager();
        initTag();
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.Search_FromText)
    public void onViewClicked() {
        String name = SearchFromEdit.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
        } else {
            mEditor.putString("Name", name);
            manger.insert(name);
            mEditor.commit();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.Search_FromLayout, new MainSearchFragment());
            transaction.commit();

        }
    }

    private void initTag(){
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Search_FromLayout,new SearchListFragment());
        transaction.commit();


    }


}
