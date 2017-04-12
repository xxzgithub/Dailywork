package bawei.com.dailywork.utils;

import android.content.Context;
import android.util.Log;

import com.bawei.xlistviewlibrary.XListView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import bawei.com.dailywork.adapter.XlvAdapter;
import bawei.com.dailywork.bean.JsonBean;


/**
 * autohor:谢兴张(asus)
 * date:2017/4/11
 * effect:
 */

public class AnalyData {



    public static void requestJson(String path, String uri, final XListView xlv, final Context context){

        RequestParams params = new RequestParams(path);
        params.addQueryStringParameter("uri",uri);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("zzzzzzzzzz",result);
                //解析result
                Gson gson = new Gson();
                JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                List<JsonBean.ResultBean.DataBean> data = jsonBean.getResult().getData();
                Log.d("ssssssss", data.size()+"");
                XlvAdapter adapter = new XlvAdapter(context, data);
                 xlv.setAdapter(adapter);
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });

    }



}
