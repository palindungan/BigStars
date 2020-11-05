package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.view.IDataPembayaranFeeListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.BayarFee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataPembayaranFeeListPresenter implements IDataPembayaranFeeListPresenter {

    Context context;
    IDataPembayaranFeeListView dataPembayaranFeeListView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<BayarFee> dataModelArrayList;

    public DataPembayaranFeeListPresenter(Context context, IDataPembayaranFeeListView dataPembayaranFeeListView) {
        this.context = context;
        this.dataPembayaranFeeListView = dataPembayaranFeeListView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadDataList(String id_pengajar) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "pembayaran/fee/list_data"; // url http request

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

                                BayarFee playerModel = new BayarFee();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                String id_bayar_fee = dataobj.getString("id_bayar_fee");
                                String id_admin = dataobj.getString("id_admin");
                                String waktu = dataobj.getString("waktu");
                                String total_pertemuan = dataobj.getString("total_pertemuan");
                                String total_harga_fee = dataobj.getString("total_harga_fee");

                                playerModel.setId_bayar_fee(id_bayar_fee);

                                playerModel.setId_admin(id_admin);
                                playerModel.setWaktu(waktu);
                                playerModel.setTotal_pertemuan(total_pertemuan);
                                playerModel.setTotal_harga_fee(total_harga_fee);

                                dataModelArrayList.add(playerModel);
                            }
                            dataPembayaranFeeListView.onSetupListView(dataModelArrayList);

                        } else {
                            dataModelArrayList = new ArrayList<>();
                            dataPembayaranFeeListView.onSetupListView(dataModelArrayList);
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
