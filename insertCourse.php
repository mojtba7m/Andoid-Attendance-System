<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'con.php';
		global $connect;
}
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
if(isset($_POST['cid'],$_POST['name'],$_POST['tid'],$_POST['batch'])){
		$cid= $_POST["cid"];
		$name=$_POST["name"];
		$tid= $_POST["tid"];
		$batch = $_POST["batch"];
	if(!empty($cid) && !empty($name) && !empty($tid)&& !empty($batch))
	{

		$sql= "select * from courses where cid like '".$cid."';";
		$result= mysqli_query($connect,$sql);
		$response= array();
		if(mysqli_num_rows($result)>0)
		{
		$json['reg_failed'] = ' Course already exist, please Insert again with a different Course ID ';
				echo json_encode($json);
		}
 		else {

			$sql = "INSERT INTO courses (cid,name,tid,batch) VALUES ('$_POST[cid]', '$_POST[name]','$_POST[tid]','$_POST[batch]');";
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