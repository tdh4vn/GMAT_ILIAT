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

    # def get_json(self, json_encoder):
    #     return json_encoder.encode(self.to_mongo())

def from_questions_to_json_string(question_list):
    json_encoder = JSONEncoder()
    json_list = [question.to_json(sort_keys=True, indent= 4 * "&nbsp", separators=(',', ': ')) for question in question_list]
    # print(json_list)
    return (",<br>".join(json_list)).replace("\n", "<br>")
        # .replace(" ", "&nbsp")

# def from_json_to_string(json_object):
#     print(json.dumps(json_object, sort_keys=True, indent=4, separators=(',', ': ')))
#     return json.dumps(json_object, sort_keys=True, indent=4, separators=(',', ': '))