<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
//print_r($_POST);
//Registration endpoint to communiate between app and DB
//echo "here";
require_once 'include\db_functions.php';
require_once 'PHPMailer\PHPMailerAutoload.php';
//require_once 'include/db_connect.php';

//JSON response array
$response=array('error'=>FALSE);


if(isset($_POST['username'])&&isset($_POST['password'])&&isset($_POST['email'])&&isset($_POST['name'])&&isset($_POST['phone'])
&&isset($_POST['id_type'])&&isset($_POST['id_num'])&&isset($_POST['blood_group']))
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
	
	//echo $username;
	$mail = new PHPMailer;
$mail->SMTPDebug = -3; //Enable SMTP debugging if <0 will show each and everything happening.
$mail->isSMTP();//Set PHPMailer to use SMTP
$mail->Host = "smtp.gmail.com";//Set SMTP host name
$mail->SMTPAuth = true;//Set this to true if SMTP host requires authentication to send email
$mail->Username = "bloodbank247365@gmail.com";//Provide username and password
$mail->Password = "bloodbank123";
$mail->SMTPSecure = "tls";//If SMTP requires TLS encryption then set it
$mail->Port = 587;//Set TCP port to connect to Mail Details
$mail->From = "bloodbankadmin@bloodbank.com";
$mail->FromName = "Blood Donation ";
$mail->isHTML(TRUE);
$mail->Subject = "Thank You $name for Signing up.";
$mail->Body = "<b>We look forward to Helping you.</b><br>";
$mail->AltBody = "";
$mail->AddAddress($email,"$name");
$mail->SMTPKeepAlive = true;
//$name=$dept.$prog.$year;
//$mail->AddAttachment("doc.pdf",$name);
echo "Reached here";
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
		$user=addVolunteer($username,$password,$email,$name,$phone,$id_type,$id_number,$blood_group,$mysqli);
		if($user)
		{
			$response["error"]=FALSE;
			$response["username"]=$user["username"];
			$response["email"]=$user["email"];
			$mail->send();
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
