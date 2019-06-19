import Bus from './Bus.js';
import * as EmitType from './EmitType.js';

// 提示文字信息
export const ShowToast = msg => {
	layer.msg(msg)
}

// 打开加载层
export const ShowLoading = () => {
	Bus.$emit(EmitType.ShowLoading)
}

// 关闭加载层
export const CloseLoading = () => {
	Bus.$emit(EmitType.CloseLoading)
}