package com.stone.ordering.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BaseActivity extends Activity {

	
	 /** 
     * 关闭Activity的广播，放在自定义的基类中，让其他的Activity继承这个Activity就行 
     * 主Activity退出时,所有的Activity全部退出
     */  
    protected BroadcastReceiver finishAppReceiver = new BroadcastReceiver() {  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            finish();  
        }  
    };  
  
    @Override  
    public void onResume() {  
        super.onResume();  
        // 在当前的activity中注册广播  
        IntentFilter filter = new IntentFilter();  
        filter.addAction("com.stone.ordering");  
        this.registerReceiver(this.finishAppReceiver, filter);  
    }  
  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        this.unregisterReceiver(this.finishAppReceiver);  
    }  
}
