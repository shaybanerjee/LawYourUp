from flask import Flask, request, render_template
from flask import jsonify
import gensim

app = Flask(__name__)

@app.route('/background_process/')
def background_process():
    model = gensim.models.Doc2Vec.load("doc2vec.model")
    try:
        filename = request.args.get('proglang')
        print(filename)
        wFIleContent = open("CourtCases/" + filename, 'r').read()
        print(wFIleContent)
        wTokens= wFIleContent.split()
        wVector = model.infer_vector(wTokens)
        wSimilarities = model.docvecs.most_similar([wVector])
        wDictSimilarities = dict(wSimilarities)
        return jsonify(result=wDictSimilarities)
    except Exception, e:
        return(str(e))


def BuildFromModel():
    model = gensim.models.Doc2Vec.load("doc2vec.model")
    similar_doc = model.docvecs.most_similar(14)
    dict_docs = dict(similar_doc)
    try:
        lang = request.args.get('proglang')
        print(str(lang))
        if str(lang).lower() == 'python':
            return jsonify(result=dict_docs)
        else:
            return jsonify(result=dict_docs)
    except Exception, e:
        return (str(e))

    print dict_docs

@app.route('/')
def FormBuilder():
    return render_template('index.html')

if __name__ == '__main__':
    app.run()