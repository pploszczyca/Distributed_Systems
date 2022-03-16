# Game REST API
Form is under `localhost:3000/form`
## How to run
### With npm
Install packages and run app
```bash
$ npm install
$ npm start
```

### With docker
Build image
```bash
$ sudo docker build . -t pploszczyca/node-game-rest-app
```

Run image in 3000 port
```bash
$ docker run -p 3000:3000 -d pploszczyca/node-game-rest-app
```