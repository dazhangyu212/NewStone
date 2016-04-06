package com.stone.ordering.widget;

import com.stone.ordering.R;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * 类名:CustomDialog 描述: 公司:北京海鑫科技高科技股份有限公司 作者:chenglin 版本: V1.0 创建时间:2015年12月12日
 * 最后修改时间:2015年12月12日
 */
public class CustomDialog {
	public interface ICallback {
		void onOKButtonClick();

		void onCancelButtonClick();
	}

	/**
	 * 自定义对话框
	 * 
	 * @param activity
	 * @param title
	 *            标题
	 * @param data
	 *            显示内容
	 */
	public static void showEditDialog(final Activity activity, String title, String data) {
		ViewGroup root = null;
		final Dialog dialog = new Dialog(activity);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		View view = activity.getLayoutInflater().inflate(R.layout.dialog_tips_2_button, root);
		dialog.setContentView(view);
		dialog.show();
		TextView titleView = (TextView) view.findViewById(R.id.title);
		TextView dataView = (TextView) view.findViewById(R.id.data);
		titleView.setText(title);
		dataView.setText(data);
		Button btnConfirm = (Button) view.findViewById(R.id.btn_confirm);
		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				int id = v.getId();
				if(id == R.id.iv_close){
					dialog.dismiss();
				}else if(id == R.id.btn_confirm){
					dialog.dismiss();
				}
			}
		};
		btnConfirm.setOnClickListener(onClickListener);
		view.findViewById(R.id.iv_close).setOnClickListener(onClickListener);
		view.findViewById(R.id.btn_cancel).setVisibility(View.GONE);
	}

	/**
	 * 自定义对话框
	 * 
	 * @param activity
	 * @param title
	 *            标题
	 * @param data
	 *            显示内容
	 * @param OKText
	 *            确定键内容
	 * @param 按键响应回调
	 */
	public static void showDialog(final Activity activity, String title, String data, String OKText, String CancelText,
			final ICallback callback) {
		ViewGroup root = null;
		final Dialog dialog = new Dialog(activity);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		View view = activity.getLayoutInflater().inflate(R.layout.dialog_tips_2_button, root);
		dialog.setContentView(view);
		dialog.show();
		TextView titleView = (TextView) view.findViewById(R.id.title);
		TextView dataView = (TextView) view.findViewById(R.id.data);
		titleView.setText(title);
		dataView.setText(data);
		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				int id = v.getId();
				if(id == R.id.iv_close){
					dialog.dismiss();
				}else if(id == R.id.btn_confirm){
					dialog.dismiss();
					if (callback != null) {
						callback.onOKButtonClick();
					}
				}else if(id == R.id.btn_cancel){
					dialog.dismiss();
					if (callback != null) {
						callback.onCancelButtonClick();
					}
				}
			}
		};
		Button btnConfirm = (Button) view.findViewById(R.id.btn_confirm);
		btnConfirm.setText(OKText);
		btnConfirm.setOnClickListener(onClickListener);
		Button btncancel = (Button) view.findViewById(R.id.btn_cancel);
		btncancel.setText(CancelText);
		btncancel.setOnClickListener(onClickListener);
		view.findViewById(R.id.iv_close).setOnClickListener(onClickListener);
	}
}
