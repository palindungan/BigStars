package com.its.bigstarsapp.Activities.Data.Pengajar.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.view.IDataPengajarListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pengajar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataPengajarListPresenter implements IDataPengajarListPresenter {

    Context context;
    IDataPengajarListView dataPengajarListView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<Pengajar> dataModelArrayList;

    String statusActivity;

    public DataPengajarListPresenter(Context context, IDataPengajarListView dataPengajarListView) {
        this.context = context;
        this.dataPengajarListView = dataPengajarListView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @Override
    public void onLoadDataList() {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/pengajar/list_data"; // url http request

        if (statusActivity.equals("home->view->listPertemuanAktif")) {
            URL_DATA = base_url + "absensi/pertemuan/list_all_data_aktif"; // url http request
        } else if (statusActivity.equals("home->view->listPertemuanSemuaRiwayat")) {
            URL_DATA = base_url + "absensi/pertemuan/list_all_data_riwayat"; // url http request
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, response -> {
            try {

                JSONObject obj = new JSONObject(response);

                String success = obj.getString("success");
                String message = obj.getString("message");

                if (success.equals("1")) {

                    dataModelArrayList = new ArrayList<>();
                    JSONArray dataArray = obj.getJSONArray("data_result");
                    for (int i = 0; i < dataArray.length(); i++) {

                        Pengajar playerModel = new Pengajar();
                        JSONObject dataobj = dataArray.getJSONObject(i);

                        String id_pengajar = "";
                        String nama = "";
                        String username = "";
                        String alamat = "";
                        String no_hp = "";
                        String foto = "";

                        if (statusActivity.equals("home->view->listPertemuanAktif") || statusActivity.equals("home->view->listPertemuanSemuaRiwayat")) {
                            id_pengajar = dataobj.getString("id_pengajar");
                            nama = dataobj.getString("nama_pengajar");
                            username = dataobj.getString("username_pengajar");
                            alamat = dataobj.getString("alamat_pengajar");
                            no_hp = dataobj.getString("no_hp_pengajar");
                            foto = dataobj.getString("foto_pengajar");
                        } else {
                            id_pengajar = dataobj.getString("id_pengajar");
                            nama = dataobj.getString("nama");
                            username = dataobj.getString("username");
                            alamat = dataobj.getString("alamat");
                            no_hp = dataobj.getString("no_hp");
                            foto = dataobj.getString("foto");
                        }

                        playerModel.setId_pengajar(id_pengajar);
                        playerModel.setNama(nama);
                        playerModel.setUsername(username);
                        playerModel.setAlamat(alamat);
                        playerModel.setNo_hp(no_hp);
                        playerModel.setFoto(foto);

                        dataModelArrayList.add(playerModel);
                    }
                    dataPengajarListView.onSetupListView(dataModelArrayList);
                } else {
                    dataModelArrayList = new ArrayList<>();
                    dataPengajarListView.onSetupListView(dataModelArrayList);
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
    public void onDelete(String id_pengajar) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/pengajar/inactive_data"; // url http request

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
                params.put("id_pengajar", id_pengajar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
