const express = require('express')
const axios = require('axios')
const app = express()
const bodyParser = require('body-parser')
const port = 3000

const getDataFromCoinCap = (serverResult, startTime, endTime) => {
	axios
		.get(`https://api.coincap.io/v2/assets/bitcoin/history?interval=d1&start=${startTime}&end=${endTime}`)
		.then(res => {
			serverResult.send(res.data)
		})
		.catch(error => {
			serverResult.send(error)
		})
};

const getUnixTimeFromFormDatetime = datetime => new Date(datetime).getTime()

app.set('view engine', 'pug');
app.set('views', './views');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
	extended: true
}))

app.get('/', (req, res) => {
  res.send('Hello World!')
});

app.get('/bitcoinInfo', (req, res) => {
  res.render('form')
});

app.post('/bitcoinInfo', (req, res) => {
  getDataFromCoinCap(res, getUnixTimeFromFormDatetime(req.body.startDate), getUnixTimeFromFormDatetime(req.body.endDate))
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
});