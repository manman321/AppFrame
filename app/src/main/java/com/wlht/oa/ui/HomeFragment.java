package com.wlht.oa.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wlht.oa.KVO;
import com.wlht.oa.R;
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.adapter.HomeAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.base.util.helper.RxSchedulers;
import com.wlht.oa.bean.Banner;
import com.wlht.oa.decoration.DividerGridItemDecoration;
import com.wlht.oa.net.Api;
import com.wlht.oa.ui.audit.ApproveFragment;
import com.wlht.oa.ui.call.CallFragment;
import com.wlht.oa.ui.email.EmailFragment;
import com.wlht.oa.ui.law.LawFragment;
import com.wlht.oa.ui.signin.SigninFragment;
import com.wlht.oa.widget.BannerView;

import java.util.ArrayList;
import java.util.HashMap;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Observable;

/**
 * Created by hr on 16/6/12.
 */
public class HomeFragment extends BaseTitleFragment
{

    ArrayList<KVO<Integer,String,Class>> homeItemArray = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private HomeAdapter  mAdapter;
    private BannerView mBannerView;
    private PtrFrameLayout ptrFrameLayout;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_home);
    }


    public void initNavBar()
    {
        mTitleHeaderBar.getLeftViewContainer().setVisibility(View.GONE);
        mTitleHeaderBar.setTitle("主页");
    }

    public void initView(View view)
    {
        super.initView(view);
        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mBannerView = (BannerView)view.findViewById(R.id.banner);

        initRefresh();

        initBanner();
        initRecyclerView();

    }



    protected void initBanner()
    {

    }

    protected void initRecyclerView()
    {
        mAdapter = new HomeAdapter(getContext());
        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext(), false));
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void initRefresh()
    {
        setDefaultHeader(ptrFrameLayout);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshComplete();
                    }
                }, 500);
            }
        });
    }

    @Override
    public void initEvent(View view) {

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                KVO<Integer, String, Class> kvo = (KVO<Integer, String, Class>) data;
                if (kvo.object != null) {
                    getContext().pushFragmentToBackStack(kvo.object, null);
                }
            }
        });

        mBannerView.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                Banner banner = (Banner) data;
                Toast.makeText(getContext(), banner.title, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void initData()
    {


        homeItemArray.clear();
        homeItemArray.add(new KVO(R.drawable.icon_homepage_notice, "公告", NoticeFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_news, "新闻",NewsFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_approve, "审批",ApproveFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_email, "邮件",EmailFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_law, "法律法规",LawFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_opinion, "意见栏",PostFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_attendance, "外勤考勤",SigninFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_meal, "预约就餐",MealFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_car, "车辆管理",CarFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_call, "语音呼叫",CallFragment.class));
        homeItemArray.add(new KVO(R.drawable.icon_homepage_status, "人员状态",PersonStatusFragment.class));
        mAdapter.clear();
        mAdapter.addDatas(homeItemArray);


//        String[] imgs = new String[]{
//                "http://img3.duitang.com/uploads/item/201604/21/20160421193915_mP2Lr.thumb.700_0.jpeg",
//                "http://img3.duitang.com/uploads/item/201604/26/20160426001415_teGBZ.jpeg",
//                "http://img3.duitang.com/uploads/item/201511/08/20151108200624_cQE2t.thumb.700_0.jpeg",
//                "http://img3.duitang.com/uploads/item/201508/16/20150816132356_Gs4AU.jpeg",
//                "http://img3.duitang.com/uploads/item/201509/19/20150919211347_BcmZA.jpeg",
//                "http://www.bz55.com/uploads/allimg/121019/1-121019100228.jpg",
//        };

        String[] imgs = new String[]{
                "http://img3.redocn.com/20100314/20100313_be9927e7b3ac86bc81ebxdf2JJdFuWtU.jpg",
                "http://img3.redocn.com/20100314/20100313_be9927e7b3ac86bc81ebxdf2JJdFuWtU.jpg",

                "http://img3.redocn.com/20100314/20100313_be9927e7b3ac86bc81ebxdf2JJdFuWtU.jpg",

                "http://img3.redocn.com/20100314/20100313_be9927e7b3ac86bc81ebxdf2JJdFuWtU.jpg",

                "http://img3.redocn.com/20100314/20100313_be9927e7b3ac86bc81ebxdf2JJdFuWtU.jpg",

                "http://img3.redocn.com/20100314/20100313_be9927e7b3ac86bc81ebxdf2JJdFuWtU.jpg",


        };

        final ArrayList<Banner> data = new ArrayList<>();
        for (int i = 0;i < imgs.length;i++)
        {
            Banner banner = new Banner();
            banner.title = String.format("%d",i);
            banner.imageUrl = imgs[i];
            data.add(banner);
        }

        mBannerView.setData(data);
        mBannerView.startTurning();

        HashMap<String,Object> params = new HashMap<>();
        params.put("Account","hr");
        params.put("Force",0);
        params.put("Password","111");
        params.put("VCode","");
        params.put("__RequestVerificationToken","K2uBcx1aaOVAaCktKRzNDRLIafOcfXcq7Mp3XknNGGmTFN04Dm8xvHXjzvuUCFlSq3dVLpjUNxQur_a5z9l5Mp8d969trT_q52fBdyp4cvM1");

        mRxManager.add(Api.getInstance().movieService
                .loginPrev()
                .compose(RxSchedulers.io_main())
                .doOnError(throwable -> System.out.println("doOnError"))

                .onErrorReturn(throwable -> {
                    System.out.println("onErrorReturn");
                    return throwable.getMessage();
                })
                .onErrorResumeNext(throwable -> {
                    System.out.println("onErrorResumeNext");
                    return Observable.just(throwable.getMessage());
                })
                .subscribe(System.out::println));



    }


    @Override
    public void onClick(View v) {
        android.util.Log.e("HomeFragment", v.getTag().toString());
    }


    private void refreshComplete()
    {
        if(ptrFrameLayout != null )
        {
            ptrFrameLayout.refreshComplete();
        }
    }

    @Override
    public void onLeave() {
        super.onLeave();
        if (mBannerView != null)mBannerView.stopTurning();
    }

    @Override
    public void onBack() {
        super.onBack();
        if (mBannerView != null)mBannerView.startTurning();
    }

    //
//    private void dynamicInitMenu()
//    {
//        mMenuContainer.removeAllViews();
//        int space = 2;
//        int columnCount = 3;
//        int screenWidth = getResources().getDisplayMetrics().widthPixels;
//        screenWidth -= ((columnCount - 1) * space);
//        int itemWh = screenWidth / columnCount;
//
//        int itemCount = homeItemArray.size();
//        for (int i = 0; i < itemCount;i++)
//        {
//            KV<Integer,String> kv = homeItemArray.get(i);
//            int res = kv.key;
//            String text = kv.value;
//            HomeItem item = new HomeItem(getActivity(),itemWh);
//            item.setDrawableWithText(res, text);
////            item.setListener(this);
//            item.setOnClickListener(this);
//            item.setTag(kv);
//
//
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(itemWh,itemWh);
//            int column = i % columnCount;
//            int row = i / columnCount;
//            params.leftMargin = column * (itemWh + space);
//            params.topMargin = row * (itemWh + space);
//            item.setLayoutParams(params);
//
//            mMenuContainer.addView(item);
//
//            android.util.Log.e("HomeFragment", "index:" + i + " ,row :" + row + " ,column:" + column);
//
//        }
//    }
//
//    protected int dp2px(Context context, int dpVal) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                dpVal, context.getResources().getDisplayMetrics());
//    }
//
//
//




    /**
     * 特效,缩小两边的view
     */
    //        mBannerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
//
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
////                mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
//                int childCount = mBannerView.getChildCount();
//                int width = mBannerView.getChildAt(0).getWidth();
//                int padding = (mBannerView.getWidth() - width) / 2;
//
//                for (int j = 0; j < childCount; j++) {
//                    View v = recyclerView.getChildAt(j);
//                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
//                    float rate = 0;
//                    if (v.getLeft() <= padding) {
//                        if (v.getLeft() >= padding - v.getWidth()) {
//                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
//                        } else {
//                            rate = 1;
//                        }
//                        v.setScaleY(1 - rate * 0.1f);
//                    } else {
//                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
//                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
//                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
//                        }
//                        v.setScaleY(0.9f + rate * 0.1f);
//                    }
//                }
//            }
//        });
//
//        mBannerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                if (mBannerView.getChildCount() < 3) {
//                    if (mBannerView.getChildAt(1) != null) {
//                        View v1 = mBannerView.getChildAt(1);
//                        v1.setScaleY(0.9f);
//                    }
//                } else {
//                    if (mBannerView.getChildAt(0) != null) {
//                        View v0 = mBannerView.getChildAt(0);
//                        v0.setScaleY(0.9f);
//                    }
//                    if (mBannerView.getChildAt(2) != null) {
//                        View v2 = mBannerView.getChildAt(2);
//                        v2.setScaleY(0.9f);
//                    }
//                }
//
//            }
//        });


    @Override public boolean processBackPressed() {return false;}
}
