import bodyParser from 'body-parser';
import express from 'express'
import { startMakingRequests } from './processingData';

require('dotenv').config()

const PORT = process.env.PORT
const app = express()

app.set('view engine', 'pug');
app.set('views', './views');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
	extended: true
}))

app.get('/', (req, res) => {
  res.send('Hello World!')
});

app.get('/form', (req, res) => {
  res.render('form')
});

app.post('/form', async (req, res) => {
  await startMakingRequests(req.body.title, res)
});

app.listen(PORT, () => {
  console.log(`Example app listening on port ${PORT}`)
});

