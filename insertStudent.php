<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'con.php';
		global $connect;
}














if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

if(isset($_POST['sid'],$_POST['name'],$_POST['password'],$_POST['batch'],$_POST['email'])){
		$sid= $_POST["sid"];
		$name=$_POST["name"];
		$password= $_POST["password"];
		$batch=$_POST["batch"];
		$email = $_POST["email"];
	if(!empty($sid) && !empty($name) && !empty($password) && !empty($batch) && !empty($email))
	{

		$sql= "select * from students where sid like '".$sid."';";
		$result= mysqli_query($connect,$sql);
		$response= array();
		if(mysqli_num_rows($result)>0)
		{
				$json['reg_failed'] = ' User already exist, please Signup again with a different index ';
				echo json_encode($json);

		}
 		else {

			$sql = "INSERT INTO students  (sid,name,password,batch,email) VALUES ('$_POST[sid]','$_POST[name]','$_POST[password]','$_POST[batch]','$_POST[email]');";
			$result = mysqli_query($connect,$sql);
				$json['success'] = 'Registration Success...';
			echo json_encode($json);
		}

}
else{
				$json['empty'] = 'you must type all inputs';

			echo json_encode($json);
	
}
	mysqli_close($connect); 

}

?>