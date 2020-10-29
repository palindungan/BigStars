package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.presenter.IDataPertemuanEdit2Presenter;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.view.IDataPertemuanEdit2View;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataPertemuanEdit2Activity extends AppCompatActivity implements View.OnClickListener, IDataPertemuanEdit2View, OnMapReadyCallback {

    public static final String EXTRA_ID_PERTEMUAN = "EXTRA_ID_PERTEMUAN";
    public static final String EXTRA_HARI_PERTEMUAN = "EXTRA_HARI_PERTEMUAN";
    public static final String EXTRA_WAKTU_MULAI = "EXTRA_WAKTU_MULAI";
    public static final String EXTRA_WAKTU_BERAKHIR = "EXTRA_WAKTU_BERAKHIR";
    public static final String EXTRA_LOKASI_MULAI_LA = "EXTRA_LOKASI_MULAI_LA";
    public static final String EXTRA_LOKASI_MULAI_LO = "EXTRA_LOKASI_MULAI_LO";
    public static final String EXTRA_LOKASI_BERAKHIR_LA = "EXTRA_LOKASI_BERAKHIR_LA";
    public static final String EXTRA_LOKASI_BERAKHIR_LO = "EXTRA_LOKASI_BERAKHIR_LO";
    public static final String EXTRA_DESKRIPSI = "EXTRA_DESKRIPSI";
    public static final String EXTRA_HARGA_FEE = "EXTRA_HARGA_FEE";
    public static final String EXTRA_HARGA_SPP = "EXTRA_HARGA_SPP";
    public static final String EXTRA_STATUS_FEE = "EXTRA_STATUS_FEE";
    public static final String EXTRA_STATUS_SPP = "EXTRA_STATUS_SPP";
    public static final String EXTRA_STATUS_KONFIRMASI = "EXTRA_STATUS_KONFIRMASI";
    public static final String EXTRA_STATUS_PERTEMUAN = "EXTRA_STATUS_PERTEMUAN";
    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_NAMA_PENGAJAR = "EXTRA_NAMA_PENGAJAR";
    public static final String EXTRA_ID_KELAS_PERTEMUAN = "EXTRA_ID_KELAS_PERTEMUAN";
    public static final String EXTRA_HARI_KELAS_PERTEMUAN = "EXTRA_HARI_KELAS_PERTEMUAN";
    public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
    public static final String EXTRA_JAM_BERAKHIR = "EXTRA_JAM_BERAKHIR";
    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA_MATA_PELAJARAN = "EXTRA_NAMA_MATA_PELAJARAN";

    String id_pertemuan, hari_pertemuan, waktu_mulai, waktu_berakhir, lokasi_mulai_la,
            lokasi_mulai_lo, lokasi_berakhir_la, lokasi_berakhir_lo, deskripsi, harga_fee,
            harga_spp, status_fee, status_spp, status_konfirmasi, status_pertemuan;
    String id_pengajar, nama_pengajar;
    String id_kelas_pertemuan, hari_kelas_pertemuan, jam_mulai, jam_berakhir;
    String id_mata_pelajaran, nama_mata_pelajaran;

    IDataPertemuanEdit2Presenter dataPertemuanEdit2Presenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNamaMataPelajaran, edtWaktuMulai, edtWaktuBerakhir, edtHargaFee, edtHargaSpp;
    TextView tvStatusPertemuan, tvStatusKonfirmasi;
    Button btnBatal, btnValid, btnInvalid, btnGetLokasi, btnNext;

    // String statusActivity;

    public static Dialog dialog;

    // ------------------ start google map ---------------------
    private static final String TAG = DataPertemuanEdit2Activity.class.getSimpleName();
    private GoogleMap map;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    // [START maps_current_place_state_keys]
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    // [END maps_current_place_state_keys]

    // --------------------- end of google map -------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pertemuan_edit2);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}