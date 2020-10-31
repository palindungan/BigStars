package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.presenter.DataPertemuanEdit2Presenter;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.presenter.IDataPertemuanEdit2Presenter;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.view.IDataPertemuanEdit2View;
import com.its.bigstarsapp.Activities.Data.Pertemuan.List.DataPertemuanListActivity;
import com.its.bigstarsapp.Activities.Home.Pengajar.HomePengajarActivity;
import com.its.bigstarsapp.Activities._Main.MainActivity;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

import java.util.HashMap;
import java.util.Objects;

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
    EditText edtDeskripsi;
    Button btnGetLokasi, btnFinish;

    String hak_akses;

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

        // ------------------ start google map ---------------------
        // [START_EXCLUDE silent]
        // [START maps_current_place_on_create_save_instance_state]
        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }
        // [END maps_current_place_on_create_save_instance_state]
        // [END_EXCLUDE]
        // --------------------- end of google map -------------------

        setContentView(R.layout.activity_data_pertemuan_edit2);

        id_pertemuan = getIntent().getStringExtra(EXTRA_ID_PERTEMUAN);
        hari_pertemuan = getIntent().getStringExtra(EXTRA_HARI_PERTEMUAN);
        waktu_mulai = getIntent().getStringExtra(EXTRA_WAKTU_MULAI);
        waktu_berakhir = getIntent().getStringExtra(EXTRA_WAKTU_BERAKHIR);
        lokasi_mulai_la = getIntent().getStringExtra(EXTRA_LOKASI_MULAI_LA);
        lokasi_mulai_lo = getIntent().getStringExtra(EXTRA_LOKASI_MULAI_LO);
        lokasi_berakhir_la = getIntent().getStringExtra(EXTRA_LOKASI_BERAKHIR_LA);
        lokasi_berakhir_lo = getIntent().getStringExtra(EXTRA_LOKASI_BERAKHIR_LO);
        deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);
        harga_fee = getIntent().getStringExtra(EXTRA_HARGA_FEE);
        harga_spp = getIntent().getStringExtra(EXTRA_HARGA_SPP);
        status_fee = getIntent().getStringExtra(EXTRA_STATUS_FEE);
        status_spp = getIntent().getStringExtra(EXTRA_STATUS_SPP);
        status_konfirmasi = getIntent().getStringExtra(EXTRA_STATUS_KONFIRMASI);
        status_pertemuan = getIntent().getStringExtra(EXTRA_STATUS_PERTEMUAN);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        nama_pengajar = getIntent().getStringExtra(EXTRA_NAMA_PENGAJAR);

        id_kelas_pertemuan = getIntent().getStringExtra(EXTRA_ID_KELAS_PERTEMUAN);
        hari_kelas_pertemuan = getIntent().getStringExtra(EXTRA_HARI_KELAS_PERTEMUAN);
        jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
        jam_berakhir = getIntent().getStringExtra(EXTRA_JAM_BERAKHIR);

        id_mata_pelajaran = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        nama_mata_pelajaran = getIntent().getStringExtra(EXTRA_NAMA_MATA_PELAJARAN);

        dataPertemuanEdit2Presenter = new DataPertemuanEdit2Presenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtDeskripsi = findViewById(R.id.edt_deskripsi);
        btnGetLokasi = findViewById(R.id.btn_get_lokasi);
        btnFinish = findViewById(R.id.btn_finish);

        globalProcess.initActionBar(toolbar);

        inisiasiAwal();

        btnGetLokasi.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
    }

    private void inisiasiAwal() {
        if (!deskripsi.equals("kosong")) {
            edtDeskripsi.setText(deskripsi);
        }

        HashMap<String, String> user = sessionManager.getDataUser();
        hak_akses = user.get(sessionManager.HAK_AKSES);
        if (hak_akses != null) {
            if (hak_akses.equals("admin")) {
                btnFinish.setVisibility(View.GONE);
                edtDeskripsi.setFocusable(false);
            } else if (hak_akses.equals("pengajar")) {
                btnFinish.setVisibility(View.VISIBLE);
                btnGetLokasi.setVisibility(View.VISIBLE);
            }
        }

        if (status_pertemuan.equals("Selesai") || status_pertemuan.equals("Batal")) {
            btnFinish.setVisibility(View.GONE);
            btnGetLokasi.setVisibility(View.GONE);
        }

        inisiasiGmap();
    }

    private void inisiasiGmap() {
        // [START_EXCLUDE silent]
        // Construct a PlacesClient
        Places.initialize(getApplicationContext(), getString(R.string.maps_api_key));
        // The entry point to the Places API.

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Build the map.
        // [START maps_current_place_map_fragment]
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        // [END maps_current_place_map_fragment]
        // [END_EXCLUDE]
    }

    // [START maps_current_place_location_permission]
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
    // [END maps_current_place_location_permission]

    // [START maps_current_place_update_location_ui]
    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", Objects.requireNonNull(e.getMessage()));
        }
    }
    // [END maps_current_place_update_location_ui]

    // [START maps_current_place_get_device_location]
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.getResult();
                        if (lastKnownLocation != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lastKnownLocation.getLatitude(),
                                            lastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                            double la = lastKnownLocation.getLatitude();
                            double lo = lastKnownLocation.getLongitude();
                            lokasi_berakhir_la = String.valueOf(la);
                            lokasi_berakhir_lo = String.valueOf(lo);
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.");
                        Log.e(TAG, "Exception: %s", task.getException());
                        map.moveCamera(CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                        map.getUiSettings().setMyLocationButtonEnabled(false);
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }
    // [END maps_current_place_get_device_location]

    private void onLoadGoogleMap() {
        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }

    private void showDialogFinishPertemuan() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiFinishAbsen());
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaFinishAbsen())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {

                    String inputDeskripsi = edtDeskripsi.getText().toString().trim();
                    boolean isEmpty = false;

                    if (TextUtils.isEmpty(inputDeskripsi)) {
                        isEmpty = true;
                        edtDeskripsi.setError(globalMessage.getValidasiDeskripsiKosong());
                        globalProcess.onErrorMessage(globalMessage.getValidasiDeskripsiKosong());
                    } else if (lokasi_berakhir_la.equals("kosong") && lokasi_berakhir_lo.equals("kosong")) {
                        isEmpty = true;
                        globalProcess.onErrorMessage(globalMessage.getValidasiLokasiKosong());
                    }

                    try {
                        if (!isEmpty) {
                            dataPertemuanEdit2Presenter.onFinish(
                                    "" + id_pertemuan,
                                    "" + inputDeskripsi,
                                    "" + lokasi_berakhir_la,
                                    "" + lokasi_berakhir_lo);
                        }
                    } catch (Exception e) {
                        globalProcess.onErrorMessage(globalMessage.getErrorFinishAbsen() + e.toString());
                    }

                })
                .setNegativeButton(globalMessage.getTidak(), (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_get_lokasi) {
            onLoadGoogleMap();
        } else if (view.getId() == R.id.btn_finish) {
            showDialogFinishPertemuan();
        }
    }

    @Override
    public void goToDataRiwayatPertemuanListScreen() {
        sessionManager.setStatusActivity("homePengajar->view->dataPertemuanHistory");
        Intent intent = new Intent(getApplicationContext(), DataPertemuanListActivity.class);
        intent.putExtra(DataPertemuanListActivity.EXTRA_ID_PENGAJAR, id_pengajar);
        startActivity(intent);
    }

    @Override
    public void goToHomePengajarScreen() {
        Intent intent = new Intent(this, HomePengajarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // [START maps_current_place_on_map_ready]
    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        // [START_EXCLUDE]
        // [START map_current_place_set_info_window_adapter]
        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.
        this.map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,
                        findViewById(R.id.map), false);

                TextView title = infoWindow.findViewById(R.id.title);
                title.setText(marker.getTitle());

                TextView snippet = infoWindow.findViewById(R.id.snippet);
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });
        // [END map_current_place_set_info_window_adapter]

        // Prompt the user for permission.
        getLocationPermission();
        // [END_EXCLUDE]

        // Turn on the My Location layer and the related control on the map.
        if (!lokasi_berakhir_la.equals("kosong") && !lokasi_berakhir_lo.equals("kosong")) {
            updateLocationUI();

            this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(
                            Double.parseDouble(lokasi_berakhir_la),
                            Double.parseDouble(lokasi_berakhir_lo)
                    ), DEFAULT_ZOOM));
        } else {
            if (hak_akses.equals("pengajar")) {
                onLoadGoogleMap();
            }
        }
    }
    // [END maps_current_place_on_map_ready]

    // [START maps_current_place_on_request_permissions_result]
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
            }
        }
    }
    // [END maps_current_place_on_request_permissions_result]

    // [START maps_current_place_on_save_instance_state]
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
        }
        super.onSaveInstanceState(outState);
    }
    // [END maps_current_place_on_save_instance_state]

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}