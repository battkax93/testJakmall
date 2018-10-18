package sunny.testjakmall.network.remote;


import android.content.SharedPreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sunny.testjakmall.Global;
import sunny.testjakmall.network.model.Answer;


public interface SOService {

    String url = Global.BASE_URL_STACK2;
    String url2 = Global.BASE_URL_STACK3;
    String url3 = Global.BASE_URL_STACK4;

    @GET(url)
    Call<Answer> getAnswers();

    @GET(url2)
    Call<Answer> getAnswers2();

    @GET(url3)
    Call<Answer> getAnswers3();

    @GET(url)
    Call<Answer> getMoreAnswer();
    // RxJava
    // Observable<SOAnswersResponse> getAnswers();

    @GET(url)
    Call<List<Answer>> getAnswers(@Query("tagged") String tags);


}
