package com.vaenow.appupdate.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;



/**
 * @author: zhengliang
 * @date: 2019/1/4
 * @description: 自定义dialog
 */
public class UpdateDialog extends Dialog implements View.OnClickListener {

  private Context context;

  private int layoutResID;
  private int[] styleResIDs;

  /**
   * 要监听的控件id
   */
  private int[] listenedItems;

  private OnCenterItemClickListener listener;


  UpdateDialog(Context context, int layoutResID,int[] styleResIDs, int[] listenedItems) {
    super(context, styleResIDs[0]);
    this.styleResIDs = styleResIDs;
    this.context = context;
    this.layoutResID = layoutResID;
    this.listenedItems = listenedItems;

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Window window = getWindow();
    // 此处可以设置dialog显示的位置为居中
    if (window != null) {
      setContentView(layoutResID);
      // 宽度全屏
      WindowManager windowManager = ((Activity) context).getWindowManager();
      DisplayMetrics dm = new DisplayMetrics();
      windowManager.getDefaultDisplay().getMetrics(dm);
      WindowManager.LayoutParams lp = getWindow().getAttributes();
      // 设置dialog宽度为屏幕的4/5
      lp.width = dm.widthPixels;
      window.setAttributes(lp);
      window.setGravity(Gravity.CENTER);
      // 添加动画效果
      window.setWindowAnimations(this.styleResIDs[1]);

      // 点击Dialog外部消失
      setCanceledOnTouchOutside(true);
    }
    for (int id : listenedItems) {
      findViewById(id).setOnClickListener(this);
    }
  }

  public interface OnCenterItemClickListener {

    void onCenterItemClick(UpdateDialog dialog, View view);

  }

  public void setOnCenterItemClickListener(OnCenterItemClickListener listener) {
    this.listener = listener;
  }

  @Override
  public void onClick(View view) {
    dismiss();
    listener.onCenterItemClick(this, view);
  }
}

