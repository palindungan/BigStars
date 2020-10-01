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

    function list_data_post()
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

                $id_Kelas_pertemuan = $row["id_kelas_pertemuan"];

                $data_id = array(
                    'id_Kelas_pertemuan' =>  $id_Kelas_pertemuan,
                    'status_data' => 'active'
                );

                $count = $this->M_universal->get_data('kelas_pertemuan_detail', $data_id)->num_rows();

                // ambil detail data db
                $data = array(
                    'id_kelas_pertemuan' =>  $id_Kelas_pertemuan,
                    'hari' => $row["hari"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_berakhir' => $row["jam_berakhir"],
                    'harga_fee' => $row["harga_fee"],
                    'harga_spp' => $row["harga_spp"],
                    'id_sharing' => $row["id_sharing"],
                    'nama_sharing' => $row["nama_sharing"],
                    'status_data' => $row["status_data"],
                    'id_mata_pelajaran' => $row["id_mata_pelajaran"],
                    'nama_mata_pelajaran' => $row["nama_mata_pelajaran"],
                    'id_pengajar' => $row["id_pengajar"],
                    'nama_pengajar' => $row["nama_pengajar"],
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
        $id_kelas_pertemuan = $this->post('id_kelas_pertemuan');

        $data = array();

        $data = array(
            'status_data' => 'inactive'
        );

        $where = array(
            'id_kelas_pertemuan' => $id_kelas_pertemuan
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
        $id_kelas_pertemuan = $this->M_kode->id_kelas_pertemuan();
        $id_pengajar = $this->post('id_pengajar');
        $id_mata_pelajaran = $this->post('id_mata_pelajaran');
        $hari = $this->post('hari');
        $jam_mulai = $this->post('jam_mulai');
        $jam_berakhir = $this->post('jam_berakhir');
        $harga_fee = $this->post('harga_fee');
        $harga_spp = $this->post('harga_spp');
        $id_sharing = "null";
        $nama_sharing = "kosong";

        $data = array(
            'id_kelas_pertemuan'    => $id_kelas_pertemuan,
            'id_pengajar'           => $id_pengajar,
            'id_mata_pelajaran'     => $id_mata_pelajaran,
            'hari'                  => $hari,
            'jam_mulai'             => $jam_mulai,
            'jam_berakhir'          => $jam_berakhir,
            'harga_fee'             => $harga_fee,
            'harga_spp'             => $harga_spp,
            'id_sharing'            => $id_sharing,
            'nama_sharing'          => $nama_sharing,
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
        $id_kelas_pertemuan = $this->post('id_kelas_pertemuan');
        $id_pengajar = $this->post('id_pengajar');
        $id_mata_pelajaran = $this->post('id_mata_pelajaran');
        $hari = $this->post('hari');
        $jam_mulai = $this->post('jam_mulai');
        $jam_berakhir = $this->post('jam_berakhir');
        $harga_fee = $this->post('harga_fee');
        $harga_spp = $this->post('harga_spp');

        $data = array(
            'id_kelas_pertemuan'    => $id_kelas_pertemuan,
            'id_pengajar'           => $id_pengajar,
            'id_mata_pelajaran'     => $id_mata_pelajaran,
            'hari'                  => $hari,
            'jam_mulai'             => $jam_mulai,
            'jam_berakhir'          => $jam_berakhir,
            'harga_fee'             => $harga_fee,
            'harga_spp'             => $harga_spp
        );

        $where = array(
            'id_kelas_pertemuan' => $id_kelas_pertemuan
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

    function sharing_data_post()
    {
        $id_kelas_pertemuan = $this->post('id_kelas_pertemuan');
        $id_sharing = $this->post('id_sharing');
        $nama_sharing = $this->post('nama_sharing');

        $data = array(
            'id_sharing'            => $id_sharing,
            'nama_sharing'          => $nama_sharing
        );

        $where = array(
            'id_kelas_pertemuan' => $id_kelas_pertemuan
        );

        $update =  $this->M_universal->update_data($where, 'Kelas_pertemuan', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Dibagikan Kepada " . $nama_sharing;
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Sharing Data Kelas";
            $this->response($result, 200);
        }
    }

    function delete_sharing_data_post()
    {
        $id_kelas_pertemuan = $this->post('id_kelas_pertemuan');
        $id_sharing = "null";
        $nama_sharing = "kosong";

        $data = array(
            'id_sharing'            => $id_sharing,
            'nama_sharing'          => $nama_sharing
        );

        $where = array(
            'id_kelas_pertemuan' => $id_kelas_pertemuan
        );

        $update =  $this->M_universal->update_data($where, 'Kelas_pertemuan', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Menghapus Sharing";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Gagal Menghapus Sharing";
            $this->response($result, 200);
        }
    }
}
