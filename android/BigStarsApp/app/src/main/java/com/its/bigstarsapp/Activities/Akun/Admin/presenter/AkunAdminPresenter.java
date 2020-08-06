package com.its.bigstarsapp.Activities.Akun.Admin.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Akun.Admin.view.IAkunAdminView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AkunAdminPresenter implements IAkunAdminPresenter {

    Context context;
    IAkunAdminView akunAdminView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public AkunAdminPresenter(Context context, IAkunAdminView akunAdminView) {
        this.context = context;
        this.akunAdminView = akunAdminView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadData(String id_admin) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "akun/admin/get_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA, response -> {
            try {
                JSONObject obj = new JSONObject(response);

                String success = obj.getString("success");
                String message = obj.getString("message");

                if (success.equals("1")) {
                    JSONArray dataArray = obj.getJSONArray("data_result");
                    for (int i = 0; i < dataArray.length(); i++) {

                        JSONObject dataobj = dataArray.getJSONObject(i);

                        String nama = dataobj.getString("nama");
                        String username = dataobj.getString("username");
                        String foto = dataobj.getString("foto");

                        akunAdminView.setNilaiDefault(
                                "" + nama,
                                "" + username,
                                "" + foto);
                    }
                } else {
                    globalProcess.onErrorMessage(message);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                globalProcess.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
            }
        }, error -> globalProcess.onErrorMessage(globalMessage.getMessageConnectionError())) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_admin", id_admin);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onUpdate(String id_admin, String nama, String username, String password, String foto) {

    }
}
