<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Wali_murid extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
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
        $query = $this->M_universal->get_data('wali_murid', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_wali_murid' => $row["id_wali_murid"],
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'alamat' => $row["alamat"],
                    'no_hp' => $row["no_hp"]
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
        $id_wali_murid = $this->post('id_wali_murid');

        $data = array();

        $data = array(
            'status_data' => 'inactive'
        );

        $where = array(
            'id_wali_murid' => $id_wali_murid
        );

        $update =  $this->M_universal->update_data($where, 'wali_murid', $data);
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
        $username = $this->post('username');
        $password = $this->post('password');
        $alamat = $this->post('alamat');
        $no_hp = $this->post('no_hp');

        $data = array(
            'nama'          => $nama,
            'username'      => $username,
            'password'      => password_hash($password, PASSWORD_DEFAULT),
            'alamat'        => $alamat,
            'no_hp'         => $no_hp
        );

        $insert =  $this->M_universal->input_data('wali_murid', $data);
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

    function update_data_post()
    {
        $id_wali_murid = $this->post('id_wali_murid');
        $nama = $this->post('nama');
        $username = $this->post('username');
        $password = $this->post('password');
        $alamat = $this->post('alamat');
        $no_hp = $this->post('no_hp');

        $data = array();

        if (empty($password)) {
            $data = array(
                'id_wali_murid' => $id_wali_murid,
                'nama'          => $nama,
                'username'      => $username,
                'alamat'        => $alamat,
                'no_hp'         => $no_hp
            );
        } else {
            $data = array(
                'id_wali_murid' => $id_wali_murid,
                'nama'          => $nama,
                'username'      => $username,
                'password'      => password_hash($password, PASSWORD_DEFAULT),
                'alamat'        => $alamat,
                'no_hp'         => $no_hp
            );
        }

        $where = array(
            'id_wali_murid' => $id_wali_murid
        );

        $update =  $this->M_universal->update_data($where, 'wali_murid', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengupdate Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Mengupdate Data";
            $this->response($result, 200);
        }
    }
}
