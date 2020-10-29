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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.com.huy_firebase.fragment.HoaDonFragment;
import vn.com.huy_firebase.fragment.LoaiSachFragment;
import vn.com.huy_firebase.model.HoaDon;
import vn.com.huy_firebase.model.LoaiSach;
import vn.com.huy_firebase.model.NonUI;

public class HoaDonDAO {

    private DatabaseReference mDatabase;

    NonUI nonUI;
    Context context;
    String key;
    HoaDonFragment fr;

    public HoaDonDAO(Context context){
        this.mDatabase = FirebaseDatabase.getInstance().getReference("HoaDon");
        this.context = context;
        this.nonUI = new NonUI(context);
    }

    public HoaDonDAO(Context context, HoaDonFragment fr){
        this.mDatabase = FirebaseDatabase.getInstance().getReference("HoaDon");
        this.context = context;
        this.nonUI = new NonUI(context);
        this.fr = fr;
    }

    public List<HoaDon> getAll(){
        final List<HoaDon> list = new ArrayList<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    HoaDon item = data.getValue(HoaDon.class);

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

    public List<HoaDon> getAll1(){
        final List<HoaDon> list = new ArrayList<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    HoaDon item = data.getValue(HoaDon.class);

                    list.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                nonUI.toast("Không kết nối database");
            }
        };
        mDatabase.addValueEventListener(listener);
        return list;
    }

    public void insert(HoaDon item){

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

    public void update(final HoaDon item){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data: dataSnapshot.getChildren()){
                    if (data.child("maHoaDon").getValue(String.class).equals(item.getMaHoaDon())) {

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

    public void delete(final HoaDon item){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data: dataSnapshot.getChildren()){

                    if (data.child("maTheLoai").getValue(String.class).equalsIgnoreCase(item.getMaHoaDon())){
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
