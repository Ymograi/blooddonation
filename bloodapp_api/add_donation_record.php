<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
require_once 'include\db_functions.php';
// print_r($_POST);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
//echo "Here0";
// JSON response array
$response=array("error"=>FALSE);
if(isset($_POST['seeker'])&&isset($_POST['volunteer'])&&isset($_POST['agency'])&&isset($_POST['date_time']))
{
  // echo "inside main if";
  $seeker=$_POST['seeker'];
  $volunteer=$_POST['volunteer'];
  $agency=$_POST['agency'];
  $date_time=$_POST['date_time'];

  $stm1="select * from seeker where username='$seeker'";

  // echo $stm1;

  $result=$mysqli->query($stm1);
  if($result->num_rows==0)
  {
    $response['error']=TRUE;
    $response['error_msg']="No such seeker";
    echo json_encode($response);
  }
  else
  {
    $stm2="select * from volunteer where username='$volunteer'";

    // echo $stm;

    $result=$mysqli->query($stm2);
    if($result->num_rows==0)
    {
      $response['error']=TRUE;
      $response['error_msg']="No such volunteer";
      echo json_encode($response);
    }
    else
    {
      $stm3="insert into donates_blood_to values ('$seeker','$volunteer','$date_time','$agency')";
      // echo $stm3;
      $result=$mysqli->query($stm3);
      if(!$result)
      {
        $response['error']=TRUE;
        $response['error_msg']="Error in stroing data.";
        echo json_encode($response);
      }

      else
      {
        $response['error']=FALSE;
        $response['error_msg']="Database update successfully.";
        echo json_encode($response);
      }
    }
  }

}
else
{
  $response['error']=TRUE;
	$response['error_msg']="Required parameters are missing.";
	echo json_encode($response);
}
?>
