> 文章版权声明：https://blog.csdn.net/qq_23179075/article/details/86581672

# [cordova-plugin-android-update](https://github.com/azhengyongqin/cordova-plugin-android-update)

App updater for Cordova/PhoneGap

本插件在[ cordova-plugin-app-update](https://github.com/vaenow/cordova-plugin-app-update) 的基础上修改的，主要修改内容：
> 1. 修改了更新提示框样式（网易云音乐APP样式）
> 2. 提示框中加入了更新详情。
> 3. 在原插件配置文件中加入了 `<remark></remark>` 标签，来配置更新内容提示。

# Demo
Try it yourself:

Just clone and install this demo. [cordova-plugin-app-update-DEMO](https://github.com/vaenow/cordova-plugin-app-update-demo) :tada:

 * 如果喜欢它，请别忘了给我一颗鼓励的星
 * Support me a `Star` if it is necessary.  :+1:

# Preview
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190121181415373.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzIzMTc5MDc1,size_16,color_FFFFFF,t_70)

# 

![在这里插入图片描述](https://img-blog.csdnimg.cn/2019012118143088.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzIzMTc5MDc1,size_16,color_FFFFFF,t_70)

# Install

### Latest published version on npm (with Cordova CLI >= 5.0.0) 

> `"cordova-android": "6.3.0"`

`cordova plugin add https://github.com/azhengyongqin/cordova-plugin-android-update --save`

# Usage

- Simple:
```js
var updateUrl = "http://192.168.0.1/version.xml";
window.AppUpdate.checkAppUpdate(onSuccess, onFail, updateUrl);
```

- Verbose
```js
var appUpdate = cordova.require('cordova-plugin-app-update.AppUpdate');
var updateUrl = "http://192.168.0.1/version.xml";
appUpdate.checkAppUpdate(onSuccess, onFail, updateUrl);
```

- Auth download  [MORE](https://github.com/vaenow/cordova-plugin-app-update/pull/62)
```js
appUpdate.checkAppUpdate(onSuccess, onFail, updateUrl, {
    'authType' : 'basic',
    'username' : 'test',
    'password' : 'test'
})
```

- Skip dialog boxes
```js
appUpdate.checkAppUpdate(onSuccess, onFail, updateUrl, {
    'skipPromptDialog' : true,
    'skipProgressDialog' : true
})
```

### versionCode

You can simply get the versionCode from typing those code in `Console`

```js
var versionCode = AppVersion.build
console.log(versionCode)  // 302048
```


versionName | versionCode
------- | ----------------
0.0.1  | 18
0.3.4  | 3048  
3.2.4   | 302048
12.234.221  | 1436218

### server version.xml file

```xml
<update>
    <version>10200</version>
    <name>1.2.0</name>
    <remark>1.加入自动升级功能\n2.修复了一些bug\n3.不用扫描下载了</remark>
    <url>http://http://192.168.0.1/android.ap</url>
</update>
```

在原插件配置文件中加入了 `<remark></remark>` 标签，来配置更新内容提示。

### `checkAppUpdate` code

```java
    /**
     * 对比版本号
     */
    int VERSION_NEED_UPDATE = 201; //检查到需要更新； need update
    int VERSION_UP_TO_UPDATE = 202; //软件是不需要更新；version up to date
    int VERSION_UPDATING = 203; //软件正在更新；version is updating

    /**
     * 版本解析错误
     */
    int VERSION_RESOLVE_FAIL = 301; //版本文件解析错误 version-xml file resolve fail
    int VERSION_COMPARE_FAIL = 302; //版本文件对比错误 version-xml file compare fail

    /**
     * 网络错误
     */
    int REMOTE_FILE_NOT_FOUND = 404;
    int NETWORK_ERROR = 405;

    /**
     * 没有相应的方法
     */
    int NO_SUCH_METHOD = 501;

    /**
     * Permissions
     */
    int PERMISSION_DENIED = 601;

    /**
     * 未知错误
     */
    int UNKNOWN_ERROR = 901;
```
# Languages
* 🇨🇳 zh
* 🇺🇸 en 
* 🇩🇪 de 
* 🇫🇷 fr 
* 🇵🇹 pt 
* 🇧🇩 bn 
* 🇵🇱 pl 
* 🇮🇹 it 
* 🇪🇸 es
* 🇷🇺 ru
* 🇰🇷 ko

# Platforms
Android only

# License
MIT

# :snowflake: :beers:

* Please let me know if you have any questions.
* [cordova-plugin-android-update](https://github.com/azhengyongqin/cordova-plugin-android-update)

