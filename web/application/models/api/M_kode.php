<?php
class M_kode extends CI_Model
{

    function id_murid()
    {
        $field = "id_murid";
        $tabel = "murid";
        $digit = "5";
        $kode = "MR";

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
            }
        } else {
            $kd = "MR00001";
        }
        return $kd;
    }

    function id_pengajar()
    {
        $field = "id_pengajar";
        $tabel = "pengajar";
        $digit = "5";
        $kode = "PE";

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
            }
        } else {
            $kd = "PE00001";
        }
        return $kd;
    }


    function id_kelas_pertemuan()
    {
        $field = "id_kelas_pertemuan";
        $tabel = "kelas_pertemuan";
        $digit = "5";
        $kode = "KL";

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
            }
        } else {
            $kd = "KL00001";
        }
        return $kd;
    }

    // function get_id_wali_murid()
    // {
    //     $field = "id_wali_murid";
    //     $tabel = "wali_murid";
    //     $digit = "3";
    //     $kode = "WM";

    //     $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
    //     $kd = "";
    //     if ($q->num_rows() > 0) {
    //         foreach ($q->result() as $k) {
    //             $tmp = ((int) $k->kd_max) + 1;
    //             $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
    //         }
    //     } else {
    //         $kd = "WM001";
    //     }
    //     return $kd;
    // }

    // function get_id_mata_pelajaran()
    // {
    //     $field = "id_mata_pelajaran";
    //     $tabel = "mata_pelajaran";
    //     $digit = "2";
    //     $kode = "MP";

    //     $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
    //     $kd = "";
    //     if ($q->num_rows() > 0) {
    //         foreach ($q->result() as $k) {
    //             $tmp = ((int) $k->kd_max) + 1;
    //             $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
    //         }
    //     } else {
    //         $kd = "MP01";
    //     }
    //     return $kd;
    // }
}
