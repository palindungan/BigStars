package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List.view.IDataPembayaranSppListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.BayarSpp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataPembayaranSppListPresenter implements IDataPembayaranSppListPresenter {

    Context context;
    IDataPembayaranSppListView dataPembayaranSppListView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<BayarSpp> dataModelArrayList;

    public DataPembayaranSppListPresenter(Context context, IDataPembayaranSppListView dataPembayaranSppListView) {
        this.context = context;
        this.dataPembayaranSppListView = dataPembayaranSppListView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadDataList(String id_wali_murid) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "pembayaran/spp/list_data"; // url http request

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

                                BayarSpp playerModel = new BayarSpp();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                String id_bayar_spp = dataobj.getString("id_bayar_spp");
                                String waktu = dataobj.getString("waktu");
                                String total_pertemuan = dataobj.getString("total_pertemuan");
                                String total_harga_spp = dataobj.getString("total_harga_spp");

                                String nama_wali_murid = dataobj.getString("nama_wali_murid");

                                String id_admin = dataobj.getString("id_admin");

                                playerModel.setId_bayar_spp(id_bayar_spp);
                                playerModel.setWaktu(waktu);
                                playerModel.setTotal_pertemuan(total_pertemuan);
                                playerModel.setTotal_harga_spp(total_harga_spp);

                                playerModel.setId_wali_murid(id_wali_murid);
                                playerModel.setNama_wali_murid(nama_wali_murid);

                                playerModel.setId_admin(id_admin);

                                dataModelArrayList.add(playerModel);
                            }
                            dataPembayaranSppListView.onSetupListView(dataModelArrayList);

                        } else {
                            dataModelArrayList = new ArrayList<>();
                            dataPembayaranSppListView.onSetupListView(dataModelArrayList);
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
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
