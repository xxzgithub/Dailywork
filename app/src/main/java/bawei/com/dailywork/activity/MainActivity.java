package bawei.com.dailywork.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import bawei.com.dailywork.R;
import bawei.com.dailywork.fragment.FragmentUse;

import static com.loopj.android.http.AsyncHttpClient.log;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
    @ViewInject(R.id.tab_FindFragment_title)
    private TabLayout mTab;
    @ViewInject(R.id.vp_FindFragment_pager)
    private ViewPager mVp;
    private ArrayList<Fragment> mFragments;
    private String[] title = new String[]{"头条", "社会", "国内", "娱乐", "体育", "军事", "科技", "财经", "时尚", "国际"};
    String[] pahtArray = {"tt", "shehui", "gn", "yl", "ty", "js", "kj", "cj", "ss", "gj"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initdata();
        setadap();
    }

    private void setadap() {

        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        mTab.setupWithViewPager(mVp);
       // mTab.setTabsFromPagerAdapter(mVp);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initdata() {
        mFragments = new ArrayList<>();
        for (String paht : pahtArray) {
            log.d("ggggggggggggg",paht);
            mFragments.add(FragmentUse.getFragment(paht));
        }
    }


}
