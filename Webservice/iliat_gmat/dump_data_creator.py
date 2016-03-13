__author__ = 'qhuydtvt'

from questions import Question, QuestionCollection
import mongoengine

db = mongoengine.connect("gmat", host='103.1.209.92', port=27017)

question_list = [question for question in Question.objects]

version = "0.0.1"

#question_collection = QuestionCollection(version=version,
                                         #questions=question_list)

#question_collection.save()

question_collection = QuestionCollection.objects[0]
print(question_collection.to_json())


db.close()