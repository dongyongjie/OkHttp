package com.bwie.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.bwie.okhttp.view.XListView;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String url="http://v.juhe.cn/weixin/query?pno=&ps=&dtype=&key=ba7c9a7f869110b2388429238d57dcf1";
    private XListView xListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        xListView = (XListView) findViewById(R.id.xListView);
        //设置xListView支持上拉加载
        xListView.setPullLoadEnable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                play();
            }
        }).start();
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override//下拉刷新的方法
            public void onRefresh() {
                start(2);
            }

            @Override//上拉加载的方法
            public void onLoadMore() {
                start(3);
            }
        });
    }
    public void start(int count){
        //得到OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //得到Request对象,内部也是通过建造者模式去封装的一些请求参数
        Request reqest = new Request.Builder().url(url).build();
        //得到call对象
        Call call = okHttpClient.newCall(reqest);
        try {
            //得到实体类,执行联网操作
            Response execute = call.execute();
            //可以根据自己的需要,返回相应的类型:
            //可以返回byte数组, 可以返回 InputStream ,可以返回字符串
            // byte[] bytes = body.bytes();
            //InputStream inputStream = body.byteStream();
            String string = execute.body().string();//注意这里是string()方法,不要写成toString()
            Log.i("TAG", "start: "+string);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void play(){
        OkHttpClient okHttpClient = new OkHttpClient();

    }
}
