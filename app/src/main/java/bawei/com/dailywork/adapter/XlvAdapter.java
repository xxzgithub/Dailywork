package bawei.com.dailywork.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import bawei.com.dailywork.R;
import bawei.com.dailywork.bean.JsonBean;

/**
 * autohor:谢兴张(asus)
 * date:2017/4/11
 * effect:XlistView适配器
 */

public class XlvAdapter extends BaseAdapter {
    public Context context;
    private List<JsonBean.ResultBean.DataBean> data;

    public XlvAdapter(Context context, List<JsonBean.ResultBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public Object getItem(int position) {
        return data.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.xlv_item, null);
            viewHolder.from = (TextView) convertView.findViewById(R.id.from);
            viewHolder.des = (TextView) convertView.findViewById(R.id.des);
            viewHolder.image1 = (ImageView) convertView.findViewById(R.id.image1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.from.setText(data.get(position).getTitle());
        viewHolder.des.setText(data.get(position).getAuthor_name());
        ImageOptions imageOptions = new ImageOptions.Builder().setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120)).setRadius(DensityUtil.dip2px(5)).setImageScaleType(ImageView.ScaleType.CENTER_CROP).setLoadingDrawableId(R.mipmap.ic_launcher).setUseMemCache(true).setFailureDrawableId(R.mipmap.ic_launcher).build();
        x.image().bind(viewHolder.image1, data.get(position).getThumbnail_pic_s(), imageOptions);
        return convertView;
    }

    class ViewHolder {
        ImageView image1;
        TextView from, des;
    }
}
