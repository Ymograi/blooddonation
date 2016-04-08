<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
print_r($_POST);
//Registration endpoint to communiate between app and DB
//echo "here";
require_once 'include/db_functions.php';
//require_once 'include/db_connect.php';

//JSON response array
$response=array('error'=>FALSE);

if(isset($_POST['username'])&&isset($_POST['password'])&&isset($_POST['email'])&&isset($_POST['type']))
{
	echo "ifblock";
	//Get the POST parameters
	$email=$_POST['email'];
	$password=$_POST['password'];
	$username=$_POST['username'];
	$type=$_POST['type'];
	echo $username;
	//Check if user already exists

	if(checkUser($username,$type,$mysqli))
	{
		echo "If block for checkUser.";
		$response['error']=TRUE;
		$response['error_msg']="User with username ".$username." already exists.";
		echo json_encode($response);
	}
	else
	{
		//Create new user
		$user=addUser($username,$password,$email,$type,$mysqli);
		if($user)
		{
			$response["error"]=FALSE;
			$response["username"]=$user["username"];
			$response["email"]=$user["email"];
			echo json_encode($response);
		}
		else
		{
			//Data not stored
			$response['error']=TRUE;
			$response['error_msg']="Error in storing data.";
			echo json_encode($response);
		}
	}

}
else
{
	$response['error'] = TRUE;
    $response['error_msg'] = "Required parameters (username, email or password) are missing!";
    echo json_encode($response);
}
?>
