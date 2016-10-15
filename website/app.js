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
  
	var urlSpecified = document.getElementById('urlSpef');
	var articleTitle = document.getElementById('articleTitle');
	
	var urlSpef = firebase.database().ref().child('DBurlSpef');
	var dbRef = firebase.database().ref().child('DBarticleTitle');
	
	urlSpef.on('value', snap => urlSpecified.innerText = snap.val());
	dbRef.on('value', snap => articleTitle.innerText = snap.val());
	
	firebase.database().ref().child('DBarticleSummary').on('value', snap => articleSummary.innerText = snap.val());
	firebase.database().ref().child('DBcitation').on('value', snap => citation.innerText = snap.val());
}());