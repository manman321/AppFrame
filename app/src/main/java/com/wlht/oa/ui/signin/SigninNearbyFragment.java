package com.wlht.oa.ui.signin;/**
 * Created by hr on 16/6/30.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.wlht.oa.KV;
import com.wlht.oa.KVO;
import com.wlht.oa.R;
import com.wlht.oa.adapter.SigninNearbyAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.decoration.DividerItemMiniDecoration;
import com.wlht.oa.util.StringUtils;

public class SigninNearbyFragment extends BaseTitleFragment implements PoiSearch.OnPoiSearchListener {
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrame;
    MapView mMapView;
    SigninNearbyAdapter mAdapter;

    AMap aMap;
    MapView mapView;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin_nearby, null, false);
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        init();
        return view;
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("我的位置");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mPtrFrame = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        initRecyclerView();
    }


    protected void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemMiniDecoration(getActivity(), DividerItemMiniDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter = new SigninNearbyAdapter());
    }


    @Override
    public void initEvent(View view) {

    }



    @Override
    public void initData() {


        mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMyLocation.key, mMyLocation.value),9));

        PoiSearch.Query query = new PoiSearch.Query("写字楼","地名地址信息",mMyLocation.object);
        query.setPageSize(10);
        query.setPageNum(1);
        PoiSearch poiSearch = new PoiSearch(getContext(),query);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(mMyLocation.key,mMyLocation.value),1500));
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    KVO<Double,Double,String> mMyLocation = null;

    @Override
    public void onEnter(Object data) {
        super.onEnter(data);
        if (data instanceof KV)
        {
            mMyLocation = (KVO<Double,Double,String>)data;
            return;
        }
        popTopFragment();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                break;
        }
    }



    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        mAdapter.clear();
        mAdapter.addDatas(poiResult.getPois());
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.icon_signin_location));// 设置小蓝点的图标
//        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
//        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
//        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);

//        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setScaleControlsEnabled(false);
        aMap.getUiSettings().setZoomGesturesEnabled(false);
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setZoomPosition(8);
    }

}
