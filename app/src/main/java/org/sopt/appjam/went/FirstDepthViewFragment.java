package org.sopt.appjam.went;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.appjam.went.Communication.AppController;
import org.sopt.appjam.went.Communication.NetworkService;
import org.sopt.appjam.went.Controller.Depth1Adapter;
import org.sopt.appjam.went.Model.ShopItem;
import org.sopt.appjam.went.Model.ShopResult;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NOEP on 15. 7. 1..
 */


//���⿡ �ִ� ��� �޼ҵ带 �������� �ű��

public class FirstDepthViewFragment extends Fragment{

    //for logging
    private static final String TAG = "FirstDepthViewFragment";


    /*
    instance statement
     */
    public static FirstDepthViewFragment newInstance() {

        FirstDepthViewFragment fragment = new FirstDepthViewFragment();
        return fragment;

    }
    /*
    Constructer with empty
     */
    public FirstDepthViewFragment() {
        //empty
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }



    /**
     * NetworkService
     * deprecated for main activity
     */
    NetworkService networkService;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstancedState) {

        View v = inflater.inflate(R.layout.fragment_first_depth_view ,container,false);

        networkService = AppController.getInstance().getNetworkService();

        initView(v);
        viewListener();
        initModel();
        initController();

        return v;
    }

    EditText editText_query;
    Button button_search;
    ListView listView_result;

    private void initView(View view) {

        editText_query = (EditText) view.findViewById(R.id.editText_query);
        button_search = (Button) view.findViewById(R.id.button_search);
        listView_result = (ListView) view.findViewById(R.id.listView_fragment_view);


    }



    /**
     * ��ư�� ���� �����ʸ� ���� ���� �����߽��ϴ�.
     */
    private void viewListener() {

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFromServer();

            }
        });

    }

    /**
     * ��(�ڷ�)�� ���� ��� �κ��Դϴ�.
     */
    ArrayList<ShopItem> arrayList_shopItem;
    ShopResult shopResult;

    private void initModel() {

        arrayList_shopItem = new ArrayList<>();

    }

    /**
     * ��Ʈ�ѷ��� ���� �κ��Դϴ�
     * <p/>
     * CustomAdapter�� ListView���ٰ� adapter ����
     */

    Depth1Adapter customAdapter;

    private void initController() {

        customAdapter = new Depth1Adapter (this.getActivity());

        listView_result.setAdapter(customAdapter);

    }


    /**
     * �����κ��� �ڷḦ �������� �޼ҵ��Դϴ�!!
     */

    private void getFromServer() {


        /**
         * EditText�� �Է��� ���� �޾ƿͼ� q�� ����!
         */

        String q = editText_query.getText().toString();

        /**
         * String q�� ���� �ִ��� Ȯ��
         */
        if (TextUtils.isEmpty(q))
            return;


        /**
         * HashMap Ŭ������ ��ü���ٰ� query�� ���� ��������
         *
         * key - value ������ �־��ݴϴ�.
         */

        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("q", q);
        parameters.put("result", "15");
        parameters.put("pageno", "1");
        parameters.put("sort", "pop");
        parameters.put("output", "json");


        /**
         * �񵿱� ó���� ��Ʈ��ŷ�� �մϴ�.
         *
         * parameter�� query�� ���� ������ �����մϴ�.(���� �ؽø�)
         */
        networkService.getDataAsync(parameters, new Callback<Object>() {


            /**
             *
             * @param o : �����ÿ� ���� ����� ���� ��ü
             * @param response : �����ÿ� ���� response��� �մϴ�
             */
            @Override
            public void success(Object o, Response response) {

                /**
                 * �Ľ��� ���� Gson ��ü�� �̿��մϴ�.
                 *
                 * �� �� ���� ��ü o �� Json���·� ���� jsonString���� ����(String ���·�).
                 */

                Gson gson = new Gson();

                String jsonString = gson.toJson(o);

                try {

                    JSONObject json = new JSONObject(jsonString);
                    // jsonString�� JSON��ü�� �����

                    json = json.getJSONObject("channel");
                    // channel �ȿ� �ִ� JSON ��ü�� �����ɴϴ�! => ���� api ���� ����

                    shopResult = gson.fromJson(json.toString(), ShopResult.class);
                    // �� �� json�� ���ڿ� ���·� �޾ƿͼ� GSON�� �̿��� shopResult�� �������ݴϴ�.

                    customAdapter.setSource((ArrayList<ShopItem>) shopResult.item);
                    // �� ������� item�� ����͸� ���ؼ� ����Ʈ���� source�� �������ݴϴ�.

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void failure(RetrofitError error) {

                Log.e("ERROR", "Error : " + error.getUrl() + ">>>>" + error.getMessage());

            }

        });


    }
}