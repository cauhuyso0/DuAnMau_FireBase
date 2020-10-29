package vn.com.huy_firebase.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.HoaDon;
import vn.com.huy_firebase.model.HoaDonChiTiet;
import vn.com.huy_firebase.model.Sach;

import static vn.com.huy_firebase.Activity.Main2Activity.hoaDons;
import static vn.com.huy_firebase.Activity.Main2Activity.listHDCTs;
import static vn.com.huy_firebase.Activity.Main2Activity.saches;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeFragment extends Fragment {
    TextView tvTuNgay, tvDenNgay, tvKetQua;
    Button btnTim;
    String tuNgay = "";
    String denNgay = "";
    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_thong_ke, container, false);

        tvTuNgay = view.findViewById(R.id.tvTuNgay);
        tvDenNgay = view.findViewById(R.id.tvDenNgay);
        tvKetQua = view.findViewById(R.id.tvKetQua);
        btnTim = view.findViewById(R.id.btnTim);

        tvTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTuNgay();
            }
        });

        tvDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDenNgay();
            }
        });
        setBtnTim();
        return view;
    }

    public void setTuNgay() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        final int thang = calendar.get(Calendar.MONTH);
        final int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tuNgay = simpleDateFormat.format(calendar.getTime());
                tvTuNgay.setText(tuNgay);
            }
        }, nam, thang, ngay);
        datePickerDialog.show();

    }

    public void setDenNgay() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        final int thang = calendar.get(Calendar.MONTH);
        final int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                denNgay = simpleDateFormat.format(calendar.getTime());
                tvDenNgay.setText(denNgay);
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    public void setBtnTim(){
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date1 = null;
                Date date2 = null;
                String strDate1 = tvTuNgay.getText().toString();
                String strDate2 = tvDenNgay.getText().toString();
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(strDate1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(strDate2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                double tong = 0;
                double tien = 0;
                int soLuong = 0;


                for (int i = 0; i < hoaDons.size(); i++){
                    HoaDon hoaDon = hoaDons.get(i);
                    try {
                        if (date1.before(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayMua())) || date1.equals(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayMua())) && date2.after(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayMua())) || date2.equals(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayMua()))){
                            for (int j = 0; j < listHDCTs.size(); j++){
                                HoaDonChiTiet hoaDonChiTiet = listHDCTs.get(j);
                                if (hoaDonChiTiet.getMaHDCT().substring(0,4).equals(hoaDon.getMaHoaDon())){
                                    soLuong =  hoaDonChiTiet.getSoLuongMua();

                                    for (int k = 0; k < saches.size(); k++){
                                        Sach sach = saches.get(k);

                                        if(sach.getTenSach().equals(hoaDonChiTiet.getTenSach())){
                                            tien = sach.getGiaBia();
                                            tong += (tien*soLuong);

                                        }
                                    }
                                }

                            }
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                List<HoaDon> hoaDonList = new ArrayList<>();
                List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
                ArrayList<HoaDonChiTiet> arrayList = new ArrayList<>();

                for (int i = 0; i < hoaDons.size(); i++){
                    HoaDon hoaDon = hoaDons.get(i);
                    try {
                        if (date1.before(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayMua())) || date1.equals(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayMua())) && date2.after(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayMua())) || date2.equals(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayMua()))){
                            hoaDonList.add(hoaDon);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                for (int j = 0; j < hoaDonList.size(); j++){
                    for (HoaDonChiTiet h: listHDCTs){
                        if (h.getMaHDCT().substring(0,4).equals(hoaDonList.get(j).getMaHoaDon())){
                            hoaDonChiTietList.add(h);
                        }
                    }
                }
                ArrayList<String> aaa = new ArrayList<>();
                for (Sach sach: saches){
                    int sl = 0;
                    String tenSach2 = null;
                    for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietList){
                        if (sach.getTenSach().equals(hoaDonChiTiet.getTenSach())){
                            sl += hoaDonChiTiet.getSoLuongMua();
                            System.out.println(sach.getTenSach());
                            //                            tenSach2 = hoaDonChiTiet.getTenSach();
                        }
                    }

                    arrayList.add(new HoaDonChiTiet(sach.getTenSach(), sl));
//                    System.out.println(sach.getTenSach());
//                    System.out.println(sl);
                }

                int max = 0;
                String name = "";
                for (int i = 0; i <arrayList.size(); i++){
                    for (int j = i +1; j < arrayList.size(); j++){
                        if (arrayList.get(i).getSoLuongMua()>arrayList.get(j).getSoLuongMua()){
                            max = arrayList.get(i).getSoLuongMua();
                            name = arrayList.get(i).getTenSach();
                        }else if (arrayList.get(i).getSoLuongMua() < arrayList.get(j).getSoLuongMua()){
                            max = arrayList.get(j).getSoLuongMua();
                            name = arrayList.get(j).getTenSach();
                        }
                    }
                }

               System.out.println(hoaDonList);
                System.out.println(hoaDonChiTietList);
                System.out.println(arrayList);
                tvKetQua.setText("Tổng tiền : " + tong +"\n" +
                                "Sách bán chạy nhất là: " + name + " với: " + max
                );

               }

        });
    }

}
