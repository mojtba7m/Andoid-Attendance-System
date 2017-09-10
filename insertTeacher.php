<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'con.php';
		global $connect;
}
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
if(isset($_POST['tid'],$_POST['name'],$_POST['password'],$_POST['email'])){
		$tid= $_POST["tid"];
		$name=$_POST["name"];
		$password= $_POST["password"];
		$email = $_POST["email"];
	if(!empty($tid) && !empty($name) && !empty($password)&& !empty($email))
	{

		$sql= "select * from teachers where tid like '".$tid."';";
		$result= mysqli_query($connect,$sql);
		$response= array();
		if(mysqli_num_rows($result)>0)
		{
				$json['reg_failed'] = ' User already exist, please Signup again with a different index ';
				echo json_encode($json);
		}
 		else {

			$sql = "INSERT INTO teachers (tid,name,password,email) VALUES ('$_POST[tid]', '$_POST[name]','$_POST[password]','$_POST[email]');";
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