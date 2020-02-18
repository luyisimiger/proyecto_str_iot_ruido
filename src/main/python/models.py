
class Sensor():

    def __init__(self, name, port_read, port_write, min, max):
        self.name = name
        self.port_read = port_read
        self.port_write = port_write
        self.min = min
        self.max = max

    def document(self):
        return {
            "name": self.name,
            "port_read": self.port_read,
            "port_write": self.port_write,
            "min": self.min,
            "max": self.max,

        }

    def __repr__(self):
        return f"{self.name}, ({self.min}, {self.max})"

    def __str__(self):
        return self.__repr__()


class Medition(EmbeddedDocument):
    fechahora = DateTimeField(required=True)
    value = FloatField(required=True)

    def __repr__(self):
        return f"{self.fechahora}, {self.value}"

    def __str__(self):
        return self.__repr__()
