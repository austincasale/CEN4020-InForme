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
	
	firebase.database().ref().child('DBarticleSummary').on('value', snap => articleSummary.innerText = snap.val());
	firebase.database().ref().child('DBcitation').on('value', snap => citation.innerText = snap.val());
}());

// How the hell do we do write data from the input box to the database?
function storeURL(urlSpef) {
  firebase.database().ref('cen4020informe/' + DBurlSpef).set({
    DBurlSpef: urlSpef
  });
}
