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

    function id_pertemuan()
    {
        date_default_timezone_set('Asia/Jakarta');
        $field = "id_pertemuan";
        $tabel = "pertemuan";
        $digit = "4";
        $ymd = date('ymd');

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel WHERE SUBSTR($field, 3, 6) = $ymd LIMIT 1");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = sprintf('%0' . $digit . 's',  $tmp); // 0 berapa kali + $tmp
            }
        } else {
            $kd = "0001";
        }
        return 'PT' . date('ymd') . '-' . $kd; // SELECT SUBSTR('PT191218-0001', 3, 6); dari digit ke 3 sampai 6 digit seanjutnya
    }

    function id_bayar_fee()
    {
        date_default_timezone_set('Asia/Jakarta');
        $field = "id_bayar_fee";
        $tabel = "bayar_fee";
        $digit = "4";
        $ymd = date('ymd');

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel WHERE SUBSTR($field, 3, 6) = $ymd LIMIT 1");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = sprintf('%0' . $digit . 's',  $tmp); // 0 berapa kali + $tmp
            }
        } else {
            $kd = "0001";
        }
        return 'BF' . date('ymd') . '-' . $kd; // SELECT SUBSTR('PT191218-0001', 3, 6); dari digit ke 3 sampai 6 digit seanjutnya
    }

    function id_bayar_spp()
    {
        date_default_timezone_set('Asia/Jakarta');
        $field = "id_bayar_spp";
        $tabel = "bayar_spp";
        $digit = "4";
        $ymd = date('ymd');

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel WHERE SUBSTR($field, 3, 6) = $ymd LIMIT 1");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = sprintf('%0' . $digit . 's',  $tmp); // 0 berapa kali + $tmp
            }
        } else {
            $kd = "0001";
        }
        return 'SP' . date('ymd') . '-' . $kd; // SELECT SUBSTR('PT191218-0001', 3, 6); dari digit ke 3 sampai 6 digit seanjutnya
    }
}
