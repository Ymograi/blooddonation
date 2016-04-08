<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
print_r($_POST);
//Registration endpoint to communiate between app and DB
//echo "here";
require_once 'include\db_functions.php';
//require_once 'include/db_connect.php';

//JSON response array
$response=array('error'=>FALSE);

// echo ("username".isset($_POST['username']));
// echo ("password".isset($_POST['password']));
// echo ("email".isset($_POST['email']));
// echo ("name".isset($_POST['name']));
// echo ("phone".isset($_POST['phone']));
// echo ("idtype".isset($_POST['id_type']));
// echo ("idno".isset($_POST['id_num']));

if(isset($_POST['username'])&&isset($_POST['password'])&&isset($_POST['email'])&&isset($_POST['name'])
&&isset($_POST['phone'])&&isset($_POST['id_type'])&&isset($_POST['id_num']))
{

	echo "ifblock";
	//Get the POST parameters
	$email=$_POST['email'];
	$password=$_POST['password'];
	$username=$_POST['username'];
	$name=$_POST['name'];
	$phone=$_POST['phone'];
	$id_type=$_POST['id_type'];
	$id_number=$_POST['id_num'];
	//echo $username;
	//Check if user already exists
	if(checkUser($username,'seeker',$mysqli))
	{
		$response['error']=TRUE;
		$response['error_msg']="Seeker with username ".$username." already exists.";
		echo json_encode($response);
	}
	else
	{
		//Create new user
		$user=addSeeker($username,$password,$email,$name,$phone,$id_type,$id_number,$mysqli);
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
