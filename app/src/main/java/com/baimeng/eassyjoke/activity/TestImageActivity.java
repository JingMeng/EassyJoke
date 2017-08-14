package com.baimeng.eassyjoke.activity;

import android.content.Intent;
import android.view.View;

import com.baimeng.eassyjoke.R;
import com.baimeng.eassyjoke.imageselector.ImageSelectorActivity;
import com.baimeng.framelibrary.base.BaseSkinActivity;
import com.baimeng.framelibrary.base.DefaultNavigationBar;
import com.baimeng.framelibrary.skin.SkinResources;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/14.
 */

public class TestImageActivity extends BaseSkinActivity {
    private ArrayList<String> mImageList ;
    @Override
    public void changeSkin(SkinResources skinResources) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {
        new DefaultNavigationBar.Builder(this)
                .setLeftText("返回")
                .setTitle("投稿")
                .setRightText("发布")
                .setRightClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(MainActivity.class);
                    }
                })
                .setLeftClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .build();
    }

    @Override
    protected void setContentView(){
        setContentView(R.layout.activity_test_image);
    }

    public void selectorimg(View view){
        Intent intent = new Intent(this,ImageSelectorActivity.class);
        intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_COUNT,9);
        intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE,ImageSelectorActivity.MODE_MULTI);
        intent.putStringArrayListExtra(ImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,mImageList);
        intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA,true);
        startActivity(intent);
    }
}
