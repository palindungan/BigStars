<?php
class M_home extends CI_Model
{
    function tampil_data($table)
    {
        return $this->db->get($table);
    }

    function count_num_row($table)
    {
        return $this->db->get($table)->num_rows();
    }

    function input_data($table, $data)
    {
        $this->db->insert($table, $data);
    }

    function hapus_data($table, $where)
    {
        // idnya
        $this->db->where($where);

        // tabelnya
        $this->db->delete($table);
    }

    function get_data($table, $where)
    {
        return $this->db->get_where($table, $where);
    }

    function update_data($where, $data, $table)
    {
        $this->db->where($where);
        $this->db->update($table, $data);
    }
}
