package com.example.sipos_daniel_mobilalk;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar;
import android.widget.Button;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Torrent extends AppCompatActivity {
    private static final String LOG_TAG = Torrent.class.getName();
    private FirebaseUser user;
    private ImageView torrentImage;
    private TextView torrentText;
    private CardView imageCard;
    private RatingBar ratingBar;
    private Button downloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torrent);

        torrentImage = findViewById(R.id.torrentImage);
        torrentText = findViewById(R.id.torrentTitle);
        imageCard = findViewById(R.id.imageCard);
        ratingBar = findViewById(R.id.ratingBar);
        downloadButton = findViewById(R.id.downloadButton);

        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        imageCard.startAnimation(scaleUp);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Torrent.this, "Letöltés elkezdődött!", Toast.LENGTH_SHORT).show();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(Torrent.this, "Értékelés: " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        try {
            user = FirebaseAuth.getInstance().getCurrentUser();
            if(user != null) {
                Log.d(LOG_TAG, "Helyes felhasználó");
                torrentText.setText("Üdvözöljük " + (user.getDisplayName() != null ? user.getDisplayName() : "a Torrent alkalmazásban!"));
            } else {
                Log.d(LOG_TAG, "Helytelen felhasználó");
                torrentText.setText("Üdvözöljük a Torrent alkalmazásban!");
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Firebase hiba: " + e.getMessage());
            torrentText.setText("Üdvözöljük a Torrent alkalmazásban!");
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}