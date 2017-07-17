package com.baimeng.eassyjoke;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baimeng.framelibrary.base.BaseApplication;
import com.baimeng.framelibrary.base.BaseSkinActivity;
import com.baimeng.framelibrary.base.DefaultNavigationBar;
import com.baimeng.framelibrary.base.ExceptionCrashHandler;
import com.baimeng.framelibrary.base.HttpCallBack;
import com.baimeng.library.dialog.AlertDialog;
import com.baimeng.library.fixbug.FixDexManager;
import com.baimeng.library.http.HttpUtils;
import com.baimeng.library.ioc.CheckNet;
import com.baimeng.library.ioc.OnClick;
import com.baimeng.library.ioc.ViewById;
import com.baimeng.library.utils.XPermissionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseSkinActivity {
    @ViewById(R.id.sample_text)
    private TextView tv;
    @ViewById(R.id.parent)
    private LinearLayout mParent ;

    @Override
    protected void initData() {
        tv.setText("口活的活好");

        Map<String, Object> params = new HashMap<>();
        params.put("key","a0126d32f1215b0e769fd7c352bafd01");

        new HttpUtils(this).post().url("http://v.juhe.cn/WNXG/city")
                .addParams(params)
                .execute();
        //获取上次崩溃文件上传至服务器
        File crashFile = ExceptionCrashHandler.getInstance().getCrashFile();
        if(crashFile.exists()){
            //上传至服务器
            try {
                InputStreamReader fileReader = new InputStreamReader(new FileInputStream(crashFile));
                char[] chars = new char[1024];
                int len = 0 ;
                while ((len = fileReader.read(chars))!= -1){
                    String str = new String(chars,0,len);
                    Log.e("TAG",str) ;
                }
            }catch (Exception e){

            }
        }

        //获取本地内存卡中的fix.patch

        XPermissionUtils.requestPermissions(this,0,new String [] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE},new XPermissionUtils.OnPermissionListener(){
            @Override
            public void onPermissionGranted() {
                //阿里的热修复
               // aliFixBug();

                //自己的热修复
                fixDexBug();
            }

            @Override
            public void onPermissionDenied() {

            }
        });



//        AsyncTask <Void,String,Void> task = new AsyncTask<Void,String ,Void>(){
//            @Override
//            protected Void doInBackground(Void... params) {
//                return null;
//            }
//        };
//        task.execute();
    }

    private void fixDexBug() {
        File fixFile = new File(Environment.getExternalStorageDirectory(), "fix.dex");
        if(fixFile.exists()){
            FixDexManager fixDexManager = new FixDexManager(this);
            try {
                fixDexManager.fixDex(fixFile.getAbsolutePath());
                Toast.makeText(this,"我的修复成功",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this,"我的修复失败",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    private void aliFixBug() {
        File fixFile = new File (Environment.getExternalStorageDirectory(),"fix.apatch");
        if(fixFile.exists()){
            try {
                BaseApplication.patchManager.addPatch(fixFile.getAbsolutePath());
                Toast.makeText(MainActivity.this,"Bug修复成功",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this,"Bug修复失败",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
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
                .setLeftClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .build();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @OnClick({R.id.sample_text,R.id.iv_img})
    @CheckNet
    private void onClick(View view){
        if (view.getId()==R.id.sample_text){
            Log.e("=======","text点击");
            showDialog();
        }else if(view.getId() == R.id.iv_img){
//            Toast.makeText(this, "测试："+ (2/0), Toast.LENGTH_SHORT).show();
            Log.e("=======","img点击");
            showDialog();
        }

    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setContentView(R.layout.dialog)
                .setText(R.id.weibo,"微博")
                .setText(R.id.weixin,"微信")
                .setOnClickListener(R.id.share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"分享",Toast.LENGTH_SHORT).show();
                    }
                })
                .fromBottom(true)
                .fullWidth()
                .show();

    }
}