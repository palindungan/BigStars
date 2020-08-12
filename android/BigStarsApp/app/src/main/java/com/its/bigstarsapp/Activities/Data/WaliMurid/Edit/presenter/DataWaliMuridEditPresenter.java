package com.its.bigstarsapp.Activities.Data.WaliMurid.Edit.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.WaliMurid.Edit.view.IDataWaliMuridEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataWaliMuridEditPresenter implements IDataWaliMuridEditPresenter {

    Context context;
    IDataWaliMuridEditView dataWaliMuridEditView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public DataWaliMuridEditPresenter(Context context, IDataWaliMuridEditView dataWaliMuridEditView) {
        this.context = context;
        this.dataWaliMuridEditView = dataWaliMuridEditView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onUpdate(String id_wali_murid, String nama, String username, String password, String alamat, String no_hp) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/wali_murid/update_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {
                            globalProcess.onSuccessMessage(message);
                            dataWaliMuridEditView.backPressed();
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
                params.put("id_wali_murid", id_wali_murid);
                params.put("nama", nama);
                params.put("username", username);
                params.put("password", password);
                params.put("alamat", alamat);
                params.put("no_hp", no_hp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
