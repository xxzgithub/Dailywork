package bawei.com.dailywork.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.xlistviewlibrary.XListView;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import bawei.com.dailywork.R;
import bawei.com.dailywork.adapter.XlvAdapter;
import bawei.com.dailywork.bean.JsonBean;

import static bawei.com.dailywork.utils.UrlPath.path;

/**
 * autohor:谢兴张(asus)
 * date:2017/4/11
 * effect:
 */

@ContentView(R.layout.fragment_main)
public class FragmentUse extends Fragment {
    @ViewInject(R.id.xlv)
    private XListView xlv;
    private String mUrl;

    public static FragmentUse getFragment(String url) {
        FragmentUse userFragment = new FragmentUse();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        userFragment.setArguments(bundle);
        return userFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mUrl = bundle.getString("url");
       // AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        /*boolean isWiFi = NetWrok.isWiFi(getActivity());
        if(isWiFi){

        }else{
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("当前没有网络");
            progressDialog.setMessage("拼命加载中.......");
            progressDialog.setCancelable(true);
            progressDialog.show();
        }*/
        boolean dataEnabled = NetworkUtils.getDataEnabled();
        if(dataEnabled){
            initdata();
        }else{
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("当前没有网络");
            progressDialog.setMessage("拼命加载中.......");
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        return x.view().inject(this, inflater, container);
    }

    private void initdata() {
//            AnalyData.requestJson(UrlPath.path,mUrl,xlv,getActivity());

        RequestParams params = new RequestParams(path);
        params.addQueryStringParameter("uri",mUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("zzzzzzzzzz",result);
                //解析result
                Gson gson = new Gson();
                JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                List<JsonBean.ResultBean.DataBean> data = jsonBean.getResult().getData();
//                Log.d("ssssssss", data.size()+"");
                LogUtil.d(data.size()+"");
                XlvAdapter adapter = new XlvAdapter(getActivity(), data);
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
