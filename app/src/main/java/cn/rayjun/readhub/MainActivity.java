package cn.rayjun.readhub;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rayjun.readhub.fragment.DevFragment;
import cn.rayjun.readhub.fragment.HotFragment;
import cn.rayjun.readhub.fragment.TechFragment;

public class MainActivity extends AppCompatActivity {


    protected Context context;


    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.viewPagerTab)
    SmartTabLayout viewPagerTab;

    private FragmentPagerItemAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        context = MainActivity.this;
        initView();
    }


    protected void initView() {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final int[] tabIcons = {R.drawable.hot, R.drawable.tech, R.drawable.developer};

        FragmentPagerItems pages = FragmentPagerItems.with(context)
                .add(R.string.hot, HotFragment.class)
                .add(R.string.tech, TechFragment.class)
                .add(R.string.developer, DevFragment.class)
                .create();

        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);

        viewPager.setOffscreenPageLimit(pages.size());
        viewPager.setAdapter(adapter);

       viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
                @Override
                public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                    View view = inflater.inflate(R.layout.custom_tab_icon, container, false);
                    ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_icon);
                    tabIcon.setBackgroundResource(tabIcons[position % tabIcons.length]);
                    return view;
                }
        });
        viewPagerTab.setViewPager(viewPager);
    }


}
