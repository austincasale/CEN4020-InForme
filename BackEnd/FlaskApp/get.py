from flask import Flask

app = Flask(__name__)

@app.route('/')
def basic():
        return 'What up boy'


