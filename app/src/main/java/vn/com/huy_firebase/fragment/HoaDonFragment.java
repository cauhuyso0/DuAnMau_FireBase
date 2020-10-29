package vn.com.huy_firebase.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.com.huy_firebase.Adapter.HoaDonAdapter;
import vn.com.huy_firebase.Adapter.ListViewAdapter;
import vn.com.huy_firebase.DAO.HoaDonChiTietDAO;
import vn.com.huy_firebase.DAO.HoaDonDAO;
import vn.com.huy_firebase.DAO.SachDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.HoaDon;
import vn.com.huy_firebase.model.HoaDonChiTiet;
import vn.com.huy_firebase.model.Sach;

import static vn.com.huy_firebase.Activity.Main2Activity.listHDCTs;
import static vn.com.huy_firebase.Activity.Main2Activity.saches;
import static vn.com.huy_firebase.fragment.SachFragment.listSaches;


/**
 * A simple {@link Fragment} subclass.
 */
public class HoaDonFragment extends Fragment {
    FloatingActionButton btnFloat;
    RecyclerView rcvHoaDon;
    HoaDonAdapter adapter;
    HoaDonDAO dao;
    HoaDon hoaDon;
    HoaDonChiTiet hoaDonChiTiet;
    HoaDonChiTietDAO chiTietDAO;
    LinearLayoutManager mLayoutManager;
    public static List<HoaDon> listHoaDons;
    String tenSach;
    int soLuong;
    String maHDCT;
    List<HoaDonChiTiet> listView = new ArrayList<>();
    List<HoaDonChiTiet> arrayList = new ArrayList<>();
    // public static List<HoaDonChiTiet> listHDCTs;

    public HoaDonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);

        btnFloat = view.findViewById(R.id.btnFloat);
        rcvHoaDon = view.findViewById(R.id.rcvHoaDon);

        chiTietDAO = new HoaDonChiTietDAO(getActivity(), this);

        dao = new HoaDonDAO(getActivity(), this);
        listHoaDons = dao.getAll();


            mLayoutManager = new LinearLayoutManager(getActivity());
            rcvHoaDon.setLayoutManager(mLayoutManager);

            adapter = new HoaDonAdapter(getActivity(), listHoaDons, this);
            rcvHoaDon.setAdapter(adapter);

            btnFloat.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    View view1 = LayoutInflater.from(getContext()).inflate(R.layout.them_hoa_don, null, false);
                    TextView tvMaHD = view1.findViewById(R.id.tvMaHD);
                    TextView tvNgay = view1.findViewById(R.id.tvNgay);
                    final Button btnADD = view1.findViewById(R.id.btnADD);
                    Date date = new Date();
                    String strDateFormat = "dd/MM/yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                    final String ngay = sdf.format(date);
                    final String maHD = "HD0" + (listHoaDons.size() + 1);
                    tvMaHD.setText(maHD);
                    tvNgay.setText(ngay);

                    btnADD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                            final View view2 = LayoutInflater.from(getContext()).inflate(R.layout.them_hoa_don_ct, null);
                            final TextView tvMaHDCT = view2.findViewById(R.id.tvMaHDCT);
                            final Spinner spnTenSach = view2.findViewById(R.id.spnTenSach);
                            final EditText edtSoLuong = view2.findViewById(R.id.edtSoLuong);
                            final ListView lv = view2.findViewById(R.id.lvHDCT1);
                            Button btnADDHDCT = view2.findViewById(R.id.btnADDHDCT);

                            maHDCT = maHD + "+0" +(listView.size()+ 1);
                            tvMaHDCT.setText(maHDCT);

                            List list1 = new ArrayList();

                            for (int i = 0; i < saches.size(); i++){
                                Sach sach = saches.get(i);
                                list1.add(sach.getTenSach());
                            }

                            ArrayAdapter arrayAdapter =new ArrayAdapter(view2.getContext(), android.R.layout.simple_spinner_item, list1);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spnTenSach.setAdapter(arrayAdapter);

                            btnADDHDCT.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tenSach = spnTenSach.getSelectedItem().toString();
                                    soLuong = Integer.parseInt(edtSoLuong.getText().toString());
                                    HoaDonChiTiet hoaDonChiTiet2 = new HoaDonChiTiet(maHDCT,tenSach,soLuong);
                                    listView.add(hoaDonChiTiet2);

                                    ListViewAdapter listViewAdapter = new ListViewAdapter(view2.getContext(), listView);
                                    lv.setAdapter(listViewAdapter);
                                    listViewAdapter.notifyDataSetChanged();
                                    maHDCT = maHD + "+0" +(listView.size()+ 1);
                                    tvMaHDCT.setText(maHDCT);
                                }
                            });

                            builder1.setTitle("Mã Hóa Đơn: " + maHD)
                                    .setView(view2)
                                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            builder1.show();
                        }
                    });

                    builder.setTitle("Add Hóa Đơn")
                            .setView(view1)
                            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    hoaDon = new HoaDon(maHD, ngay);
                                    dao.insert(hoaDon);

                                    for (int i = 0; i < listView.size();i++){
                                        hoaDonChiTiet = listView.get(i);
                                        chiTietDAO.insert(hoaDonChiTiet);
                                    }
                                    Sach sach1 = null;
                                    for (int j = 0; j < saches.size(); j++){
                                        if (hoaDonChiTiet.getTenSach().equals(saches.get(j).getTenSach())){
                                            String maLS = saches.get(j).getMaTheLoai();
                                            String maSach = saches.get(j).getMaSach();
                                            String tenSach = saches.get(j).getTenSach();
                                            String nXB = saches.get(j).getNXB();
                                            String tacGia = saches.get(j).getTacGia();
                                            int soLuongMua = (saches.get(j).getSoLuong() - hoaDonChiTiet.getSoLuongMua());
                                            double giaBan = saches.get(j).getGiaBia();

                                            sach1 = new Sach(maSach,maLS,tenSach,tacGia, nXB, giaBan, soLuongMua);

                                        }
                                    }
                                    SachDAO sachDAO = new SachDAO(getContext());
                                    sachDAO.update(sach1);

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();

                }
            });
        return view;
    }
    public void capnhatLV(){

        adapter.notifyItemInserted(listHoaDons.size());

        adapter.notifyDataSetChanged();
    }

}
