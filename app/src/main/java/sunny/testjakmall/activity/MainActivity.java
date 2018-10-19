package sunny.testjakmall.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sunny.testjakmall.R;
import sunny.testjakmall.network.model.Value;

public class MainActivity extends AppCompatActivity implements MainContract.mainView {

    TestAdapter testAdapter;
    static Dialog dialog;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swRefresh;
    ProgressBar progressBar;
    TextView tv;
    MainContract.mainPresent present;
    int cek = 1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        pDialog();
        initRV();
        run(1);
    }

    @Override
    public void init() {
        rv = findViewById(R.id.rv_test);
        tv = findViewById(R.id.tv_test);
        swRefresh = findViewById(R.id.sw_refresh);
        progressBar = findViewById(R.id.progressBar);

        String title = "TEST JAKMALL";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.RED), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);

        present = new MainPresent(this);
        testAdapter = new TestAdapter(getApplicationContext(), new ArrayList<Value>(0));

        SpannableString content = new SpannableString("+ add more data");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv.setText(content);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "+1 item", Toast.LENGTH_SHORT).show();
                cek++;
                run(cek);
            }
        });

        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                run(1);
                if(tv.getVisibility() == View.GONE){
                    tv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog.show();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDialog(Context ctx) {
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        progressBar.setVisibility(View.GONE);
        swRefresh.setRefreshing(false);
    }

    @Override
    public void run(int tipe) {
        progressDialog.show();
        present.getData(this, testAdapter, tipe);
        if (tipe == 3) {
            cek = 0;
            tv.setVisibility(View.GONE);
        }
        progressDialog.dismiss();
    }

    public void pDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
    }

    public void initRV() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(testAdapter);
        rv.setHasFixedSize(true);
    }

}
