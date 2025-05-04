package com.example.listevents.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.listevents.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewDate;
    private TextView textViewPlace;
    private TextView textViewDesc;
    private ImageView imageViewProfile;
    private ColorDrawable placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_details);

        textViewName = findViewById(R.id.textViewEventName);
        textViewDate = findViewById(R.id.textViewEventDate);
        textViewPlace = findViewById(R.id.textViewEventPlace);
        textViewDesc = findViewById(R.id.textViewEventDesc);
        imageViewProfile = findViewById(R.id.imageViewEventProfile);

        String name =  getIntent().getStringExtra("name");
        String date =  getIntent().getStringExtra("date");
        String place = getIntent().getStringExtra("place");
        String desc =  getIntent().getStringExtra("desc");
        String image = getIntent().getStringExtra("image");
        placeholder = new ColorDrawable(Color.GRAY);

        textViewName.setText(name);
        textViewDate.setText(date);
        textViewPlace.setText(place);
        textViewDesc.setText(desc);

        int drawableId = this.getResources().getIdentifier(
                image,
                "drawable",
                this.getPackageName()
        );

        Glide
                .with(this)
                .load(drawableId)
                .apply(new RequestOptions()
                        .centerCrop()
                        .fitCenter()
                        .placeholder(placeholder)
                )
                .into(imageViewProfile);

    }
}