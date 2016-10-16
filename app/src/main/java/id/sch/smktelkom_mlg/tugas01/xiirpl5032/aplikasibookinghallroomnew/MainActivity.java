package id.sch.smktelkom_mlg.tugas01.xiirpl5032.aplikasibookinghallroomnew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import id.sch.smktelkom_mlg.tugas01.xiirpl5032.aplikasibookinghallroomnew.adapter.KelasAdapter;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etNama;
    EditText etNomor;
    Button bOK;
    RadioGroup rgJumlah;
    TextView tvHasil, tvFata;
    CheckBox cbMM, cbKS, cbCM, cbEA, cbSL;
    int nFata;
    Spinner spJenis, spKelas;
    String[][] arKelas = {{"A (class A)","B (class B)","C (class C)"},
            {"A1","A2","B1","B2"},{"A3","A4","B3","B4"},{"C1","C2","C3","C4"}};
    ArrayList<String> listKelas = new ArrayList<>();
    KelasAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etNomor = (EditText) findViewById(R.id.editTextNomor);
        bOK = (Button) findViewById(R.id.buttonOK);
        rgJumlah = (RadioGroup) findViewById(R.id.radioGroupJumlah);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        cbMM = (CheckBox) findViewById(R.id.checkBoxMM);
        cbKS = (CheckBox) findViewById(R.id.checkBoxKS);
        cbCM = (CheckBox) findViewById(R.id.checkBoxCM);
        cbEA = (CheckBox) findViewById(R.id.checkBoxEA);
        cbSL = (CheckBox) findViewById(R.id.checkBoxSL);

        tvFata = (TextView) findViewById(R.id.textViewFata);

        spJenis = (Spinner) findViewById(R.id.spinnerJenis);
        spKelas = (Spinner) findViewById((R.id.spinnerKelas));
        adapter = new KelasAdapter(this,listKelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKelas.setAdapter(adapter);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doProcess();

            }
        });

        spJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                          {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
                                              {
                                                  listKelas.clear();
                                                  listKelas.addAll(Arrays.asList(arKelas[pos]));
                                                  adapter.setJenis((String)spJenis.getItemAtPosition(pos));
                                                  adapter.notifyDataSetChanged();
                                                  spKelas.setSelection(0);
                                              }
                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView)
                                              {

                                              }
                                          }
        );

        cbMM.setOnCheckedChangeListener(this);
        cbKS.setOnCheckedChangeListener(this);
        cbCM.setOnCheckedChangeListener(this);
        cbEA.setOnCheckedChangeListener(this);
        cbSL.setOnCheckedChangeListener(this);
    }

    private void doProcess() {
        String hasil = null;
        if(rgJumlah.getCheckedRadioButtonId()!=-1)
        {
            RadioButton rb = (RadioButton)
                    findViewById(rgJumlah.getCheckedRadioButtonId());
            hasil = rb.getText().toString();
        }
        if(hasil == null)
        {
            hasil = "Anda Belum Memilih Jumlah Orang";
        }

        String hasil2 ="Fasilitas Tambahan yang dipilih :\n";
        int startlen =hasil2.length();
        if(cbMM.isChecked()) hasil2+=cbMM.getText()+"\n";
        if(cbKS.isChecked()) hasil2+=cbKS.getText()+"\n";
        if(cbCM.isChecked()) hasil2+=cbCM.getText()+"\n";
        if(cbEA.isChecked()) hasil2+=cbEA.getText()+"\n";
        if(cbSL.isChecked()) hasil2+=cbSL.getText()+"\n";

        if(hasil2.length()==startlen) hasil2+="Tidak Ada pada Pilihan";


        if(isValid()) {
            String nama = etNama.getText().toString();
            String nomor = etNomor.getText().toString();
            tvHasil.setText("Nama            : " + nama +  "\n\n" +
                    "No. KTP        : " + nomor + "\n\n" +
                    "Jumlah          : " + hasil + "\n\n" +
                    hasil2 + "\n" +
                    "Anda memilih jenis ruang : " + spJenis.getSelectedItem().toString()
                    + " dan Kelas (Nomor) Ruang"  + spKelas.getSelectedItem().toString()+ "\n\n" +
                    "Terimkasih telah memesan");

        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String nomor = etNomor.getText().toString();

        if(nama.isEmpty())
        {
            etNama.setError("Nama Belum Diisi");
            valid = false;
        }

        else if(nama.length()<3)
        {
            etNama.setError("Nama Minimal 3 Karakter");
            valid = false;
        }
        else
        {
            etNama.setError(null);
        }
        if(nomor.isEmpty())
        {
            etNomor.setError("Nomor KTP Belum Diisi");
            valid = false;
        }

        else if(nomor.length()!=16)
        {
            etNomor.setError("Jumlah nomor KTP tidak benar");
            valid = false;
        }
        else {
            etNomor.setError(null);
        }
        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) nFata+=1;
        else nFata-=1;
        tvFata.setText("Fasilitas Tambahan ("+nFata+" terpilih)");
    }

}
