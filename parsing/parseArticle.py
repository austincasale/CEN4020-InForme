# Implemented by Austin and Miguel
from bs4 import BeautifulSoup
import requests, time, re, sys
# Input any article on https://www.theguardian.com/

def getBody(url):
	# Make our request to our URL
	response = requests.get(url)

	# Check response and declare soup object
	try:
		if response.ok:
			html = response.content
			soup = BeautifulSoup(html, 'html.parser')
	except NameError:
		print "No URL entered"
		sys.exit()

	# Sleep to avoid spamming requests and getting timed out, don't be a dick
	time.sleep(.2)

	# Prettify is used to print the html for the url in a way that is easier to read
	#print soup.prettify()

	# Need logic to find tag with most <p> inside

	# Find <div class = "whatever name is"
	text = soup.find("div", "content__article-body from-content-api js-article__body")


	#using .text because the type(text) is a bs4 obj
	# sub can remove elements
	try:
		x = re.sub('<[^<]+?>', "", text.text)
		x = re.sub('\n', "", text.text)
	except AttributeError:
		print "Shit, couldn't find anything to parse"
		sys.exit()

	print x
	# Print article title
	print "\n\nTitle: ", soup.title.text

url = raw_input("Please enter the url: ")

# Two ideas:
# _____________Idea 1 ____________
# Goal will be to analyze the URL and accurately predict the website
# Html parsing will be different for each site
# _____________Idea 2 ____________
# Algorithm to accurately find the tags storing
# the article body, this approach would be better if possible
# wouldn't need a template for every site being accessed (buzzfeed, guardian,foxnews, etc..)



getBody(url)





