package humayrafahreri.laundryapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import humayrafahreri.laundryapps.Pelanggan.PelangganActivity;

public class MainActivity extends AppCompatActivity {

    String username;
    CardView cv_laundry, cv_layanan, cv_pelanggan, cv_promo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = getIntent().getStringExtra("username");
        Toast.makeText(this, " " + username, Toast.LENGTH_SHORT).show();

        cv_laundry=(CardView) findViewById(R.id.cv_laundry);
        cv_layanan=(CardView) findViewById(R.id.cv_layanan);
        cv_pelanggan=(CardView) findViewById(R.id.cv_pelanggan);
        cv_promo=(CardView) findViewById(R.id.cv_promo);

        cv_laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "LAUNDRY", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LaundryActivity.class);
                startActivity(intent);
            }
        });

        cv_layanan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, LayananActivity.class);
                startActivity(intent);
            }
        });

        cv_pelanggan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, PelangganActivity.class);
                startActivity(intent);
            }
        });

        cv_promo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, PromoActivity.class);
                startActivity(intent);
            }
        });

    }
}