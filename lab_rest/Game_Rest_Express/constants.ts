export const PORT = 3000
export const CHEAPSHARK_STORES_URL = 'https://www.cheapshark.com/api/1.0/stores'
export const CHEAPSHARK_GAME_ID = (gameTitle: String) => `https://www.cheapshark.com/api/1.0/games?title=${gameTitle}&limit=1`
export const CHEAPSHARK_GAME_DATA = (gameID: number) => `https://www.cheapshark.com/api/1.0/games?ids=${gameID}`
export const RAWG_GAME_ID = (gameTitle: String) => `https://api.rawg.io/api/games?key=${process.env.RAWG_API_KEY}&search=${gameTitle}`
export const RAWG_GAME_DATA = (gameID: number) => `https://api.rawg.io/api/games/${gameID}?key=${process.env.RAWG_API_KEY}`
export const GIANT_BOMB_GAME_ID = (gameTitle: String) => `https://www.giantbomb.com/api/search/?format=json&limit=1&query=${gameTitle}&resources=game&api_key=${process.env.GIANT_BOMB_API_KEY}`
export const GIANT_BOMB_GAME_DATA = (gameID: number) => `https://www.giantbomb.com/api/game/${gameID}/?format=json&api_key=${process.env.GIANT_BOMB_API_KEY}`
export const GIANT_BOMB_GAME_REVIEW = (reviewID: number) => `https://www.giantbomb.com/api/review/${reviewID}/?format=json&api_key=${process.env.GIANT_BOMB_API_KEY}`