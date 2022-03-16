import { AxiosResponse } from 'axios';
import { Response } from 'express-serve-static-core';
import { fetchAllUrls, fetchUrl } from './axiosUtilis';
import { CHEAPSHARK_GAME_DATA, CHEAPSHARK_GAME_ID, CHEAPSHARK_STORES_URL, GIANT_BOMB_GAME_DATA, GIANT_BOMB_GAME_ID, GIANT_BOMB_GAME_REVIEW, RAWG_GAME_DATA, RAWG_GAME_ID } from './urlPaths';
import Deal from './Deal';
import { calculateAverange, calculateAverangeMetacriticScore, calculateAverangePrice, maxMetascoreFromAllPlatforms } from './mathUtilis';

const NO_DATA = "No data"

export async function startMakingRequests(gameTitle: string, serverResult: Response<any, Record<string, any>, number>) {
    const urls = [
        CHEAPSHARK_STORES_URL,
        CHEAPSHARK_GAME_ID(gameTitle),
        RAWG_GAME_ID(gameTitle),
        GIANT_BOMB_GAME_ID(gameTitle)
    ]

    fetchAllUrls(urls, serverResult, (storesDataCheapShark, gameDataCheapShark, gameDataRawg, gameDataGiantBomb) => processFirstDatas(serverResult, storesDataCheapShark, gameDataCheapShark, gameDataRawg, gameDataGiantBomb))
}
  
async function processFirstDatas(
    serverResult: Response<any, Record<string, any>, number>,
    storesDataCheapShark: AxiosResponse<any, any>, 
    gameDataCheapShark: AxiosResponse<any, any>, 
    gameDataRawg: AxiosResponse<any, any>,
    gameDataGiantBomb: AxiosResponse<any, any>
) {
    const storesMap = new Map<number, string>()

    if(checkIfGameExists(gameDataCheapShark, gameDataRawg, gameDataGiantBomb)) {
        serverResult.render("no_result", {
            resultInformation: "The game is not found."
        })
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

    fetchAllUrls(urls, serverResult, (cheapsharkData, rawgData, giantBombData) => processGamesData(serverResult, storesMap, gameIDCheapShark, cheapsharkData, rawgData, giantBombData))
}

function checkIfGameExists(gameDataCheapShark: AxiosResponse<any, any>, 
    gameDataRawg: AxiosResponse<any, any>,
    gameDataGiantBomb: AxiosResponse<any, any>
){
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
    const cheapsharkData = cheapsharkResponse.data[gameIDCheapShark]
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
        ammountOfCharacters: giantBombData.characters !== null ? giantBombData.characters.length : NO_DATA,
        ammountOfLocations: giantBombData.locations !== null ? giantBombData.locations.length : NO_DATA
    })
}

function makeDealsArray(cheapsharkData: { deals: { storeID: number; price: any; retailPrice: any; }[]; }, storesMap: Map<number, string>,): Array<Deal> {
    return cheapsharkData.deals.map((element: { storeID: number; price: any; retailPrice: any; }): Deal => { return {
        storeName: storesMap.get(element.storeID),
        price: element.price,
        retailPrice: element.retailPrice
    }})
}

async function addScoreReviewFromGiantBomb(scoresArray: Array<number>, giantBombData: any) {
    if(giantBombData.reviews != undefined) {
        scoresArray.push((await fetchUrl(GIANT_BOMB_GAME_REVIEW(giantBombData.reviews[0].id))).data.results.score)
    }
}
