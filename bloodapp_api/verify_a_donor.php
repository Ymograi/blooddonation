<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
require_once 'include\db_functions.php';
// print_r($_POST);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
//echo "Here0";
// JSON response array
$response=array("error"=>FALSE);
if(isset($_POST['username'])&&isset($_POST['type'])&&isset($_POST['valid']))
{
  // echo "Inside main if";
  $type=$_POST['type'];
  $username=$_POST['username'];//user name of donor to update
  $valid=$_POST['valid'];
  if(strcmp($type,'admin')!=0)
  {
    // echo "Inside not admin if";
    $response['error']=TRUE;
    $response['error_message']='Invalid user';
    echo json_encode($response);
  }
  // echo "After not admin if";

  else
  {
    $stm="update volunteer set verified_by = '$valid' where username = '$username'; ";

    // echo $stm;
  }


    $result=$mysqli->query($stm);

    if(!$result)
    {
      $response['error']=TRUE;
      $response['error_message']="Error in fetching data.";
      echo json_encode($response);
    }

    else
    {
      $response['error']=FALSE;
      $response['error_message']="Validated successfully";
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
