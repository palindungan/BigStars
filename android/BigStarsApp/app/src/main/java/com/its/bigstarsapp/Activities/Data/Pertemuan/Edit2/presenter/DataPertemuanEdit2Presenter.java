package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.view.IDataPertemuanEdit2View;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataPertemuanEdit2Presenter implements IDataPertemuanEdit2Presenter {
    Context context;
    IDataPertemuanEdit2View dataPertemuanEdit2View;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public DataPertemuanEdit2Presenter(Context context, IDataPertemuanEdit2View dataPertemuanEdit2View) {
        this.context = context;
        this.dataPertemuanEdit2View = dataPertemuanEdit2View;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onFinish(String id_pertemuan, String deskripsi, String lokasi_berakhir_la, String lokasi_berakhir_lo) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "absensi/pertemuan/finish_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {
                            globalProcess.onSuccessMessage(message);
                            dataPertemuanEdit2View.goToHomePengajarScreen();
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
                params.put("id_pertemuan", id_pertemuan);
                params.put("deskripsi", deskripsi);
                params.put("lokasi_berakhir_la", lokasi_berakhir_la);
                params.put("lokasi_berakhir_lo", lokasi_berakhir_lo);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
