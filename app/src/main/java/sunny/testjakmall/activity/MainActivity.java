package sunny.testjakmall.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.design.internal.BottomNavigationMenu;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

import sunny.testjakmall.R;
import sunny.testjakmall.network.model.Answer;
import sunny.testjakmall.network.model.Value;

public class MainActivity extends AppCompatActivity implements MainContract.mainView {

    TestAdapter testAdapter;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swRefresh;
    ProgressBar progressBar;
    TextView tv;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    MainContract.mainPresent present;
    int cek = 0;
    Boolean isRefreshing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initRV();
    }

    @Override
    public void init() {
        rv = findViewById(R.id.rv_test);
        tv = findViewById(R.id.tv_test);
        swRefresh = findViewById(R.id.sw_refresh);
        progressBar = findViewById(R.id.progressBar);
        present = new MainPresent(this);
        testAdapter = new TestAdapter(getApplicationContext(), new ArrayList<Value>(0));

        SpannableString content = new SpannableString("+ add more data");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv.setText(content);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                cek++;
                run(cek);
            }
        });

        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                run(1);
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        swRefresh.setRefreshing(false);
    }

    @Override
    public void run(int tipe) {
        present.getData(this,testAdapter,tipe);
        if(tipe==3){
            cek = 0;
            tv.setVisibility(View.GONE);
        }
    }

    private void initRV() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(testAdapter);
        rv.setHasFixedSize(true);
    }

    public void scrollToTop(int pos){
        Log.d("cek","clicked");
        if ( layoutManager != null) {
            rv.smoothScrollBy(pos,0);
            testAdapter.notifyDataSetChanged();
        }
    }
}
