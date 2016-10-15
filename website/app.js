(function() {



  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyB_rPPGzLDgkO7ohjDRSUddvjhDk120fWg",
    authDomain: "mycentest.firebaseapp.com",
    databaseURL: "https://mycentest.firebaseio.com",
    storageBucket: "mycentest.appspot.com",
    messagingSenderId: "308805634952"
  };

	firebase.initializeApp(config);
  
	var bigOne = document.getElementById('bigOne');
	var dbRef = firebase.database().ref().child('text');
	dbRef.on('value', snap => bigOne.innerText = snap.val());
	firebase.database().ref().child('testing').on('value', snap => testOne.innerText = snap.val());

}());