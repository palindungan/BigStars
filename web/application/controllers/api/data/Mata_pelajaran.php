<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Mata_pelajaran extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_kode");
    }

    function list_data_get()
    {
        // variable array
        $result = array();
        $result['data_result'] = array();

        // data array untuk where db
        $where = array(
            'status_data' => 'active'
        );

        // mengambil data
        $query = $this->M_universal->get_data('mata_pelajaran', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama' => $row["nama"]
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

    function inactive_data_post()
    {
        $id_mata_pelajaran = $this->post('id_mata_pelajaran');

        $data = array();

        $data = array(
            'status_data' => 'inactive'
        );

        $where = array(
            'id_mata_pelajaran' => $id_mata_pelajaran
        );

        $update =  $this->M_universal->update_data($where, 'mata_pelajaran', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menghapus Data ";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menghapus Data ";
            $this->response($result, 200);
        }
    }

    function add_data_post()
    {
        // ambil data
        $nama = $this->post('nama');

        $data = array(
            'nama'  => $nama
        );

        $insert =  $this->M_universal->input_data('mata_pelajaran', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menambah Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menambah Data";
            $this->response($result, 200);
        }
    }
}
