# CEN4020-InForme Compilation Instructions
Website:
* Download the /website/ folder and all of its contents. Open index.html in your local web browser (any should do). You can test functionality from there.
* Unit Test: Open UnitTest.html from the /website/ folder. As instructed, type in an invalid or valid URL into the input box and press "Submit." If valid: you will be shown what was saved to the database (which should reflect what you typed in). If invalid, you will be instructed on the acceptable format for URLs.

Android:

* Needs to be run on Android phone with latest software
* Note: Developer options may need to be on

* Copy app-debug.apk from repository into an Android phone SD card
* Explore the files from the phone and find app-debug.apk, click it
* Phone will ask to run the app with special permissions
* Install
* Open


Python (Back-End):
* Get parseArticle.py and just run it "python parseArticle.py". It will prompt for a URL and you need to input any article URL
  from https://www.theguardian.com/us, i.e https://www.theguardian.com/us-news/2016/oct/19/donald-trump-and-hillary-clinton-face-fear-and-loathing-at-third-debate
* When on server cd /home/informe/backend/api -> export FLASK_APP=startFlask.py -> flask run --host=162.243.88.222 then visit 162.243.88.222:5000 for results
* Unit Test: Download parseArticleUnitTest.py off github, run it with "python parseArticleUnitTest.py". It will run for 3 guardian article links and one random string that isn't a link. It tests to be sure it gets an article body.

