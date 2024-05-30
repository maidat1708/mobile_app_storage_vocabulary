package com.example.btlmobile.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btlmobile.Notification;
import com.example.btlmobile.R;
import com.example.btlmobile.api.ApiService;
import com.example.btlmobile.model.Vocabulary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int NOTIFICATION_ID = 2;
    private Bitmap bitmap;
    private Spinner sp;
    private ImageView img_upload;
    private EditText edit_text_word, edit_text_meaning, edit_text_accent, edit_text_sentence;
    private Button btn_add, btn_cancel;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                img_upload.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
        btn_add.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        img_upload.setOnClickListener(this);
    }

    private void initView() {
        sp = findViewById(R.id.spinner_add);
        edit_text_word = findViewById(R.id.edit_text_word);
        edit_text_meaning = findViewById(R.id.edit_text_meaning);
        btn_add = findViewById(R.id.btn_add);
        btn_cancel = findViewById(R.id.btn_cancel);
        edit_text_accent = findViewById(R.id.edit_text_accent);
        edit_text_sentence = findViewById(R.id.edit_text_sentence);
        img_upload = findViewById(R.id.img_upload);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.type)));
    }

    @Override
    public void onClick(View view) {
        if (view == btn_cancel) {
            sendNotification();
            finish();
        }
        if (view == btn_add) {
            String base64Image = "";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] bytes = baos.toByteArray();
                base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
            }
            Vocabulary vocabulary = new Vocabulary(0, edit_text_word.getText().toString()
                    , edit_text_meaning.getText().toString()
                    , sp.getSelectedItem().toString()
                    , edit_text_accent.getText().toString()
                    , base64Image
                    , edit_text_sentence.getText().toString());
            if (vocabulary.getWord().isEmpty() || vocabulary.getMeaning().isEmpty()) {
                Toast.makeText(this, "Data can't empty", Toast.LENGTH_SHORT).show();
                edit_text_word.setText("");
                edit_text_meaning.setText("");
                img_upload.setImageResource(R.drawable.upload);
                edit_text_accent.setText("");
                edit_text_sentence.setText("");
            } else {
                sendNotification();
                callApiAddVocab(vocabulary);
            }
        }
        if (view == img_upload) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intent);
        }
    }

    private void callApiAddVocab(Vocabulary vocabulary) {
        mProgressDialog.show();
        ApiService.apiService.addVocabulary(vocabulary).enqueue(new Callback<Vocabulary>() {
            @Override
            public void onResponse(Call<Vocabulary> call, Response<Vocabulary> response) {
                Toast.makeText(AddActivity.this, "Add Vocabulary Success", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<Vocabulary> call, Throwable throwable) {
                Toast.makeText(AddActivity.this, "Add Vocabulary Error", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
                finish();
            }
        });
    }

    private void sendNotification() {
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.add_notification);
        android.app.Notification notification = new NotificationCompat.Builder(this, Notification.CHANNEL_ID)
                .setContentTitle("Notification add")
                .setContentText("Add vocabulary success")
                .setSmallIcon(R.drawable.icon_smaill_notification)
                .setLargeIcon(bitmap2).build();
        NotificationManagerCompat
                managerCompat = NotificationManagerCompat.from(getApplicationContext());
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
            return;
        managerCompat.notify(NOTIFICATION_ID, notification);
    }
//    private void createNotificationChannel(String mess) {
//        // Tạo NotificationChannel chỉ trên API 26+ vì lớp NotificationChannel mới và không có trong thư viện hỗ trợ
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "Thong bao"; // Sửa context thành context của lớp KhoanChiControl
//            String description = mess; // Sửa context thành context của lớp KhoanChiControl
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//
//            channel.setDescription(description);
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE); // Sửa MainActivity.this thành context của lớp KhoanChiControl
//
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        // Tạo Bitmap từ tập tin hình ảnh drawable
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher); // Sửa getResources() thành context.getResources()
//
//        // Xây dựng thông báo
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setContentTitle("Thong bao")
//                .setContentText("Them khoan chi thanh cong")
//                .setColor(Color.RED)
//                .setDefaults(NotificationCompat.DEFAULT_SOUND)
//                .setLargeIcon(bitmap)
//                .setSmallIcon(R.drawable.ic_trending_down_white_24dp)
//                .setCategory(NotificationCompat.CATEGORY_ALARM)
//                .setAutoCancel(true);
//
//        // Hiển thị thông báo
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
//        managerCompat.notify(NOTIFICATION_ID, builder.build()); // Sửa notificationid thành NOTIFICATION_ID
//    }
}