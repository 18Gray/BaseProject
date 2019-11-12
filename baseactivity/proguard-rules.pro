#-ignorewarnings
#-keepattributes *Annotation*
#-keepattributes InnerClasses
#-keepattributes Signature
#-renamesourcefileattribute SourceFile
#
#-applymapping misc/core_mapping.txt
#-keep class com.alipay.euler.andfix.** { *; }
#-keep class * extends java.lang.annotation.Annotation
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#
#-keepclassmembers class com.ss.newmedia.theme.BuildThemeRes {
#    public static ** getResMap();
#}
#
#-keepclassmembers class com.ss.android.article.base.feature.detail.view.MyWebChromeClient {
#    public void onSelectionStart(android.webkit.WebView);
#}
#-keepclassmembers class com.ss.android.newmedia.app.BrowserFragment$MyWebChromeClient {
#    public void onSelectionStart(android.webkit.WebView);
#}
#-keepclassmembers class * extends com.nd.android.pandahome.widget.view.WidgetCommonBackground {
#    public void *(int);
#}
#-keepclassmembers class com.ss.android.newmedia.webview.UploadableWebChromeClient {
#    public void openFileChooser(android.webkit.ValueCallback, java.lang.String, java.lang.String);
#    public void openFileChooser(android.webkit.ValueCallback, java.lang.String);
#    public void openFileChooser(android.webkit.ValueCallback);
#}
#-keepclassmembers class * {
#    native <methods>;
#    @com.ss.android.messagebus.Subscriber *;
#}
#
##-keepnames class !android.support.v7.internal.view.menu.**,android.support.** {*;}
#
##-keep class com.google.android.apps.analytics.** {*;}
#
#-keep class eclipse.local.sdk.** {*;}
#
##-keep class com.ss.android.**.R$* {
##    public static <fields>;
##}
##-keep class com.bytedance.**.R$* {
##    public static <fields>;
##}
#
#-keep public enum com.handmark.pulltorefresh.library.PullToRefreshBase$** {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);}
#
#-keep class * implements com.ss.android.common.shrink.IThirdLibAdapter
#-keep public class android.support.v4.content.ContextCompat* {*;}
#-keep public class android.support.v4.content.WakefulBroadcastReceiver* {*;}
#-keep public class android.support.v4.util.SimpleArrayMap* {*;}
#-keep public class android.support.v4.content.Loader* {*;}
#-keep public class android.support.v4.app.LoaderManager* {*;}
#-keep public class android.support.v4.util.* {*;}
#-keep public class android.support.v4.app.NotificationCompat* {*;}
#-keep public class android.support.design.widget.TabLayout* {*;}
#-keep public class android.support.design.widget.TabLayout$* {*;}
#-keep public class * implements com.ss.android.common.update.UpdateChecker
#-keep public class * implements com.ss.android.update.UpdateChecker
#-keep interface com.ss.android.newmedia.ad.I* {*;}
#-keep class com.ss.android.common.http.impl.apache.cookie.SerializableCookie {*;}
#-keep class com.bytedance.article.common.utility.StringUtils {*;}
#-keep class com.bytedance.article.common.utility.Logger {*;}
#-keep class * implements java.io.Serializable {*;}
#-keep class * implements android.os.Parcelable {*;}
#-keep class com.ss.android.newmedia.ConnectivityReceiver {*;}
#-keep class com.ss.android.newmedia.downloads.** {*;}
#-keep class com.ss.android.common.applog.NativeCrashHandler {*;}
#-keep interface com.ss.android.common.I* {*;}
#-keep interface com.ss.android.pushmanager.thirdparty.I* {*;}
#-keep class * implements com.bytedance.common.plugin.interfaces.pushmanager.IPushAdapter {*;}
#-keep class com.ss.android.dex.party.DexDependManager {*;}
#
#-keep public class pl.droidsonroids.gif.GifIOException{*;}
#-keep class u.aly.** {*;}
#-keep class com.umeng.analytics** {*;}
#-keep class com.ss.android.download.DownloadAdapter** {*;}
#
#-keep class com.alipay.android.app.IAlixPay{*;}
#-keep class com.alipay.android.app.IAlixPay$Stub{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
#-keep class com.alipay.sdk.app.PayTask{ public *;}
#-keep class com.alipay.sdk.auth.AlipaySDK{ public *;}
#-keep class com.alipay.sdk.auth.APAuthInfo{ public *;}
#-keep class com.alipay.mobilesecuritysdk.*
#-keep class com.ut.*
#
#-keep class com.bytedance.common.plugin.faces.** {*;}
#-keep class com.bytedance.common.plugin.interfaces.** {*;}
#-keep class com.bytedance.common.plugin.baseface.** {*;}
#-keep class com.bytedance.common.plugin.component.** {*;}
#-keep class com.bytedance.common.plugin.mop.** {*;}
#-keep class com.bytedance.common.plugin.framework.util.PluginUtil* {
#    public static <methods>;
#}
#
#-keep class com.ss.android.account.model.** { *;}
#-keep class com.ss.android.article.base.feature.user.location.model.** { *;}
#
#
#-keep class com.tencent.mm.opensdk.** {*;}
#-keep class com.tencent.wxop.** {*;}
#-keep class com.tencent.mm.sdk.** {*;}
#-keep class com.baidu.** {*;}
##-keep class com.umeng.** {*;}
## Umeng:
#-keepclassmembers class * {
#   public <init> (org.json.JSONObject);
#}
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#-keep class com.xiaomi.** {*;}
#-keep class com.igexin.** {*;}
#-keep class com.amap.api.location.**{*;}
#-keep class com.aps.**{*;}
#-keep class com.alimama.mobile.**{*; }
#-keep public class net.z4tech.** {*;}
#-keepnames class com.aspire.** {*;}
#-keepnames class org.apache.** {*;}
#-keep class android.app.**{ *;}
#
#-dontwarn com.baidu.**
#-dontwarn com.umeng.**
#-dontwarn com.taobao.**
#-dontwarn com.mobisage.**
#-dontwarn com.qihoo360.union.**
#-dontwarn com.aspire.mmupdatesdk.**
#-dontwarn okio.**
#-dontwarn com.squareup.okhttp.**
#-dontwarn com.chukong.cocosplay.**
#-dontwarn com.networkbench.**
#-dontwarn com.alimama.mobile.**
#-dontwarn com.alipay.**
#-dontwarn android.test.**
#-dontwarn org.msgpack.**
#-dontwarn com.google.gson.**
#-dontwarn com.android.volley.**
#-dontwarn com.xiaomi.**
#-dontwarn uk.co.senab.photoview.**
#-dontwarn com.nostra13.universalimageloader.**
#-dontwarn com.facebook.**
#-dontwarn android.content.res.AssetManager
#-dontwarn org.android.agoo.impl.PushService*
#
##add for cocos game start
#-keep class com.ss.android.article.base.feature.game.CocosPlayWrapper {*;}
#-keep class com.ss.android.article.base.feature.game.CocosPlayProxy {*;}
#-keep class com.ss.android.article.base.CocosGameHelper {*;}
#-keep class com.chukong.cocosplay.**{ *;}
#-keep class org.cocos2dx.lib.**{ *;}
#-keep class com.coco.**{ *;}
#-keep class android.content.**{ *;}
#-keep class android.os.**{ *;}
##add for cocos game end
#
## Fresco
## Keep our interfaces so they can be used by other ProGuard rules.
## See http://sourceforge.net/p/proguard/bugs/466/
#-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
### Do not strip any method/class that is annotated with @DoNotStrip
#-keep @com.facebook.common.internal.DoNotStrip class *
#-keepclassmembers class * {
#    @com.facebook.common.internal.DoNotStrip *;
#}
#
## Gson
#-keep class sun.misc.Unsafe { *; }
#-keep class com.umeng.message.** {
#        *;
#}
#-keep class com.umeng.message.protobuffer.* {
#        public <fields>;
#        public <methods>;
#}
#-keep class com.squareup.wire.* {
#        public <fields>;
#        public <methods>;
#}
#-keep class com.umeng.message.local.* {
#        public <fields>;
#        public <methods>;
#}
#-keep class org.android.agoo.impl.*{
#        public <fields>;
#        public <methods>;
#}
#-keep class org.android.agoo.service.* {*;}
#-keep class org.android.spdy.**{*;}
#-keep public class com.ss.android.article.news.R$*{
#    public static final int *;
#}
#
## KVObject
#-keepclassmembers class * extends com.ss.android.kvobj.object.IKVObject {*;}
#
## MM Update
#-keep class com.aspire.mmupdatesdk.sdk.SdkEncrypt.** {*;}
#
## baidu statistics sdk
#-keep public class com.baidu.mobile.appmon.MonActivity{ public protected *;}
#
## appee sdk
#-keep class com.appsee.** {*;}
#
#-keep class com.ss.android.article.base.feature.shrink.extend.**
## ijk播放器
#-keep class tv.danmaku.ijk.media.player.** {
#       *;
#}
## 点播SDK
#-keep class com.ss.ttm.** {*;}
#
#-keep class **$Properties
##service for lockscreen
#-keepclasseswithmembers class com.ss.android.article.base.app.setting.LocalSettings {
#    public static void setLockScreenGuideShown();
#}
#
#-keep class com.ss.android.lockscreen.LockScreenDependAdapter {*;}
#-keep class com.ss.android.lockscreen.data.** {*;}
#
#-keep class com.ss.android.lockscreen.wrapper.LockScreenSDKWrapper {*;}
#
## keep webeditor addJavascriptInterface object
#-keep class com.ss.android.editor.JsCallbackReceiver {*;}
#
#-keep class com.ss.sys.ces.a {*;}
#
## keep mall open sdk
#-keep class com.alibaba.sdk.android.** {*;}
#-keep class com.jdwx.sdk.**{*;}
#
#-keep class com.huawei.android.pushagent.**{*;}
#-keep class com.huawei.android.pushselfshow.**{*;}
#-keep class com.huawei.android.microkernel.**{*;}
#-keep class com.baidu.mapapi.**{*;}
#
## 视频录制
#-dontwarn com.ss.android.mediautils.**
#-keep class com.ss.android.mediautils.** {*;}
## 解决定位不能使用的问题
#-keep class * implements com.bytedance.article.dex.ILocationDepend {*;}
#
## kepler sdk
#-keep class com.kepler.jd.**{
#    public <fields>;
#    public <methods>;
#    public *;
#}
#
## dingding share
#-keep class com.android.dingtalk.share.ddsharemodule.** {*;}
#
## Retrofit
##-keep class retrofit2.** { *; }
##-dontwarn retrofit2.**
##-dontwarn rx.**
## Platform calls Class.forName on types which do not exist on Android to determine platform.
#-keepattributes *Annotation*
#-dontnote com.bytedance.retrofit2.Platform
#-keepclasseswithmembers class * {
#    @com.bytedance.retrofit2.http.* <methods>;
#}
#-keepattributes Signature
#-keepattributes Exceptions
#
#-keep interface com.bytedance.article.common.setting.ISettings {*;}
#-keep class * implements com.bytedance.article.common.setting.ISettings {*;}
#
#-keep class com.ss.android.video.SSMediaPlayerWrapper {*;}
#-keep interface com.ss.android.IGlobalSettingObserver {*;}
##phone
#-keep class com.ss.android.article.base.utils.PhoneCallHelper {*;}
#-keep interface com.ss.android.article.base.utils.PhoneCallHelper$*{*;}
#-keep interface com.ss.android.article.base.utils.PhoneCallHelper$CallPhoneCallback {*;}
#-keep interface com.ss.android.article.base.utils.PhoneCallHelper$ActivityPauseListener {*;}
#-keep class com.f100.main.util.FPhoneCallHelper {*;}
#-keep interface com.ss.android.article.base.utils.CallPhoneVirtualCallback {*;}
#-keep class * implements com.ss.android.article.base.utils.CallPhoneVirtualCallback {*;}
#-keep class com.ss.android.common.app.permission.PermissionsResultAction {*;}
#
#-keep class * extends android.app.Instrumentation {*;}
#-keep class com.ss.android.article.base.feature.video.** {*;}
#-keep class com.ss.android.livechat.chat.widget.** {*;}
#-dontwarn com.ss.android.mediautils.**
#-keep class com.ss.android.mediautils.** {*;}
#-keep class * extends android.app.Instrumentation {*;}
#-keep class com.ss.android.article.common.view.** {*;}
#-keep class com.ss.android.push.daemon.NativeDaemonBase {*;}
#
## 小视频相关
#-keep interface com.ss.android.article.common.module.IVideoDepend {*;}
#-keep class com.ss.android.article.common.module.VideoDependManager {*;}
#-keep class com.ss.android.ugc.detail.detail.model.TTCoverInfo { *; }
#-keep class com.ss.android.ugc.detail.detail.model.ugc.UgcDetailModel { *; }
#-keep class com.ss.ttm.player.TTMediaPlayer {
#    public static <methods>;
#}
#
##homepage相关
#-keep class com.f100.main.homepage.HomepageDependAdapter {*;}
#-keep class com.ss.android.article.common.module.IHomepageDepend {*;}
#-keep class com.f100.main.following.FollowDependAdapter {*;}
#-keep class com.f100.android.mapplugin.MapViewAdapter {*;}
#-keep class com.ss.android.article.lite.adapter.MainDependAdapter {*;}
#-keep class com.f100.main.account_cancellation.AccountCancellationDependAdapter {*;}
#-keep class com.f100.main.homepage.PhoneCallDependAdapter {*;}
#
#-keepnames class org.msgpack.MessageTypeException.** {*;}
#-keepnames class org.msgpack.packer.** {*;}
#-keepnames class org.msgpack.template.** {*;}
#-keepnames class org.msgpack.type.** {*;}
#-keepnames class org.msgpack.unpacker.** {*;}
#-keepnames class org.msgpack.MessagePack.** {*;}
#
#-keep class com.xiaomi.** {*;}
#-keep class com.umeng.common.message.** {*;}
#-keep class com.umeng.message.* {
#        public <fields>;
#        public <methods>;
#}
#-keep class com.umeng.message.protobuffer.* {
#        public <fields>;
#        public <methods>;
#}
#-keep class com.squareup.wire.* {
#        public <fields>;
#        public <methods>;
#}
#-keep class com.umeng.message.local.* {
#        public <fields>;
#        public <methods>;
#}
#-keep class org.android.agoo.impl.*{
#        public <fields>;
#        public <methods>;
#}
#
#-keep public class com.ss.android.article.lite.R$color,com.bytedance.common.plugin.face.R$color {
#    public static final int hwpush_*;
#}
#
#-keep public class com.ss.android.article.lite.R$drawable,com.bytedance.common.plugin.face.R$drawable {
#    public static final int hwpush_*;
#    public static int status_icon;
#    public static int status_icon_l;
#}
#
#-keep public class com.ss.android.article.lite.R$id,com.bytedance.common.plugin.face.R$id {
#    public static final int hwpush_*;
#    public static int big_pic;
#    public static int icon;
#    public static int line1;
#    public static int line3;
#    public static int linear_buttons;
#    public static int linear_icons;
#    public static int listview_layout;
#    public static int push_big_*;
#    public static int push_pure_bigview_*;
#    public static int right_btn;
#    public static int small_btn;
#    public static int smallicon;
#    public static int status_bar_latest_event_content;
#    public static int text;
#    public static int title;
#}
#
#-keep public class com.ss.android.article.lite.R$layout,com.bytedance.common.plugin.face.R$layout {
#    public static final int hwpush_*;
#    public static int push_expandable_big_image_notification;
#    public static int push_expandable_big_text_notification;
#    public static int push_pure_pic_notification;
#}
#
#-keep public class com.ss.android.article.lite.R$menu,com.bytedance.common.plugin.face.R$menu {
#    public static final int hwpush_*;
#}
#
#-keep public class com.ss.android.article.lite.R$plurals,com.bytedance.common.plugin.face.R$plurals {
#    public static final int hwpush_*;
#}
#
#-keep public class com.ss.android.article.lite.R$string,com.bytedance.common.plugin.face.R$string {
#    public static final int hwpush_*;
#    public static int app_name;
#    public static int cloudpush_app_name;
#    public static int cloudpush_version_value;
#    public static int hours_ago;
#    public static int just_now;
#    public static int minutes_ago;
#}
#
#-keep class com.umeng.VersonUtil {*;}
#
## for cronet plugin
#-keep interface com.bytedance.frameworks.baselib.network.http.cronet.I* {*;}
#
#
## native crash
#-keep class com.bytedance.article.common.nativecrash.JniToJava {
#    public static <methods>;
#}
#
#-keep interface com.ss.android.pushmanager.IMessageContext {*;}
#-keep class com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider {
#    public <fields>;
#    public <methods>;
#}
#-keep class com.ss.android.push.sswo.SswoManager {
#    public <fields>;
#    public <methods>;
#}
#
#-keep class com.ss.android.push.sswo.SswoActivity {
#    public <fields>;
#    public <methods>;
#}
#-keep interface com.ss.android.message.IPushLifeCycleListener {*;}
#
#-keep class com.ss.android.push.** {*;}
#
## 需要存入数据库的类
#-keep class com.ixigua.storage.database.DBData
#
##反作弊混淆
#-keep class com.ss.android.common.applog.UserInfo { *; }
#-keep class com.ss.android.common.applog.GlobalContext { *; }
#-keep class com.ss.android.common.applog.EstrBean {*;}
#
##applog-toolkit
#-keep class com.ss.android.common.util.EventsSender {*;}
#-keep,allowobfuscation @interface com.facebook.proguard.annotations.DoNotStrip
#-keep,allowobfuscation @interface com.facebook.proguard.annotations.KeepGettersAndSetters
#-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
#
#-keep public class com.facebook.** {
#  *;
#}
## Do not strip any method/class that is annotated with @DoNotStrip
#-keep @com.facebook.proguard.annotations.DoNotStrip class *
#-keep @com.facebook.common.internal.DoNotStrip class *
#-keepclassmembers class * {
#    @com.facebook.proguard.annotations.DoNotStrip *;
#    @com.facebook.common.internal.DoNotStrip *;
#}
#
#-keepclassmembers @com.facebook.proguard.annotations.KeepGettersAndSetters class * {
#  void set*(***);
#  *** get*();
#}
#-keep class com.facebook.soloader.** { *; }
#-keepclassmembers class com.facebook.soloader.SoLoader {
#     static <fields>;
#}
#-keep class * extends com.facebook.react.bridge.JavaScriptModule { *; }
#-keep class * extends com.facebook.react.bridge.NativeModule { *; }
#-keepclassmembers,includedescriptorclasses class * { native <methods>; }
#-keepclassmembers class *  { @com.facebook.react.uimanager.UIProp <fields>; }
#-keepclassmembers class *  { @com.facebook.react.uimanager.annotations.ReactProp <methods>; }
#-keepclassmembers class *  { @com.facebook.react.uimanager.annotations.ReactPropGroup <methods>; }
#
#-dontwarn com.facebook.react.**
#-keep class com.facebook.react.** {*;}
## Keep our interfaces so they can be used by other ProGuard rules.
## See http://sourceforge.net/p/proguard/bugs/466/
#-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
#-keep,allowobfuscation @interface com.facebook.soloader.DoNotOptimize
#
## Do not strip any method/class that is annotated with @DoNotStrip
#-keep @com.facebook.common.internal.DoNotStrip class *
#-keepclassmembers class * {
#    @com.facebook.common.internal.DoNotStrip *;
#}
#
## Do not strip any method/class that is annotated with @DoNotOptimize
#-keep @com.facebook.soloader.DoNotOptimize class *
#-keepclassmembers class * {
#    @com.facebook.soloader.DoNotOptimize *;
#}
#
## Keep native methods
#-keepclassmembers class * {
#    native <methods>;
#}
#
#-dontwarn okio.**
#-dontwarn com.squareup.okhttp.**
#-dontwarn okhttp3.**
#-dontwarn javax.annotation.**
#-dontwarn com.android.volley.toolbox.**
#-dontwarn com.facebook.infer.**
#
#
#-keep @android.support.annotation.Keep class *
#
#-keep class com.ss.android.common.helper.MiraStatHelper{*;}
#
##sec 防抓取 start--
## 【0】old source
#-dontusemixedcaseclassnames
#-dontwarn com.ss.android.common.applog.**
#-keep class com.ss.android.common.applog.* {*;}
#-keep public class com.ss.sys.ces.utils.NetInterface {
#    public *;
#}
#
## 【1】SecSDK Interface
#-keep public class com.ss.sys.ces.out.*  {
#    public *;
#}
## 【2】SecSDK Main Function
#-keep public class com.ss.sys.ces.a {*;}
## 【3】for gorgon in 20181128
#-keep class com.ss.sys.ces.gg.tt {
#    public *;
#}
## 【4】for defendence about default usage
#-keep class com.ss.sys.ces.die.xx {
#    public *;
#}
#
## 【5】*verify check
#-keep public class com.ss.sys.ck.SCCheckUtils {*;}
#-keep public class com.ss.sys.ck.SCCheckListener {*;}
#-keep public class com.ss.sys.ck.SCCheckCallback {*;}
#
##sec 防抓取end --
#
#-keep class com.ss.android.article.common.module.IUgcFeedDepend {*;}
#-keep class com.f100.fugc.aggrlist.UgcFeedDependImpl {*;}
#-keep class com.f100.fugc.announcement.AnnounceModel {*;}
#
#-keep class com.ss.android.article.common.module.IRNDepend {*;}
#-keep class com.f100.reactnative.RNDependImpl {*;}
#-keep class android.support.v7.widget.SwitchCompat
#-keep class android.support.v4.widget.SwipeRefreshLayout {*;}
#-keep class com.bytedance.common.utility.io.FileUtils {
#    public <methods>;
#}
#-keep class com.bytedance.common.utility.reflect.Reflect {*;}
#-keep class com.ss.android.http.legacy.utils.URLEncodedUtils{*;}
#-keep class android.support.v4.net.ConnectivityManagerCompat {*;}
#-keep class com.bytedance.depend.utility.Logger {*;}
#-keep class com.bytedance.ies.geckoclient.GeckoClient$Builder {*;}
#
##im
#-keep class com.ss.android.websocket.** {*;}
#-keep class com.squareup.wire.** { *; }
#-keepclassmembers class com.bytedance.im.core.model.* implements java.io.Serializable { *; }
#-keep class com.bytedance.im.core.proto.** { *; }
#-keep class com.tencent.wcdb.** { *; }
#-keep class net.jpountz.** { *; }
#-keep class com.ss.ttuploader.** {*;}
#
#
#
##Glide
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public class * extends com.bumptech.glide.module.AppGlideModule
#-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}
#-keep class com.bumptech.glide.** { *; }
#-keep public class com.ss.android.image.glide.fetch.TTNetFetchException
#
#
#-keep class * implements com.bytedance.router.IModuleEntry
#
#
#
#### greenDAO 3
#-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
#public static java.lang.String TABLENAME;
#}
#-keep class **$Properties
#
## If you do not use SQLCipher:
#-dontwarn org.greenrobot.greendao.database.**
#
##Winnow
#-keep public class * extends com.bytedance.android.winnow.WinnowHolder
#-keepclassmembers  class * extends com.bytedance.android.winnow.WinnowHolder {
#     public <init>(android.view.View);
#}
#
##launchTask
#-keepnames class * extends com.ss.android.article.lite.boost.task.AbsLaunchTask
#
#-keep public class com.f100.richtext.spandealer.LinkSpanDealer {*;}
#-keep public class com.f100.richtext.spandealer.SpanDealerFactory {*;}
#
##防止Jacoco类被混淆
#-keep class org.jacoco.agent.**{*;}
#
##防止覆盖率上报类被混淆
#-keep class com.bytedance.test.codecoverage.**{*;}