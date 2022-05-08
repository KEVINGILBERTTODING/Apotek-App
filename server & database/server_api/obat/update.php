<?php
include "koneksi.php";

$id =  $_POST["id_obat"];
$kode =  $_POST["kode_obat"];
$namaobat =  $_POST["nama_obat"];
$satuanobat =  $_POST["satuan_obat"];
$jumlahobat =  $_POST["jumlah_obat"];
$jenisobat =  $_POST["jenis_obat"];
$descobat =  $_POST["desc_obat"];
$expireddate =  $_POST["expired_date"];

$query = "UPDATE obat SET kode_obat='$kode', nama_obat='$namaobat', satuan_obat='$satuanobat', jumlah='$jumlahobat', jenis='$jenisobat' , deskripsi='$descobat', expired='$expireddate' WHERE id='$id'";
$result = mysqli_query($con, $query);

$response = array();
if ($result) {
    $response['message'] = "Success ! Data berhasil diperbarui";
} else {
    $response['message'] = "Failed ! Data gagal diperbarui";
}
echo json_encode($response);
