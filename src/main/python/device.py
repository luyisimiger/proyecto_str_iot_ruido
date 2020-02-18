import threading
from pymongo import MongoClient
from arduino import arduino_simulator
from interactive import db

s1 = db.sensores.find_one({"name": "Sensor Sala 1"})
s2 = db.sensores.find_one({"name": "Sensor Sala 2"})
s3 = db.sensores.find_one({"name": "Sensor Sala 3"})
s4 = db.sensores.find_one({"name": "Sensor Sala 4"})
s5 = db.sensores.find_one({"name": "Sensor Sala 5"})

h1 = threading.Thread(target=arduino_simulator, args=(s1,))
h2 = threading.Thread(target=arduino_simulator, args=(s2,))
h3 = threading.Thread(target=arduino_simulator, args=(s3,))
h4 = threading.Thread(target=arduino_simulator, args=(s4,))
h5 = threading.Thread(target=arduino_simulator, args=(s5,))

h1.start()
h2.start()
h3.start()
h4.start()
h5.start()
