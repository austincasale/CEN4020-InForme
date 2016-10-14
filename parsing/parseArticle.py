# Implemented by Austin and Miguel

from bs4 import BeautifulSoup
import requests, time, re
# Input any article on https://www.theguardian.com/

url = raw_input("Please enter the url: ")

# Two ideas:
# _____________Idea 1 ____________
# Goal will be to analyze the URL and accurately predict the website
# Html parsing will be different for each site
# _____________Idea 2 ____________
# Algorithm to accurately find the tags storing
# the article body, this approach would be better if possible
# wouldn't need a template for every site being accessed (buzzfeed, guardian,foxnews, etc..)

# Make our request to our URL
response = requests.get(url)

# Check response and declare soup object
if response.ok:
	html = response.content
	soup = BeautifulSoup(html, 'html.parser')

# Sleep to avoid spamming requests and getting timed out, don't be a dick
time.sleep(.2)

# Prettify is used to print the html for the url in a way that is easier to read
#print soup.prettify()

# Find <div class = "whatever name is"
text = soup.find("div", "content__article-body from-content-api js-article__body")

#using .text because the type(text) is a bs4 obj
# sub can remove elements
x = re.sub('<[^<]+?>', "", text.text)
x = re.sub('\n', "", text.text)

print x
# Print article title
print "Title: ", soup.title.text


