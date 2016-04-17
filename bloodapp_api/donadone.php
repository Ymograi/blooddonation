<?php
require_once '\include\config.php';
error_reporting(E_ALL); ini_set('display_errors', 1);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
$username=$_POST["username"];
$response["error"]="true";
echo $username;
print_r($response);
$stm="select volunteer,date_time,agency from donates_blood_to where volunteer='".$username."';";
echo $stm;
$result=$mysqli->query($stm);
  if($result->num_rows>0)
  {
    $json=mysqli_fetch_all($result,MYSQLI_ASSOC);
    print_r ($json);
    $response["error"]="False";
    $response["dondone"]=$json;
 }
 $rows=$result->num_rows;
 $response["nos"]=$rows;
echo json_encode($response);


?>
