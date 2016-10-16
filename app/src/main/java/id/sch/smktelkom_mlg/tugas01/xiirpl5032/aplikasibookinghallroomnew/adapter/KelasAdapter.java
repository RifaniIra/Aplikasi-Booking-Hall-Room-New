package id.sch.smktelkom_mlg.tugas01.xiirpl5032.aplikasibookinghallroomnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.tugas01.xiirpl5032.aplikasibookinghallroomnew.R;

/**
 * Created by SATELLITE on 16/10/2016.
 */
public class KelasAdapter extends ArrayAdapter<String> {

    String mJenis = "";

    public KelasAdapter(Context context, ArrayList<String> listJenis) {

        super(context, R.layout.row_spinner_kelas, listJenis);
    }

    public void setJenis(String Jenis) {
        this.mJenis = Jenis;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return getCustomView(position, view, parent);
    }

    private View getCustomView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.row_spinner_kelas, parent, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.textViewTitle);
        tvTitle.setText(getItem(position).substring(0,1));
        TextView tvKelas = (TextView) view.findViewById(R.id.textViewKelas);
        tvKelas.setText(getItem(position));
        TextView tvJenis = (TextView) view.findViewById(R.id.textViewJenis);
        tvJenis.setText(mJenis);

        return  view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        return getCustomView(position, view, parent);
    }
}
