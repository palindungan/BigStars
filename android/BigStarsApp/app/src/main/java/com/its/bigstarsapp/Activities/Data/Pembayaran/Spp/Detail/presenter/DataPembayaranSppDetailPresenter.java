package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.view.IDataPembayaranSppDetailView;
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

public class DataPembayaranSppDetailPresenter implements IDataPembayaranSppDetailPresenter {

    Context context;
    IDataPembayaranSppDetailView dataPembayaranSppDetailView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<Pertemuan> dataModelArrayList;

    public DataPembayaranSppDetailPresenter(Context context, IDataPembayaranSppDetailView dataPembayaranSppDetailView) {
        this.context = context;
        this.dataPembayaranSppDetailView = dataPembayaranSppDetailView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadDataListPertemuan(String id_bayar_spp, String id_wali_murid) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "pembayaran/spp/list_data_pertemuan"; // url http request

        if (!id_bayar_spp.equals("kosong")) {
            URL_DATA = base_url + "pembayaran/spp/list_data_bayar_spp_detail"; // url http request
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);

                        String success = obj.getString("success");
                        String message = obj.getString("message");

                        if (success.equals("1")) {

                            String setNamaWaliMurid = "";
                            int totalPertemuan = 0;
                            int totalSpp = 0;

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
                                String status_spp = dataobj.getString("status_spp") + " (" + harga_spp + ")";
                                String status_konfirmasi = dataobj.getString("status_konfirmasi");
                                String status_pertemuan = dataobj.getString("status_pertemuan");

                                String id_pengajar = dataobj.getString("id_pengajar");
                                String nama_pengajar = dataobj.getString("nama_pengajar");

                                String id_kelas_pertemuan = dataobj.getString("id_kelas_pertemuan");
                                String hari_kelas_pertemuan = dataobj.getString("hari_kelas_pertemuan");
                                String jam_mulai = dataobj.getString("jam_mulai");
                                String jam_berakhir = dataobj.getString("jam_berakhir");

                                String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                                String nama_mata_pelajaran = dataobj.getString("nama_mata_pelajaran");

                                String nama_wali_murid = dataobj.getString("nama_wali_murid");

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

                                playerModel.setId_wali_murid(id_wali_murid);
                                playerModel.setNama_wali_murid(nama_wali_murid);

                                dataModelArrayList.add(playerModel);

                                setNamaWaliMurid = nama_wali_murid;
                                totalPertemuan = totalPertemuan + 1;
                                totalSpp = totalSpp + Integer.parseInt(harga_spp);
                            }
                            dataPembayaranSppDetailView.onSetData(
                                    setNamaWaliMurid,
                                    totalPertemuan,
                                    totalSpp);
                            dataPembayaranSppDetailView.onSetupListView(dataModelArrayList);
                        } else {
                            dataModelArrayList = new ArrayList<>();
                            dataPembayaranSppDetailView.onSetupListView(dataModelArrayList);
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
                params.put("id_bayar_spp", id_bayar_spp);
                params.put("id_wali_murid", id_wali_murid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBayar(String id_wali_murid, String id_admin, String total_pertemuan, String total_spp) {
        String base_url = globalVariable.getUrlData();
        String URL_DATA = base_url + "pembayaran/spp/add_data"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if (success.equals("1")) {
                            globalProcess.onSuccessMessage(message);
                            dataPembayaranSppDetailView.backPressed();
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
                params.put("id_wali_murid", id_wali_murid);
                params.put("id_admin", id_admin);
                params.put("total_pertemuan", total_pertemuan);
                params.put("total_spp", total_spp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
