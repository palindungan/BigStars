<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Pengajar extends REST_Controller
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
        $query = $this->M_universal->get_data('pengajar', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_pengajar' => $row["id_pengajar"],
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'alamat' => $row["alamat"],
                    'no_hp' => $row["no_hp"],
                    'foto' => $row["foto"]
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
        $id_pengajar = $this->post('id_pengajar');

        $data = array();

        $data = array(
            'status_data' => 'inactive'
        );

        $where = array(
            'id_pengajar' => $id_pengajar
        );

        $update =  $this->M_universal->update_data($where, 'pengajar', $data);
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
        $id_pengajar    = $this->M_kode->id_pengajar();
        $nama           = $this->post('nama');
        $username       = $this->post('username');
        $password       = $this->post('password');
        $alamat         = $this->post('alamat');
        $no_hp          = $this->post('no_hp');
        $foto           = $this->post('foto');

        $nama_foto = "NONE";

        if (!empty($foto)) {
            $nama_foto = $id_pengajar;
        }

        $data = array(
            'id_pengajar'   => $id_pengajar,
            'nama'          => $nama,
            'username'      => $username,
            'password'      => password_hash($password, PASSWORD_DEFAULT),
            'alamat'        => $alamat,
            'no_hp'         => $no_hp,
            'foto'          => $nama_foto
        );

        $insert =  $this->M_universal->input_data('pengajar', $data);
        if ($insert) {

            if (!empty($foto)) {
                $path = "./upload/image/pengajar/$nama_foto.jpg";
                file_put_contents($path, base64_decode($foto));
            }

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
        $id_pengajar       = $this->post('id_pengajar');
        $id_wali_pengajar  = $this->post('id_wali_pengajar');
        $nama           = $this->post('nama');
        $foto           = $this->post('foto');

        $nama_foto      = $id_pengajar;

        $where = array(
            'id_pengajar' => $id_pengajar
        );

        $cek_foto = "";
        // mengambil data dari database
        $query = $this->M_universal->get_data('pengajar', $where);
        foreach ($query->result_array() as $row) {
            $cek_foto = $row["foto"];
        }

        if (!empty($foto)) {

            if ($cek_foto != "NONE") {
                // lokasi gambar berada
                $path = './upload/image/pengajar/';
                $format = '.jpg';
                unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
            }

            $path2 = "./upload/image/pengajar/$nama_foto.jpg";
            file_put_contents($path2, base64_decode($foto));
        } else {

            if ($cek_foto == "NONE") {
                $nama_foto = "NONE";
            }
        }

        $data = array();

        $data = array(
            'id_pengajar'      => $id_pengajar,
            'id_wali_pengajar' => $id_wali_pengajar,
            'nama'          => $nama,
            'foto'          => $nama_foto
        );

        $update =  $this->M_universal->update_data($where, 'pengajar', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Update Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Update Data";
            $this->response($result, 200);
        }
    }
}
