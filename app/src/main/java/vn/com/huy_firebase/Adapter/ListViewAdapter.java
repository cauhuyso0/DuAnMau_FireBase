package vn.com.huy_firebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.HoaDonChiTiet;
import vn.com.huy_firebase.model.Sach;

import static vn.com.huy_firebase.Activity.Main2Activity.saches;

public class ListViewAdapter extends ArrayAdapter<HoaDonChiTiet> {

    public ListViewAdapter(@NonNull Context context, @NonNull List<HoaDonChiTiet> hoaDonChiTiets) {
        super(context,0 ,hoaDonChiTiets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HoaDonChiTiet hoaDonChiTiet = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_view_hoa_don_ct, parent, false);
        }
        TextView tvMaHDCT = convertView.findViewById(R.id.tvMaHDCT);
        TextView tvTenSach = convertView.findViewById(R.id.tvTenSach);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
        TextView tvThanhTien = convertView.findViewById(R.id.tvThanhTien);

        tvMaHDCT.setText(hoaDonChiTiet.getMaHDCT());
        tvTenSach.setText(hoaDonChiTiet.getTenSach());
        tvSoLuong.setText(hoaDonChiTiet.getSoLuongMua()+"");

        String tenSach = tvTenSach.getText().toString();
        int soLuong = Integer.parseInt(tvSoLuong.getText().toString());
        double tien = 0;
        for (int i = 0; i < saches.size(); i++){
            Sach sach = saches.get(i);
            if(tenSach.equals(sach.getTenSach())){
                tien = sach.getGiaBia();
            }
        }
        double thanhTien = (tien*soLuong);
        tvThanhTien.setText("Thành tiền: "+thanhTien);

        return convertView;
    }
}
