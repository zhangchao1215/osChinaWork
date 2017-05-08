package jiyun.com.oschinawork.config;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.base.BaseFragment;

import static android.R.attr.fragment;
import static jiyun.com.oschinawork.App.lastFragment;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ConfigFragment {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private BaseFragment fragment;
    private Boolean isbask;
    private String simpleName;

    /*
            单例模式,懒汉式
             */
    private ConfigFragment() {
        init();
    }

    private static ConfigFragment configFragment;

    public static synchronized ConfigFragment getInstance() {
        if (configFragment == null) {
            configFragment = new ConfigFragment();
            return configFragment;
        }
        return configFragment;

    }

    public ConfigFragment init() {
        //获取事物管理Fragment
        manager = App.activity.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        return this;
    }

    //开始
    public ConfigFragment start(Class<? extends BaseFragment> fragmentClass) {
        this.isbask = false;
        simpleName = fragmentClass.getSimpleName();
//        transaction = manager.beginTransaction();
        //根据tag查找fragment 如果能找到就代表fragment已经实例化了，否则动态实例化；

        fragment = (BaseFragment) manager.findFragmentByTag(simpleName);
        if (fragment == null) {
            try {
                //java的动态代理，动态创建Fragment对象
                fragment = fragmentClass.newInstance();
                transaction.add(R.id.FrameLayout_contentGroup, fragment, simpleName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //隐藏上一个Fragment,记录最后一个Fragment

        if (App.lastFragment != null) {
            transaction.hide(App.lastFragment);
        }


        //显示Fragment
        transaction.show(fragment);
        //把每个fragment添加到回退站中，记录了类名
        transaction.addToBackStack(simpleName);
        return this;
    }


    //加载数据

    public ConfigFragment Params(Bundle bundle) {
        if (bundle != null) {
            fragment.setParams(bundle);
        }
        return this;
    }

    //退出

    public ConfigFragment isBack(boolean isbask) {
        this.isbask = isbask;
        //返回键
        if (isbask) {
        }
        return this;
    }

    //记录上一个Fragment,隐藏上一个
    public BaseFragment build() {
        //记录跳转的类名，并加入到回退站
        transaction.addToBackStack(simpleName);
        App.lastFragment = fragment;
        transaction.commit();//提交事物
        return fragment;

    }

}
