package vn.com.huy_firebase.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.com.huy_firebase.DAO.LoaiSachDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.fragment.LoaiSachFragment;
import vn.com.huy_firebase.model.LoaiSach;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {

    static  Context context;
    List<LoaiSach> ds;
    LoaiSachFragment fr;

    public LoaiSachAdapter(Context context, List<LoaiSach> ds, LoaiSachFragment fr){
        this.context = context;
        this.ds = ds;
        this.fr = fr;
    }

    @NonNull
    @Override
    public LoaiSachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.card_view_loai_sach, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachAdapter.ViewHolder holder, final int position) {
        final LoaiSach loaiSach = ds.get(position);
        holder.tvMaTheLoai.setText("Mã Loại Sách: "+loaiSach.getMaTheLoai());
        holder.tvTenTheLoai.setText("Tên Loại Sách: "+loaiSach.getTenTheLoai());
        holder.tvMoTa.setText("Mô tả: "+loaiSach.getMoTa());
        holder.tvViTri.setText("Vị trí: "+loaiSach.getViTri()+"");

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View view = LayoutInflater.from(context).inflate(R.layout.them_loai_sach, null, false);
                final EditText edtMaLS = view.findViewById(R.id.edtMaLS);
                final EditText edtTenLS = view.findViewById(R.id.edtTenLS);
                final EditText edtMoTa = view.findViewById(R.id.edtMoTa);
                final EditText edtViTri = view.findViewById(R.id.edtViTri);

                LoaiSach loaiSach1 = ds.get(position);
                System.out.println(ds);
                edtMaLS.setText(loaiSach1.getMaTheLoai());
                builder.setTitle("Cập nhật loại sách")
                        .setView(view)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String maLS = edtMaLS.getText().toString();
                                String tenLS = edtTenLS.getText().toString();
                                String moTa = edtMoTa.getText().toString();
                                int viTri = Integer.parseInt(edtViTri.getText().toString());
                                LoaiSach loaiSach2 = new LoaiSach(maLS,tenLS, moTa, viTri);
                                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                                loaiSachDAO.update(loaiSach2);

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
                builder.setTitle("Bạn muốn xóa "+ loaiSach.getTenTheLoai())
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                                loaiSachDAO.delete(ds.get(position));
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
        public TextView tvMaTheLoai, tvTenTheLoai, tvMoTa, tvViTri;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaTheLoai = itemView.findViewById(R.id.tvMaTheLoai);
            tvTenTheLoai = itemView.findViewById(R.id.tvTenTheLoai);
            tvMoTa = itemView.findViewById(R.id.tvMoTa);
            tvViTri = itemView.findViewById(R.id.tvViTri);
        }
    }
}
