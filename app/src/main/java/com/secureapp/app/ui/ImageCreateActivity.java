package com.secureapp.app.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.secureapp.app.R;

public class ImageCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_create);
        initUI();
        checkPermissions();
    }

    private void initUI() {
        findViewById(R.id.btnRefresh).setOnClickListener(v -> Toast.makeText(this, "تحديث", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnPlus).setOnClickListener(v -> Toast.makeText(this, "اشترك في Plus", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnMenu).setOnClickListener(v -> Toast.makeText(this, "القائمة", Toast.LENGTH_SHORT).show());
        findViewById(R.id.actionCreateImage).setOnClickListener(v -> Toast.makeText(this, "إنشاء صورة", Toast.LENGTH_SHORT).show());
        findViewById(R.id.actionWriteEdit).setOnClickListener(v -> Toast.makeText(this, "الكتابة أو التحرير", Toast.LENGTH_SHORT).show());
        findViewById(R.id.actionSearch).setOnClickListener(v -> Toast.makeText(this, "البحث", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnVoice).setOnClickListener(v -> Toast.makeText(this, "صوت", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnMic).setOnClickListener(v -> Toast.makeText(this, "ميكروفون", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnAdd).setOnClickListener(v -> Toast.makeText(this, "إضافة", Toast.LENGTH_SHORT).show());
    }

    private void checkPermissions() {
        String[] permissions = { 
            Manifest.permission.CAMERA, 
            Manifest.permission.READ_EXTERNAL_STORAGE, 
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
        };

        boolean allGranted = true;
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) { 
                allGranted = false; 
                break; 
            }
        }
        if (!allGranted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, 200);
        }
    }
}
