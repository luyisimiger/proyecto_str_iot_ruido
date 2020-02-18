import random
import time
from interactive import db

def get_session(name, portwrite, portread):
  a = random.randint(0, 199)
  b = random.randint(a+1, 200)
  id = int(time.time() * 1000)
  time.sleep(0.5)

  return {
    "_id": id,
    "name": name,
    "port_write": portwrite,
    "port_read": portread,
    "min": a,
    "max": b
  }

db.sensores.delete_many({"name": "Sensor Sala 1"})
db.sensores.delete_many({"name": "Sensor Sala 2"})
db.sensores.delete_many({"name": "Sensor Sala 3"})
db.sensores.delete_many({"name": "Sensor Sala 4"})
db.sensores.delete_many({"name": "Sensor Sala 5"})

s1 = get_session("Sensor Sala 1", "/dev/ttyS21", "/dev/ttyS22")
s2 = get_session("Sensor Sala 2", "/dev/ttyS23", "/dev/ttyS24")
s3 = get_session("Sensor Sala 3", "/dev/ttyS25", "/dev/ttyS26")
s4 = get_session("Sensor Sala 4", "/dev/ttyS27", "/dev/ttyS28")
s5 = get_session("Sensor Sala 5", "/dev/ttyS29", "/dev/ttyS30")

db.sensores.insert_one(s1)
db.sensores.insert_one(s2)
db.sensores.insert_one(s3)
db.sensores.insert_one(s4)
db.sensores.insert_one(s5)
