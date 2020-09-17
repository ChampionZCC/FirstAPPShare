package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.example.myapplication.fragmnet.BlankFragment;
import com.example.myapplication.fragmnet.FragmentManagerHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;


/**
 * @author zhoucong
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;
    private TextView mTextView;
    private ArrayList<String> mList;
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ZXingLibrary.initDisplayOpinion(this);
//        Hawk.init(this).build();

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.container, BlankFragment.newInstance("hello world"))
//                .commit();
        FragmentManagerHandler.addAsRootFragment(getSupportFragmentManager(), BlankFragment.newInstance("hello world"), R.id.content);
        initView();
        initData();
//        initBlueTooth();
        initPermission();
        HashMap<String, Integer> map = new HashMap<>();


//        View rootview = findViewById(android.R.id.content);
//        Banner.make(rootview,this,Banner.SUCCESS,"This is a successful message",Banner.TOP).show();
//        Banner.make(rootview,this,Banner.ERROR,"This is an error message",Banner.TOP).show();
//        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        okHttpClientBuilder.addInterceptor(loggingInterceptor);
//
//        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
//        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
//        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());


    }

//    private void initBlueTooth() {
//        Ble ble = Ble.create(getApplicationContext());
//        ble.startScan();
//    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

//    private void getRuntimePermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            String[] perms = {Manifest.permission.CAMERA,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
//            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED
//                    || checkSelfPermission(perms[1]) == PackageManager.PERMISSION_DENIED) {
//                requestPermissions(perms, 200);
//            } else {
//                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
//            }
//        }
//    }


    private void initView() {
        mList = new ArrayList<>();
        mList.add("cc");
        mList.add("dd");
        mList.add("ee");
        mList.add("aa");
        SwipeMenuRecyclerView swipeRecyclerView = findViewById(R.id.rv_swipe);
        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {

                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                // 注意：哪边不想要菜单，那么不要添加即可。
                SwipeMenuItem addItem = new SwipeMenuItem(MainActivity.this)
                        .setBackground(R.drawable.ic_launcher_background)
                        .setImage(R.mipmap.ic_launcher)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                SwipeMenuItem addItem1 = new SwipeMenuItem(MainActivity.this)
                        .setBackground(R.drawable.ic_launcher_background)
                        .setImage(R.mipmap.ic_launcher)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(addItem); // 添加菜单到右侧。
                rightMenu.addMenuItem(addItem1);
            }
        };
// 设置监听器。
        swipeRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                Toast.makeText(MainActivity.this, direction + " " + adapterPosition + " " + menuPosition, Toast.LENGTH_SHORT).show();
            }
        };

        // 菜单点击监听。
        swipeRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

        // 添加适配器

//        RecyclerView recyclerView = findViewById(R.id.rv_test);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TestAdapter testAdapter = new TestAdapter(this, initData());
        testAdapter.setHasStableIds(true);
//        recyclerView.setItemAnimator(null);
//        recyclerView.setAdapter(testAdapter);
        swipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRecyclerView.setAdapter(testAdapter);

        mTextView = findViewById(R.id.tv_test_button1);
        mTextView2 = findViewById(R.id.tv_test_button2);


        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                SharedPreferencesUtils.setParam(MainActivity.this, "1", now);
//                FragmentManagerHandler.pushFragment(getSupportFragmentManager(), BlankFragment.newInstance("hello world2"), BlankFragment2.newInstance("hello world"));
//                Hawk.put("1", mList);
//                gson();
//                Observable.from(initData()).subscribe(new Action1<TestBean>() {
//                    @Override
//                    public void call(TestBean bean) {
//                        Log.e("MainActivity", "call: " + bean.getName());
//                    }
//                });
//                Intent intent = new Intent(getApplication(), CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(MainActivity.this)
                        .setMessage("加载中...")
                        .setCancelable(true)
                        .setCancelOutside(true);
                LoadingDailog dialog=loadBuilder.create();
                dialog.show();
//                FragmentManagerHandler.popFragment(getSupportFragmentManager());
//                Intent i1 = new Intent();
//                i1.setData(Uri.parse("baidumap://map/marker?location=40.057406655722,116.2964407172&title=Marker&content=makeamarker&traffic=on&src=andr.baidu.openAPIdemo"));
//                startActivity(i1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }

    public void gson() {
        Gson gson = new Gson();
        List<TestBean> list = new ArrayList<>();
        TestBean testBean1 = new TestBean("大挖参数", 12238, 92340);
        TestBean testBean2 = new TestBean("小挖参数", 12347, 96234);
        TestBean testBean3 = new TestBean("微挖参数", 12346, 923423);
        TestBean testBean4 = new TestBean("中挖参数", 11239, 99123);
        list.add(testBean1);
        list.add(testBean2);
        list.add(testBean3);
        list.add(testBean4);

        HashMap hashMap = new HashMap();
        hashMap.put("Config1", testBean1);
        hashMap.put("Config2", testBean2);
        hashMap.put("Config3", testBean3);

        Log.e("MainActivity", "对象转json: " + gson.toJson(hashMap));
        System.out.println("对象转json: " + gson.toJson(list));
//        System.out.println("集合转json: "+gson.toJson(list));

        System.out.println("----------------------------------");

//        TestBean jsonObiect = gson.fromJson(gson.toJson(person), TestBean.class);
        List<TestBean> jsonListObject = gson.fromJson(gson.toJson(list), new TypeToken<List<TestBean>>() {
        }.getType());
//        System.out.println("格式化jsonObject: "+jsonObiect);
        Log.e("MainActivity", "格式化jsonListObject: " + jsonListObject);
//        System.out.println("格式化jsonListObject: " + jsonListObject);
    }

    private List<TestBean> initData() {
        List<TestBean> list = new ArrayList<>();
        TestBean testBean1 = new TestBean("小明", 18, 90);
        TestBean testBean2 = new TestBean("小红", 17, 96);
        TestBean testBean3 = new TestBean("小王", 16, 93);
        TestBean testBean4 = new TestBean("小周", 19, 99);
        list.add(testBean1);
        list.add(testBean2);
        list.add(testBean3);
        list.add(testBean4);

        return list;
    }


}