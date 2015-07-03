package org.sopt.appjam.went.Communication;

/**
 * Created by NOEP on 15. 7. 1..
 */

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 * Application ��ü�� �����ϰ� ���ִ� Class�Դϴ�.
 *
 * 4���� ������Ʈ(Activity, CP, BR, Service)���� ���� App�� ����� �� �����̵˴ϴ�.
 */

public class AppController extends Application {


    /**
     * ���� �� AppController ��ü�� ����Ű�� instance��� ������
     *
     * �ν��Ͻ��� �������� ���� getInstance() �޼ҵ� ������
     *
     */

    private static AppController instance;

    //private static final String ENDPOINT = "http://127.0.0.1:8000";
    private  static final String ENDPOINT = "https://apis.daum.net";

    public static AppController getInstance(){return instance;}


    /**
     * App�� ������ �� ����Ǵ� onCreate() �޼ҵ�
     */
    public void onCreate(){

        super.onCreate();

        AppController.instance = this;
        AppController.instance.init();

    }


    /**
     * NetworkService�� ����Ű�� networkService��� ������
     *
     * �ν��Ͻ��� �������� ���� getNetworkSercie() �޼ҵ� ����
     */
    private NetworkService networkService;
    public NetworkService getNetworkService() {return networkService;}


    /**
     * ��Ʈ��ŷ�� ���� NetworkService ��ü�� ����� �����Դϴ�.
     *
     * RestAdapter�� Builder�� ���� ������ Reference ����
     *
     * Builder�� RestAdapter�� ���� ��� �������� ����� ��ü�̸�
     *
     * �������� builder.build()�� ���ؼ� adapter ��ü�� �����
     *
     * NetworkService�� ����͸� ���ؼ� ����ϴ�.
     */
    private void init(){

        /**
         * ��Ű ������ �����ϱ� ���� CookieManger�� Http ó�� ���̺귯���� OkHttp�� �̿��ؼ� ��Ű�� ����� �մϴ�.
         */

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        OkHttpClient client = new OkHttpClient();
        client.setCookieHandler(cookieManager);

        RestAdapter.Builder builder = new RestAdapter.Builder();
       // builder.setEndpoint("https://apis.daum.net");
        builder.setEndpoint(ENDPOINT);

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {

                //��Ʈ��ũ�� ��û�ϱ� ���� ������ ó���� ���⼭ ���� �� �ִٰ� �մϴ� ����� ���

                request.addQueryParam("apikey", NetworkService.API_KEY);

            }
        });

        builder.setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS);
        builder.setClient(new OkClient(client));

        RestAdapter adapter = builder.build();
        networkService = adapter.create(NetworkService.class);

    }


}
