package com.baimeng.framelibrary.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.baimeng.framelibrary.R;
import com.baimeng.library.navigation.AbsNavigationBar;

/**
 * Created by Administrator on 2017/7/13.
 */

public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder.DefaultNavigationBarParams> {

    public DefaultNavigationBar(DefaultNavigationBar.Builder.DefaultNavigationBarParams params) {
        super(params);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        //绑定效果
        setText(R.id.title_text ,getParams().mTitle);
        setText(R.id.right_text,getParams().mRightText);
        setText(R.id.left_text,getParams().mLeftText);

        setOnClickListener(R.id.right_text,getParams().mRightClickListener);
        //左边写一个默认的finish activity
        setOnClickListener(R.id.left_text,getParams().mLeftClickListener);
    }

    public static class Builder extends AbsNavigationBar.Builder{
        DefaultNavigationBarParams P ;

        public Builder(Context context) {
            super(context, null);
            P = new DefaultNavigationBarParams(context , null);
        }

        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            P = new DefaultNavigationBarParams(context , parent);
        }

        @Override
        public DefaultNavigationBar build() {
            DefaultNavigationBar navigationBar = new DefaultNavigationBar(P);
            return navigationBar;
        }

        //设置所有效果

        public Builder setTitle(String title){
            P.mTitle = title ;
            return this ;
        }

        public Builder setLeftText(String text){
            P.mLeftText = text ;
            return this ;
        }

        public Builder setRightText(String text){
            P.mRightText = text ;
            return this ;
        }

        public Builder setRightIcon( int res){
            P.mRightIcon = res ;
            return this ;
        }

        public Builder setLeftIcon( int res){
            P.mLeftIcon = res ;
            return this ;
        }


        public Builder setRightClick(View.OnClickListener listener){
            P.mRightClickListener = listener ;
            return this ;
        }

        public Builder setLeftClick( View.OnClickListener listener){
            P.mLeftClickListener = listener ;
            return this ;
        }


        public static class DefaultNavigationBarParams extends AbsNavigationParams{

            public String mTitle;
            public String mRightText;
            public int mRightIcon;
            public int mLeftIcon;
            public View.OnClickListener mLeftClickListener = new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    ((Activity)mContext).finish();
                }
            };
            public View.OnClickListener mRightClickListener;
            public String mLeftText;

            DefaultNavigationBarParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
