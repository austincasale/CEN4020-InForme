(function() {
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCliDQbzo_m9ZdVSbes7qrlMx6ZIdPEFcw",
    authDomain: "cen4020informe.firebaseapp.com",
    databaseURL: "https://cen4020informe.firebaseio.com",
    storageBucket: "cen4020informe.appspot.com",
    messagingSenderId: "464639354758"
  };
  firebase.initializeApp(config);

	var urlSpecified = document.getElementById('urlSpef');
	var articleTitle = document.getElementById('articleTitle');
	
	var urlSpef = firebase.database().ref().child('DBurlSpef');
	var dbTitle = firebase.database().ref().child('DBarticleTitle');
	
	urlSpef.on('value', snap => urlSpecified.innerText = snap.val());
	dbTitle.on('value', snap => articleTitle.innerText = snap.val());
	
	// Long form of live updating the database
	firebase.database().ref().child('DBarticleSummary').on('value', snap => articleSummary.innerText = snap.val());
	firebase.database().ref().child('DBcitation').on('value', snap => citation.innerText = snap.val());
	
	
}());
	

// Written by Ernie 10/17/2016
function storeURL() {
	var url = document.getElementById('urlSpef').value;	// Store the value of the text field
	var dbURL = firebase.database().ref().child('DBurlSpef');	// We want to put our data at the main db, at child DBurlSpef
	
	dbURL.set(url);	// Set the value of our child to the value in url, aka our text field
}
