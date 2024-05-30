package com.example.btlmobile.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btlmobile.R;
import com.example.btlmobile.api.ApiService;
import com.example.btlmobile.model.Vocabulary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private Bitmap bitmap;
    private ImageView img_upload;
    private EditText edit_text_word, edit_text_meaning,edit_text_accent,edit_text_sentence;
    private Button btn_update,btn_cancel,btn_delete;
    private Vocabulary vocabulary;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        Intent intent = getIntent();
        int idVocab = (int)intent.getIntExtra("idVocab",0);
        Log.e("check idVocav",String.valueOf(idVocab));
        callApiGetById(idVocab);
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                                Log.e("check butmap",bitmap.toString());
                                img_upload.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
        btn_update.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        img_upload.setOnClickListener(this);
    }

    private void initView() {
        sp = findViewById(R.id.spinner_add);
        edit_text_word = findViewById(R.id.edit_text_word);
        edit_text_meaning = findViewById(R.id.edit_text_meaning);
        edit_text_accent = findViewById(R.id.edit_text_accent);
        edit_text_sentence = findViewById(R.id.edit_text_sentence);
        img_upload = findViewById(R.id.img_upload);
        btn_update = findViewById(R.id.btn_add);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_delete = findViewById(R.id.btn_delete);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.type)));
    }

    @Override
    public void onClick(View view) {
        if(view == btn_cancel){
            finish();
        }
        if(view == btn_update){

            ByteArrayOutputStream baos =new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            byte [] b=baos.toByteArray();
            String temp = Base64.encodeToString(b, Base64.DEFAULT);

            Vocabulary vocab = new Vocabulary(vocabulary.getId(),edit_text_word.getText().toString()
                    ,edit_text_meaning.getText().toString()
                    ,sp.getSelectedItem().toString()
                    ,edit_text_accent.getText().toString()
                    ,temp
                    ,edit_text_sentence.getText().toString());
            callApiUpdateVocab(vocab);
        }
        if(view == btn_delete){
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Message delete");
            builder.setMessage("Are you sure delete word " + vocabulary.getWord() + "?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    callApiDeleteVocab(vocabulary);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if(view == img_upload){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intent);
        }
    }
    private void callApiUpdateVocab(Vocabulary vocabulary){
        mProgressDialog.show();
        ApiService.apiService.updateVocabulary(vocabulary).enqueue(new Callback<Vocabulary>() {
            @Override
            public void onResponse(Call<Vocabulary> call, Response<Vocabulary> response) {
                mProgressDialog.dismiss();
                System.out.println(response.body().toString());
                Toast.makeText(UpdateDeleteActivity.this,"Update Vocabulary Success",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Vocabulary> call, Throwable throwable) {
                mProgressDialog.dismiss();
                Toast.makeText(UpdateDeleteActivity.this,"Add Vocabulary Error",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void callApiDeleteVocab(Vocabulary vocabulary){
        mProgressDialog.show();
        ApiService.apiService.deleteVocabulary(vocabulary.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mProgressDialog.dismiss();
                System.out.println(vocabulary.toString());
                Toast.makeText(UpdateDeleteActivity.this,"Delete Vocabulary Success",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                mProgressDialog.dismiss();
                Toast.makeText(UpdateDeleteActivity.this,"Delete Vocabulary Error",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void callApiGetById(int id){
        mProgressDialog.show();
        ApiService.apiService.findById(id).enqueue(new Callback<Vocabulary>() {
            @Override
            public void onResponse(Call<Vocabulary> call, Response<Vocabulary> response) {
                vocabulary = response.body();
                System.out.println(vocabulary.toString());
                if(vocabulary.getImage() != null && !vocabulary.getImage().isEmpty()){
                    byte [] encodeByte = Base64.decode(vocabulary.getImage(),Base64.DEFAULT);
                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                    img_upload.setImageBitmap(Bitmap.createBitmap(bitmap1));
                }
                else img_upload.setImageResource(R.drawable.upload);
                edit_text_word.setText(vocabulary.getWord());
                edit_text_meaning.setText(vocabulary.getMeaning());
                edit_text_accent.setText(vocabulary.getAccent());
                edit_text_sentence.setText(vocabulary.getSentence());
                int p = 0;
                for(int i = 0 ; i < sp.getCount(); i++){
                    if(sp.getItemAtPosition(i).toString().equalsIgnoreCase(vocabulary.getType())){
                        p = i;
                        break;
                    }
                }
                sp.setSelection(p);
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Vocabulary> call, Throwable throwable) {
                mProgressDialog.dismiss();
            }
        });
    }
}