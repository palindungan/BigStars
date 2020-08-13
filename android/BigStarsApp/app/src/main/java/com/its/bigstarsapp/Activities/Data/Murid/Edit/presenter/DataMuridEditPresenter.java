package com.its.bigstarsapp.Activities.Data.Murid.Edit.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.Murid.Edit.view.IDataMuridEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.WaliMurid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataMuridEditPresenter implements IDataMuridEditPresenter {

    Context context;
    IDataMuridEditView dataMuridEditView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<WaliMurid> dataModelArrayList;

    public DataMuridEditPresenter(Context context, IDataMuridEditView dataMuridEditView) {
        this.context = context;
        this.dataMuridEditView = dataMuridEditView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onUpdate(String id_murid, String id_wali_murid, String nama, String foto) {

    }

    @Override
    public void onLoadDataListWaliMurid() {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/wali_murid/list_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, response -> {
            try {

                JSONObject obj = new JSONObject(response);

                String success = obj.getString("success");
                String message = obj.getString("message");

                if (success.equals("1")) {

                    dataModelArrayList = new ArrayList<>();
                    JSONArray dataArray = obj.getJSONArray("data_result");
                    for (int i = 0; i < dataArray.length(); i++) {

                        WaliMurid playerModel = new WaliMurid();
                        JSONObject dataobj = dataArray.getJSONObject(i);

                        String id_wali_murid = dataobj.getString("id_wali_murid");
                        String nama = dataobj.getString("nama");
                        String username = dataobj.getString("username");
                        String alamat = dataobj.getString("alamat");
                        String no_hp = dataobj.getString("no_hp");

                        playerModel.setId_wali_murid(id_wali_murid);
                        playerModel.setNama(nama);
                        playerModel.setUsername(username);
                        playerModel.setAlamat(alamat);
                        playerModel.setNo_hp(no_hp);

                        dataModelArrayList.add(playerModel);
                    }
                    dataMuridEditView.onSetupListView(dataModelArrayList);

                } else {
                    dataModelArrayList = new ArrayList<>();
                    dataMuridEditView.onSetupListView(dataModelArrayList);
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
}
