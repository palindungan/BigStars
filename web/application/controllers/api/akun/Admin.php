<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Admin extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function get_data_post()
    {
        // menangkap data
        $id_admin = $this->post('id_admin');

        // variable array
        $result = array();
        $result['data_result'] = array();

        // data array untuk where db
        $where = array(
            'id_admin' => $id_admin,
            'status_data' => 'active'
        );

        // mengambil data
        $query = $this->M_universal->get_data('admin', $where);

        // cek apakah ada data 
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_admin' => $row["id_admin"],
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'foto' => $row["foto"]
                );

                array_push($result['data_result'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Success Berhasil Masuk";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Error Data Admin Tidak Ditemukan";
            $this->response($result, 200);
        }
    }

    function update_data_post()
    {
        $id_admin   = $this->post('id_admin');
        $nama       = $this->post('nama');
        $username   = $this->post('username');
        $password   = $this->post('password');
        $foto       = $this->post('foto');
        $nama_foto  = $id_admin;

        $where = array(
            'id_admin' => $id_admin
        );

        $cek_foto = "";
        // mengambil data dari database
        $query = $this->M_universal->get_data('admin', $where);
        foreach ($query->result_array() as $row) {
            $cek_foto = $row["foto"];
        }

        if (!empty($foto)) {

            if ($cek_foto != "NONE") {
                // lokasi gambar berada
                $path = './upload/image/admin/';
                $format = '.jpg';
                unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
            }

            $path2 = "./upload/image/admin/$nama_foto.jpg";
            file_put_contents($path2, base64_decode($foto));
        } else {

            if ($cek_foto == "NONE") {
                $nama_foto = "NONE";
            }
        }

        $data = array();

        if (empty($password)) {
            $data = array(
                'id_admin'  => $id_admin,
                'nama'      => $nama,
                'username'  => $username,
                'foto'      => $nama_foto
            );
        } else {
            $data = array(
                'id_admin'  => $id_admin,
                'nama'      => $nama,
                'username'  => $username,
                'password'  => password_hash($password, PASSWORD_DEFAULT),
                'foto'      => $nama_foto
            );
        }

        $update =  $this->M_universal->update_data($where, 'admin', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Update Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Update Data";
            $this->response(array($result, 502));
        }
    }
}
