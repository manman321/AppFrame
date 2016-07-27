package com.wlht.oa;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.orm.LiteOrm;
import com.wlht.oa.base.BaseApplication;
import com.wlht.oa.bean.User;
import com.wlht.oa.util.JsonKit;
import com.wlht.oa.util.NetworkProber;
import com.wlht.oa.util.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;
import in.srain.cube.Cube;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.request.RequestCacheManager;



/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 *
 * @author 火蚁 (http://my.oschina.net/LittleDY)
 * @version 1.0
 * @created 2014-04-22
 */
public class AppContext extends BaseApplication {
    public static final String KEY_FRITST_START = "KEY_FRITST_START";
    public static final int PAGE_SIZE = 20;// 默认分页大小

    private static AppContext instance;

    private String loginUid;

    private boolean login;

    private ImageLoader imageLoader;

    public ImageLoader imageLoader()
    {
        return imageLoader;
    }

    public static float SCALE = 1;

    private LiteOrm liteOrm;

    public LiteOrm liteOrm()
    {
        if (liteOrm == null)
        {
            liteOrm = LiteOrm.newSingleInstance(this,"oa.db");
            liteOrm.setDebugged(true);
        }
        return liteOrm;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initFile();
        initCrash();
        init();
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
//        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(configuration); // Get
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

//        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, "5745c10a67e58e3e940027fa", "test", MobclickAgent.EScenarioType.E_UM_NORMAL));
//        MobclickAgent.setCheckDevice(false);
//        MobclickAgent.setDebugMode(true);
//        com.squareup.leakcanary .LeakCanary.install(this);
//        Fresco.initialize(this);
        imageLoader = ImageLoaderFactory.create(this);
        isNetworkConnected = NetworkProber.checkNet(this);
//        System.out.println("TOTAL MEMORY  " + Runtime.getRuntime().totalMemory());
//        SCALE = (40 * 1024*1024 * 5) /(Runtime.getRuntime().maxMemory());
        float scale = (40 * 1024*1024 * 5) /(Runtime.getRuntime().maxMemory());
        //通常来讲200M
        int maxMemory = (int)Runtime.getRuntime().maxMemory() /(1024*1024);
        System.out.println("MAX   MEMORY  " + maxMemory);
        if (maxMemory < 50)
        {
            SCALE = 32;
        }else if (maxMemory < 100)
        {
            SCALE = 16;
        }else if (maxMemory < 130)
        {
            SCALE = 2;
        }


//        Picasso.setSingletonInstance(new Picasso.Builder(this).memoryCache(new LruCache(40 * 1024)).build());
        initLogin();
//        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler(this));
//        UIHelper.sendBroadcastForNotice(this);
        String dir = "request-cache";
        RequestCacheManager.init(this, dir, 1024 * 10, 1024 * 10);
        Cube.onCreate(this);
//        initMeiqiaSDK();
//        customConfig();
    }


//    public DisplayImageOptions getWholeOptions() {
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.default_home_banner) //设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.default_home_banner)//设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.default_home_banner)  //设置图片加载/解码过程中错误时候显示的图片
//                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
//                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
//                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片以如何的编码方式显示
//                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
//                        //.decodingOptions(BitmapFactory.Options decodingOptions)//设置图片的解码配置
//                .delayBeforeLoading(0)//int delayInMillis为你设置的下载前的延迟时间
//                        //设置图片加入缓存前，对bitmap进行设置
//                        //.preProcessor(BitmapProcessor preProcessor)
//                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//                .build();//构建完成
//
//        return options;
//    }

//
//    private void initMeiqiaSDK() {
//        // 替换成自己的key
//        // 发布sdk时用
//        String meiqiaKey = "4559e2dce847fe7e578f61a738b47d82";
//
////        MQConfig.init(this,meiqiaKey, Picasso.RequestTransformer);
//
//        MQConfig.init(this, meiqiaKey, new PicassoImageLoader(), new OnInitCallback() {
//            @Override
//            public void onSuccess(String clientId) {
////                Toast.makeText(MainActivity.this, "init success", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(int code, String message) {
////                Toast.makeText(MainActivity.this, "int failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    private void customConfig() {
//        // 配置自定义信息
//        MQConfig.ui.titleGravity = MQConfig.ui.MQTitleGravity.CENTER;
//        MQConfig.ui.backArrowIconResId = R.drawable.nav_back;
//        MQConfig.ui.titleBackgroundResId = R.color.main_color;
//        MQConfig.ui.titleTextColorResId = R.color.white;
//
////        MQConfig.ui.leftChatBubbleColorResId = R.color.test_green;
////        MQConfig.ui.leftChatTextColorResId = R.color.test_red;
////        MQConfig.ui.rightChatBubbleColorResId = R.color.test_red;
////        MQConfig.ui.rightChatTextColorResId = R.color.test_green;
//
//    }

    private void initCrash() {
//        //This makes the library not launch the error activity when the app crashes while it is in background.
//        CustomActivityOnCrash.setLaunchErrorActivityWhenInBackground(false);
//        //This sets the restart activity. If you don't do this, the "Restart app" button will change to "Close app".
//        CustomActivityOnCrash.setRestartActivityClass(com.wlht.oa.ui.MinaActivity.class);
//
//        //This hides the "error details" button, thus hiding the stack trace
////        CustomActivityOnCrash.setShowErrorDetails(false);
//
//        //This sets a custom error activity class instead of the default one.
//        //Comment it to see the customization effects on the default error activity.
//        //Uncomment to use the custom error activity
//        CustomActivityOnCrash.setErrorActivityClass(CustomErrorActivity.class);
//
//        //This enables CustomActivityOnCrash
//        CustomActivityOnCrash.install(this);
    }

    private void init() {
        // 初始化网络请求
//        AsyncHttpClient client = new AsyncHttpClient();
//        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
//        client.setCookieStore(myCookieStore);
//        ApiHttpClient.setHttpClient(client);
//        ApiHttpClient.setCookie(ApiHttpClient.getCookie(this));

        // Log控制器
//        KJLoger.openDebutLog(true);
//        TLog.DEBUG = BuildConfig.DEBUG;
//
//        // Bitmap缓存地址
//        HttpConfig.CACHEPATH = "OSChina/imagecache";
    }




    private void initLogin() {
        User user = getLoginUser();
        if (null != user && user.getId() != null && user.getId().trim().length() > 0) {
            login = true;
            loginUid = user.getId();
        } else {
            this.cleanLoginInfo();
        }
    }


    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    public boolean containsProperty(String key) {
        Properties props = getProp();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProp() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }


    public void saveFavSkuIds(ArrayList<String> ids)
    {
        setProperty("FAV_SKU_IDS", JsonKit.toJson(ids));
    }

    public ArrayList<String> favSkuIdFromCache()
    {
        String skuIds =  AppContext.getInstance().getProperty("FAV_SKU_IDS");
        if (StringUtils.isEmpty(skuIds))return new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        try {
            ArrayList<String> vaue = new Gson().fromJson(skuIds,new TypeToken<ArrayList<String>>() {}.getType());
            if (vaue != null )
            {
                list.addAll(vaue);
            }
        }catch (Exception e){e.printStackTrace();}
        return list;
    }

    public ArrayList<String> addFavSkuId(String skuId)
    {
        ArrayList<String> list =  favSkuIdFromCache();
        list.add(skuId);
        saveFavSkuIds(list);
        return list;
    }

    public ArrayList<String> removeFavSkuId(String skuId)
    {
        ArrayList<String> list =  favSkuIdFromCache();
        list.remove(skuId);
        saveFavSkuIds(list);
        return list;
    }



    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 获取App唯一标识
     *
     * @return
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }









    public String monthlyId()
    {
        String id = getProperty("monthly.monthlyid");
        if (id == null)id = "";
        return id;
    }


    public String userOwnMonthlyId()
    {
        String id = getProperty("monthly.id");
        if (id == null)id = "";
        return id;
    }

    public int monthlyMaxSelected()
    {
        int max = 0;
        try
        {
            String value = getProperty("monthly.selectedSize");
            if (StringUtils.isEmpty(value))return max;
            max = Integer.valueOf(value);
        }catch (Exception e){e.printStackTrace();}
        return max;
    }

    public String monthlyDetail()
    {
        return getProperty("monthly_detail");
    }

    public void setMonthlyDetail(String detial)
    {
        setProperty("monthly_detail", detial);
    }



    public void clearMonthlyCheck()
    {
        removeProperty(
                "monthly.id",
                "monthly.image",
                "monthly.pushSize",
                "monthly.endDate",
                "monthly.validDay",
                "monthly.desposit",
                "monthly.isUsed",
                "monthly.selectedSize",
                "monthly.payNo",
                "monthly.monthlyName",
                "monthly.payAmount",
                "monthly.customerId",
                "monthly.monthlyid",
                "monthly.monthlyDesc",
                "monthly.rentPrice",
                "monthly.startDate",
                "monthly.status",
                "monthly.packageNum",
                "monthly_detail"
        );
    }


    /**
     * 保存登录信息
     *
     * @param user 用户信息
     */
    @SuppressWarnings("serial")
    public void saveUserInfo(final User user) {
        this.loginUid = user.getId();
        this.login = true;
        setProperties(new Prop() {
            {
                setProperty("user.uid", user.getId());
                setProperty("user.userName", user.getUserName());
                setProperty("user.face", user.getHeadImg());// 用户头像-文件名
                setProperty("user.phone", user.getPhone());
                setProperty("user.mobile", user.getMobile());
                setProperty("user.gender", String.valueOf(user.getGender()));
                setProperty("user.level", user.getLevel());
                setProperty("user.birthday", user.getBirthday());
                setProperty("user.profession", user.getProfession());
                setProperty("user.income", user.getIncome());
                setProperty("user.address", user.getAddress());
                setProperty("user.signature", user.getSignature());
                setProperty("user.name", user.getName());
                setProperty("user.email", user.getEmail());
            }
        });
    }


    /**
     * 更新用户信息
     *
     * @param user
     */
    @SuppressWarnings("serial")
    public void updateUserInfo(final User user) {
        setProperties(new Prop() {
            {
                setProperty("user.uid", user.getId());
                setProperty("user.userName", user.getUserName());
                setProperty("user.face", user.getHeadImg());// 用户头像-文件名
                setProperty("user.phone", user.getPhone());
                setProperty("user.mobile", user.getMobile());
                setProperty("user.gender", String.valueOf(user.getGender()));
                setProperty("user.level", user.getLevel());

                setProperty("user.birthday", user.getBirthday());
                setProperty("user.profession", user.getProfession());
                setProperty("user.income", user.getIncome());
                setProperty("user.address", user.getAddress());
                setProperty("user.signature", user.getSignature());
                setProperty("user.name", user.getName());
                setProperty("user.email", user.getEmail());
            }
        });
    }

    /**
     * 获得登录用户的信息
     *
     * @return
     */
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public User getLoginUser() {
        User user = new User();
        user.setId(getProperty("user.uid"));
        user.setUserName(getProperty("user.userName"));
        user.setHeadImg(getProperty("user.face"));
        user.setPhone(getProperty("user.phone"));
        try
        {
            user.setGender(Integer.valueOf(getProperty("user.gender")));
        }catch (Exception e){}

        user.setLevel(getProperty("user.level"));
        user.setMobile(getProperty("user.mobile"));
        String birthday = getProperty("user.birthday");
        try {
            if (birthday.contains("-"))
            {
                user.setBirthday(birthday);

            }else
            {
                Long birl = Long.valueOf(birthday);
                String bir=  format.format(new Date(birl));
                user.setBirthday(bir);
            }
        }catch (Exception e){
            user.setBirthday(birthday);
        }


        user.setProfession(getProperty("user.profession"));
        user.setIncome(getProperty("user.income"));
        user.setAddress(getProperty("user.address"));
        user.setSignature(getProperty("user.signature"));
        user.setName(getProperty("user.name"));
        user.setEmail(getProperty("user.email"));

        return user;
    }

    /**
     * 清除登录信息
     */
    public void cleanLoginInfo() {
//        MobclickAgent.onProfileSignOff();
        this.loginUid = "";
        this.login = false;
        removeProperty(
                "user.uid",
                "user.userName",
                "user.face",
                "user.phone",
                "user.gender",
                "user.level",
                "user.birthday",
                "user.profession",
                "user.income",
                "user.address",
                "user.signature",
                "user.name",
                "user.email");


        removeProperty("cart.clothe", "cart.subscribe");
        removeProperty(
                "addr.id",
                "addr.province",
                "addr.city",
                "addr.district",
                "addr.street",
                "addr.name",
                "addr.phone"
        );
        clearMonthlyCheck();
    }

    public String getLoginUid() {
        return loginUid;
    }

    public boolean isLogin() {
        return login;
    }


    private boolean isNetworkConnected = true;
    public boolean isNetworkConnected()
    {
        if (!isNetworkConnected)
        {
           isNetworkConnected = NetworkProber.checkNet(this);
        }
        return isNetworkConnected;
    }

    public void setNetworkConnected(boolean connected)
    {
        isNetworkConnected = connected;
    }



    public void setCartNo(String no)
    {
        setProperty("cart.clothe", no);
    }



    public String getCartNo()
    {
        String no = getProperty("cart.clothe");
        if (no == null || no.trim().length() == 0)no = "0";
        return no;
    }

    public void setSubscribeBagNo(String no)
    {
        setProperty("cart.subscribe", no);
    }

    public String getSubscribeBagNo()
    {
        String no = getProperty("cart.subscribe");
        if (no == null || no.trim().length() == 0)no = "0";
        return no;
    }




    /**
     * 用户注销
     */
    public void Logout() {
        cleanLoginInfo();
//        ApiHttpClient.cleanCookie();
        this.cleanCookie();
        this.login = false;
        this.loginUid = "";

        Intent intent = new Intent(Constants.INTENT_ACTION_LOGOUT);
        sendBroadcast(intent);
    }

    /**
     * 清除保存的缓存
     */
    public void cleanCookie() {
        removeProperty(AppConfig.CONF_COOKIE);
    }

    /**
     * 清除app缓存
     */
    public void clearAppCache() {
//        DataCleanManager.cleanDatabases(this);
//        // 清除数据缓存
//        DataCleanManager.cleanInternalCache(this);
//        // 2.2版本才有将应用缓存转移到sd卡的功能
//        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
//            DataCleanManager.cleanCustomCache(MethodsCompat
//                    .getExternalCacheDir(this));
//        }
//        // 清除编辑器保存的临时内容
//        Properties props = getProp();
//        for (Object key : props.keySet()) {
//            String _key = key.toString();
//            if (_key.startsWith("temp"))
//                removeProperty(_key);
//        }
//        Core.getKJBitmap().cleanCache();
    }

    public static void setLoadImage(boolean flag) {
//        set(KEY_LOAD_IMAGE, flag);
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }



    public static boolean isFristStart() {
        return getPreferences().getBoolean(KEY_FRITST_START, true);
    }

    public static void setFristStart(boolean frist) {
        set(KEY_FRITST_START, frist);
    }


    private void initFile() {
        File folder = new File(AppConfig.APP_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
            createFolder();

        } else {
            if (folder.isDirectory()) {
                createFolder();
            } else {
            }
        }
    }



    public void createFolder() {
//        File imageFolder = new File(AppConfig.DEFAULT_SAVE_FILE_PATH);
//        if (!imageFolder.exists()) {
//            imageFolder.mkdirs();
//        }

        File camerFolder = new File(AppConfig.CAMERA_PATH);
        if (!camerFolder.exists()) {
            camerFolder.mkdirs();
        }

        File photoFolder = new File(AppConfig.DEFAULT_SAVE_IMAGE_PATH);
        if (!photoFolder.exists()) {
            photoFolder.mkdirs();
        }



        if (getProperty("is_first_install") == null || "".equals(getProperty("is_first_install")))
        {
            if (!new File(AppConfig.DEFAULT_SHARE_IMAGE).exists())
            {
                InputStream input = null;
                OutputStream output = null;
                try {
                    input = getAssets().open("ic_launcher.png");
                    output = new FileOutputStream(AppConfig.DEFAULT_SHARE_IMAGE);
                    byte[] buffer = new byte[1024];
                    int read = -1;
                    while ((read = input.read(buffer)) > 0)
                    {
                        output.write(buffer,0,read);
                    }
                    if (output != null)output.flush();
                    setProperty("is_first_install","finish");
                }catch (Exception e){
                    setProperty("is_first_install","");
                    e.printStackTrace();
                }
                finally {
                    try {
                        if (input != null)input.close();
                        if (output != null)output.close();
                    }catch (Exception e){e.printStackTrace();}
                }
            }

            if (!new File(AppConfig.DEFAULT_SHARE_BAG).exists())
            {
                InputStream input = null;
                OutputStream output = null;
                try {
                    input = getAssets().open("mmexport.jpg");
                    output = new FileOutputStream(AppConfig.DEFAULT_SHARE_BAG);
                    byte[] buffer = new byte[1024];
                    int read = -1;
                    while ((read = input.read(buffer)) > 0)
                    {
                        output.write(buffer,0,read);
                    }
                    if (output != null)output.flush();
                    setProperty("is_first_install","finish");
                }catch (Exception e){
                    setProperty("is_first_install","");
                    e.printStackTrace();}
                finally {
                    try {
                        if (input != null)input.close();
                        if (output != null)output.close();
                    }catch (Exception e){e.printStackTrace();}
                }
            }
        }








//        File dbFolder = new File(AppConfig.DATABASE_PATH);
//        if (!dbFolder.exists()) {
//            dbFolder.mkdirs();
//        }
//        File accountPicFolder = new File(AppConfig.ACCOUNT_PICTURE_PATH);
//        if (!accountPicFolder.exists()) {
//            accountPicFolder.mkdirs();
//        }
        File downloadFolder = new File(AppConfig.DEFAULT_SAVE_FILE_PATH);
        if (!downloadFolder.exists()) {
            downloadFolder.mkdirs();
        }
    }



    private class Prop extends Properties
    {
        @Override
        public Object setProperty(String name, String value) {
            if (value == null)value = "";
            return super.setProperty(name, value);
        }
    }

}
