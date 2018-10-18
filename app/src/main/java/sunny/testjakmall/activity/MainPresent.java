package sunny.testjakmall.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sunny.testjakmall.network.model.Answer;
import sunny.testjakmall.network.model.Value;
import sunny.testjakmall.network.remote.ApiUtils;
import sunny.testjakmall.network.remote.SOService;

/**
 * Created by Wayan-MECS on 10/18/2018.
 */

public class MainPresent implements MainContract.mainPresent {

    private MainContract.mainView mView;
    SOService mServices = ApiUtils.getSOService();

    public MainPresent(MainContract.mainView mView) {
        this.mView = mView;
    }

    @Override
    public void getData(final Context ctx, final TestAdapter adapter, int tipe) {
        if (tipe == 1) {
            mServices.getAnswers().enqueue(new Callback<Answer>() {
                @Override
                public void onResponse(Call<Answer> call, Response<Answer> response) {
                    mView.showProgress();
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("Status", "success" + response.body().getValue());
                        adapter.updateAnswers(response.body().getValue());
                        mView.hideProgress();
                    } else {
                        Log.d("Status", "failed");
                        mView.hideProgress();
                        Toast.makeText(ctx, "check your connection", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Answer> call, Throwable t) {
                    mView.hideProgress();
                    Toast.makeText(ctx, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (tipe == 2){
            mServices.getAnswers2().enqueue(new Callback<Answer>() {
                @Override
                public void onResponse(Call<Answer> call, Response<Answer> response) {
                    Log.d("Status", "success" + response.body().getValue());
                    adapter.updateAnswers(response.body().getValue());
                    mView.hideProgress();
                }

                @Override
                public void onFailure(Call<Answer> call, Throwable t) {
                    Log.d("Status", "failed");
                    mView.hideProgress();
                    Toast.makeText(ctx, "check your connection", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
         mServices.getAnswers3().enqueue(new Callback<Answer>() {
             @Override
             public void onResponse(Call<Answer> call, Response<Answer> response) {
                 Log.d("Status", "success" + response.body().getValue());
                 adapter.updateAnswers(response.body().getValue());
                 mView.hideProgress();
             }

             @Override
             public void onFailure(Call<Answer> call, Throwable t) {
                 Log.d("Status", "failed");
                 mView.hideProgress();
                 Toast.makeText(ctx, "check your connection", Toast.LENGTH_SHORT).show();
             }
         });
        }
    }

    @Override
    public void addNewData() {

    }
}
