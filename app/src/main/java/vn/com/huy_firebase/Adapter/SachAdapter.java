package vn.com.huy_firebase.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.com.huy_firebase.DAO.SachDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.fragment.SachFragment;
import vn.com.huy_firebase.model.LoaiSach;
import vn.com.huy_firebase.model.Sach;

import static vn.com.huy_firebase.fragment.LoaiSachFragment.listLoaiSachs;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {

    static  Context context;
    List<Sach> ds;
    SachFragment fr;

    public SachAdapter(Context context, List<Sach> ds, SachFragment fr){
        this.context = context;
        this.ds = ds;
        this.fr = fr;
    }

    @NonNull
    @Override
    public SachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.card_view_sach, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, final int position) {
        final Sach sach = ds.get(position);

        holder.tvMaSach.setText("Mã Sách: "+sach.getMaSach()+"");
        holder.tvMaTheLoai.setText("Mã Loại Sách: "+sach.getMaTheLoai());
        holder.tvTenSach.setText("Tên Sách: "+sach.getTenSach());
        holder.tvTacGia.setText("baoVeASM: " + sach.getTacGia());
        holder.tvNXB.setText("NXB: "+sach.getNXB());
        holder.tvGiaBan.setText("Giá Bán: "+sach.getGiaBia()+"");
        holder.tvSoLuong.setText("Số Lượng: "+sach.getSoLuong());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               View view = LayoutInflater.from(context).inflate(R.layout.them_sach, null, false);
                final EditText edtMaSach = view.findViewById(R.id.edtMaSach);
                final Spinner spnMaLS = view.findViewById(R.id.spnMaLS);
                final EditText edtTenSach = view.findViewById(R.id.edtTenSach);
                final EditText edtNXB = view.findViewById(R.id.edtNXB);
                final EditText edtTacGia = view.findViewById(R.id.edtTacGia);
                final EditText edtSoLuong = view.findViewById(R.id.edtSoLuong);
                final EditText edtGiaBan = view.findViewById(R.id.edtGiaBan);

                List list1 = new ArrayList();

                for (int i = 0; i < listLoaiSachs.size(); i++){
                    LoaiSach loaiSach = listLoaiSachs.get(i);
                    list1.add(loaiSach.getMaTheLoai());
                }

                ArrayAdapter arrayAdapter =new ArrayAdapter(context, android.R.layout.simple_spinner_item, list1);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMaLS.setAdapter(arrayAdapter);

                edtMaSach.setText(sach.getMaSach());
                edtMaSach.setEnabled(false);
                edtMaSach.setFocusable(false);
                builder.setTitle("Cập nhật sách")
                        .setView(view)
                        .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String maSach = edtMaSach.getText().toString();
                                String tenSach = edtTenSach.getText().toString();
                                String nXB = edtNXB.getText().toString();
                                String maLS = spnMaLS.getSelectedItem().toString();
                                String tacGia = edtTacGia.getText().toString();
                                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());
                                double giaBan = Double.parseDouble(edtGiaBan.getText().toString());

                                Sach sach1 = new Sach(maSach,maLS,tenSach,tacGia, nXB, giaBan, soLuong);
                                SachDAO sachDAO = new SachDAO(context);
                                sachDAO.update(sach1);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn muốn xóa "+ sach.getTenSach())
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SachDAO sachDAO = new SachDAO(context);
                                sachDAO.delete(ds.get(position));
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

    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMaSach, tvMaTheLoai, tvTenSach, tvTacGia, tvNXB, tvGiaBan,tvSoLuong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvMaTheLoai = itemView.findViewById(R.id.tvMaTheLoai);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvTacGia = itemView.findViewById(R.id.tvTacGia);
            tvNXB = itemView.findViewById(R.id.tvNXB);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);

        }
    }
}
