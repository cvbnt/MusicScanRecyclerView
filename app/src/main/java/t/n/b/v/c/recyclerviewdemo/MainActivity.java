package t.n.b.v.c.recyclerviewdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private MusicAdapter mAdapter;
    public static List<Music> list;
    private RecyclerView mRecyclerView;
    private Button mButton;
    private static final int REQUEST_STORAGE_PERMISSIONS=0;
    private static final String[] STORAGE_PERMISSIONS=new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        mContext=this;
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()){
                    list=Scan.getMusicData(mContext);
                    mAdapter=new MusicAdapter(mContext,list);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                }else {
                    requestPermissions(STORAGE_PERMISSIONS,REQUEST_STORAGE_PERMISSIONS);
                }
            }
        });

    }
    private void initview() {
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mButton=(Button)findViewById(R.id.button);
    }

    private boolean checkPermissions(){
        int result= ContextCompat.checkSelfPermission(MainActivity.this,STORAGE_PERMISSIONS[0]);
        return result== PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case REQUEST_STORAGE_PERMISSIONS:
                if(checkPermissions()){
                    list=Scan.getMusicData(MainActivity.this);
                }
            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }
}
