package bawei.com.dailywork.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import org.xutils.x;

/**
 * autohor:谢兴张(asus)
 * date:2017/4/11
 * effect:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); //xUtils初始化
        Utils.init(getApplicationContext());
    }
}
