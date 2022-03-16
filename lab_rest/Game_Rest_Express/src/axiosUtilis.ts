import axios, { AxiosResponse } from "axios";
import { Response } from 'express-serve-static-core';

export function fetchAllUrls(
    urls: string[], 
    serverResult: Response<any, Record<string, any>, number>,
    onThen: (...args: AxiosResponse<any, any>[]) => void
){
    axios
        .all(urls.map(fetchUrl))
        .then(axios.spread(onThen))
        .catch(error => showUnavaibleMessage(serverResult))  
}

export function fetchUrl(url: string) {
    return axios.get(encodeURI(url))
}

function showUnavaibleMessage(serverResult: Response<any, Record<string, any>, number>) {
    serverResult.render("no_result", {
      resultInformation: "Website is currently unavaible. Try later."
    })
}