package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.view.IDataKelasPertemuanEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.MataPelajaran;
import com.its.bigstarsapp.Models.Pengajar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataKelasPertemuanEditPresenter implements IDataKelasPertemuanEditPresenter {
    Context context;
    IDataKelasPertemuanEditView dataKelasPertemuanEditView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<MataPelajaran> dataModelArrayListMataPelajaran;
    ArrayList<Pengajar> dataModelArrayListPengajar;

    public DataKelasPertemuanEditPresenter(Context context, IDataKelasPertemuanEditView dataKelasPertemuanEditView) {
        this.context = context;
        this.dataKelasPertemuanEditView = dataKelasPertemuanEditView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onUpdate(String id_kelas_pertemuan, String id_pengajar, String id_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee, String harga_spp) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/update_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {
                            globalProcess.onSuccessMessage(message);
                            dataKelasPertemuanEditView.backPressed();
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
                params.put("id_pengajar", id_pengajar);
                params.put("id_mata_pelajaran", id_mata_pelajaran);
                params.put("hari", hari);
                params.put("jam_mulai", jam_mulai);
                params.put("jam_berakhir", jam_berakhir);
                params.put("harga_fee", harga_fee);
                params.put("harga_spp", harga_spp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onSharingKelasPertemuan(String id_kelas_pertemuan, String id_sharing, String nama_sharing) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/sharing_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {
                            globalProcess.onSuccessMessage(message);
                            dataKelasPertemuanEditView.backPressed();
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
                params.put("id_sharing", id_sharing);
                params.put("nama_sharing", nama_sharing);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onLoadDataListMataPelajaran() {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/mata_pelajaran/list_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, response -> {
            try {

                JSONObject obj = new JSONObject(response);

                String success = obj.getString("success");
                String message = obj.getString("message");

                if (success.equals("1")) {

                    dataModelArrayListMataPelajaran = new ArrayList<>();
                    JSONArray dataArray = obj.getJSONArray("data_result");
                    for (int i = 0; i < dataArray.length(); i++) {

                        MataPelajaran playerModel = new MataPelajaran();
                        JSONObject dataobj = dataArray.getJSONObject(i);

                        String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                        String nama = dataobj.getString("nama");

                        playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                        playerModel.setNama(nama);

                        dataModelArrayListMataPelajaran.add(playerModel);
                    }
                    dataKelasPertemuanEditView.onSetupListViewMataPelajaran(dataModelArrayListMataPelajaran);

                } else {
                    dataModelArrayListMataPelajaran = new ArrayList<>();
                    dataKelasPertemuanEditView.onSetupListViewMataPelajaran(dataModelArrayListMataPelajaran);
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
    public void onLoadDataListPengajar() {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "data/pengajar/list_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, response -> {
            try {

                JSONObject obj = new JSONObject(response);

                String success = obj.getString("success");
                String message = obj.getString("message");

                if (success.equals("1")) {

                    dataModelArrayListPengajar = new ArrayList<>();
                    JSONArray dataArray = obj.getJSONArray("data_result");
                    for (int i = 0; i < dataArray.length(); i++) {

                        Pengajar playerModel = new Pengajar();
                        JSONObject dataobj = dataArray.getJSONObject(i);

                        String id_pengajar = dataobj.getString("id_pengajar");
                        String nama = dataobj.getString("nama");
                        String username = dataobj.getString("username");
                        String alamat = dataobj.getString("alamat");
                        String no_hp = dataobj.getString("no_hp");
                        String foto = dataobj.getString("foto");

                        playerModel.setId_pengajar(id_pengajar);
                        playerModel.setNama(nama);
                        playerModel.setUsername(username);
                        playerModel.setAlamat(alamat);
                        playerModel.setNo_hp(no_hp);
                        playerModel.setFoto(foto);

                        dataModelArrayListPengajar.add(playerModel);
                    }
                    dataKelasPertemuanEditView.onSetupListViewPengajar(dataModelArrayListPengajar);
                } else {
                    dataModelArrayListPengajar = new ArrayList<>();
                    dataKelasPertemuanEditView.onSetupListViewPengajar(dataModelArrayListPengajar);
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
