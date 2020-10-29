package vn.com.huy_firebase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;
import java.util.List;


import vn.com.huy_firebase.DAO.HoaDonChiTietDAO;
import vn.com.huy_firebase.DAO.HoaDonDAO;
import vn.com.huy_firebase.DAO.LoaiSachDAO;
import vn.com.huy_firebase.DAO.NguoiDungDAO;
import vn.com.huy_firebase.DAO.SachDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.fragment.HoaDonFragment;
import vn.com.huy_firebase.fragment.NguoiDungFragment;
import vn.com.huy_firebase.fragment.QuanLySachFragment;
import vn.com.huy_firebase.fragment.ThongKeFragment;
import vn.com.huy_firebase.model.HoaDon;
import vn.com.huy_firebase.model.HoaDonChiTiet;
import vn.com.huy_firebase.model.LoaiSach;
import vn.com.huy_firebase.model.NguoiDung;
import vn.com.huy_firebase.model.Sach;

public class Main2Activity extends AppCompatActivity {
    public static ArrayList<NguoiDung> nguoiDungs = new ArrayList<>();
    public static ArrayList<LoaiSach> loaiSaches = new ArrayList<>();
    public static ArrayList<Sach> saches = new ArrayList<>();
    public static ArrayList<HoaDonChiTiet> listHDCTs = new ArrayList<>();
    public static ArrayList<HoaDon> hoaDons = new ArrayList<>();
    DrawerLayout drawer_layout;
    Toolbar toolbar;
    NavigationView nvView;
    TextView tvTen;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        map();
        actionBar();
        iteamSelected();
        getNguoiDung();
       // getLoaiSach();
        getSach();
        getHDCT();
        getHoaDon();
        View view = nvView.getHeaderView(0);
        tvTen = view.findViewById(R.id.tvTen);
        Intent intent = getIntent();
        String ten = intent.getStringExtra("ten");
        tvTen.setText(ten);

    }

    public void getHDCT(){
        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        listHDCTs = (ArrayList<HoaDonChiTiet>) hoaDonChiTietDAO.getAll();
    }

    public void getSach(){
        SachDAO sachDAO = new SachDAO(this);
        saches = (ArrayList<Sach>) sachDAO.getAll1();
    }

    public void getLoaiSach(){
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(this);
        loaiSaches = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

    }

    public void getHoaDon(){
        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
        hoaDons = (ArrayList<HoaDon>) hoaDonDAO.getAll1();
    }
    public void getNguoiDung(){
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        nguoiDungs = (ArrayList<NguoiDung>) nguoiDungDAO.getAll();
    }

    public void map(){
        toolbar = findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvView = (NavigationView) findViewById(R.id.nvView);
    }

    public void actionBar(){
        setSupportActionBar(toolbar);
        ActionBar ab =  getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black);
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void iteamSelected() {
        nvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nguoiDung:
                        NguoiDungFragment nguoiDungFragment = new NguoiDungFragment();

                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_layout, nguoiDungFragment)
                                .commit();
                        drawer_layout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.quanLySach:
                        QuanLySachFragment quanLySachFragment = new QuanLySachFragment();

                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_layout, quanLySachFragment)
                                .commit();
                        drawer_layout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.hoaDon:
                        HoaDonFragment hoaDonFragment = new HoaDonFragment();

                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_layout, hoaDonFragment)
                                .commit();
                        drawer_layout.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.thongKe:
                        ThongKeFragment thongKeFragment = new ThongKeFragment();

                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_layout, thongKeFragment)
                                .commit();
                        drawer_layout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.exit:
                        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        intent.putExtra("key", bundle);
                        startActivity(intent);

                        break;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id==android.R.id.home){
            drawer_layout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
