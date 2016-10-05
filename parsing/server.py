from flask import Flask
app = Flask(__name__)

@app.route('/')
def hello_world():
	return 'InforMe API'

@app.route('/article/<url>')
def parse_article(url):
	return 'You want to summarize ' + url
