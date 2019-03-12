// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.10/js/esri/copyright.txt for details.
//>>built
define("esri/renderers/jsonUtils","dojo/_base/lang dojo/has ../kernel ./SimpleRenderer ./UniqueValueRenderer ./ClassBreaksRenderer".split(" "),function(c,e,f,g,h,k){var d={fromJson:function(a){var b;switch(a.type||""){case "simple":b=new g(a);break;case "uniqueValue":b=new h(a);break;case "classBreaks":b=new k(a)}return b}};e("extend-esri")&&c.mixin(c.getObject("renderer",!0,f),d);return d});