package com.vaenow.appupdate.android;

/**
 * Created by LuoWen on 2015/12/14.
 */
public class Version {
  /**
   * 本地版本号
   */
  private int localCode;
  /**
   * 服务器版本号
   */
  private int remoteCode;
  /**
   * 服务器版本名称
   */
  private String remoteName;
  /**
   * 更新内容
   */
  private String remoteRemark;


  public Version(int localCode, int remoteCode, String remoteName, String remoteRemark) {
    this.localCode = localCode;
    this.remoteCode = remoteCode;
    this.remoteName = remoteName;
    this.remoteRemark = remoteRemark;
  }

  public int getLocalCode() {
    return localCode;
  }

  public int getRemoteCode() {
    return remoteCode;
  }

  public String getRemoteName() {
    return remoteName;
  }

  public String getRemoteRemark() {
    return remoteRemark;
  }
}