package com.example.snow.viewpagerfragment;


import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    //fragment 控件
    private Fragment messageFragment;
    private Fragment contactFragment;
    private Fragment newsFragment;
    private Fragment settingFragment;

    //layout控件
    private LinearLayout layout_message;
    private LinearLayout layout_contact;
    private LinearLayout layout_news;
    private LinearLayout layout_setting;
    //图片控件
    private ImageView messageImg;
    private ImageView contactImg;
    private ImageView newsImg;
    private ImageView settingImg;
    //文本控件
    private TextView messageTV;
    private TextView contactTV;
    private TextView newsTV;
    private TextView settingTV;

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        selected(0);
    }

    private void initView() {

        //初始化控件
        messageImg = (ImageView) findViewById(R.id.iv_message);
        contactImg = (ImageView) findViewById(R.id.iv_contact);
        newsImg = (ImageView) findViewById(R.id.iv_news);
        settingImg = (ImageView) findViewById(R.id.iv_setting);

        messageTV = (TextView) findViewById(R.id.tv_message);
        contactTV = (TextView) findViewById(R.id.tv_contact);
        newsTV = (TextView) findViewById(R.id.tv_news);
        settingTV = (TextView) findViewById(R.id.tv_setting);

        layout_contact = (LinearLayout) findViewById(R.id.layout_contact);
        layout_message = (LinearLayout) findViewById(R.id.layout_message);
        layout_news = (LinearLayout) findViewById(R.id.layout_news);
        layout_setting = (LinearLayout) findViewById(R.id.layout_setting);

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);


        messageFragment = new MessageFragment();
        contactFragment = new ContactFragment();
        newsFragment = new NewsFragment();
        settingFragment = new SettingFragment();

        fragmentList = new ArrayList<>();

        fragmentList.add(messageFragment);
        fragmentList.add(contactFragment);
        fragmentList.add(newsFragment);
        fragmentList.add(settingFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvent() {
        layout_message.setOnClickListener(this);
        layout_contact.setOnClickListener(this);
        layout_news.setOnClickListener(this);
        layout_setting.setOnClickListener(this);


    }

    //点击事件的监听
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.layout_message:
                selected(0);
                break;
            case R.id.layout_contact:
                selected(1);
                break;
            case R.id.layout_news:
                selected(2);
                break;
            case R.id.layout_setting:
                selected(3);
                break;

        }

    }

    private void selected(int i) {
        //选中的时候讲图片设置为亮色
        //改变fragment 的内容
        setTab(i);
        //设置当前的viewpager 和选择的相同
        mViewPager.setCurrentItem(i);

    }

    private void setTab(int i) {
        restImg();
        switch (i) {
            case 0:
                messageImg.setImageResource(R.drawable.message_selected);
                messageTV.setTextColor(Color.WHITE);
                break;
            case 1:
                contactImg.setImageResource(R.drawable.contacts_selected);
                contactTV.setTextColor(Color.WHITE);
                break;
            case 2:
                newsImg.setImageResource(R.drawable.news_selected);
                newsTV.setTextColor(Color.WHITE);
                break;
            case 3:
                settingImg.setImageResource(R.drawable.setting_selected);
                settingTV.setTextColor(Color.WHITE);
                break;

        }
    }

    //重置图片颜色 暗色
    private void restImg() {
        messageTV.setTextColor(Color.BLACK);
        messageImg.setImageResource(R.drawable.message_unselected);

        contactTV.setTextColor(Color.BLACK);
        contactImg.setImageResource(R.drawable.contacts_unselected);

        newsTV.setTextColor(Color.BLACK);
        newsImg.setImageResource(R.drawable.news_unselected);

        settingTV.setTextColor(Color.BLACK);
        settingImg.setImageResource(R.drawable.setting_unselected);

    }
}
