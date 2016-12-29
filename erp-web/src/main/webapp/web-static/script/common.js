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

$(document).ajaxComplete(function(event, xhr, settings) {  
    if(xhr.getResponseHeader && xhr.getResponseHeader("sessionstatus")=="timeOut"){  
        if(xhr.getResponseHeader("loginPath")){
            alert("会话过期,请重新登录!");
            window.top.location.replace(xhr.getResponseHeader("loginPath"));  
        }
    }  
});