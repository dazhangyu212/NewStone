package com.stone.ordering.activity;

import com.stone.ordering.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
	private long exitTime = 0; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (KeyEvent.KEYCODE_BACK == keyCode) {  
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出  
            if (System.currentTimeMillis() - exitTime > 2000) {  
                Toast.makeText(getApplicationContext(), "再按一次退出程序",  
                        Toast.LENGTH_SHORT).show();  
                // 将系统当前的时间赋值给exitTime  
                exitTime = System.currentTimeMillis();  
            } else {  
                exitApp();  
            }  
            return true;  
        }  
        return super.onKeyDown(keyCode, event);  
    }  
	
	/** 
     * 退出应用程序的方法，发送退出程序的广播 
     */  
    private void exitApp() {  
        Intent intent = new Intent();  
        intent.setAction("com.stone.ordering");  
        this.sendBroadcast(intent);  
    }  
}
