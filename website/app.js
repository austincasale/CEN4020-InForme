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
  
  // Get elements
  const preObject = document.getElementById('object');
  
  
  // create references
  const dbRefObject = firebase.database().ref().child('object');
  
  // Sync object changes
  dbRefObject.on('value', snap => console.log(snap.val()));
  
  
  
  }());