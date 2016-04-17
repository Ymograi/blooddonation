<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
require_once 'include\db_functions.php';
// print_r($_POST);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
//echo "Here0";
// JSON response array
$response=array("error"=>FALSE);
if(isset($_POST['lat'])&&isset($_POST['lon'])&&isset($_POST['bld']))
{
  $lat=$_POST['lat'];
  $longi=$_POST['lon'];
  $bld=$_POST['bld'];

  $stm="select * from volunteer where lat is NOT NULL and lon is NOT NULL and verified_by is NOT NULL and blood_group='$bld'";

  // echo $stm;

  $result=$mysqli->query($stm);

  if(!$result)
  {
    $response['error']=TRUE;
    $response['error_msg']="Error in fetching data.";
    echo json_encode($response);
  }

  else if($result->num_rows>0)
  {
    //$students=$result->fetch_assoc();
    $json = mysqli_fetch_all ($result, MYSQLI_ASSOC);
    $response['donors']=json_encode($json);
    $response['no']=$result->num_rows;
    $response['error_msg']="Fetched ".$result->num_rows." rows.";
    echo json_encode($response);
  }
  else
  {
    $response['error_msg']='No donors found.';
    $response['no']=0;
    echo json_encode($response);
  }
}
else
{
  $response['error']=TRUE;
	$response['error_msg']="Required parameters are missing.";
	echo json_encode($response);
}
?>
