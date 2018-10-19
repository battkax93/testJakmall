package sunny.testjakmall.activity;

import android.content.Context;

/**
 * Created by Wayan-MECS on 10/18/2018.
 */

public interface MainContract {

    interface mainView{
        void init();
        void showProgress();
        void showDialog(Context ctx);
        void hideProgress();
        void run(int type);
    }

    interface mainPresent{
        void getData(Context ctx, TestAdapter adapter, int tipe);
        void addNewData();
    }

}
