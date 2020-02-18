from mongoengine import *

class Sensor(Document):
    code = StringField(required=True)
    name = StringField(required=True)
    min = IntField(required=True)
    max = IntField(required=True)

    meditions = ListField(EmbeddedDocumentField(Medition))

    def __repr__(self):
        return f"{self.code} - {self.name}, ({self.min}, {self.max})"

    def __str__(self):
        return self.__repr__()


class Medition(EmbeddedDocument):
    fechahora = DateTimeField(required=True)
    value = FloatField(required=True)

    def __repr__(self):
        return f"{self.fechahora}, {self.value}"

    def __str__(self):
        return self.__repr__()
