<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($tid,$password)
		{
			$query = "Select * from teachers where tid='$tid' and password = '$password' ";
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0){
				$query="Select * from teachers where tid='$tid' and status= '0';";
				$result=mysqli_query($this->connection, $query);
				if(mysqli_num_rows($result)>0){
					$json['NotActive'] = 'Account is not activated , wait for Admin';
					echo json_encode($json);
					mysqli_close($this->connection);
				}
				else{
				$json['success'] = ' Welcome ';
				echo json_encode($json);
				mysqli_close($this -> connection);
				}
			}
			else{
				
				$json['error'] = 'Wrong ID or password';
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['tid'],$_POST['password'])) {
		$tid = $_POST['tid'];
		$password = $_POST['password'];
		
		if(!empty($tid) && !empty($password)){
			
//			$encrypted_password = md5($password);
			$user-> does_user_exist($tid,$password);
			
		}else{
			echo json_encode("you must type both inputs");
		}
		
	}
?>