# Sistema de Tiempo Real | Sensores del Aeropuerto - Universidad de Cartagena

Desarrollado por **Luis Miguel Mejia** y **Saad Bittar**

## Requerimiento


## Comandos

### camando para habilitar emulacion de dos puertos seriales
```
sudo socat -d -d PTY,link=/dev/ttyS21 PTY,link=/dev/ttyS22
sudo socat -d -d PTY,link=/dev/ttyS23 PTY,link=/dev/ttyS24
sudo socat -d -d PTY,link=/dev/ttyS25 PTY,link=/dev/ttyS26
sudo socat -d -d PTY,link=/dev/ttyS27 PTY,link=/dev/ttyS28
sudo socat -d -d PTY,link=/dev/ttyS29 PTY,link=/dev/ttyS30
```

### comando para iniciar el servidor de mongodb
```
service mongodb start
```

### comando para activar sensores
```
sudo python3 device.py
```
