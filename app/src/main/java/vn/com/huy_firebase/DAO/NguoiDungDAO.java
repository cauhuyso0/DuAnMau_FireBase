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

import vn.com.huy_firebase.fragment.NguoiDungFragment;
import vn.com.huy_firebase.model.NguoiDung;
import vn.com.huy_firebase.model.NonUI;

public class NguoiDungDAO {
    private DatabaseReference mDatabase;

    NonUI nonUI;
    Context context;
    String key;
    NguoiDungFragment fr;

    public NguoiDungDAO(Context context){
        this.mDatabase = FirebaseDatabase.getInstance().getReference("NguoiDung");
        this.context = context;
        this.nonUI = new NonUI(context);
    }

    public NguoiDungDAO(Context context, NguoiDungFragment fr){
        this.mDatabase = FirebaseDatabase.getInstance().getReference("NguoiDung");
        this.context = context;
        this.nonUI = new NonUI(context);
        this.fr = fr;
    }

    public List<NguoiDung> getAll(){
        final List<NguoiDung> list = new ArrayList<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    NguoiDung item = data.getValue(NguoiDung.class);

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

    public void insert(NguoiDung item){

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

    public void update(final NguoiDung item){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data: dataSnapshot.getChildren()){
                    if (data.child("userName").getValue(String.class).equals(item.getUserName())) {

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

    public void delete(final NguoiDung item){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data: dataSnapshot.getChildren()){

                    if (data.child("userName").getValue(String.class).equalsIgnoreCase(item.getUserName())){
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
