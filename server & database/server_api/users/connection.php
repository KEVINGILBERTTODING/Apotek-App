<?php
define('HOSTNAME', 'localhost');
define('USERNAME', 'root');
define('PASSWORD', '');
define('DB_SELECT', 'apotek');
$koneksi = new mysqli(HOSTNAME, USERNAME, PASSWORD, DB_SELECT)
	or die("Koneksi ke database gagal");
