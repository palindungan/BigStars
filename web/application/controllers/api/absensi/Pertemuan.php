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
    }

    function mulai_absen_post()
    {
        // ambil data
        $id_pertemuan       = $this->M_kode->id_pertemuan();
        $id_pengajar        = $this->post('id_pengajar');
        $id_kelas_pertemuan = $this->post('id_kelas_pertemuan');

        $data = array(
            'id_pertemuan'          => $id_pertemuan,
            'id_pengajar'           => $id_pengajar,
            'id_kelas_pertemuan'    => $id_kelas_pertemuan,
        );

        $insert =  $this->M_universal->input_data('mata_pelajaran', $data);
        if ($insert) {

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
