package com.apotekapp.apotek.Utill;

public class ServerAPI {


    //Server API untuk data obat
    public static final String URL_Create_Obat = "http://192.168.11.19/apotek/obat/create.php";
    public static final String URL_Read_Obat = "http://192.168.11.19/apotek/obat/read.php";
    public static final String URL_DELETE_Obat = "http://192.168.11.19/apotek/obat/delete.php";
    public static final String URL_Update_Obat = "http://192.168.11.19/apotek/obat/update.php";

    public static final String URL_Read_Obat_Sirup = "http://192.168.11.19/apotek/obat/read_sirup.php";
    public static final String URL_Read_Obat_Tablet = "http://192.168.11.19/apotek/obat/read_tablet.php";
    public static final String URL_Read_Obat_Oles = "http://192.168.11.19/apotek/obat/read_obat_oles.php";
    public static final String URL_Read_Obat_Lain = "http://192.168.11.19/apotek/obat/read_obat_lain.php";


    // Server API untuk data apoteker
    public static final String URL_Read_Apoteker = "http://192.168.11.19/apotek/apoteker/read.php";
    public static final String URL_Create_Apoteker = "http://192.168.11.19/apotek/apoteker/create.php";
    public static final String URL_DELETE_Apoteker = "http://192.168.11.19/apotek/apoteker/delete.php";
    public static final String URL_Update_Apoteker = "http://192.168.11.19/apotek/apoteker/update.php";

    // Server API untuk data users
    public static final String URL_Login = "http://192.168.11.19/apotek/users/login.php";
    public static final String URL_Register = "http://192.168.11.19/apotek/users/register.php";
    public static final String URL_Update_Login = "http://192.168.11.19/apotek/users/update_login_user.php";

}
