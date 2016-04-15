<?php
print_r($_POST);
error_reporting(E_ALL); ini_set('display_errors', 1);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
require_once 'include\config.php';
$response=array("error"=>FALSE);
echo $_POST['username'];
if(isset($_POST['username'])&&isset($_POST['lat'])&&isset($_POST['long']))
{
	//Get the POST parameters
	$username=$_POST['username'];
	$lat=(double)$_POST['lat'];
	$lon=(double)$_POST['long'];
	//$loc=$lat." ".$long;
	$stmt=$mysqli->prepare("update volunteer set lat=?,lon=? where username=?");
	$stmt->bind_param("dds",$lat,$lon,$username);
	$result=$stmt->execute();
	$stmt->close();
	if($result)
		{

            $response["error"]=FALSE;
			$response["username"]=$username;
			echo json_encode($response);
		}
		else
		{
			$response['error']=TRUE;
			$response['error_msg']="Database could not be updated.";
			echo json_encode($response);
		}
}
else
{
	$response['error'] = TRUE;
    $response['error_msg'] = "Required parameters (username, email or password) are missing!";
    echo json_encode($response);
}
?>
