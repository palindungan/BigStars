<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Fee extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_kode");
        date_default_timezone_set('Asia/Jakarta');
    }
    
    function list_data_post()
    {
        $id_bayar_fee   = $this->post('id_bayar_fee');
        $id_pengajar    = $this->post('id_pengajar');

        // variable array
        $result['data_result'] = array();

        // data array untuk where db
        $where = array(
            'id_pengajar' => $id_pengajar,
            'status_fee' => 'Belum Terbayar',
            'status_konfirmasi' => 'Valid'
        );

        // mengambil data
        $query = $this->M_universal->get_data('view_pertemuan', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pertemuan'          => $row["id_pertemuan"],
                    'id_pengajar'           => $row["id_pengajar"],
                    'id_kelas_pertemuan'    => $row["id_kelas_pertemuan"],

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
                    'status_spp'            => $row["status_spp"],
                    'status_konfirmasi'     => $row["status_konfirmasi"],
                    'status_pertemuan'      => $row["status_pertemuan"],

                    'nama_pengajar'         => $row["nama_pengajar"],

                    'id_kelas_pertemuan'    => $row["id_kelas_pertemuan"],
                    'hari_kelas_pertemuan'  => $row["hari_kelas_pertemuan"],
                    'jam_mulai'             => $row["jam_mulai"],
                    'jam_berakhir'          => $row["jam_berakhir"],

                    'id_mata_pelajaran'     => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran'   => $row["nama_mata_pelajaran"],
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
