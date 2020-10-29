package vn.com.huy_firebase.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.com.huy_firebase.fragment.LoaiSachFragment;

import vn.com.huy_firebase.model.LoaiSach;

import vn.com.huy_firebase.model.NonUI;

import static vn.com.huy_firebase.Activity.Main2Activity.loaiSaches;

public class LoaiSachDAO {
    private DatabaseReference mDatabase;

    NonUI nonUI;
    Context context;
    String key;
    LoaiSachFragment fr;

    public LoaiSachDAO(Context context){
        this.mDatabase = FirebaseDatabase.getInstance().getReference("LoaiSach");
        this.context = context;
        this.nonUI = new NonUI(context);
    }

    public LoaiSachDAO(Context context, LoaiSachFragment fr){
        this.mDatabase = FirebaseDatabase.getInstance().getReference("LoaiSach");
        this.context = context;
        this.nonUI = new NonUI(context);
        this.fr = fr;
    }


    public List<LoaiSach> getAll(){
        final List<LoaiSach> list = new ArrayList<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                   LoaiSach item = data.getValue(LoaiSach.class);

                    list.add(item);
                }
                   fr.capnhatLV();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                nonUI.toast("Không kết nối database");
            }
        };
        mDatabase.addValueEventListener(listener);
        return list;
    }

    public void insert(LoaiSach item){

        key = mDatabase.push().getKey();

        mDatabase.child(key).setValue(item)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        nonUI.toast("insert thành công");
                        Log.d("insert", "insert thành công");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                nonUI.toast("insert thất bại");
                Log.d("insert", "insert thất bại");
            }
        });
    }

    public void update(final LoaiSach item){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loaiSaches.clear();
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    if (data.child("maTheLoai").getValue(String.class).equals(item.getMaTheLoai())) {

                        key = data.getKey();

                        mDatabase.child(key).setValue(item)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        nonUI.toast("update thành công");
                                        Log.d("update","update thành công");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        nonUI.toast("update thất bại");
                                        Log.d("update", "update thất bại");
                                    }
                                });
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void delete(final LoaiSach item){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data: dataSnapshot.getChildren()){

                    if (data.child("maTheLoai").getValue(String.class).equalsIgnoreCase(item.getMaTheLoai())){
                        key = data.getKey();

                        Log.d("getKey","onCreate: key :" + key);

                        mDatabase.child(key).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        nonUI.toast("delete thành công");
                                        Log.d("delete", "delete thành công");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        nonUI.toast("delete thất bại");
                                        Log.d("delete", "delete thất bại");
                                    }
                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
