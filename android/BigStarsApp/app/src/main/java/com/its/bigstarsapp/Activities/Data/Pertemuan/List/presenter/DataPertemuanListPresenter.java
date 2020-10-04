package com.its.bigstarsapp.Activities.Data.Pertemuan.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.Pertemuan.List.view.IDataPertemuanListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pertemuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataPertemuanListPresenter implements IDataPertemuanListPresenter {

    Context context;
    IDataPertemuanListView dataPertemuanListView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<Pertemuan> dataModelArrayList;

    public DataPertemuanListPresenter(Context context, IDataPertemuanListView dataPertemuanListView) {
        this.context = context;
        this.dataPertemuanListView = dataPertemuanListView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadDataList(String id_pengajar) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/pertemuan/list_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {

                        JSONObject obj = new JSONObject(response);

                        String success = obj.getString("success");
                        String message = obj.getString("message");

                        if (success.equals("1")) {

                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("data_result");
                            for (int i = 0; i < dataArray.length(); i++) {

                                Pertemuan playerModel = new Pertemuan();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");

                                playerModel.setId_mata_pelajaran(id_mata_pelajaran);

                                dataModelArrayList.add(playerModel);
                            }
                            dataPertemuanListView.onSetupListView(dataModelArrayList);

                        } else {
                            dataModelArrayList = new ArrayList<>();
                            dataPertemuanListView.onSetupListView(dataModelArrayList);
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
                params.put("id_pengajar", id_pengajar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
