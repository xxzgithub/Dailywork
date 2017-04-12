package bawei.com.dailywork.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * autohor:谢兴张(asus)
 * date:2017/4/12
 * effect:
 */

public class NetWrok {

    //判断是不是WiFi
    public static boolean isWiFi(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.getType() == manager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    //判断是不是流量
    public static boolean isMobile(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.getType() == manager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }
}



