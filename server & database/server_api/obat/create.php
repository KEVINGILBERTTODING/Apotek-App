<?php
include_once('koneksi.php');

$kode_obat = $_POST['kode_obat'];
$nama_obat = $_POST['nama_obat'];
$satuan_obat = $_POST['satuan_obat'];
$jumlah = $_POST['jumlah'];
$jenis = $_POST['jenis'];
$deskripsi = $_POST['deskripsi'];
$expired = $_POST['expired'];



$insert = "INSERT INTO obat (kode_obat,nama_obat,satuan_obat,jumlah, jenis, deskripsi, expired) 
VALUES('$kode_obat','$nama_obat','$satuan_obat','$jumlah', '$jenis', '$deskripsi','$expired')";
$exeinsert = mysqli_query($con, $insert);
$response = array();
if ($exeinsert) {
    $response['code'] = 1;
    $response['message'] = "Berhasil! Obat berhasil ditambahkan";
} else {
    $response['code'] = 0;
    $response['message'] = "Gagal! Obat gagal ditambahkan";
}
echo json_encode($response);
