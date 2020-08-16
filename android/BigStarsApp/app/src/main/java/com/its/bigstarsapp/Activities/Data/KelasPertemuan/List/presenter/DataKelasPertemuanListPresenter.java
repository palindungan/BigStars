package com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.view.IDataKelasPertemuanListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.KelasPertemuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataKelasPertemuanListPresenter implements IDataKelasPertemuanListPresenter {

    Context context;
    IDataKelasPertemuanListView dataKelasPertemuanListView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<KelasPertemuan> dataModelArrayList;

    public DataKelasPertemuanListPresenter(Context context, IDataKelasPertemuanListView dataKelasPertemuanListView) {
        this.context = context;
        this.dataKelasPertemuanListView = dataKelasPertemuanListView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadDataList(String id_pengajar) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/list_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {

                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray = jsonObject.getJSONArray("data_result");
                            for (int i = 0; i < dataArray.length(); i++) {

                                KelasPertemuan playerModel = new KelasPertemuan();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                String id_kelas_p = dataobj.getString("id_kelas_p");
                                String hari = dataobj.getString("hari");
                                String jam_mulai = dataobj.getString("jam_mulai");
                                String jam_berakhir = dataobj.getString("jam_berakhir");
                                String harga_fee = dataobj.getString("harga_fee");
                                String harga_spp = dataobj.getString("harga_spp");
                                String nama_pelajaran = dataobj.getString("nama_pelajaran");
                                String nama_sharing = dataobj.getString("nama_sharing");

                                String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                                String id_sharing = dataobj.getString("id_sharing");
                                String nama_pengajar = dataobj.getString("nama_pengajar");

                                String jumlah_murid = dataobj.getString("jumlah_murid");

                                playerModel.setId_kelas_p(id_kelas_p);
                                playerModel.setHari(hari);
                                playerModel.setJam_mulai(jam_mulai);
                                playerModel.setJam_berakhir(jam_berakhir);
                                playerModel.setHarga_fee(harga_fee);
                                playerModel.setHarga_spp(harga_spp);
                                playerModel.setNama_pelajaran(nama_pelajaran);
                                playerModel.setNama_sharing(nama_sharing);

                                playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                                playerModel.setId_pengajar(id_pengajar);
                                playerModel.setId_sharing(id_sharing);
                                playerModel.setNama_pengajar(nama_pengajar);

                                playerModel.setJumlah_murid(jumlah_murid);

                                dataModelArrayList.add(playerModel);
                            }

                            dataKelasPertemuanListView.onSetupListView(dataModelArrayList);

                        } else {
                            dataModelArrayList = new ArrayList<>();
                            dataKelasPertemuanListView.onSetupListView(dataModelArrayList);
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

    @Override
    public void onDelete(String id_kelas_pertemuan) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/inactive_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {
                            globalProcess.onSuccessMessage(message);
                            dataKelasPertemuanListView.onRefreshDataList();
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
                params.put("id_kelas_pertemuan", id_kelas_pertemuan);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
