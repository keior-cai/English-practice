import Axios from 'axios';
import qs from 'qs';
import Bus from './Bus.js';
import { GetToken } from './DealFun.js'
import * as EmitType from './EmitType.js';
import { ShowToast, ShowLoading, CloseLoading } from './Interactive.js';

export const Get = (url, data = {}, { loading = true, headers = {} } = {}) => {
	return new Promise((resolve, reject) => {
		if(loading) {
			ShowLoading()
		}
		headers.token = GetToken()
		Axios.get(url, {
			params: data,
			headers
		}).then(res => {
			if(loading) {
				CloseLoading()
			}
			resolve(res.data)
		}).catch(res => {
			if(loading) {
				CloseLoading()
			}
			ShowToast('请求失败，请检查网络连接')
			$('.mu-loading-wrap').remove()
			reject(res)
		})
	})
}

export const Post = (url, data = {}, { loading = true, headers = {}, qsOpen = true } = {}) => {
	return new Promise((resolve, reject) => {
		if(loading) {
			ShowLoading()
		}
		let sendData = data;
		if(qsOpen) {
			sendData = qs.stringify(data, {
				arrayFormat: 'repeat'
			})
		}
		headers.token = GetToken()
		Axios.post(url, sendData, {
			timeout: 30000,
			headers
		}).then(res => {
			if(loading) {
				CloseLoading()
			}
			resolve(res.data)
		}).catch(res => {
			if(loading) {
				CloseLoading()
			}
			ShowToast('请求失败，请检查网络连接')
			$('.mu-loading-wrap').remove()
			reject(res)
		})
	})
}