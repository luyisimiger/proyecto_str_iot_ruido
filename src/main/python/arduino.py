import serial
import random

from time import sleep

def arduino_simulator(sensor):
    
    port_write = sensor["port_write"]
    name = sensor["name"] 
    min = sensor["min"]
    max = sensor["max"]
    ser = serial.Serial(port_write, 9600)

    while True:
        ruido = random.randint(min, max)
        message = str(ruido) + "\n"

        # data = dict(ruido=ruido)
        # message = str(data) + "\n"
        ser.write(message.encode("raw_unicode_escape"))
        print(f"ruido: {name}", message)
        sleep(3)

    ser.close()
