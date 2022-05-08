<?php
include "koneksi.php";

$id =  $_POST["id"];
$id_apoteker =  $_POST["id_apoteker"];
$nama =  $_POST["nama"];
$kota =  $_POST["kota"];
$no_hp =  $_POST["no_hp"];
$shift =  $_POST["shift"];
$alamat =  $_POST["alamat"];

$query = "UPDATE apoteker SET id_apoteker='$id_apoteker', nama='$nama', kota='$kota', no_hp='$no_hp', shift='$shift', alamat='$alamat' WHERE id='$id'";
$result = mysqli_query($con, $query);

$response = array();
if ($result) {
    $response['message'] = "Berhasil! Data berhasil diperbarui";
} else {
    $response['message'] = "Gagal! Data gagal diperbarui";
}
echo json_encode($response);
