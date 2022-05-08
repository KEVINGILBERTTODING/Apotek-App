<?php
include_once('koneksi.php');

$id_apoteker = $_POST['id_apoteker'];
$nama = $_POST['nama'];
$kota = $_POST['kota'];
$no_hp = $_POST['no_hp'];
$shift = $_POST['shift'];
$alamat = $_POST['alamat'];



$insert = "INSERT INTO apoteker (id_apoteker,nama,kota,no_hp,shift,alamat) 
VALUES('$id_apoteker','$nama','$kota','$no_hp','$shift','$alamat')";
$exeinsert = mysqli_query($con, $insert);
$response = array();
if ($exeinsert) {
    $response['code'] = 1;
    $response['message'] = "Berhasil! Data Apoteker berhasil ditambahkan";
} else {
    $response['code'] = 0;
    $response['message'] = "Gagal! Data Apoteker berhasil ditambahkan";
}
echo json_encode($response);
