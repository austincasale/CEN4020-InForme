# CEN4020-InForme Compilation Instructions
Website:
* Download home.html and open in local web browser (Chrome, IE, Firefox, etc).
* Test Cases: 

Android:
* TBD
* Test Cases:

Python (Back-End):
* Get parseArticle.py and just run it "python parseArticle.py". It will prompt for a URL and you need to input any article URL
  from https://www.theguardian.com/us, i.e https://www.theguardian.com/us-news/2016/oct/19/donald-trump-and-hillary-clinton-face-fear-and-loathing-at-third-debate
* When on server cd /home/informe/backend/api -> export FLASK_APP=startFlask.py -> flask run --host=162.243.88.222 then visit 162.243.88.222:5000 for results
* Unit Test: Download parseArticleUnitTest.py off github, run it with "python parseArticleUnitTest.py". It will run for 3 guardian article links and one random string that isn't a link. It tests to be sure it gets an article body.

