<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Test extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function login_post()
    {
        // ambil data
        $nik = $this->post('nik');
        $password = $this->post('password');
        $device_id = $this->post('device_id');

        // variable array
        $result['data'] = array();

        $data = array(
            'name' => 'name',
            'address' => 'address',
            'social_media' => 'social_media',
            'phone_number' => 'phone_number',
            'register_status' => 'register_status',
            'resort_police_location' => 'resort_police_location',
            'driver_license_photo' => 'driver_license_photo',
            'nik' => $nik,
            'token' => '0000000000'
        );

        array_push($result['data'], $data);

        $result['data'] = (object) $result['data'];

        // membuat array untuk di transfer
        $result["success"] = true;
        $result["message"] = "Success Berhasil Masuk";
        $this->response($result, 200);
    }

    function register_post()
    {
        // ambil data
        $name = $this->post('name');
        $address = $this->post('address');
        $social_media = $this->post('social_media');
        $phone_number = $this->post('phone_number');
        $register_status = $this->post('register_status');
        $resort_police_location = $this->post('resort_police_location');
        $driver_license_photo = $this->post('driver_license_photo');
        $nik = $this->post('nik');
        $password = $this->post('password');

        // variable array
        $result['data'] = array();

        $data = array(
            'name' => 'name',
            'address' => 'address',
            'social_media' => 'social_media',
            'phone_number' => 'phone_number',
            'register_status' => 'register_status',
            'resort_police_location' => 'resort_police_location',
            'driver_license_photo' => 'driver_license_photo',
            'nik' => $nik,
            'token' => '0000000000'
        );

        array_push($result['data'], $data);

        $result['data'] = (object) $result['data'];

        // membuat array untuk di transfer
        $result["success"] = true;
        $result["message"] = "Success Berhasil Daftar";
        $this->response($result, 200);
    }

    function home_post()
    {
        // ambil data
        $nik = $this->post('nik');
        
        // variable array
        $result['data'] = array();

        $data = array(
            'nik' => 'nik',
            'token' => '0000000000'
        );

        array_push($result['data'], $data);

        $result['data'] = (object) $result['data'];

        // membuat array untuk di transfer
        $result["success"] = true;
        $result["message"] = "Success Berhasil Request Home";
        $this->response($result, 200);
    }
}
