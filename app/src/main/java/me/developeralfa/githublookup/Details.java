package me.developeralfa.githublookup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    UserAsyncTask userAsyncTask;
    ImageView imageView;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent p = getIntent();
        imageView = findViewById(R.id.img);
        name = findViewById(R.id.name);
        userAsyncTask = new UserAsyncTask(new UserAsyncTask.OnDownloadListener() {
            @Override
            public void onDownload(User user) {
                name.setText(user.name);
                ImageAsyncTask imageAsyncTask = new ImageAsyncTask(new ImageAsyncTask.OnDownloadListener() {
                    @Override
                    public void onDownload(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
                imageAsyncTask.execute(user.image);

            }
        });
        userAsyncTask.execute("https://api.github.com/users/"+p.getStringExtra("text"));
    }
}
