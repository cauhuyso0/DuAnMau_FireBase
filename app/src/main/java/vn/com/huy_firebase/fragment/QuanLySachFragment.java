package vn.com.huy_firebase.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import vn.com.huy_firebase.Adapter.PageAdapter;
import vn.com.huy_firebase.DAO.LoaiSachDAO;
import vn.com.huy_firebase.DAO.SachDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.LoaiSach;
import vn.com.huy_firebase.model.Sach;

import static vn.com.huy_firebase.fragment.LoaiSachFragment.listLoaiSachs;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanLySachFragment extends Fragment {
    ViewPager vp;
    TabLayout tl;

    public QuanLySachFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);

        vp = view.findViewById(R.id.vp);
        tl =view.findViewById(R.id.tl);

        setHasOptionsMenu(true);

        setViewPager();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sach_option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.themLoaiSach:
                themLoaiSach();
                break;

            case R.id.timLoaiSach:

                break;

            case R.id.themSach:
                themSach();
                break;
            case R.id.timSach:
                timSach();
                break;

            case R.id.exit:

                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    public void themSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.them_sach, null);

        final Spinner spnMaLS =(Spinner) view.findViewById(R.id.spnMaLS);

        List list = new ArrayList();

        for (int i = 0; i < listLoaiSachs.size(); i++){
            LoaiSach loaiSach = listLoaiSachs.get(i);
            list.add(loaiSach.getMaTheLoai());
        }

        ArrayAdapter arrayAdapter =new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaLS.setAdapter(arrayAdapter);


        builder.setTitle("Thêm Loại Sách")
                .setView(view)
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edtMaSach = view.findViewById(R.id.edtMaSach);
                        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
                        EditText edtTacGia = view.findViewById(R.id.edtTacGia);
                        EditText edtNXB = view.findViewById(R.id.edtNXB);
                        EditText edtGiaBan =  view.findViewById(R.id.edtGiaBan);
                        EditText edtSoLuong = view.findViewById(R.id.edtSoLuong);
                        SachDAO
                        dao = new SachDAO(getContext());
                        String maLS = spnMaLS.getSelectedItem().toString();
                        String maSach = edtMaSach.getText().toString();
                        String tenSach = edtTenSach.getText().toString();
                        String tacGia = edtTacGia.getText().toString();
                        String nXB = edtNXB.getText().toString();
                        double giaBan =Double.parseDouble(edtGiaBan.getText().toString());
                        int soLuong = Integer.parseInt(edtSoLuong.getText().toString());

                        Sach sach = new Sach(maLS, maSach, tenSach, tacGia, nXB, giaBan, soLuong);
                        dao.insert(sach);


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();

    }

    public void themLoaiSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.them_loai_sach, null);

        builder.setTitle("Thêm Loại Sách")
                .setView(view)
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edtMaLS = view.findViewById(R.id.edtMaLS);
                        EditText edtTenLS = view.findViewById(R.id.edtTenLS);
                        EditText edtMoTa = view.findViewById(R.id.edtMoTa);
                        EditText edtViTri = view.findViewById(R.id.edtViTri);

                        LoaiSachDAO dao = new LoaiSachDAO(getContext());

                        String maLS = edtMaLS.getText().toString();
                        String tenLS = edtTenLS.getText().toString();
                        String moTa = edtMoTa.getText().toString();
                        int viTri = Integer.parseInt(edtViTri.getText().toString());

                        LoaiSach loaiSach = new LoaiSach(maLS, tenLS, moTa, viTri);
                        dao.insert(loaiSach);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();

    }

    public void timSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tim_sach, null, false);
        EditText edtTimSach = view.findViewById(R.id.edtTimSach);
        builder.setTitle("Tìm sách")
                .setView(view)
                .setPositiveButton("Tìm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }


        public void setViewPager(){
        tl.addTab(tl.newTab().setText("Loại Sách"));
        tl.addTab(tl.newTab().setText("Sách"));

        PageAdapter adapter = new PageAdapter(getFragmentManager());
        vp.setAdapter(adapter);

        tl.setTabGravity(TabLayout.GRAVITY_CENTER);

        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
