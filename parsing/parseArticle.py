from bs4 import BeautifulSoup
import requests, time, re

response = requests.get('https://www.theguardian.com/us-news/2016/oct/05/donald-trump-hillary-clinton-vp-debate')

if response.ok:
	html = response.content
	soup = BeautifulSoup(html, 'html.parser')

time.sleep(.2)

#print soup.prettify()

text = soup.find("div", "content__article-body from-content-api js-article__body")

x = re.sub('<[^<]+?>', "", text.text)

print x
print soup.title.text
