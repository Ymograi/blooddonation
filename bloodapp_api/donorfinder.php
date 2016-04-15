<?php
require_once'\include\config.php';
error_reporting(E_ALL); ini_set('display_errors', 1);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
require_once'\include\db_connect.php';
//$lat1=$_POST["lat"];
//$lon1=$_POST["lon"];
$lat1=0.000;
$lon1=0.000;
// $lat2=0.006362536817;
// $lon2=0.006362536817;
$stm="select lat,lon from volunteer where lat is NOT NULL and lon is NOT NULL";
// and lat between ".$lat1-.006362546817."and ".$lat1+.006362546817."and lon between".$lon1-.006362546817."and ".$lon1+.006362546817.";";
echo $stm."<br>";
$result=$mysqli->query($stm);
$json=mysqli_fetch_all($result,MYSQLI_ASSOC);
$rows=$result->num_rows;
echo $rows."<br>";
$json[0]["result"]="yes";
print_r($json[0]);
// //$set=array();
// //$result=$mysqli->query($stm);
// $theta = $lon1 - $lon2;
// $dist = sin(deg2rad($lat1)) * sin(deg2rad($lat2)) +  cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * cos(deg2rad($theta));
// $dist = acos($dist);
// $dist = rad2deg($dist);
// $kms = $dist * 60 * 1.1515*1.609344;
// //$unit = strtoupper($unit);
// echo ($kms);




?>
