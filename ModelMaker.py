from os import listdir
from os.path import isfile, join
import gensim

LabeledSentence = gensim.models.doc2vec.LabeledSentence

class LabeledLineSentence(object):
    def __init__(self, doc_list, labels_list):
       self.labels_list = labels_list
       self.doc_list = doc_list
    def __iter__(self):
        for idx, doc in enumerate(self.doc_list):
            yield LabeledSentence(words=doc.split(), tags=[self.labels_list[idx]])


docLabels = []
docLabels = [f for f in listdir("/Users/shayonbanerjee/Documents/CourtCases") if f.endswith('.txt')]
data = []
for doc in docLabels:
    file = open("/Users/shayonbanerjee/Documents/CourtCases/" + doc, 'r')
    data.append(file.read())
    file.close()
it = LabeledLineSentence(data, docLabels)
model = gensim.models.Doc2Vec(size=300, window=10, min_count=5, workers=11,alpha=0.025, min_alpha=0.025) # use fixed learning rate
model.build_vocab(it)
token_count = sum([len(sentence) for sentence in it])
print("Time to start training the model...")
for epoch in range(10):
    model.train(it, total_examples = token_count, epochs = model.iter)
    model.alpha -= 0.002 # decrease the learning rate
    model.min_alpha = model.alpha # fix the learning rate, no deca
    model.train(it, total_examples = token_count, epochs = model.iter)
    print("Training...")
model.save("doc2vec.model")
print("done")