package com.stone.ordering.activity;

import com.stone.ordering.R;
import com.stone.ordering.adapter.PhotoWallAdapter;
import com.stone.ordering.adapter.PhotoWallAdapter.Images;

import android.os.Bundle;
import android.widget.GridView;

/**
 * 类名:OOMActivity
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月30日
 */
public class OOMActivity extends BaseActivity {

	/** 
     * 用于展示照片墙的GridView 
     */  
    private GridView mPhotoWall;  
  
    /** 
     * GridView的适配器 
     */  
    private PhotoWallAdapter adapter;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_oom);  
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);  
        adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWall);  
        mPhotoWall.setAdapter(adapter);  
    }  
  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        // 退出程序时结束所有的下载任务  
        adapter.cancelAllTasks();  
    }  

}
