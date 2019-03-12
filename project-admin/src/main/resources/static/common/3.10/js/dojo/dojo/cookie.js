/*
	Copyright (c) 2004-2011, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/cookie",["./_base/kernel","./regexp"],function(b,g){b.cookie=function(h,e,c){var a=document.cookie,d;if(1==arguments.length)d=(d=a.match(RegExp("(?:^|; )"+g.escapeString(h)+"\x3d([^;]*)")))?decodeURIComponent(d[1]):void 0;else{c=c||{};a=c.expires;if("number"==typeof a){var f=new Date;f.setTime(f.getTime()+864E5*a);a=c.expires=f}a&&a.toUTCString&&(c.expires=a.toUTCString());e=encodeURIComponent(e);var a=h+"\x3d"+e,b;for(b in c)a+="; "+b,f=c[b],!0!==f&&(a+="\x3d"+f);document.cookie=a}return d};
b.cookie.getAll=function(b){var e=[];if(b=document.cookie.match(RegExp("(?:^|; )"+g.escapeString(b)+"\x3d([^;]*)","g")))for(var c=0;c<b.length;c++){var a=b[c],d=a.indexOf("\x3d");-1<d&&(a=a.substring(d+1),e.push(decodeURIComponent(a)))}return e};b.cookie.isSupported=function(){"cookieEnabled"in navigator||(this("__djCookieTest__","CookiesAllowed"),navigator.cookieEnabled="CookiesAllowed"==this("__djCookieTest__"),navigator.cookieEnabled&&this("__djCookieTest__","",{expires:-1}));return navigator.cookieEnabled};
return b.cookie});