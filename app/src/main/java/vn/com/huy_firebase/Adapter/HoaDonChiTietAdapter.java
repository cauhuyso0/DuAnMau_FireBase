package vn.com.huy_firebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.com.huy_firebase.R;
import vn.com.huy_firebase.fragment.HoaDonChiTietFragment;
import vn.com.huy_firebase.model.HoaDonChiTiet;



public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.ViewHolder> {

    static Context context;
    int co = 0;
    HoaDonChiTiet hoaDonChiTiet;
    List<HoaDonChiTiet> ds;
    HoaDonChiTietFragment fr;

    public HoaDonChiTietAdapter(Context context, List<HoaDonChiTiet> ds, HoaDonChiTietFragment fr){
        this.context = context;
        this.ds = ds;
        this.fr = fr;
    }


    @NonNull
    @Override
    public HoaDonChiTietAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.card_view_hoa_don_ct, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HoaDonChiTietAdapter.ViewHolder holder, final int position) {

        final HoaDonChiTiet hoaDonChiTiet = ds.get(position);
        holder.tvMaHDCT.setText("Mã HDCT"+hoaDonChiTiet.getMaHDCT());
        holder.tvTenSach.setText("Tên Sách: "+hoaDonChiTiet.getTenSach());
        holder.tvSoLuong.setText("Số Lượng: "+hoaDonChiTiet.getSoLuongMua()+"");

    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMaHDCT, tvTenSach, tvSoLuong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaHDCT = itemView.findViewById(R.id.tvMaHDCT);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        }
    }
}