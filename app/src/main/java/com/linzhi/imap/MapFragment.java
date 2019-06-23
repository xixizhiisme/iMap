package com.linzhi.imap;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {
    private MapView mapView=null;
    private BaiduMap baiduMap;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    protected LatLng target=new LatLng(30.689362,103.822179);
    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SDKInitializer.initialize(getActivity().getApplicationContext());
        View rootView= inflater.inflate(R.layout.fragment_map, container, false);
        mapView=rootView.findViewById(R.id.mapTextView);
        baiduMap=mapView.getMap();//获取地图控制器
        MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.newLatLng(target);
        baiduMap.setMapStatus(mapStatusUpdate);
        mapStatusUpdate= MapStatusUpdateFactory.zoomTo(16);
        baiduMap.setMapStatus(mapStatusUpdate);
        Button btn_start_location = rootView.findViewById(R.id.location);
        btn_start_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BDLocationUtils bdLocationUtils = new BDLocationUtils(getActivity());
                bdLocationUtils.doLocation();//开启定位
                bdLocationUtils.mLocationClient.start();//开始定位
            }
        });
            /*onRquestPermissionsResult及以下if判断语句引用自
            作者：iOnesmile
            来源：CSDN
            原文：https://blog.csdn.net/u010134293/article/details/52808832
            用于解决Android6.0及以上版本手机上扫描不到蓝牙设备并抛出异常的问题*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
            }
        }
        /*SensorManager mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;//地图显示模式:普通
        mCurrentMarker = null;//null为默认
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.location);//自定义显示定位图标*/
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO request success
                }
                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
}
