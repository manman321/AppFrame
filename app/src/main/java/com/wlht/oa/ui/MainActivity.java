package com.wlht.oa.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.transition.Fade;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.orm.log.OrmLog;
import com.wlht.oa.AppContext;
import com.wlht.oa.AppManager;
import com.wlht.oa.R;
import com.wlht.oa.base.BaseActivity;
import com.wlht.oa.base.BaseFragment;
import com.wlht.oa.base.util.helper.RxSchedulers;
import com.wlht.oa.bean.Menu;
import com.wlht.oa.bean.TestModel;
import com.wlht.oa.bean.User;
import com.wlht.oa.net.Api;
import com.wlht.oa.net.PersistentCookieStore;
import com.wlht.oa.ui.approve.ApproveCreateFragment;
import com.wlht.oa.ui.approve.CateFragment;
import com.wlht.oa.ui.call.CallAddFragment;
import com.wlht.oa.ui.contact.ContactFragment;
import com.wlht.oa.ui.contact.ContactTestFragment;
import com.wlht.oa.ui.user.LoginFragment;
import com.wlht.oa.util.StringUtils;
import com.wlht.oa.widget.PopMenu;
import com.wlht.oa.widget.PopMenuItem;
import com.wlht.oa.widget.PopMenuItemListener;
import com.wlht.oa.widget.TabItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import in.srain.cube.app.CubeFragment;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import rx.Observable;


public class MainActivity extends BaseActivity {

    PopMenu mPopMenu;
    private TabItem homeTabItem, messageTabItem, contractTabItem, meTabItem;
    protected BaseFragment home,message,contact,me;
    protected HashMap<TabItem,BaseFragment> cacheFragment = new HashMap<>();

