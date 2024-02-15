<?php
include "conn.php";
session_start();

if(isset($_POST['register'])){
    $cn = $_POST['cn'];
    $email = $_POST['email'];
    $pass = $_POST['pass'];

    // Validate Email
    if(!filter_var($email, FILTER_VALIDATE_EMAIL)){
    
    } else {
        $check = mysqli_query($conn, "SELECT * FROM users WHERE email='$email'");
        $n = mysqli_num_rows($check);

        if($n <= 0){
            $insert = mysqli_query($conn, "INSERT INTO users VALUES('0','$cn','$email','$pass')");
            if($insert == true){
                ?>
                <script>
                alert ("Successfully Registered!");
                window.location.href = "index.php";
                </script>
                <?php
            } else {
                ?>
                <script>
                alert("Error in Registration!");
                window.location.href = "reg.php";
                </script>
                <?php
            }
        } else {
            ?>
            <script>
            alert ("Email already exists!");
            window.location.href = "reg.php";
            </script>
            <?php
        }
    }
}

if(isset($_POST['login'])){
    $email = $_POST['email'];
    $pass = $_POST['pass'];

    // Validate Email
    if(!filter_var($email, FILTER_VALIDATE_EMAIL)){
        
    } else {
        $check = mysqli_query($conn, "SELECT * FROM users WHERE email='$email' AND pass='$pass'");
        $num = mysqli_num_rows($check);

        if($num >= 1){
            ?>
 
            <script>
            alert("Login succesfuly!");
            window.location.href="reg.php";
        </script>
        <?php
        }else{
            ?>
        
            <script>
            alert("wrong email.please try again!");
            window.location.href="index.php";
        </script>
        <?php
        }
        }
        
    }
           ?>