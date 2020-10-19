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

    String statusActivity;

    public DataPertemuanListPresenter(Context context, IDataPertemuanListView dataPertemuanListView) {
        this.context = context;
        this.dataPertemuanListView = dataPertemuanListView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @Override
    public void onLoadDataList(String id_pengajar) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = "";
        if (statusActivity.equals("homePengajar->view->dataPertemuanAktif") || statusActivity.equals("editKelasPertemuan->view->dataPertemuanAktif")) {
            URL_DATA = base_url + "absensi/pertemuan/list_data_aktif"; // url http request
        } else if (statusActivity.equals("homePengajar->view->dataPertemuanHistory")) {
            URL_DATA = base_url + "absensi/pertemuan/list_data_history"; // url http request
        }

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

                                String id_pertemuan = dataobj.getString("id_pertemuan");
                                String hari_pertemuan = dataobj.getString("hari_pertemuan");
                                String waktu_mulai = dataobj.getString("waktu_mulai");
                                String waktu_berakhir = dataobj.getString("waktu_berakhir");
                                String lokasi_mulai_la = dataobj.getString("lokasi_mulai_la");
                                String lokasi_mulai_lo = dataobj.getString("lokasi_mulai_lo");
                                String lokasi_berakhir_la = dataobj.getString("lokasi_berakhir_la");
                                String lokasi_berakhir_lo = dataobj.getString("lokasi_berakhir_lo");
                                String deskripsi = dataobj.getString("deskripsi");
                                String harga_fee = dataobj.getString("harga_fee");
                                String harga_spp = dataobj.getString("harga_spp");
                                String status_fee = dataobj.getString("status_fee");
                                String status_spp = dataobj.getString("status_spp");
                                String status_konfirmasi = dataobj.getString("status_konfirmasi");
                                String status_pertemuan = dataobj.getString("status_pertemuan");

                                String nama_pengajar = dataobj.getString("nama_pengajar");

                                String id_kelas_pertemuan = dataobj.getString("id_kelas_pertemuan");
                                String hari_kelas_pertemuan = dataobj.getString("hari_kelas_pertemuan");
                                String jam_mulai = dataobj.getString("jam_mulai");
                                String jam_berakhir = dataobj.getString("jam_berakhir");

                                String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                                String nama_mata_pelajaran = dataobj.getString("nama_mata_pelajaran");

                                playerModel.setId_pertemuan(id_pertemuan);
                                playerModel.setHari_pertemuan(hari_pertemuan);
                                playerModel.setWaktu_mulai(waktu_mulai);
                                playerModel.setWaktu_berakhir(waktu_berakhir);
                                playerModel.setLokasi_mulai_la(lokasi_mulai_la);
                                playerModel.setLokasi_mulai_lo(lokasi_mulai_lo);
                                playerModel.setLokasi_berakhir_la(lokasi_berakhir_la);
                                playerModel.setLokasi_berakhir_lo(lokasi_berakhir_lo);
                                playerModel.setDeskripsi(deskripsi);
                                playerModel.setHarga_fee(harga_fee);
                                playerModel.setHarga_spp(harga_spp);
                                playerModel.setStatus_fee(status_fee);
                                playerModel.setStatus_spp(status_spp);
                                playerModel.setStatus_konfirmasi(status_konfirmasi);
                                playerModel.setStatus_pertemuan(status_pertemuan);

                                playerModel.setId_pengajar(id_pengajar);
                                playerModel.setNama_pengajar(nama_pengajar);

                                playerModel.setId_kelas_pertemuan(id_kelas_pertemuan);
                                playerModel.setHari_kelas_pertemuan(hari_kelas_pertemuan);
                                playerModel.setJam_mulai(jam_mulai);
                                playerModel.setJam_berakhir(jam_berakhir);

                                playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                                playerModel.setNama_mata_pelajaran(nama_mata_pelajaran);

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
