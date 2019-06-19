import * as Api from './ApiInterface'

const ErrorCode = {}

export const GetReturnCode = (type, code) => {
	if(ErrorCode[type][code]) {
		return ErrorCode[type][code]
	} else {
		return '服务器繁忙'
	}
}