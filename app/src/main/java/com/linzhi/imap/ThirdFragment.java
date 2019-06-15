package com.linzhi.imap;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment implements Runnable{
    private final String TAG = "Rate";
    private float rateDollar=1/6.7f;
    private float rateEuro=1/11f;
    private float rateWon=500f;
    private String updateDate="";

    EditText rmb;
    TextView show;
    Handler handler;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @SuppressLint("HandlerLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_third, container, false);
        rmb = rootView.findViewById(R.id.rmb);
        show = rootView.findViewById(R.id.showOut);

        //获取sp里保存的数据
        SharedPreferences share=getActivity().getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        rateDollar=share.getFloat("rate_dollar",0.0f);
        rateEuro=share.getFloat("rate_euro",0.0f);
        rateWon=share.getFloat("rate_won",0.0f);
        updateDate=share.getString("update_date","");
        //获取当前系统时间
        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr=sdf.format(today);
        Log.i(TAG,"onCreate:sp rateDollar="+rateDollar);
        Log.i(TAG,"onCreate:sp rateEuro="+rateEuro);
        Log.i(TAG,"onCreate:sp rateWon="+rateWon);
        Log.i(TAG, "onCreate: sp updateDate="+updateDate);

        //判断时间
        if(!todayStr.equals(updateDate)){
            Log.i(TAG, "onCreate: 需要更新");
            //开启子线程
            Thread t=new Thread(this);
            t.start();
        }else {
            Log.i(TAG, "onCreate: 不需要更新");
        }

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==5){
                    Bundle bdl=(Bundle) msg.obj;
                    rateDollar=bdl.getFloat("rate_dollar");
                    rateEuro=bdl.getFloat("rate_euro");
                    rateWon=bdl.getFloat("rate_won");
                    Log.i(TAG, "handleMessage: dollar:"+rateDollar);
                    Log.i(TAG, "handleMessage: euro:"+rateEuro);
                    Log.i(TAG, "handleMessage: won:"+rateWon);
                    //保存更新的日期
                    SharedPreferences share=getActivity().getSharedPreferences("myrate",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=share.edit();
                    editor.putFloat("rate_dollar",rateDollar);
                    editor.putFloat("rate_euro",rateEuro);
                    editor.putFloat("rate_won",rateWon);
                    editor.putString("update_date",todayStr);
                    editor.apply();
                    Toast.makeText(getActivity(),"汇率已更新",Toast.LENGTH_SHORT).show();
                    /*Log.i(TAG, "handleMessage: getMessage msg="+str);
                    show.setText(str);*/
                }
                super.handleMessage(msg);
            }
        };

        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button1=getActivity().findViewById(R.id.rate_dollar);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.rate_dollar){
                    String str = rmb.getText().toString();
                    Log.i(TAG,"onClick:get str="+str);
                    float r = 0;
                    if(str.length()>0){
                        r = Float.parseFloat(str);
                    }else{
                        Toast.makeText(getActivity(), "请输入金额", Toast.LENGTH_SHORT).show();
                    }
                    show.setText(String.format("%.2f", r *rateDollar));
                }
            }
        });
        Button button12=getActivity().findViewById(R.id.rate_euro);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.rate_euro){
                    String str = rmb.getText().toString();
                    Log.i(TAG,"onClick:get str="+str);
                    float r = 0;
                    if(str.length()>0){
                        r = Float.parseFloat(str);
                    }else{
                        Toast.makeText(getActivity(), "请输入金额", Toast.LENGTH_SHORT).show();
                    }
                    show.setText(String.format("%.2f", r *rateEuro));
                }
            }
        });
        Button button2=getActivity().findViewById(R.id.rate_won);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.rate_won){
                    String str = rmb.getText().toString();
                    Log.i(TAG,"onClick:get str="+str);
                    float r = 0;
                    if(str.length()>0){
                        r = Float.parseFloat(str);
                    }else{
                        Toast.makeText(getActivity(), "请输入金额", Toast.LENGTH_SHORT).show();
                    }
                    show.setText(String.format("%.2f", r *rateWon));
                }
            }
        });
        Button button22=getActivity().findViewById(R.id.btn_opencfg);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_opencfg){
                    Intent intent=new Intent(getActivity().getApplicationContext(),MyList2Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==2){
            Bundle bundle=data.getExtras();
            rateDollar = bundle.getFloat("key_dollar",0.1f);
            rateEuro = bundle.getFloat("key_euro",0.1f);
            rateWon = bundle.getFloat("key_won",0.1f);
            Log.i(TAG,"onActivityResult:rateDollar="+rateDollar);
            Log.i(TAG,"onActivityResult:rateEuro="+rateEuro);
            Log.i(TAG,"onActivityResult:rateWon="+rateWon);

            //将新设置的汇率写到sp里
            SharedPreferences share=getActivity().getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=share.edit();
            editor.putFloat("rate_dollar",rateDollar);
            editor.putFloat("rate_euro",rateEuro);
            editor.putFloat("rate_won",rateWon);
            editor.commit();
            Log.i(TAG,"onActivityResult:数据已保存到share");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void run() {
        Log.i(TAG, "run: run()……");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //用于保存获取的汇率
        Bundle bundle;
        bundle=getFromBOC();
        //获取msg对象，用于返回主线程
        Message msg=handler.obtainMessage(5);
        //msg.what=5;
        //msg.obj="Hello from run()";
        msg.obj=bundle;
        handler.sendMessage(msg);

        //bundle中保存获取的汇率
    }
    private Bundle getFromBOC() {
        Bundle bundle=new Bundle();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            Log.i(TAG, "run: "+doc.title());
            Elements tables=doc.getElementsByTag("table");
            Element table6=tables.get(0);
            //获取TD中的数据
            Elements tds=table6.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=6){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                Log.i(TAG, "run: "+td1.text()+"==>"+td2.text());
                String str1=td1.text();
                String val=td2.text();
                if("美元".equals(str1)){
                    bundle.putFloat("rate_dollar",100f/Float.parseFloat(val));
                }else if("欧元".equals(str1)){
                    bundle.putFloat("rate_euro",100f/Float.parseFloat(val));
                }else if("韩元".equals(str1)){
                    bundle.putFloat("rate_won",100f/Float.parseFloat(val));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
    }
    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer=new char[bufferSize];
        final StringBuilder out=new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312");
        for(;;){
            int rsz=((InputStreamReader) in).read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
}
