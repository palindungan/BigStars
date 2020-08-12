package com.its.bigstarsapp.Activities.Data.Murid.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Murid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataMuridListPresenter implements IDataMuridListPresenter {

    Context context;
    IDataMuridListView dataMuridListView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<Murid> dataModelArrayList;

    public DataMuridListPresenter(Context context, IDataMuridListView dataMuridListView) {
        this.context = context;
        this.dataMuridListView = dataMuridListView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadDataList() {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/murid/list_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, response -> {
            try {

                JSONObject obj = new JSONObject(response);

                String success = obj.getString("success");
                String message = obj.getString("message");

                if (success.equals("1")) {

                    dataModelArrayList = new ArrayList<>();
                    JSONArray dataArray = obj.getJSONArray("data_result");
                    for (int i = 0; i < dataArray.length(); i++) {

                        Murid playerModel = new Murid();
                        JSONObject dataobj = dataArray.getJSONObject(i);

                        String id_murid = dataobj.getString("id_murid");
                        String nama = dataobj.getString("nama");
                        String id_wali_murid = dataobj.getString("id_wali_murid");
                        String nama_wali_murid = dataobj.getString("nama_wali_murid");
                        String alamat = dataobj.getString("alamat");
                        String foto = dataobj.getString("foto");

                        playerModel.setId_murid(id_murid);
                        playerModel.setNama(nama);
                        playerModel.setId_wali_murid(id_wali_murid);
                        playerModel.setNama_wali_murid(nama_wali_murid);
                        playerModel.setAlamat(alamat);
                        playerModel.setFoto(foto);

                        dataModelArrayList.add(playerModel);
                    }
                    dataMuridListView.onSetupListView(dataModelArrayList);

                } else {
                    dataModelArrayList = new ArrayList<>();
                    dataMuridListView.onSetupListView(dataModelArrayList);
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
    public void onDelete(String id_murid) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/murid/inactive_data"; // url http request

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
                params.put("id_murid", id_murid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
