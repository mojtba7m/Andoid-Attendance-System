<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($sid,$password)
		{
			$query = "Select * from students where sid='$sid' and password = '$password' ";
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0){
				$query="Select * from students where sid='$sid' and status= '0';";
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
				
				$json['error'] = 'Wrong Index or password';
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['sid'],$_POST['password'])) {
		$sid = $_POST['sid'];
		$password = $_POST['password'];
		
		if(!empty($sid) && !empty($password)){
			
//			$encrypted_password = md5($password);
			$user-> does_user_exist($sid,$password);
			
		}else{
			$json['empty'] = 'you must type both inputs';

			echo json_encode($json);
		}
		
	}
?>