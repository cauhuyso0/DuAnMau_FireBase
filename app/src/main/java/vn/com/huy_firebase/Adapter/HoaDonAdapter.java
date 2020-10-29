package vn.com.huy_firebase.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.com.huy_firebase.DAO.HoaDonChiTietDAO;
import vn.com.huy_firebase.DAO.HoaDonDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.fragment.HoaDonFragment;
import vn.com.huy_firebase.model.HoaDon;
import vn.com.huy_firebase.model.HoaDonChiTiet;
import vn.com.huy_firebase.model.NguoiDung;

import static vn.com.huy_firebase.Activity.Main2Activity.listHDCTs;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    static Context context;
    HoaDonChiTietDAO chiTietDAO;
    LinearLayoutManager mLayoutManager;
    HoaDonChiTietAdapter adapter;
    HoaDonChiTiet hoaDonChiTiet;
    int co = 0;
    HoaDon hoaDon1;
    List<HoaDon> ds;
    HoaDonFragment fr;

    public HoaDonAdapter(Context context, List<HoaDon> ds, HoaDonFragment fr){
        this.context = context;
        this.ds = ds;
        this.fr = fr;
    }

    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.card_view_hoa_don, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HoaDonAdapter.ViewHolder holder, final int position) {

        final HoaDon hoaDon = ds.get(position);
        holder.tvMaHD.setText(hoaDon.getMaHoaDon());
        holder.tvNgay.setText("Ngày lập: "+hoaDon.getNgayMua());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view =LayoutInflater.from(context).inflate(R.layout.listview_hdct, null, false);
                ListView lv = view.findViewById(R.id.lvHDCT);

                List list = new ArrayList();
                for (int i = 0; i < listHDCTs.size(); i++){
                    hoaDonChiTiet = listHDCTs.get(i);
                   if (hoaDonChiTiet.getMaHDCT().substring(0,4).equals(hoaDon.getMaHoaDon())){


                       list.add(hoaDonChiTiet);
                   }

                }
                ListViewAdapter adapter = new ListViewAdapter(context, list);
                lv.setAdapter(adapter);
                builder.setView(view);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMaHD, tvNgay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           tvMaHD = itemView.findViewById(R.id.tvMaHD);
           tvNgay = itemView.findViewById(R.id.tvNgay);
        }
    }
}