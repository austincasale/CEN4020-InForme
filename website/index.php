<!-- Implemented by Dexter and Ernest -->

<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3/org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>

<head>
  <meta charset="utf-8">
  <title>InforMe</title>
</head>

<body>
  <h1> Welcome to the InforMe Website! </h1>
  <p> We're in the process of building. Stay tuned. :) </p>
  
  <h1 id="Sample"></h1>

<!-- Firebase Script, should be listed first. // NYI -->

<script src="https://www.gstatic.com/firebasejs/3.4.1/firebase.js"></script>
<!--
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCliDQbzo_m9ZdVSbes7qrlMx6ZIdPEFcw",
    authDomain: "cen4020informe.firebaseapp.com",
    databaseURL: "https://cen4020informe.firebaseio.com",
    storageBucket: "cen4020informe.appspot.com",
    messagingSenderId: "464639354758"
  };
  firebase.initializeApp(config);
  
  var db = firebase.database();
  var ref = db.ref("informe-c050b");
  ref.on("Sample", function(snapshot){
  console.log(snapshot.val());
  }, function (errorObject) {
  console.log("The read failed: " + errorObject.code);
  });
</script>
 -->
 
 <pre id="object"></pre>
 
 
 <script src="app.js"></script>
 
 
 

<form action="index.php" method="post">
	Enter URL Here:	<input type="text" name="article" /><br  />
	<input type="submit" name="submit" value="Fetch Article" />
</form>

<p>

<?php
echo $_POST['article'];
echo $_REQUEST['article'];
?>

</p>
 
</body>
</html>
