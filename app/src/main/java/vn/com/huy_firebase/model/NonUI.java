package vn.com.huy_firebase.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class NonUI {
    Context context;

    public NonUI(Context context){this.context = context;}

    public void toast(final  String text){

        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable(){
            public void run(){
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
