package humayrafahreri.laundryapps.Pelanggan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import humayrafahreri.laundryapps.R;
import humayrafahreri.laundryapps.adapter.AdapterPelanggan;
import humayrafahreri.laundryapps.database.SQLiteHelper;
import humayrafahreri.laundryapps.model.ModelPelanggan;

public class PelangganActivity extends AppCompatActivity {
    SQLiteHelper db;
    Button btnPelAdd;
    RecyclerView rvPelanggan;
    AdapterPelanggan adapterPelanggan;
    ArrayList<ModelPelanggan> list;
    ProgressDialog progressDialog;
    AlphaAnimation btnAnimasi = new AlphaAnimation(1F,0.5F);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pelanggan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars =
                    insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                            systemBars.bottom);
                    return insets;
                });
        setView();
        eventHandling();
        getData();
    }
    private View.OnClickListener onClickListener = new
            View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(btnAnimasi);
                    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)
                            v.getTag();
                    int position = viewHolder.getAdapterPosition();
                    ModelPelanggan mp = list.get(position);
                    Intent intent = new Intent(humayrafahreri.laundryapps.Pelanggan.PelangganActivity.this,PelangganEditActivity.class);
                            intent.putExtra("ID", mp.getId());
                            intent.putExtra("NAMA", mp.getNama());
                            intent.putExtra("EMAIL", mp.getEmail());
                            intent.putExtra("HP", mp.getHp());
                            startActivity(intent);
                    Toast.makeText(PelangganActivity.this, "" +mp.getNama(),
                            Toast.LENGTH_SHORT).show();
                }
            };
    private void getData() {
        list.clear();
        showMsg();
        try {
            List<ModelPelanggan> p = db.getPelanggan();
            if (p.size() > 0){
                    list.addAll(p);  // Simplified adding all data
                    adapterPelanggan = new AdapterPelanggan(this, list);
                    rvPelanggan.setAdapter(adapterPelanggan);
                    adapterPelanggan.setOnItemClickListener(onClickListener);  // Handle click event
                    adapterPelanggan.notifyDataSetChanged();
            }else{
                Toast.makeText(this, "Data tidak ditemukan",
                        Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            progressDialog.dismiss();
        }
    }
    private void eventHandling() {
        btnPelAdd.setOnClickListener(v -> {
            v.startAnimation(btnAnimasi);
            startActivity(new Intent(PelangganActivity.this, PelangganAddActivity.class));
        });
    }
    private void setView() {
        db = new SQLiteHelper(this);
        progressDialog = new ProgressDialog(this);
        btnPelAdd = (Button) findViewById(R.id.btnPelAdd);
        rvPelanggan = (RecyclerView) findViewById(R.id.rvPelanggan);
        list = new ArrayList<>();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPelanggan.setHasFixedSize(true);
        rvPelanggan.setLayoutManager(llm);
    }
    private void showMsg() {
        progressDialog.setTitle("Informasi");
        progressDialog.setMessage("Loading Data..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData(); // Memuat ulang data setelah penghapusan atau pembaruan
    }
}
