<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
//print_r($_POST);
//Registration endpoint to communiate between app and DB
//echo "here";
require_once 'include\db_functions.php';
//require_once 'include/db_connect.php';

//JSON response array
$response=array('error'=>FALSE);


if(isset($_POST['username'])&&isset($_POST['password'])&&isset($_POST['email'])&&isset($_POST['name'])&&isset($_POST['phone'])
&&isset($_POST['id_type'])&&isset($_POST['id_num'])&&isset($_POST['blood_group'])&&isset($_POST['rh_factor']))
{
	//echo "ifblock";
	//Get the POST parameters
	$email=$_POST['email'];
	$password=$_POST['password'];
	$username=$_POST['username'];
	$name=$_POST['name'];
	$phone=$_POST['phone'];
	$id_type=$_POST['id_type'];
	$id_number=$_POST['id_num'];
	$blood_group=$_POST['blood_group'];
	$rh_factor=$_POST['rh_factor'];
	//echo $username;
	//Check if user already exists
	if(checkUser($username,'volunteer',$mysqli))
	{
		$response['error']=TRUE;
		$response['error_msg']="Volunteer with username ".$username." already exists.";
		echo json_encode($response);
	}
	else
	{
		//Create new user
		$user=addVolunteer($username,$password,$email,$name,$phone,$id_type,$id_number,$blood_group,$rh_factor,$mysqli);
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
    $response['error_msg'] = "Required parameters are missing!";
    echo json_encode($response);
}
?>
