<?php
error_reporting(E_ALL); ini_set('display_errors', 1);
//Contains the functions required to query and update the database
//echo "Functions";
require_once 'include\config.php';
	// Function to add user to system
	function addUser($username,$password,$type,$email,$mysqli)
	{
		$hash=hashSSHA($password);
		$encrypted_password=$hash["encrypt"];
		$salt=$hash["salt"];
		//$conn=new mysqli("localhost", "root", "", "adbms_api") or die("Error connecting to database.");
	    //echo "connection created";
		$stmt=$mysqli->prepare("insert into admin values (?,?,?,?)");
		$stmt->bind_param("ssss",$username,$encrypted_password,$email,$salt);
		$result=$stmt->execute();
		$stmt->close();

		//Check for successful store in DB
		if($result)
		{
			$stmt=$mysqli->prepare("select * from admin where username=?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
		}
		else
			return false;
	}

	//Function to validate user credentials for login
	function loginUser($username,$password,$type,$mysqli)
	{   //$conn=new mysqli("localhost", "root", "", "adbms_api") or die("Error connecting to database.");
	    //echo "loginUser";
			if(strcmp($type,'admin')==0)
				$stmt=$mysqli->prepare("select * from admin where username=?");
			elseif(strcmp($type,'volunteer')==0)
				$stmt=$mysqli->prepare("select * from volunteer where username=?");
			else {
				$stmt=$mysqli->prepare("select * from seeker where username=?");
			}
		$stmt->bind_param("s",$username);

		if($stmt->execute())
		{
			$user=$stmt->get_result()->fetch_assoc();
			$stmt->close();

			//verify the user's password
			$salt=$user['salt'];
			$encrypted_password=$user['password'];
			$hash=checkhashSSHA($salt,$password);//Generating the encrypted pasword from user's input
			if($encrypted_password==$hash)
			{
				//User credentials are valid
				return $user;
			}
		}
		else
		{
				return false;
		}
	}

	//Function to check existence of a user
	function checkUser($username,$type,$mysqli)
	{	//$conn=new mysqli("localhost", "root", "", "adbms_api") or die("Error connecting to database.");
	    //echo "connection created";
		$stmt=$mysqli->prepare("select username from $type where username=?");
		$stmt->bind_param("s",$username);
		$stmt->execute();
		$stmt->store_result();

		if($stmt->num_rows>0)
		{
			//User exists
			$stmt->close();
			return true;
		}
		else
		{
			$stmt->close();
			return false;
		}

	}

	function addSeeker($username,$password,$email,$name,$phone,$id_type,$id_number,$mysqli)
	{
		$hash=hashSSHA($password);
		$encrypted_password=$hash["encrypt"];
		$salt=$hash["salt"];
		//$conn=new mysqli("localhost", "root", "", "adbms_api") or die("Error connecting to database.");
	    //echo "connection created";
		$stmt=$mysqli->prepare("insert into seeker(username,password,salt,email,name,phone,id_type,id_number) values (?,?,?,?,?,?,?,?)");
		$stmt->bind_param("ssssssss",$username,$encrypted_password,$salt,$email,$name,$phone,$id_type,$id_number);
		$result=$stmt->execute();
		$stmt->close();

		//Check for successful store in DB
		if($result)
		{
			$stmt=$mysqli->prepare("select * from seeker where username=?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
      $stmt->close();
      return $user;
		}
		else
			return false;
	}

	function addVolunteer($username,$password,$email,$name,$phone,$id_type,$id_number,$blood_group,$rh_factor,$mysqli)
	{
		$hash=hashSSHA($password);
		$encrypted_password=$hash["encrypt"];
		$salt=$hash["salt"];
		//$conn=new mysqli("localhost", "root", "", "adbms_api") or die("Error connecting to database.");
			//echo "connection created";
		$stmt=$mysqli->prepare("insert into volunteer(username,password,salt,email,name,phone,id_type,id_number,blood_group,rh_factor) values (?,?,?,?,?,?,?,?,?,?);");
		$stmt->bind_param("ssssssssss",$username,$encrypted_password,$salt,$email,$name,$phone,$id_type,$id_number,$blood_group,$rh_factor);
		$result=$stmt->execute();
		$stmt->close();

		//Check for successful store in DB
		if($result)
		{
			$stmt=$mysqli->prepare("select * from volunteer where username=?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			return $user;
		}
		else
			return false;
	}

	//Function to encrypt password and return the salt and password
	function hashSSHA($password)
	{
		$salt=sha1(rand());
		$salt=substr($salt,0,10);
		$encrypt=base64_encode(sha1($password.$salt,true).$salt);
		$hash=array("salt"=>$salt,"encrypt"=>$encrypt);
		return $hash;
	}

	//Function to check is password matches
	function checkhashSSHA($salt,$password)
	{
		$hash=base64_encode(sha1($password.$salt,true).$salt);
		return $hash;
	}
?>
