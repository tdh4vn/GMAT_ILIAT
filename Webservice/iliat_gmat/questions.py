__author__ = 'qhuydtvt'

from mongoengine import *
import json
from json_encoder import JSONEncoder

class Question(Document):
    type = StringField()
    sub_type = StringField()
    stimulus = StringField()
    stem = StringField()
    answer_choices = ListField(StringField())
    right_answer = IntField()
    explanation = StringField()

def from_questions_to_json_string(question_list):
    json_encoder = JSONEncoder()
    json_list = [str(json_encoder.encode(question.to_mongo())) for question in question_list]
    return ",<br>".join(json_list)
