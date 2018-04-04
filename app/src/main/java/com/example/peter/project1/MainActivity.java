package com.example.peter.project1;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peter.project1.Adapter.AdapterSlideShow;
import com.example.peter.project1.Adapter.adapter_rc_horizontalview;
import com.example.peter.project1.Fragment.SlideshowFragment;
import com.example.peter.project1.Model.SanPham;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager_slideshow;
    ImageButton imgb;
    DrawerLayout dw;
    TextView tv_xemtatca;
    ImageView  imgV_sp;
    RecyclerView rc_MonChinh,rc_Mon_Vat,rc_Thuc_uong;
    CircleIndicator circleIndicator;
    ArrayList<SanPham> arrayListSanPhamSlideShow;
    ArrayList<SanPham> arrayListMonChinh;
    ArrayList<SanPham> arrayListMonAnVat;
    ArrayList<SanPham> arrayListThucUong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        AnhXa();
       //load data
        Thread threadData = new ThreadData();
        threadData.start();
        setUphorizontalView();
        setUpCircleIndicator();
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dw.openDrawer(GravityCompat.START);
            }
        });

        tv_xemtatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SanPhamActivity.class);
                startActivity(i);
            }
        });
//        setUpCircleIndicator();

    }
    public void AnhXa(){
        arrayListThucUong=new ArrayList<>();
        arrayListMonAnVat=new ArrayList<>();
        arrayListMonChinh =new ArrayList<>();
        arrayListSanPhamSlideShow=new ArrayList<>();
        rc_MonChinh=findViewById(R.id.rc_mon_chinh);
        rc_Mon_Vat=findViewById(R.id.rc_mon_van_vat);
        rc_Thuc_uong=findViewById(R.id.rc_thuc_uong);
        imgb = findViewById(R.id.img_btn_menu);
        dw = findViewById(R.id.drawer_layout);
        tv_xemtatca = findViewById(R.id.tv_xemtatca1);
        viewPager_slideshow=findViewById(R.id.vp_slideshow);
        circleIndicator=findViewById(R.id.indicator_default);
    }
    public void getDataarrayListMonChinh(){
        for (int i = 0; i < 10; i++) {
            SanPham sp = new SanPham("sp" + i, 5 + i * 10, R.drawable.garan, 1, 1);
            arrayListMonChinh.add(sp);
        }
    }
    public void getDataarrayListMonAnVat(){
        for (int i = 0; i < 10; i++) {
            SanPham sp = new SanPham("sp" + i, 5 + i * 10, R.drawable.garan, 1, 2);
            arrayListMonAnVat.add(sp);
        }
    }
    public void getDataarrayListThucUong(){
        for (int i = 0; i < 10; i++) {
            SanPham sp = new SanPham("sp" + i, 5 + i * 10, R.drawable.garan, 1, 3);
            arrayListThucUong.add(sp);
        }

    }
    public void setUpCircleIndicator(){
        FragmentPagerAdapter adapter= new AdapterSlideShow(getSupportFragmentManager(),arrayListSanPhamSlideShow);
        viewPager_slideshow.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager_slideshow);


    }

    public void setDataarrayListSanPhamSlideShow(){
        for(int i=0;i<2;i++){
            arrayListSanPhamSlideShow.add(arrayListMonChinh.get(i));
        }
        for(int i=2;i<4;i++){
            arrayListSanPhamSlideShow.add(arrayListMonAnVat.get(i));
        }
        for(int i=4;i<6;i++){
            arrayListSanPhamSlideShow.add(arrayListThucUong.get(i));
        }
    }
    public void setUphorizontalView(){
        //rc Mon Chinh
        adapter_rc_horizontalview adapterMonChinh = new adapter_rc_horizontalview(arrayListMonChinh,MainActivity.this);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rc_MonChinh.setLayoutManager(layoutManager);
        rc_MonChinh.setAdapter(adapterMonChinh);
        // rc Mon an Vat
        LinearLayoutManager layoutManager1= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        adapter_rc_horizontalview adapterAnVat = new adapter_rc_horizontalview(arrayListMonAnVat,MainActivity.this);
        rc_Mon_Vat.setLayoutManager(layoutManager1);
        rc_Mon_Vat.setAdapter(adapterAnVat);
        // rc Thuc uong
        LinearLayoutManager layoutManager2= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        adapter_rc_horizontalview adapterThucUong = new adapter_rc_horizontalview(arrayListThucUong,MainActivity.this);
        rc_Thuc_uong.setLayoutManager(layoutManager2);
        rc_Thuc_uong.setAdapter(adapterThucUong);


    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            super.run();
            getDataarrayListMonAnVat();
            getDataarrayListMonChinh();
            getDataarrayListThucUong();
            setDataarrayListSanPhamSlideShow();
        }
    }

}
