package org.apache.cordova.appupdate;

import android.content.res.Resources;

/**
 * Created by LuoWen on 16/9/16.
 */
public class MsgHelper {
  private String packageName;
  private Resources resources;
  public static String APPUPDATE_PROGRESS = "appupdate_progress";
  public static String UPDATE_PROGRESS = "update_progress";
  public static String UPDATING = "updating";
  public static String UPDATE_BG = "update_bg";
  public static String DOWNLOAD_COMPLETE_TITLE = "download_complete_title";
  public static String DOWNLOAD_COMPLETE_POS_BTN = "download_complete_pos_btn";
  public static String DOWNLOAD_COMPLETE_NEU_BTN = "download_complete_neu_btn";
  public static String UPDATE_ERROR_TITLE = "update_error_title";
  public static String UPDATE_ERROR_MESSAGE = "update_error_message";
  public static String UPDATE_ERROR_YES_BTN = "update_error_yes_btn";

  public static String LAYOUT_DIALOG_UPDATE = "layout_dialog_update";
  public static String TV_VERSION_CODE = "tv_version_code";
  public static String TV_REMARK = "tv_remark";
  public static String BTN_DIALOG_UPDATE = "btn_dialog_update";
  public static String STYLE_MY_DIALOG = "MyDialog";
  public static String STYLE_BOTTOM_MENU_ANIMATIOn = "bottom_menu_animation";


  MsgHelper(String packageName, Resources resources) {
    this.packageName = packageName;
    this.resources = resources;
  }

  public int getId(String name) {
    return resources.getIdentifier(name, "id", packageName);
  }

  public int getString(String name) {
    return resources.getIdentifier(name, "string", packageName);
  }

  public int getLayout(String name) {
    return resources.getIdentifier(name, "layout", packageName);
  }

  public int getStyle(String name) {
    return resources.getIdentifier(name, "style", packageName);
  }
}
