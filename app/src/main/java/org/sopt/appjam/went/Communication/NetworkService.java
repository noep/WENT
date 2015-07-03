package org.sopt.appjam.went.Communication;
/**
 * Created by NOEP on 15. 7. 1..
 */

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;


/**
 * Retrofit �����Դϴ�.
 *
 * �ڼ��� ������ Reference�� �Ʒ� ��ũ�� �����ϼ���
 *
 * http://square.github.io/retrofit/
 *
 */


public interface NetworkService {


    /**
     * �Ʒ����̴°� API_KEY�̸� ����� app�� �־�ξ����� ���Ȼ� ��ü�� ������ ���ؼ� ����Ű�� �޾ư��ϴ�
     *
     * Local Device -> Server(Get API_KEY) -> Request �̷����Դϴ�.
     */

    //TODO : API_KEY �ֱ�

    public static final String API_KEY = "4abc597591421f87535a41b5eef18c5e";
    //public static final String API_KEY = "91e048991a4e9d0779f8191a2aed49ae";

    /**
     * GET ������� ��û�� �ϸ� ����ȭ ������� �ڷḦ ��û�մϴ�.
     *
     * @param parameters : Reference�� �����ϸ� �������� Query�� �������̱� ������ QueryMap �������� �޾Ƽ� ��û!
     * @return : ������ ��ü(����)�� ��ȯ�մϴ�.
     */

    @GET("/shopping/search")
    Object getDataSync(@QueryMap HashMap<String, String> parameters);


    /**
     * GET ������� ��û�� �ϸ� �񵿱� ������� �ڷḦ ��û�մϴ�.
     *
     * �ȵ���̵忡���� ���������� �񵿱�ȭ ������� �ϵ��� �����ϸ� ����Ǿ��ٰ� �մϴ�.
     *
     * @param parameters : Reference�� �����ϸ� �������� Query�� �������̱� ������ QueryMap �������� �޾Ƽ� ��û!
     * @param callback : Object�� �������� ���� �� ����� ������ Callback���� ó���� �մϴ�.
     */


    @GET("/shopping/search")
   void getDataAsync(@QueryMap HashMap<String, String> parameters, Callback<Object> callback);

    @GET("/insert/users/{user-id}")
    void getDataAsync(@Path("user-id") long id, Callback<Object> callback);

}
