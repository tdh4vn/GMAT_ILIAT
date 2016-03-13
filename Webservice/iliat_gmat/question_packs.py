__author__ = 'qhuydtvt'

from mongoengine import *

class QuestionPack(Document):
    available_time = StringField()
    question_ids = ListField(StringField())

class QuestionPackCollection(Document):
    question_packs = ListField(EmbeddedDocumentField('QuestionPack'))

