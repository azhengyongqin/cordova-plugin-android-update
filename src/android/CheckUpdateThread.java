package com.vaenow.appupdate.android;

import android.AuthenticationOptions;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;

import org.apache.cordova.LOG;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LuoWen on 2015/12/14.
 */
public class CheckUpdateThread implements Runnable {
  private String TAG = "CheckUpdateThread";

  /**
   * 保存解析的XML信息
   */
  private HashMap<String, String> mHashMap;
  private Context mContext;
  private List<Version> queue;
  private String packageName;
  private String updateXmlUrl;
  private AuthenticationOptions authentication;
  private Handler mHandler;

  private void setMHashMap(HashMap<String, String> mHashMap) {
    this.mHashMap = mHashMap;
  }

  public HashMap<String, String> getMHashMap() {
    return mHashMap;
  }

  public CheckUpdateThread(Context mContext, Handler mHandler, List<Version> queue, String packageName, String updateXmlUrl, JSONObject options) {
    this.mContext = mContext;
    this.queue = queue;
    this.packageName = packageName;
    this.updateXmlUrl = updateXmlUrl;
    this.authentication = new AuthenticationOptions(options);
    this.mHandler = mHandler;
  }

  @Override
  public void run() {
    // 获取当前软件版本
    Version version = getUpdateVersion();
    //ensure the queue is empty
    queue.clear();
    queue.add(version);
    if (version.getLocalCode() == 0 || version.getRemoteCode() == 0) {
      mHandler.sendEmptyMessage(Constants.VERSION_RESOLVE_FAIL);
    } else {
      mHandler.sendEmptyMessage(Constants.VERSION_COMPARE_START);
    }
  }

  /**
   * 通过url返回文件
   *
   * @param path
   * @return
   */
  private InputStream returnFileIS(String path) {
    LOG.d(TAG, "returnFileIS..");

    URL url = null;
    InputStream is = null;

    try {
      url = new URL(path);
      //利用HttpURLConnection对象,我们可以从网络中获取网页数据.
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      if (this.authentication.hasCredentials()) {
        conn.setRequestProperty("Authorization", this.authentication.getEncodedAuthorization());
      }

      conn.setDoInput(true);
      conn.connect();
      //得到网络返回的输入流
      is = conn.getInputStream();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      mHandler.sendEmptyMessage(Constants.REMOTE_FILE_NOT_FOUND);
    } catch (IOException e) {
      e.printStackTrace();
      mHandler.sendEmptyMessage(Constants.NETWORK_ERROR);
    }

    return is;
  }

  private Version getUpdateVersion() {
    int localCode = 0;
    int remoteCode = 0;
    String remoteName = "";
    String remoteRemark = "";

    try {
      // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
      localCode = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionCode;
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }

    InputStream is = returnFileIS(updateXmlUrl);
    // 解析XML文件。 由于XML文件比较小，因此使用DOM方式进行解析
    ParseXmlService service = new ParseXmlService();
    try {
      setMHashMap(service.parseXml(is));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (null != getMHashMap()) {
      remoteCode = Integer.valueOf(getMHashMap().get("version"));
      remoteName = getMHashMap().get("name");
      remoteRemark = getMHashMap().get("remark");
    }
    return new Version(localCode,remoteCode,remoteName,remoteRemark);
  }
}