<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Pertemuan extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_kode");
        date_default_timezone_set('Asia/Jakarta');
    }

    function mulai_absen_post()
    {
        // ambil data
        $id_pertemuan       = $this->M_kode->id_pertemuan();
        $id_pengajar        = $this->post('id_pengajar');
        $id_kelas_pertemuan = $this->post('id_kelas_pertemuan');

        $tanggal = date('Y-m-d H:i:s');

        $hari               = "kosong";
        $waktu_mulai        = $tanggal;
        $waktu_berakhir     = $tanggal;
        $lokasi_mulai_la    = "kosong";
        $lokasi_mulai_lo    = "kosong";
        $lokasi_berakhir_la = "kosong";
        $lokasi_berakhir_lo = "kosong";
        $deskripsi          = "kosong";
        $harga_fee          = 0;
        $harga_spp          = 0;
        $status_fee         = "Belum Terbayar";
        $status_spp         = "Belum Lunas";
        $status_konfirmasi  = "Invalid";
        $status_pertemuan   = "Belum Selesai";

        $hari = date('l', strtotime($tanggal));
        if ($hari == "Sunday") {
            $hari = "Minggu";
        } elseif ($hari == "Monday") {
            $hari = "Senin";
        } elseif ($hari == "Tuesday") {
            $hari = "Selasa";
        } elseif ($hari == "Wednesday") {
            $hari = "Rabu";
        } elseif ($hari == "Thursday") {
            $hari = "Kamis";
        } elseif ($hari == "Friday") {
            $hari = "Jum'at";
        } elseif ($hari == "Saturday") {
            $hari = "Sabtu";
        }

        // data array untuk where db
        $where = array(
            'id_kelas_pertemuan'    => $id_kelas_pertemuan,
            'status_data'           => 'active'
        );

        // mengambil data
        $query = $this->M_universal->get_data('kelas_pertemuan', $where);

        // mengeluarkan data dari database
        foreach ($query->result_array() as $row) {
            $harga_fee          = $row["harga_fee"];
            $harga_spp          = $row["harga_spp"];
        }

        $data = array(
            'id_pertemuan'          => $id_pertemuan,
            'id_pengajar'           => $id_pengajar,
            'id_kelas_pertemuan'    => $id_kelas_pertemuan,

            'hari'                  => $hari,
            'waktu_mulai'           => $waktu_mulai,
            'waktu_berakhir'        => $waktu_berakhir,
            'lokasi_mulai_la'       => $lokasi_mulai_la,
            'lokasi_mulai_lo'       => $lokasi_mulai_lo,
            'lokasi_berakhir_la'    => $lokasi_berakhir_la,
            'lokasi_berakhir_lo'    => $lokasi_berakhir_lo,
            'deskripsi'             => $deskripsi,
            'harga_fee'             => $harga_fee,
            'harga_spp'             => $harga_spp,
            'status_fee'            => $status_fee,
            'status_spp'            => $status_spp,
            'status_konfirmasi'     => $status_konfirmasi,
            'status_pertemuan'      => $status_pertemuan
        );

        $insert =  $this->M_universal->input_data('pertemuan', $data);
        if ($insert) {

            // input data detail
            $query = $this->M_universal->get_data('kelas_pertemuan_detail', $where);
            foreach ($query->result_array() as $row) {
                $data = array(
                    'id_pertemuan'  => $id_pertemuan,
                    'id_murid'      => $row["id_murid"]
                );
                $insert =  $this->M_universal->input_data('pertemuan_detail', $data);
            }

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Memulai Absensi";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Memulai Absensi";
            $this->response($result, 200);
        }
    }
}
