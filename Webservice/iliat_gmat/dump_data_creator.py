__author__ = 'qhuydtvt'

from datetime import datetime

from versions import Version
from questions import Question
from question_packs import QuestionPack
from iliat_gmat import remove_dollar_sign

#from questions import Question, QuestionCollection
#import mongoengine

#db = mongoengine.connect("gmat", host='103.1.209.92', port=27017)
#
# question_list = [question for question in Question.objects]
#
# version = "0.0.1"
#
# question_collection = QuestionCollection.objects[0]
# print(question_collection.to_json())

# db.close()

from question_packs import QuestionPack, QuestionPackCollection
from questions import QuestionCollection, Question
from question_packs import QuestionPack

import mongoengine

db = mongoengine.connect("gmat",
                         host='103.1.209.92',
                         port=27017)

# question_collection = QuestionCollection.objects[0]
# questions = question_collection.questions


# for question in questions:
#     print(question.to_json())
#     q = Question(
#         type = question.type,
#         sub_type = question.sub_type,
#         stimulus = question.stimulus,
#         stem = question.stem,
#         answer_choices = question.answer_choices,
#         right_answer = question.right_answer,
#         explanation = question.explanation
#     )
#     q.save()
#
# questions = Question.objects

# for question in questions:
#     print(question.to_json())

# v = Version(value="0.0.1")
# v.save()

questions = Question.objects
question_pack = QuestionPack(available_time="2016-03-14",
                             question_ids = [str(question.id) for question in questions ])
# version = Version.objects[0]
# question_collection = QuestionCollection(version=version.value, questions=[q for q in questions])
# remove_dollar_sign(str(question_collection.to_json()))
# question_pack.save()

# print(question_pack.to_json())

question_pack.save()

db.close()