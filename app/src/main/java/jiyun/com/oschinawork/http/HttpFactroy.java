package jiyun.com.oschinawork.http;

import android.widget.Switch;

/**
 * Created by Administrator on 2017/4/12.
 */

public class HttpFactroy {
    private static final int OKHTTP = 0;
    private static final int TYPE = OKHTTP;

    public static IHttp create() {
        IHttp iHttp = null;
        switch (TYPE) {
            case OKHTTP:
                iHttp = OkHttpUtils.getInstance();
                break;
        }
        return iHttp;
    }

}
