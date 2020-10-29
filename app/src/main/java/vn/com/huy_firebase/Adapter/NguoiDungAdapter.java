package vn.com.huy_firebase.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import vn.com.huy_firebase.DAO.NguoiDungDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.fragment.NguoiDungFragment;
import vn.com.huy_firebase.model.NguoiDung;

import static vn.com.huy_firebase.Activity.Main2Activity.nguoiDungs;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.ViewHolder> {

    static Context context;
    int co = 0;
    NguoiDung nguoiDung1;
    List<NguoiDung> ds;
    NguoiDungFragment fr;

    public NguoiDungAdapter(Context context, List<NguoiDung> ds, NguoiDungFragment fr){
        this.context = context;
        this.ds = ds;
        this.fr = fr;
    }

    @NonNull
    @Override
    public NguoiDungAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.nguoi_dung_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NguoiDungAdapter.ViewHolder holder, final int position) {

        final NguoiDung nguoiDung = ds.get(position);

        holder.tvUser.setText("UserName: " + nguoiDung.getUserName());
        holder.tvTenNguoiDung.setText("Tên: " + nguoiDung.getHoTen());
        holder.tvPhone.setText("Sđt: "+nguoiDung.getPhone());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View view = LayoutInflater.from(context)
                        .inflate(R.layout.doi_mat_khau, null, false);
                builder.setTitle("Đổi Mật Khẩu")
                        .setView(view)
                        .setPositiveButton("Đổi mk", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final EditText mkHienTai = view.findViewById(R.id.edtMKHienTai);
                                final EditText mkMoi = view.findViewById(R.id.edtMKMoi);
                                final EditText mkMoi2 = view.findViewById(R.id.edtNhapLai);

                                nguoiDung1 = nguoiDungs.get(position);
                                if (mkHienTai.getText().toString().equals(nguoiDung1.getPassword()) && mkMoi.getText().toString().equals(mkMoi2.getText().toString())){

                                    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(context);
                                    String user = nguoiDung1.getUserName();
                                    String pass = mkMoi2.getText().toString();
                                    String phone = nguoiDung1.getPhone();
                                    String ten = nguoiDung1.getHoTen();
                                    NguoiDung nguoiDung2 = new NguoiDung(user,pass,phone,ten);
                                    nguoiDungDAO.update(nguoiDung2);
                                }else {
                                    Toast.makeText(context, "Nhập sai!! Mời nhập lại", Toast.LENGTH_SHORT).show();
                                }

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
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTenNguoiDung, tvPhone, tvUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUser = itemView.findViewById(R.id.tvUser);
            tvTenNguoiDung = itemView.findViewById(R.id.tvTenNguoiDung);
            tvPhone  =itemView.findViewById(R.id.tvPhone);
        }
    }
}
