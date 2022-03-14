import axios, { AxiosResponse } from 'axios';
import bodyParser from 'body-parser';
import express from 'express'
import { Response } from 'express-serve-static-core';
import { CHEAPSHARK_GAME_DATA, CHEAPSHARK_GAME_ID, CHEAPSHARK_STORES_URL, GIANT_BOMB_GAME_DATA, GIANT_BOMB_GAME_ID, PORT, RAWG_GAME_DATA, RAWG_GAME_ID } from './constants';

require('dotenv').config()

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

async function startMakingRequests(gameTitle: string, serverResult: Response<any, Record<string, any>, number>) {
  let urls = [
    CHEAPSHARK_STORES_URL,
    CHEAPSHARK_GAME_ID(gameTitle),
    RAWG_GAME_ID(gameTitle),
    GIANT_BOMB_GAME_ID(gameTitle)
  ]
  
  axios
    .all(urls.map(fetchUrl))
    .then(axios.spread((storesDataCheapShark, gameDataCheapShark, gameDataRawg, gameDataGiantBomb) => processFirstDatas(serverResult, storesDataCheapShark, gameDataCheapShark, gameDataRawg, gameDataGiantBomb)))
}

function fetchUrl(url: string) {
    return axios.get(encodeURI(url))
}

function processFirstDatas(
  serverResult: Response<any, Record<string, any>, number>,
  storesDataCheapShark: AxiosResponse<any, any>, 
  gameDataCheapShark: AxiosResponse<any, any>, 
  gameDataRawg: AxiosResponse<any, any>,
  gameDataGiantBomb: AxiosResponse<any, any>
) {
  const storesMap = new Map<number, string>()
  const gameIDCheapShark = gameDataCheapShark.data[0].gameID
  const gameIDRawg = gameDataRawg.data.results[0].id
  const gameIDGiantBomb = gameDataGiantBomb.data.results[0].id

  fillStoresMap(storesMap, storesDataCheapShark)

  let urls = [
    CHEAPSHARK_GAME_DATA(gameIDCheapShark),
    RAWG_GAME_DATA(gameIDRawg),
    GIANT_BOMB_GAME_DATA(gameIDGiantBomb)
  ]

  axios
    .all(urls.map(fetchUrl))
    .then(axios.spread((cheapsharkData, rawgData, giantBombData) => processGamesData(serverResult, storesMap, gameIDCheapShark, cheapsharkData, rawgData, giantBombData)))
}

function fillStoresMap(storesMap: Map<number, string>, storesDataCheapShark: AxiosResponse<any, any>) {
  storesDataCheapShark.data.forEach((element: { storeID: number; storeName: string; }) => {
    storesMap.set(element.storeID, element.storeName)
  });
}

function processGamesData(
  serverResult: Response<any, Record<string, any>, number>,
  storesMap: Map<number, string>,
  gameIDCheapShark: number,
  cheapsharkResponse: AxiosResponse<any, any>,
  rawgResponse: AxiosResponse<any, any>,
  giantBombData: AxiosResponse<any, any>
) {
  const cheapsharkData = cheapsharkResponse.data[gameIDCheapShark]
  const rawgData = rawgResponse.data
  const deals: Array<Deal> = makeDealsArray(cheapsharkData, storesMap)

  serverResult.send({
    title: cheapsharkData.info.title,
    cheapestPriceEver: cheapsharkData.cheapestPriceEver.price,
    currentBestDeal: deals.sort()[0],
    currentAvgPrice: calculateAverangePrice(deals),
    deals: deals,
    description: rawgData.description,
    avgMetacriticFromAllPlatforms: calculateAverangeMetacriticScore(rawgData),
    bestMetacriticStore: maxMetascoreFromAllPlatforms(rawgData),
    releaseDate: giantBombData.data.results.original_release_date,
    ammountOfPlatforms: giantBombData.data.results.platforms.length,
    ammountOfCharacters: giantBombData.data.results.characters.length,
    ammountOfLocations: giantBombData.data.results.locations.length
  })
}

interface Deal {
  storeName: string | undefined,
  price: number,
  retailPrice: number
}

function makeDealsArray(cheapsharkData: { deals: { storeID: number; price: any; retailPrice: any; }[]; }, storesMap: Map<number, string>,): Array<Deal> {
  return cheapsharkData.deals.map((element: { storeID: number; price: any; retailPrice: any; }): Deal => { return {
    storeName: storesMap.get(element.storeID),
    price: element.price,
    retailPrice: element.retailPrice
  }})
}

function calculateAverangePrice(deals: Array<Deal>) {
  return calculateAverange(deals.map(element => element.price))
}

function calculateAverangeMetacriticScore(rawgData: any) {
  return calculateAverange(makeMetascoreArray(rawgData))
}

function calculateAverange(array: Array<number>) {
  return array.reduce((previous, current) => previous + current/array.length , 0)
}

function maxMetascoreFromAllPlatforms(rawgData: any) {
  return calculateMax(makeMetascoreArray(rawgData))
}

function makeMetascoreArray(rawgData: any) {
  return rawgData.metacritic_platforms.map((element: { metascore: number; }) => element.metascore)
}

function calculateMax(array: Array<number>) {
  return array.reduce((previous, current) => Math.max(previous, current))
}
