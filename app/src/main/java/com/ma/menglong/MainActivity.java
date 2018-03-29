package com.ma.menglong;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ma.menglong.common.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;//侧滑根标签
    private SwipeRefreshLayout swipeRefreshLayout;//下拉刷新
    private CardImageAdapter adapter;
    private List<ImageText> imageTextList=new ArrayList<>();
    private ImageText[] imageTexts={new ImageText("ttttt",R.drawable.nav_icon),
            new ImageText("ttttt",R.drawable.nav_icon),
            new ImageText("ttttt",R.drawable.nav_icon),
            new ImageText("ttttt",R.drawable.nav_icon),
            new ImageText("ttttt",R.drawable.nav_icon)
    };
//private TextView mail;
    // private NavigationView navView;//侧栏
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerLayout);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        setSwipeRefresh(swipeRefreshLayout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);//侧栏
        setNavigationItemSelected(navView);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            //显示导航
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        createTem();
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new CardImageAdapter(imageTextList);
        recyclerView.setAdapter(adapter);


    }
public void createTem(){
    imageTextList.clear();
    for (int i = 0; i < new Random().nextInt(20); i++) {
        Random random = new Random();
        int index = random.nextInt(imageTexts.length);
        imageTextList.add(imageTexts[index]);
    }
}


    /**
     * 加载menu
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    /**
     * 菜单按钮点击事件
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"backup",Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.delete:
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.setting:
                Toast.makeText(this,"setting",Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
        }
        return true;
    }

    /**
     * 双击退出
     * */
    private  long exitTime=0;//全局计时
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime)>2000){
                Snackbar.make(drawerLayout, "再按一次退出程序！(๑ت๑)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                //Toast.makeText(this,"再按一次退出程序！(๑ت๑)",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }
            else
            {
                Toast.makeText(this,"欢迎下次再来！(๑`･︶･´๑)",Toast.LENGTH_SHORT).show();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
/******************************重写函数分界线*********************************************/
    /**设置NavigationItem的点击事件
     * */
    public void setNavigationItemSelected(NavigationView navView){
        navView.setCheckedItem(R.id.nav_call);
        //点击邮箱发邮件
        View headerView=navView.inflateHeaderView(R.layout.nav_header);
        LinearLayout linearLayout=headerView.findViewById(R.id.icon_mail);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SENDTO);
                it.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.nav_header_email));
             //   it.setType("text/plain");
                startActivity(Intent.createChooser(it, "Choose Email Client"));
            }
        });
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this,"nav_call",Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this,"nav_friends",Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.nav_location:
                        Toast.makeText(MainActivity.this,"nav_location",Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(MainActivity.this,"nav_mail",Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.nav_task:
                        Toast.makeText(MainActivity.this,"nav_task",Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.nav_donate:
                        Common.openALiPay(MainActivity.this);
                        break;
                    case R.id.contactMe:
                        Common.contactMe(MainActivity.this);
                        break;
                    case R.id.about:
                        Common.showNoticeDialog(MainActivity.this);
                        //虽然这里的参数是AlertDialog.Builder(Context context)但我们不能使用getApplicationContext()获得的Context,而必须使用Activity.this,因为只有一个Activity才能添加一个窗体。
                        break;
                    default:
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    /**设置SwipeRefreshLayout刷新操作
     * */
    public void setSwipeRefresh(SwipeRefreshLayout refreshLayout){

        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                createTem();//重新生成
                                adapter.notifyDataSetChanged();//通知adapter数据改变
                                swipeRefreshLayout.setRefreshing(false);//不在刷新，取消动画
                                Toast.makeText(MainActivity.this,"刷新完成！",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
    }

}
