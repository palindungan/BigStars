<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Spp extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_kode");
        date_default_timezone_set('Asia/Jakarta');
    }

    function list_data_pertemuan_post()
    {
        // $id_bayar_spp   = $this->post('id_bayar_spp');
        $id_wali_murid    = $this->post('id_wali_murid');

        // variable array
        $result['data_result'] = array();

        // data array untuk where db
        $where = array(
            'id_wali_murid' => $id_wali_murid,
            'status_spp_detail' => 'Belum Lunas',
            'status_konfirmasi' => 'Valid',
            'status_pertemuan' => 'Selesai'
        );

        // mengambil data
        $query = $this->M_universal->get_data('view_pertemuan_detail', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pertemuan'          => $row["id_pertemuan"],
                    'hari_pertemuan'        => $row["hari_pertemuan"],
                    'waktu_mulai'           => $row["waktu_mulai"],
                    'waktu_berakhir'        => $row["waktu_berakhir"],
                    'lokasi_mulai_la'       => $row["lokasi_mulai_la"],
                    'lokasi_mulai_lo'       => $row["lokasi_mulai_lo"],
                    'lokasi_berakhir_la'    => $row["lokasi_berakhir_la"],
                    'lokasi_berakhir_lo'    => $row["lokasi_berakhir_lo"],
                    'deskripsi'             => $row["deskripsi"],
                    'harga_fee'             => $row["harga_fee"],
                    'harga_spp'             => $row["harga_spp"],
                    'status_fee'            => $row["status_fee"],
                    'status_spp'            => $row["status_spp_detail"],
                    'status_konfirmasi'     => $row["status_konfirmasi"],
                    'status_pertemuan'      => $row["status_pertemuan"],

                    'id_pengajar'           => $row["id_pengajar"],
                    'nama_pengajar'         => $row["nama_pengajar"],

                    'id_kelas_pertemuan'    => $row["id_kelas_pertemuan"],
                    'hari_kelas_pertemuan'  => $row["hari_kelas_pertemuan"],
                    'jam_mulai'             => $row["jam_mulai"],
                    'jam_berakhir'          => $row["jam_berakhir"],

                    'id_mata_pelajaran'     => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran'   => $row["nama_mata_pelajaran"],

                    'id_wali_murid'         => $row["id_wali_murid"],
                    'nama_wali_murid'       => $row["nama_wali_murid"],
                );

                array_push($result['data_result'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Success Berhasil Mengambil Data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data Masih kosong";
            $this->response($result, 200);
        }
    }

    function add_data_post()
    {
        $tanggal = date('Y-m-d H:i:s');
        // ambil data
        $id_bayar_spp       = $this->M_kode->id_bayar_spp();
        $id_wali_murid      = $this->post('id_wali_murid');
        $id_admin           = $this->post('id_admin');
        $waktu              = $tanggal;
        $total_pertemuan    = $this->post('total_pertemuan');
        $total_harga_spp    = $this->post('total_harga_spp');
        $status_data        = "active";

        $data = array(
            'id_bayar_spp'      => $id_bayar_spp,
            'id_wali_murid'     => $id_wali_murid,
            'id_admin'          => $id_admin,
            'waktu'             => $waktu,
            'total_pertemuan'   => $total_pertemuan,
            'total_harga_spp'   => $total_harga_spp,
            'status_data'       => $status_data
        );

        $insert =  $this->M_universal->input_data('bayar_spp', $data);
        if ($insert) {

            $this->add_data_detail($id_bayar_spp, $id_wali_murid);

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Pembayaran Spp";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Pembayaran Spp";
            $this->response($result, 200);
        }
    }

    function add_data_detail($id_bayar_spp, $id_wali_murid){

        // data array untuk where db
        $where = array(
            'id_wali_murid' => $id_wali_murid,
            'status_spp_detail' => 'Belum Lunas',
            'status_konfirmasi' => 'Valid',
            'status_pertemuan' => 'Selesai'
        );

        // mengambil data
        $query = $this->M_universal->get_data('view_pertemuan_detail', $where);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                $id_pertemuan_detail = $row["id_pertemuan_detail"];

                $data = array(
                    'id_bayar_spp'  => $id_bayar_spp,
                    'id_pertemuan_detail'  => $id_pertemuan_detail
                );
                $insert =  $this->M_universal->input_data('bayar_spp_detail', $data);

                // data array untuk where db
                $where = array(
                    'id_pertemuan_detail'  => $id_pertemuan_detail
                );
                $data = array(
                    'status_spp_detail'  => 'Sudah Lunas'
                );
                $update =  $this->M_universal->update_data($where, 'pertemuan_detail', $data);

                $id_pertemuan = $row["id_pertemuan"];
                $where = array(
                    'id_pertemuan'      => $id_pertemuan,
                    'status_spp_detail' => 'Belum Lunas'
                );
                $query = $this->M_universal->get_data('view_pertemuan_detail', $where);
                if ($query->num_rows() <= 0) {

                    // data array untuk where db
                    $where = array(
                        'id_pertemuan'  => $id_pertemuan
                    );
                    $data = array(
                        'status_spp'  => 'Sudah Lunas'
                    );
                    $update =  $this->M_universal->update_data($where, 'pertemuan', $data);

                }
            }
        }
    }

    function list_data_post()
    {
        $id_wali_murid    = $this->post('id_wali_murid');

        // variable array
        $result['data_result'] = array();

        // data array untuk where db
        $where = array(
            'status_data' => 'active',
            'id_wali_murid' =>  $id_wali_murid
        );

        // mengambil data
        $query = $this->M_universal->get_data('view_bayar_spp', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_bayar_spp' => $row["id_bayar_spp"],
                    'waktu' => $row["waktu"],
                    'total_pertemuan' => $row["total_pertemuan"],
                    'total_harga_spp' => $row["total_harga_spp"],
                    'id_wali_murid' => $row["id_wali_murid"],
                    'nama_wali_murid' => $row["nama_wali_murid"],
                    'id_admin' => $row["id_admin"],
                    'nama_admin' => $row["nama_admin"]
                );

                array_push($result['data_result'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Success Berhasil Mengambil Data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data Masih kosong";
            $this->response($result, 200);
        }
    }

    function list_data_bayar_fee_detail_post()
    {
        $id_bayar_spp   = $this->post('id_bayar_spp');
       
        // variable array
        $result['data_result'] = array();

        // data array untuk where db
        $where = array(
            'id_bayar_spp' => $id_bayar_spp
        );

        // mengambil data
        $query = $this->M_universal->get_data('view_bayar_spp_detail', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pertemuan'          => $row["id_pertemuan"],
                    'hari_pertemuan'        => $row["hari_pertemuan"],
                    'waktu_mulai'           => $row["waktu_mulai"],
                    'waktu_berakhir'        => $row["waktu_berakhir"],
                    'lokasi_mulai_la'       => $row["lokasi_mulai_la"],
                    'lokasi_mulai_lo'       => $row["lokasi_mulai_lo"],
                    'lokasi_berakhir_la'    => $row["lokasi_berakhir_la"],
                    'lokasi_berakhir_lo'    => $row["lokasi_berakhir_lo"],
                    'deskripsi'             => $row["deskripsi"],
                    'harga_fee'             => $row["harga_fee"],
                    'harga_spp'             => $row["harga_spp"],
                    'status_fee'            => $row["status_fee"],
                    'status_spp'            => $row["status_spp_detail"],
                    'status_konfirmasi'     => $row["status_konfirmasi"],
                    'status_pertemuan'      => $row["status_pertemuan"],

                    'id_pengajar'           => $row["id_pengajar"],
                    'nama_pengajar'         => $row["nama_pengajar"],

                    'id_kelas_pertemuan'    => $row["id_kelas_pertemuan"],
                    'hari_kelas_pertemuan'  => $row["hari_kelas_pertemuan"],
                    'jam_mulai'             => $row["jam_mulai"],
                    'jam_berakhir'          => $row["jam_berakhir"],

                    'id_mata_pelajaran'     => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran'   => $row["nama_mata_pelajaran"],

                    'id_wali_murid'         => $row["id_wali_murid"],
                    'nama_wali_murid'       => $row["nama_wali_murid"],
                );

                array_push($result['data_result'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Success Berhasil Mengambil Data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data Masih kosong";
            $this->response($result, 200);
        }
    }
}
