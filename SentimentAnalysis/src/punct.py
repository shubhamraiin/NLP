#included libraries
import nltk.classify.util
import string
from nltk.classify import MaxentClassifier
from nltk.corpus import movie_reviews, stopwords

print "Removing punctuations marks:"
stop = stopwords.words('english')

#Get all words without punctuation marks
def without_stopwords_punctuations(words):
    return {word:True for word in words if word not in string.punctuation}

negids = movie_reviews.fileids('neg')
posids = movie_reviews.fileids('pos')

negfeats = [(without_stopwords_punctuations(movie_reviews.words(fileids=[f])), 'neg') for f in negids]
posfeats = [(without_stopwords_punctuations(movie_reviews.words(fileids=[f])), 'pos') for f in posids]

negcutoff = len(negfeats)*75/100
poscutoff = len(posfeats)*75/100

trainfeats = negfeats[:negcutoff] + posfeats[:poscutoff]
testfeats = negfeats[negcutoff:] + posfeats[poscutoff:]
print 'Train on %d instances, Test on %d instances' % (len(trainfeats), len(testfeats))

algorithm = nltk.classify.MaxentClassifier.ALGORITHMS[0]
classifier=MaxentClassifier.train(trainfeats,algorithm,max_iter=5)
print 'Total accuracy:', nltk.classify.util.accuracy(classifier, testfeats)


