from flask import Flask

from questions import Question, QuestionCollection
import mongoengine

mongoengine.connect("gmat", host='103.1.209.92', port=27017)


app = Flask(__name__)

QUESTION_COLLECTION_KEY = "question_collection"
VERSION_KEY = "version"

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/question_collection')
def get_gmat_question_collection():
    OLD_OID = "$oid"
    NEW_OLD = "oid"
    question_collection = QuestionCollection.objects[0]

    return str(question_collection.to_json()).replace(OLD_OID, NEW_OLD)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=6969)
