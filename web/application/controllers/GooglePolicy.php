<?php
class GooglePolicy extends CI_Controller
{
    public function privacyPolicy()
    {
        $this->load->view('google_policy/privacy_policy');
    }

    public function termsAndConditions()
    {
        $this->load->view('google_policy/terms_and_conditions');
    }
}
