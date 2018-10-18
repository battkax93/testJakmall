package sunny.testjakmall.network.remote;


import sunny.testjakmall.Global;

public class ApiUtils {

    private ApiUtils() {}

    //set baseUrl & time out here
    public static SOService getSOService() {
        return RetrofitClient.getClient(Global.BASE_URL_STACK, Global.timeout).create(SOService.class);
    }
}
