/**
 * jQuery common functions
 * 
 * Author: bollen
 * 
 * Date: 2016-11-29
 */

function getFileExt(file){
	var filename=file.replace(/.*(\/|\\)/, ""); 
	var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';  
	return fileExt[0];
}