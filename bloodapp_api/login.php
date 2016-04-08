<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
require_once 'include\db_functions.php';
//print_r($_POST);
//echo "Here0";
//JSON response array
$response=array("error"=>FALSE);

if(isset($_POST['username'])&&isset($_POST['password'])&&isset($_POST['type']))
{
	//Get the POST parameters
	$username=$_POST['username'];
	$password=$_POST['password'];
	$type=$_POST['type'];
	//echo "Main if";

	//Try to login user
	$user=loginUser($username,$password,$type,$mysqli);

	if($user!=false)
	{
		//echo "inner if";
		$response["error"]=FALSE;
		$response["username"]=$user["username"];
		$response["email"]=$user["email"];
		$response["type"]=$type;
		echo json_encode($response);
	}

	else
	{
		//Wrong credentials
		$response['error']=TRUE;
		$response['error_msg']="Incorrect login credentials.";
		echo json_encode($response);
	}
}
else
{
	$response["error"] = TRUE;
    $response["error_msg"] = "Required parameters username, password or user type are missing!";
    echo json_encode($response);
}
?>
