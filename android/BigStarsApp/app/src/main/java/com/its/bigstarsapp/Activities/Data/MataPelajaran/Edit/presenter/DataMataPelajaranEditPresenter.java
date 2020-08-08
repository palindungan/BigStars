package com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit.view.IDataMataPelajaranEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataMataPelajaranEditPresenter implements IDataMataPelajaranEditPresenter {

    Context context;
    IDataMataPelajaranEditView dataMataPelajaranEditView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public DataMataPelajaranEditPresenter(Context context, IDataMataPelajaranEditView dataMataPelajaranEditView) {
        this.context = context;
        this.dataMataPelajaranEditView = dataMataPelajaranEditView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onUpdate(String id_mata_pelajaran, String nama) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/mata_pelajaran/update_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {
                            globalProcess.onSuccessMessage(message);
                            dataMataPelajaranEditView.backPressed();
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
                params.put("id_mata_pelajaran", id_mata_pelajaran);
                params.put("nama", nama);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
