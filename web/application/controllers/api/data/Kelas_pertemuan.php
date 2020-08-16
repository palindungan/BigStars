<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Kelas_pertemuan extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_kode");
    }

    function list_data_get()
    {
        $id_pengajar = $this->post('id_pengajar');

        // variable array
        $result = array();
        $result['data_result'] = array();

        // mengambil data
        $query = $this->M_universal->get_data_or('view_Kelas_pertemuan', $id_pengajar, 'id_pengajar', 'id_sharing');

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                $data_id = array(
                    'id_Kelas_pertemuan' => $row["id_Kelas_pertemuan"],
                    'status_data' => 'active'
                );

                $count = $this->M_universal->get_data('view_kelas_pertemuan_detail', $data_id)->num_rows();

                // ambil detail data db
                $data = array(
                    'id_Kelas_pertemuan' => $row["id_Kelas_pertemuan"],
                    'hari' => $row["hari"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],
                    'harga_fee' => $row["harga_fee"],
                    'harga_spp' => $row["harga_spp"],
                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_pelajaran' => $row["nama_pelajaran"],
                    'id_pengajar' => $row["id_pengajar"],
                    'nama_pengajar' => $row["nama_pengajar"],
                    'id_sharing' => $row["id_sharing"],
                    'nama_sharing' => $row["nama_sharing"],
                    'jumlah_murid' => $count
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
        $id_Kelas_pertemuan = $this->post('id_Kelas_pertemuan');

        $data = array();

        $data = array(
            'status_data' => 'inactive'
        );

        $where = array(
            'id_Kelas_pertemuan' => $id_Kelas_pertemuan
        );

        $update =  $this->M_universal->update_data($where, 'Kelas_pertemuan', $data);
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

        $insert =  $this->M_universal->input_data('Kelas_pertemuan', $data);
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
        $id_Kelas_pertemuan = $this->post('id_Kelas_pertemuan');
        $nama = $this->post('nama');

        $data = array();

        $data = array(
            'id_Kelas_pertemuan' => $id_Kelas_pertemuan,
            'nama'          => $nama
        );

        $where = array(
            'id_Kelas_pertemuan' => $id_Kelas_pertemuan
        );

        $update =  $this->M_universal->update_data($where, 'Kelas_pertemuan', $data);
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
