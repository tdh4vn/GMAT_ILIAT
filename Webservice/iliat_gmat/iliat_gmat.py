from flask import Flask
from questions import Question, from_questions_to_json_string
import json
import mongoengine

mongoengine.connect("gmat", host='103.1.209.92', port=27017)
app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/question_pack')
def get_gmat_question_pack():
    question_list = Question.objects
    return from_questions_to_json_string(question_list)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=6969)
