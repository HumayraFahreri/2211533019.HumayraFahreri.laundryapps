package humayrafahreri.laundryapps.Layanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.UUID;

import humayrafahreri.laundryapps.Pelanggan.PelangganActivity;
import humayrafahreri.laundryapps.R;
import humayrafahreri.laundryapps.database.SQLiteHelper;
import humayrafahreri.laundryapps.model.ModelLayanan;
import humayrafahreri.laundryapps.model.ModelPelanggan;

public class LayananAddActivity extends AppCompatActivity {
    EditText layanan, harga;
    Button btnSimpan, btnBatal;

    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addlayanan);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layanan = (EditText) findViewById(R.id.edLayAddLayanan);
        harga = (EditText) findViewById(R.id.edLayAddHarga);
        btnSimpan = (Button) findViewById(R.id.btnLayAddSimpan);
        btnBatal = (Button) findViewById(R.id.btnLayAddBatal);

        db = new SQLiteHelper(humayrafahreri.laundryapps.Layanan.LayananAddActivity.this);

        btnSimpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ModelLayanan layananModel = new ModelLayanan();
                String uniqueID = UUID.randomUUID().toString();
                layananModel.setId("" + uniqueID);
                layananModel.setNamaLayanan(layanan.getText().toString());
                layananModel.setHarga(harga.getText().toString());

                Toast.makeText(humayrafahreri.laundryapps.Layanan.LayananAddActivity.this,
                        "ID: " + layananModel.getId() +
                                " Nama Layanan: " + layananModel.getNamaLayanan() +
                                " Harga = Rp. " + layananModel.getHarga(),
                        Toast.LENGTH_SHORT).show();

                boolean cek = db.insertLayanan(layananModel);
                if (cek) {
                    Toast.makeText(humayrafahreri.laundryapps.Layanan.LayananAddActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(humayrafahreri.laundryapps.Layanan.LayananAddActivity.this, LayananActivity.class));
                    finish();
                } else {
                    Toast.makeText(humayrafahreri.laundryapps.Layanan.LayananAddActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