    @Override
    protected int getLayoutId() {return R.layout.activity_oa_main;}

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected void initView() {

        homeTabItem = (TabItem)findViewById(R.id.home);
        messageTabItem =(TabItem)findViewById(R.id.message);
        contractTabItem = (TabItem)findViewById(R.id.contract);
        meTabItem = (TabItem)findViewById(R.id.me);


        homeTabItem.setDrawableWithText(R.drawable.main_tab_apps_icon_selecter, "主页");
        messageTabItem.setDrawableWithText(R.drawable.main_tab_msg_icon_selecter, "通知");
        contractTabItem.setDrawableWithText(R.drawable.main_tab_contact_icon_selecter, "通讯录");
        meTabItem.setDrawableWithText(R.drawable.main_tab_my_icon_selecter, "我");


        mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                .addMenuItem(new PopMenuItem("接待申请", getResources().getDrawable(R.drawable.tabbar_compose_idea)))
                .addMenuItem(new PopMenuItem("出差申请", getResources().getDrawable(R.drawable.tabbar_compose_photo)))
                .addMenuItem(new PopMenuItem("请假申请", getResources().getDrawable(R.drawable.tabbar_compose_headlines)))
                .addMenuItem(new PopMenuItem("报销申请", getResources().getDrawable(R.drawable.tabbar_compose_lbs)))
                .addMenuItem(new PopMenuItem("公文审批", getResources().getDrawable(R.drawable.tabbar_compose_review)))
                .addMenuItem(new PopMenuItem("用车申请", getResources().getDrawable(R.drawable.tabbar_compose_more)))
                .addMenuItem(new PopMenuItem("加班用餐", getResources().getDrawable(R.drawable.tabbar_compose_more)))
                .setOnItemClickListener(new PopMenuItemListener() {
                    @Override
                    public void onItemClick(PopMenu popMenu, int position) {

                        pushFragmentToBackStack(ApproveCreateFragment.class,null);
//                        Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();



        if (AppContext.getInstance().isLogin())
        {
            initViewWithLogin();
        }else
        {
            findViewById(R.id.rly_container).setVisibility(View.GONE);
//            pushFragmentToBackStack(ApproveCreateFragment.class, null);
//            pushFragmentToBackStack(LoginFragment.class,null);
//            pushFragmentToBackStack(CallAddFragment.class,null);

            pushFragmentToBackStack(ContactTestFragment.class,null);


        }
    }

    private void initViewWithLogin()
    {
        if (cacheFragment.size() > 0)return;
        cacheFragment.put(homeTabItem,home = new HomeFragment());
        cacheFragment.put(messageTabItem, message = new MessageFragment());
        cacheFragment.put(contractTabItem, contact = new ContactFragment());
        cacheFragment.put(meTabItem, me = new MeFragment());

        android.support.v4.app.FragmentTransaction fg = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : cacheFragment.values())
        {
            fg.add(R.id.rly_viewholder, fragment).hide(fragment);
        }
        fg.commitAllowingStateLoss();
    }

    @Override
    protected void initEvent() {
        homeTabItem.setOnClickListener(this);
        messageTabItem.setOnClickListener(this);
        contractTabItem.setOnClickListener(this);
        meTabItem.setOnClickListener(this);
        findViewById(R.id.main_add).setOnClickListener(this);

        homeTabItem.performClick();
    }

    @Override
    protected void initData() {


        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("wahaha");
        AppContext.getInstance().liteOrm().save(user);

        user.setName("hahahh");
        AppContext.getInstance().liteOrm().save(user);


        List<User> list = AppContext.getInstance().liteOrm().query(User.class);
        OrmLog.i(list);

        TestModel testModel = new TestModel();
        testModel.setId(UUID.randomUUID().toString());
        testModel.setName("wahaha");
        testModel.setPassword("hhaha");

        AppContext.getInstance().liteOrm().save(testModel);



    }


    private void net()
    {
        PersistentCookieStore.instance().removeAll();
        Api.getInstance().movieService
                .loginPrev()
//                .compose(RxSchedulers.io_main())
                .doOnError(throwable -> System.out.println("doOnError"))
                .onErrorReturn(throwable -> {
                    System.out.println("onErrorReturn");
                    return throwable.getMessage();
                })
                .flatMap(html -> {
                    Document doc = Jsoup.parse(html);
//                    Element element = doc.getElementById("form1");
                    Elements elements = doc.getElementsByAttributeValue("name", "__RequestVerificationToken");
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("Account", "hr");
                    params.put("Force", 1);
                    params.put("Password", "111");
                    params.put("VCode", "");
                    if (elements.size() == 1) {
                        String token = elements.get(0).attr("value");
                        params.put("__RequestVerificationToken", token);
                    }
                    return Api.getInstance().movieService.login(params);
                })
                .flatMap(html -> {
                    if (html.contains("top.location='/Home'"))
                    {
                        System.out.println("登录成功");
                    }
                    return Api.getInstance().movieService.home();
                })
                .flatMap(html->{

                    Document doc = Jsoup.parse(html);
                    Elements js = doc.getElementsByAttributeValue("type", "text/javascript");
                    Element javascript = js.last();
                    String jsCode = javascript.toString();
                    HashMap<String,Object> params = new HashMap<String, Object>();
                    StringReader reader = new StringReader(jsCode);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line = null;

                    try {
                        while (null != (line = bufferedReader.readLine()))
                        {
                            if (line.contains("var userID"))
                            {
                                params.put("userid", line.substring(line.indexOf("'")+1,line.lastIndexOf("'")));
                                continue;
                            }
                            if (line.contains("AccordingTree"))
                            {
                                params.put("roleid", line.substring(line.indexOf("=")+1,line.lastIndexOf("&")));
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return Api.getInstance().movieService.menu(params);
                })

                .compose(RxSchedulers.io_main())
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    System.out.println("onErrorResumeNext");
                    return Observable.just(throwable.getMessage());
                })
                .subscribe(value -> {
                    String jsonTemp = value.replace("\\", "");
                    jsonTemp = jsonTemp.substring(2,jsonTemp.length()-2);
                    String json = jsonTemp.replace(",]", "]");
                    Menu menus = new Gson().fromJson(json, Menu.class);

                    System.out.println(menus);
                });
    }

    @Override
    public void onBackPressed() {
        if (mPopMenu != null && mPopMenu.isShowing())
        {
            mPopMenu.hide();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.main_add && !mPopMenu.isShowing())
        {
            mPopMenu.show();
            return;
        }

        if (cacheFragment.size() == 0)return;

//        if (mCurrentTabItem != null)
//        {
//            cacheFragment.get(v).onBack();
//        }

        if (mCubeFragment == cacheFragment.get(v))
        {
            return;
        }


        CubeFragment leaveFragment = mCubeFragment;
        mCubeFragment = cacheFragment.get(v);
        mCubeFragment.onBack();

        android.support.v4.app.FragmentTransaction fg = getSupportFragmentManager().beginTransaction();

        for (Map.Entry<TabItem,BaseFragment> entry : cacheFragment.entrySet())
        {
            if (v != entry.getKey())
            {
                entry.getKey().setSelected(false);
                fg.hide(entry.getValue());
            }
        }

        v.setSelected(true);
        fg.show(mCubeFragment).commitAllowingStateLoss();
        if (leaveFragment != null)leaveFragment.onLeave();





//        if (v == homeTabItem)
//        {
//            getSupportFragmentManager().beginTransaction().replace(R.id.rly_viewholder, new HomeFragment()).commitAllowingStateLoss();
//        }else if (v == messageTabItem)
//        {
//            getSupportFragmentManager().beginTransaction().replace(R.id.rly_viewholder, new MessageFragment()).commitAllowingStateLoss();
//        }else if (v == contractTabItem)
//        {
//            getSupportFragmentManager().beginTransaction().replace(R.id.rly_viewholder, new ContactFragment()).commitAllowingStateLoss();
//        }else if (v == meTabItem)
//        {
//            getSupportFragmentManager().beginTransaction().replace(R.id.rly_viewholder, new MeFragment()).commitAllowingStateLoss();
//        }

    }


    public void showView()
    {
        if (AppContext.getInstance().isLogin())
        {
            initViewWithLogin();
            homeTabItem.performClick();
            findViewById(R.id.rly_container).setVisibility(View.VISIBLE);
        }else {
            showToast("非法调用");
            AppManager.getAppManager().finishAllActivity();
        }
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (home == null && fragment instanceof HomeFragment)
        {
            cacheFragment.put(homeTabItem, home = (BaseFragment)fragment);
        }
        else if (message == null && fragment instanceof MessageFragment)
        {
            cacheFragment.put(messageTabItem, message = (BaseFragment)fragment);
        }
        else if (contact == null && fragment instanceof ContactFragment)
        {
            cacheFragment.put(contractTabItem, contact = (BaseFragment)fragment);
        }
        else if (me == null && fragment instanceof MeFragment)
        {
            cacheFragment.put(meTabItem, me = (BaseFragment)fragment);
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void test()
    {
        /**
         * xml文件中使用了着色效果
         * Tinting
         */
        final View session = findViewById(R.id.imageView);

        //Clipping 剪裁
        ViewOutlineProvider outlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,session.getWidth(),session.getHeight(),30);
            }
        };
        session.setOutlineProvider(outlineProvider);

        //动态调色板Palette
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);

        Palette palette = new Palette.Builder(bitmap).generate();
        Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
//        getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
        getWindow().setStatusBarColor(vibrant.getRgb());


//        getWindow().setEnterTransition(new Slide());
//        getWindow().setEnterTransition(new Explode());
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());

        session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                /**
                 * 点击放大动画效果
                 */
                session.clearAnimation();
                session.animate()
                        .scaleX(1.2f)
                        .scaleY(1.2f)
                        .setDuration(300).start();
                session.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        session.animate().scaleY(1.0f).scaleX(1.0f).setDuration(300)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        startActivity(new Intent(MainActivity.this, MainActivity.class),
                                                ActivityOptions
                                                        .makeSceneTransitionAnimation(MainActivity.this)
                                                        .toBundle());
                                    }
                                })
                                .start();
                    }
                }, 300);

            }
        });
        ViewGroup mainTabBar = (ViewGroup)findViewById(R.id.main_tab);


        for (int i = 0; i < mainTabBar.getChildCount();i++)
        {

        }




    }



}
