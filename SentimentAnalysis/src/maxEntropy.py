#Libraries included:
import nltk, nltk.classify.util, nltk.metrics
from nltk.classify import MaxentClassifier
from nltk.corpus import movie_reviews

print "Using original corpus:"
#Get all words
def word_feats(words):
    return {word:True for word in words}

negids = movie_reviews.fileids('neg')
posids = movie_reviews.fileids('pos')

negfeats = [(word_feats(movie_reviews.words(fileids=[f])), 'neg') for f in negids]
posfeats = [(word_feats(movie_reviews.words(fileids=[f])), 'pos') for f in posids]

negcutoff = len(negfeats)*75/100
poscutoff = len(posfeats)*25/100

trainfeats = negfeats[:negcutoff] + posfeats[:poscutoff]
testfeats = negfeats[negcutoff:] + posfeats[poscutoff:]
print 'train on %d instances, test on %d instances' % (len(trainfeats), len(testfeats))

algorithm = nltk.classify.MaxentClassifier.ALGORITHMS[0]
classifier=MaxentClassifier.train(trainfeats,algorithm,max_iter=5)
print 'Total accuracy:', nltk.classify.util.accuracy(classifier, testfeats)




