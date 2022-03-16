import Deal from "./Deal";

export function maxMetascoreFromAllPlatforms(rawgData: any) {
    return calculateMax(makeMetascoreArray(rawgData))
}

export function makeMetascoreArray(rawgData: any) {
    return rawgData.metacritic_platforms.map((element: { metascore: number; }) => element.metascore)
}

export function calculateMax(array: Array<number>) {
    return array.reduce((previous, current) => Math.max(previous, current), 0)
}

export function calculateAverangePrice(deals: Array<Deal>) {
    return calculateAverange(deals.map(element => element.price))
}

export function calculateAverangeMetacriticScore(rawgData: any) {
    return calculateAverange(makeMetascoreArray(rawgData))
}

export function calculateAverange(array: Array<number>) {
    return array.reduce((previous, current) => previous + current/array.length , 0)
}
