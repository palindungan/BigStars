package com.its.bigstarsapp.Activities._Login.presenter;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Home.Admin.HomeAdminActivity;
import com.its.bigstarsapp.Activities.Home.Pengajar.HomePengajarActivity;
import com.its.bigstarsapp.Activities._Login.view.ILoginView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements ILoginPresenter {

    Context context;
    ILoginView loginView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public LoginPresenter(Context context, ILoginView loginView) {
        this.context = context;
        this.loginView = loginView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLogin(String username, String password, String hak_akses) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "login/masuk"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        JSONArray jsonArray = jsonObject.getJSONArray("data_result");

                        if (success.equals("1")) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String id_user = object.getString("id_user").trim();
                                String nama = object.getString("nama").trim();
                                String username_response = object.getString("username").trim();

                                Intent intent = new Intent();

                                switch (hak_akses) {
                                    case "admin":
                                        intent = new Intent(context, HomeAdminActivity.class);
                                        break;
                                    case "pengajar":
                                        intent = new Intent(context, HomePengajarActivity.class);
                                        break;
                                    case "wali_murid":
                                        break;
                                    default:
                                        sessionManager.logout();
                                        break;
                                }

                                sessionManager.setDataUser(
                                        "" + id_user,
                                        "" + nama,
                                        "" + username_response,
                                        "" + hak_akses
                                );

                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(intent);
                            }
                        } else {
                            globalProcess.onErrorMessage(message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        globalProcess.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
                    }
                },
                error -> globalProcess.onErrorMessage(globalMessage.getMessageConnectionError())) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("hak_akses", hak_akses);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
