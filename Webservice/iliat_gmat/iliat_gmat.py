from flask import Flask
from questions import Question, from_questions_to_json_string
import json
import mongoengine

mongoengine.connect("gmat", host='103.1.209.92', port=27017)
app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/gmat_question_pack')
def get_gmat_question_pack():
    question_list = Question.objects
    # print(len(question_list))
    # print(from_questions_to_json_string(question_list))
    print(from_questions_to_json_string(question_list))
    return from_questions_to_json_string(question_list)

if __name__ == '__main__':
    app.run()
