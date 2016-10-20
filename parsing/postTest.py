import requests, json

#Test posting from back end server

url = 'http://posttestserver.com/post.php'
payload = {'send' : 'this'}

r = requests.post(url, data=json.dumps(payload))
#200 on success
print r.status_code

