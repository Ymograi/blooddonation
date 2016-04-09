<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
require_once 'include\db_functions.php';
//print_r($_POST);
//echo "Here0";
//JSON response array
$response=array("error"=>FALSE);
if(isset($_POST['username'])&&isset($_POST['type']))
{
  $type=$_POST['type'];
  $username=$_POST['username'];
  if(strcmp($type,'admin')!=0)
  {
    $response['error']=TRUE;
    $response['error_message']='Invalid user';
    echo json_encode($response);
  }
  else
  {
    $stm="select * from volunteer where verified_by IS NULL; ";

  	//echo $stm;

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
      $response['error_msg']='All donors have been verified.';
      $response['no']=0;
      echo json_encode($response);
    }
  }
}
else
{
  $response['error']=TRUE;
	$response['error_msg']="Required parameters are missing.";
	echo json_encode($response);
}
