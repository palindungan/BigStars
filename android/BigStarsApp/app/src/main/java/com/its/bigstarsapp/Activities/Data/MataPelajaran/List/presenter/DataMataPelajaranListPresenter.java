package com.its.bigstarsapp.Activities.Data.MataPelajaran.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.List.view.IDataMataPelajaranListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.MataPelajaran;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataMataPelajaranListPresenter implements IDataMataPelajaranListPresenter {

    Context context;
    IDataMataPelajaranListView dataMataPelajaranListView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<MataPelajaran> dataModelArrayList;

    public DataMataPelajaranListPresenter(Context context, IDataMataPelajaranListView dataMataPelajaranListView) {
        this.context = context;
        this.dataMataPelajaranListView = dataMataPelajaranListView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadDataList() {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/mata_pelajaran/list_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, response -> {
            try {

                JSONObject obj = new JSONObject(response);

                String success = obj.getString("success");
                String message = obj.getString("message");

                if (success.equals("1")) {

                    dataModelArrayList = new ArrayList<>();
                    JSONArray dataArray = obj.getJSONArray("data_result");
                    for (int i = 0; i < dataArray.length(); i++) {

                        MataPelajaran playerModel = new MataPelajaran();
                        JSONObject dataobj = dataArray.getJSONObject(i);

                        String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                        String nama = dataobj.getString("nama");

                        playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                        playerModel.setNama(nama);

                        dataModelArrayList.add(playerModel);
                    }
                    dataMataPelajaranListView.onSetupListView(dataModelArrayList);

                } else {
                    dataModelArrayList = new ArrayList<>();
                    dataMataPelajaranListView.onSetupListView(dataModelArrayList);
                    globalProcess.onErrorMessage(message);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                globalProcess.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
            }
        }, error -> globalProcess.onErrorMessage(globalMessage.getMessageConnectionError()));

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDelete(String id_mata_pelajaran) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/mata_pelajaran/inactive_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {
                            globalProcess.onSuccessMessage(message);
                            onLoadDataList();
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
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
