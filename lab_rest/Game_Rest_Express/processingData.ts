import axios, { AxiosResponse } from 'axios';
import { Response } from 'express-serve-static-core';
import { CHEAPSHARK_GAME_DATA, CHEAPSHARK_GAME_ID, CHEAPSHARK_STORES_URL, GIANT_BOMB_GAME_DATA, GIANT_BOMB_GAME_ID, GIANT_BOMB_GAME_REVIEW, RAWG_GAME_DATA, RAWG_GAME_ID } from './constants';

export async function startMakingRequests(gameTitle: string, serverResult: Response<any, Record<string, any>, number>) {
    const urls = [
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

    if(checkIfGameExists(gameDataCheapShark, gameDataRawg, gameDataGiantBomb)) {
        serverResult.render("no_result")
        return
    }

    const gameIDCheapShark = checkIfCheapSharkSearchIsGood(gameDataCheapShark) ? gameDataCheapShark.data[0].gameID : -1
    const gameIDRawg = gameDataRawg.data.results[0].id
    const gameIDGiantBomb = gameDataGiantBomb.data.results[0].id
  
    fillStoresMap(storesMap, storesDataCheapShark)
  
    const urls = [
      CHEAPSHARK_GAME_DATA(gameIDCheapShark),
      RAWG_GAME_DATA(gameIDRawg),
      GIANT_BOMB_GAME_DATA(gameIDGiantBomb)
    ]
  
    axios
      .all(urls.map(fetchUrl))
      .then(axios.spread((cheapsharkData, rawgData, giantBombData) => processGamesData(serverResult, storesMap, gameIDCheapShark, cheapsharkData, rawgData, giantBombData)))
  }

  function checkIfGameExists(gameDataCheapShark: AxiosResponse<any, any>, 
    gameDataRawg: AxiosResponse<any, any>,
    gameDataGiantBomb: AxiosResponse<any, any>) {
    return !checkIfCheapSharkSearchIsGood(gameDataCheapShark) || gameDataRawg.data.count == 0 || gameDataGiantBomb.data.number_of_total_results == 0
  }

  const checkIfCheapSharkSearchIsGood = (cheapShark: AxiosResponse<any, any>) => Object.keys(cheapShark.data).length !== 0

  function fillStoresMap(storesMap: Map<number, string>, storesDataCheapShark: AxiosResponse<any, any>) {
    storesDataCheapShark.data.forEach((element: { storeID: number; storeName: string; }) => {
      storesMap.set(element.storeID, element.storeName)
    });
  }
  
  async function processGamesData(
    serverResult: Response<any, Record<string, any>, number>,
    storesMap: Map<number, string>,
    gameIDCheapShark: number,
    cheapsharkResponse: AxiosResponse<any, any>,
    rawgResponse: AxiosResponse<any, any>,
    giantBombResponse: AxiosResponse<any, any>
  ) {
    const cheapsharkData = checkIfCheapSharkSearchIsGood(cheapsharkResponse) ? cheapsharkResponse.data[gameIDCheapShark] : defaultCheapShark
    const rawgData = rawgResponse.data
    const giantBombData = giantBombResponse.data.results
    const deals: Array<Deal> = makeDealsArray(cheapsharkData, storesMap)
    const scoresArray: Array<number> = [rawgData.rating, calculateAverangeMetacriticScore(rawgData)/20]

    await addScoreReviewFromGiantBomb(scoresArray, giantBombData)

    serverResult.render("result" ,{
        title: cheapsharkData.info.title,
        cheapestPriceEver: cheapsharkData.cheapestPriceEver.price,
        currentBestDeal: deals.sort()[0],
        currentAvgPrice: calculateAverangePrice(deals).toFixed(2),
        deals: deals,
        description: rawgData.description,
        avgMetacriticFromAllPlatforms: calculateAverangeMetacriticScore(rawgData).toFixed(2),
        bestMetacriticStore: maxMetascoreFromAllPlatforms(rawgData),
        avgScore: calculateAverange(scoresArray).toFixed(2),
        releaseDate: giantBombData.original_release_date,
        platforms: giantBombData.platforms.map((platform: { name: string; }) => platform.name),
        ammountOfCharacters: giantBombData.characters !== null ? giantBombData.characters.length : "No data",
        ammountOfLocations: giantBombData.locations !== null ? giantBombData.locations.length : "No data"
    })
}

const defaultCheapShark = {
    info: {
        title: ""
    },
    cheapestPriceEver: {
        price: ""
    },
    deals: []
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

  async function addScoreReviewFromGiantBomb(scoresArray: Array<number>, giantBombData: any) {
    if(giantBombData.reviews != undefined) {
        scoresArray.push((await fetchUrl(GIANT_BOMB_GAME_REVIEW(giantBombData.reviews[0].id))).data.results.score)
    }
  }
  
  function maxMetascoreFromAllPlatforms(rawgData: any) {
    return calculateMax(makeMetascoreArray(rawgData))
  }
  
  function makeMetascoreArray(rawgData: any) {
    return rawgData.metacritic_platforms.map((element: { metascore: number; }) => element.metascore)
  }
  
  function calculateMax(array: Array<number>) {
    return array.reduce((previous, current) => Math.max(previous, current), 0)
  }
  