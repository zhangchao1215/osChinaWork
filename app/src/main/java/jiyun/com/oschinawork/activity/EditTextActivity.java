package jiyun.com.oschinawork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.fragment.searchFragment.MainSearchFragment;

/**
 * Created by Administrator on 2017/4/14.
 */

public class EditTextActivity extends BaseActivity {
    @BindView(R.id.Search_EditText)
    public EditText SearchEditText;
    @BindView(R.id.Srarch_TextView)
    TextView SrarchTextView;
    @BindView(R.id.Srarch_TextViewQue)
    TextView SrarchTextViewQue;

    @Override
    protected int getLayoutId() {
        return R.layout.search_activity_sousuo;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        String name = SearchEditText.getText().toString().trim();
        if (name.isEmpty()) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainSearchFragment.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }

    @OnClick({R.id.Srarch_TextViewQue, R.id.Srarch_TextView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Srarch_TextViewQue:
                loadData();
                break;
            case R.id.Srarch_TextView:
                finish();
                break;
        }
    }
}
