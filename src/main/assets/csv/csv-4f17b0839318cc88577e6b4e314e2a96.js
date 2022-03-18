"use strict";function styleInject(e,t){void 0===t&&(t={});var n=t.insertAt;if(e&&"undefined"!=typeof document){var i=document.head||document.getElementsByTagName("head")[0],a=document.createElement("style");a.type="text/css","top"===n&&i.firstChild?i.insertBefore(a,i.firstChild):i.appendChild(a),a.styleSheet?a.styleSheet.cssText=e:a.appendChild(document.createTextNode(e))}}var css=".spin-indicator{position:fixed;display:inline-block;left:50%;top:50%;pointer-events:none;transform:translate(-50%,-50%)}.spin-indicator-box{color:#3370ff;vertical-align:middle;text-align:center;-webkit-transition:-webkit-transform .3s cubic-bezier(.78,.14,.15,.86);transition:-webkit-transform .3s cubic-bezier(.78,.14,.15,.86);transition:transform .3s cubic-bezier(.78,.14,.15,.86);transition:transform .3s cubic-bezier(.78,.14,.15,.86),-webkit-transform .3s cubic-bezier(.78,.14,.15,.86);font-size:12px;opacity:1;position:static;display:inline-block}.spin-indicator-dots{position:relative;display:inline-block;width:20px;height:20px;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);-webkit-animation:antRotate 1.2s linear infinite;animation:antRotate 1.2s linear infinite}.spin-indicator-dots>i{width:9px;height:9px;border-radius:100%;background-color:#3370ff;-webkit-transform:scale(.75);-ms-transform:scale(.75);transform:scale(.75);display:block;position:absolute;opacity:.3;-webkit-animation:antSpinMove 1s linear infinite alternate;animation:antSpinMove 1s linear infinite alternate;-webkit-transform-origin:50% 50%;-ms-transform-origin:50% 50%;transform-origin:50% 50%}.spin-indicator-dots .spin-indicator-dot1{left:0;top:0}.spin-indicator-dots .spin-indicator-dot2{right:0;top:0;-webkit-animation-delay:.4s;animation-delay:.4s}.spin-indicator-dots .spin-indicator-dot3{right:0;bottom:0;-webkit-animation-delay:.8s;animation-delay:.8s}.spin-indicator-dots .spin-indicator-dot4{left:0;bottom:0;-webkit-animation-delay:1.2s;animation-delay:1.2s}@keyframes antRotate{to{-webkit-transform:rotate(405deg);transform:rotate(405deg)}}@keyframes antSpinMove{to{opacity:1}}";function createCommonjsModule(e,t){return e(t={exports:{}},t.exports),t.exports}styleInject(css);var csv_min=createCommonjsModule(function(e){function g(e){return null!=e}function A(e,t,n,i){var a,r="return ",o=i?function(e,t){return s.test(e)?"Number(values[".concat(t,"]),"):function(e){return"true"===(e=e.toLowerCase())||"false"===e}(e)?"Boolean(values[".concat(t,"].toLowerCase() === 'true'),"):"String(values[".concat(t,"]),")}:function(e,t){return"values[".concat(t,"],")};if("object"===e){for(r+="{",a=0;a<t.length;++a)r+='"'.concat(t[a],'": ').concat(o(n[a],a));r="".concat(r.slice(0,-1),"}")}else{for(r+="[",a=0;a<t.length;++a)r+=o(n[a],a);r="".concat(r.slice(0,-1),"]")}return new Function("values",r)}function i(e,t){for(var n,i=0,a=0,r=t.length;a<r;a++){var o=t[a],s=-1==c.indexOf(o)?o:"\\".concat(o),l=e.match(new RegExp(s,"g"));l&&l.length>i&&(i=l.length,n=o)}return n||t[0]}function t(e,t){if(t=g(t)?t:{},this.data=e,this.options={header:!!g(t.header)&&t.header,cast:!g(t.cast)||t.cast,line:t.line,delimiter:t.delimiter},this.data instanceof Array)this.options.line=g(t.line)?t.line:"\r\n",this.options.delimiter=g(t.delimiter)?t.delimiter:",";else{this.options.line||(this.options.line=i(this.data,r)),this.options.delimiter||(this.options.delimiter=i(this.data,a));for(var n=0;n<this.options.line.length;n++){e.charCodeAt(e.length-this.options.line.length+n)!=this.options.line.charCodeAt(n)&&(this.data+=this.options.line.charAt(n))}}}var s,c,a,r;s=/^(\-|\+)?([0-9]+(\.[0-9]+)?|Infinity)$/,c=["|","^"],a=[",",";","  ","|","^"],r=["\r\n","\r","\n"],t.prototype.set=function(e,t){return this.options[e]=t,this},t.prototype.encode=function(t){if(0===this.data.length)return"";function e(e){return g(e)?"string"!=typeof e?e:'"'.concat(e.replace(/\"/g,'""'),'"'):null}var n,i,a,r,o,s,l=this.data,c=[],d=this.options.delimiter,u=l[0]instanceof Array?"array":"object",p=this.options.header,m=this.options.done,f=t?function(e){t(e.join(d))}:function(e){c.push(e.join(d))},h=l.length;if(r="object"==u?(a=Object.keys(l[0])).length:l[0].length,s=new Array(r),p){var T=p instanceof Array?p:a;for(i=0;i<r;++i)s[i]=e(T[i]);f(s)}if("object"==u)for(n=0;n<h;++n){for(o=l[n],i=0;i<r;++i)s[i]=e(o[a[i]]);f(s)}else for(n=0;n<h;++n){for(o=l[n],i=0;i<r;++i)s[i]=e(o[i]);f(s)}return c=c.join(this.options.line),m&&m(c),c},t.prototype.parse=function(e){if(0===this.data.trim().length)return[];function t(e){f.row.push((h.escaped?e.slice(1,-1).replace(/""/g,'"'):e).trim()),f.cell="",h={escaped:!1,quote:!1,cell:!0}}var n,i,a,r,o=this.data,s=[],l=this.options.done,c=this.options.cast,d=this.options.header,u=d instanceof Array?d:[],p=this.options.line,m=u.length,f={row:[],cell:""},h={escaped:!1,quote:!1,cell:!0},T=1===p.length?t:function(e){t(e.slice(0,1-p.length))},g=e?function(){e(new n(f.row))}:function(){s.push(new n(f.row))},b=function(){d?m?(n=new A("object",u,f.row,c),g(),b=g):(u=f.row,m=u.length):(n=n||new A("array",f.row,f.row,c),g(),b=g)},E=o.length,y=p.charCodeAt(p.length-1),v=this.options.delimiter.charCodeAt(0);for(a=i=0;a<=E;++a)r=o.charCodeAt(a),h.cell&&(h.cell=!1,34===r)?h.escaped=!0:h.escaped&&34===r?h.quote=!h.quote:(h.escaped&&h.quote||!h.escaped)&&(r===v?(t(f.cell+o.slice(i,a)),i=a+1):r===y&&(T(f.cell+o.slice(i,a)),i=a+1,b(),f.row=[]));return l&&l(s),s},t.prototype.forEach=function(e){return this.data instanceof Array?this.encode(e):this.parse(e)},e.exports?e.exports=t:window&&(window.CSV=t)}),css$1="*{box-sizing:border-box;margin:0;padding:0;-webkit-font-smoothing:antialiased}body{font-family:-apple-system,BlinkMacSystemFont,Helvetica Neue,Tahoma,PingFang SC,Microsoft Yahei,Arial,Hiragino Sans GB,sans-serif}body::-webkit-scrollbar{-webkit-appearance:none;background-color:transparent;width:7px;height:7px}body::-webkit-scrollbar-thumb{background:rgba(187,191,196,.5);width:7px;border-radius:3px;display:block;min-height:38px}body::-webkit-scrollbar-track{background-color:transparent;display:block}mark{background:rgba(255,198,10,.4)}mark.current-mark{background:rgba(245,74,69,.5)}";styleInject(css$1);var css$2="body,html{width:100%;height:100%}html{overflow:hidden}body{font-size:xx-small;overflow:auto}.common-style-block table{border-spacing:0;border-collapse:collapse}.common-style-block table tbody:first-child tr:first-child{font-weight:700}.common-style-block table tbody tr:nth-child(2n){background:#f8f8f8}.common-style-block table tbody tr{min-height:16px;height:16px}.common-style-block table tbody tr td{padding:0 2px;min-width:120px;border:1px solid #dee0e3}.common-style-block pre{white-space:pre-wrap;margin:0}#init-placeholder{position:absolute;top:0;left:0;max-height:100%;overflow:hidden}";styleInject(css$2);var POST_MESSAGE_EVENT={FIND_TEXT:"findText",FIND_PREV:"findPrev",FIND_NEXT:"findNext",FIND_EXIT:"findExit",FIND_STATE_CHANGED:"findStateChanged"};function Find(e,t){this.targetElements=[e],this.markInstances=[new Mark(e)],this.matchElements=[],this.currentIndex=0,this.currentClass=t||"current-mark",this.gapOffset=20,this.findId=0,this.init()}Find.prototype.init=function(){var i=this;window.addEventListener("message",function(e){var t=e.data;if(t&&t.key){var n=t.value;switch(t.key){case POST_MESSAGE_EVENT.FIND_TEXT:i.triggerFindText(n);break;case POST_MESSAGE_EVENT.FIND_PREV:i.findPrev();break;case POST_MESSAGE_EVENT.FIND_NEXT:i.findNext();break;case POST_MESSAGE_EVENT.FIND_EXIT:i.reset()}}})},Find.prototype.triggerFindText=function(t){var r=this;r.findId+=1;for(var o=r.findId,e=0;e<r.markInstances.length;e++)!function(a){setTimeout(function(){if(r.findId===o){var e=r.markInstances[a],i=r.targetElements[a];e.unmark({done:function(){r.findId===o&&e.mark(t,{separateWordSearch:!0,done:function(){if(r.findId===o){0===a&&(r.currentIndex=0,r.matchElements=[]);var e=r.matchElements.length,t=Array.prototype.slice.call(i.querySelectorAll("mark"));r.matchElements=r.matchElements.concat(t);var n=r.matchElements.length;!e&&n&&r.jumpTo(),r.updateFindState()}}})}})}},600*a)}(e)},Find.prototype.jumpTo=function(){var t=this;if(t.matchElements.length){var e=t.matchElements[t.currentIndex];document.querySelectorAll(".".concat(t.currentClass)).forEach(function(e){e.classList.remove(t.currentClass)}),e&&(e.classList.add(t.currentClass),e.scrollIntoView({behavior:"auto",block:"center",inline:"center"})),t.updateFindState()}},Find.prototype.findPrev=function(){var e=this;if(e.matchElements.length){var t=e.currentIndex-1,n=t<0?e.matchElements.length-1:t;e.currentIndex=n,e.jumpTo()}},Find.prototype.findNext=function(){var e=this;if(e.matchElements.length){var t=e.currentIndex+1,n=t>=e.matchElements.length?0:t;e.currentIndex=n,e.jumpTo()}},Find.prototype.reset=function(){var t=this;t.matchElements=[],t.currentIndex=0,t.findId+=1;for(var n=t.findId,e=0;e<t.markInstances.length;e++)!function(e){setTimeout(function(){t.findId===n&&t.markInstances[e].unmark()},200*e)}(e)},Find.prototype.updateFindState=function(){var e=this.matchElements.length;parent.postMessage({key:POST_MESSAGE_EVENT.FIND_STATE_CHANGED,value:"",context:{current:e?this.currentIndex+1:0,total:e}},"*")},Find.prototype.addTargetElement=function(e){this.targetElements.push(e),this.markInstances.push(new Mark(e))};var copyable=!0,IFRAME_EVENT={COPY_WITHOUT_PERMISSION:"copyWithoutPermission"},PARENT_EVENT={SET_COPYABLE:"setCopyable"};function handleDocumentRightClick(e){copyable||e.preventDefault()}function handleDocumentCopy(e){copyable||(e.preventDefault(),parent.postMessage({key:IFRAME_EVENT.COPY_WITHOUT_PERMISSION},"*"))}window.addEventListener("message",function(e){var t=e.data;if(t.key)switch(t.key){case PARENT_EVENT.SET_COPYABLE:copyable=t.value}}),document.addEventListener("copy",handleDocumentCopy),document.addEventListener("contextmenu",handleDocumentRightClick);var freeGlobal="object"==typeof global&&global&&global.Object===Object&&global,freeSelf="object"==typeof self&&self&&self.Object===Object&&self,root=freeGlobal||freeSelf||Function("return this")(),Symbol=root.Symbol,objectProto=Object.prototype,hasOwnProperty=objectProto.hasOwnProperty,nativeObjectToString=objectProto.toString,symToStringTag=Symbol?Symbol.toStringTag:void 0;function getRawTag(e){var t=hasOwnProperty.call(e,symToStringTag),n=e[symToStringTag];try{var i=!(e[symToStringTag]=void 0)}catch(e){}var a=nativeObjectToString.call(e);return i&&(t?e[symToStringTag]=n:delete e[symToStringTag]),a}var objectProto$1=Object.prototype,nativeObjectToString$1=objectProto$1.toString;function objectToString(e){return nativeObjectToString$1.call(e)}var nullTag="[object Null]",undefinedTag="[object Undefined]",symToStringTag$1=Symbol?Symbol.toStringTag:void 0;function baseGetTag(e){return null==e?void 0===e?undefinedTag:nullTag:symToStringTag$1&&symToStringTag$1 in Object(e)?getRawTag(e):objectToString(e)}function isObjectLike(e){return null!=e&&"object"==typeof e}var symbolTag="[object Symbol]";function isSymbol(e){return"symbol"==typeof e||isObjectLike(e)&&baseGetTag(e)==symbolTag}function isObject(e){var t=typeof e;return null!=e&&("object"==t||"function"==t)}var NAN=NaN,reTrim=/^\s+|\s+$/g,reIsBadHex=/^[-+]0x[0-9a-f]+$/i,reIsBinary=/^0b[01]+$/i,reIsOctal=/^0o[0-7]+$/i,freeParseInt=parseInt;function toNumber(e){if("number"==typeof e)return e;if(isSymbol(e))return NAN;if(isObject(e)){var t="function"==typeof e.valueOf?e.valueOf():e;e=isObject(t)?t+"":t}if("string"!=typeof e)return 0===e?e:+e;e=e.replace(reTrim,"");var n=reIsBinary.test(e);return n||reIsOctal.test(e)?freeParseInt(e.slice(2),n?2:8):reIsBadHex.test(e)?NAN:+e}var now=function(){return root.Date.now()},FUNC_ERROR_TEXT="Expected a function",nativeMax=Math.max,nativeMin=Math.min;function debounce(i,n,e){var a,r,o,s,l,c,d=0,u=!1,p=!1,t=!0;if("function"!=typeof i)throw new TypeError(FUNC_ERROR_TEXT);function m(e){var t=a,n=r;return a=r=void 0,d=e,s=i.apply(n,t)}function f(e){var t=e-c;return void 0===c||n<=t||t<0||p&&o<=e-d}function h(){var e=now();if(f(e))return T(e);l=setTimeout(h,function(e){var t=n-(e-c);return p?nativeMin(t,o-(e-d)):t}(e))}function T(e){return l=void 0,t&&a?m(e):(a=r=void 0,s)}function g(){var e=now(),t=f(e);if(a=arguments,r=this,c=e,t){if(void 0===l)return function(e){return d=e,l=setTimeout(h,n),u?m(e):s}(c);if(p)return clearTimeout(l),l=setTimeout(h,n),m(c)}return void 0===l&&(l=setTimeout(h,n)),s}return n=toNumber(n)||0,isObject(e)&&(u=!!e.leading,o=(p="maxWait"in e)?nativeMax(toNumber(e.maxWait)||0,n):o,t="trailing"in e?!!e.trailing:t),g.cancel=function(){void 0!==l&&clearTimeout(l),a=c=r=l=void(d=0)},g.flush=function(){return void 0===l?s:T(now())},g}var FUNC_ERROR_TEXT$1="Expected a function";function throttle(e,t,n){var i=!0,a=!0;if("function"!=typeof e)throw new TypeError(FUNC_ERROR_TEXT$1);return isObject(n)&&(i="leading"in n?!!n.leading:i,a="trailing"in n?!!n.trailing:a),debounce(e,t,{leading:i,maxWait:t,trailing:a})}var GLOBAL_STATE={isHighLightMode:!1,innerPreClassName:"inner-pre",outsideWrapperId:"htmlout",innerTbodyClassName:"inner-tbody",finder:null,currentMarkClassName:"current-mark",placeholderWrapperId:"init-placeholder",spinIndicatorClassName:"spin-indicator",perPreMaxLength:25e4,perInsertDelay:600,perPageMaxLineLength:200,enableFinder:!Boolean(window.jsObj),mandatoryHighLight:Boolean(window.jsObj),enableLazyLoad:Boolean(window.jsObj),tempRawData:"",unParsedRowList:[],tableElement:null},IFRAME_EVENT$1={INIT_COMPLETE:"initComplete",COMPLETE:"complete"},PARENT_EVENT$1={INIT_SRC_DATA:"initSrcData",SRC_DATA:"srcData"};function removePlaceholder(){var e=document.getElementById(GLOBAL_STATE.placeholderWrapperId),t=document.querySelector(".".concat(GLOBAL_STATE.spinIndicatorClassName));e.parentNode.removeChild(e),t.parentNode.removeChild(t)}function initFinder(){var e=document.querySelector(".".concat(GLOBAL_STATE.innerTbodyClassName)),t=document.querySelector(".".concat(GLOBAL_STATE.innerPreClassName)),n=GLOBAL_STATE.isHighLightMode?e:t;GLOBAL_STATE.finder=new Find(n,GLOBAL_STATE.currentMarkClassName)}function handleRendered(e){var t=e?IFRAME_EVENT$1.INIT_COMPLETE:IFRAME_EVENT$1.COMPLETE;parent.postMessage({key:t},"*"),e||(removePlaceholder(),GLOBAL_STATE.enableFinder&&initFinder())}function appendTbody(e){if(GLOBAL_STATE.tableElement&&GLOBAL_STATE.unParsedRowList){var t=document.createElement("tbody");t.classList.add(GLOBAL_STATE.innerTbodyClassName);for(var n=GLOBAL_STATE.unParsedRowList.splice(0,GLOBAL_STATE.perPageMaxLineLength),i=0;i<n.length;i++){for(var a=document.createElement("tr"),r=0;r<n[i].length;r++){var o=document.createElement("td");o.innerText=n[i][r],a.appendChild(o)}t.appendChild(a)}GLOBAL_STATE.tableElement.appendChild(t),!e&&GLOBAL_STATE.finder&&GLOBAL_STATE.finder.addTargetElement(t)}}function lazyLoadTbody(){GLOBAL_STATE.enableLazyLoad&&GLOBAL_STATE.unParsedRowList&&0!==GLOBAL_STATE.unParsedRowList.length&&document.body.scrollTop+document.body.clientHeight+1500>=document.body.scrollHeight&&appendTbody()}var throttleScrollFunc=throttle(lazyLoadTbody,500,{trailing:!0,leading:!0});function handleDataInsert(e,i){var a=String(e),r=GLOBAL_STATE.perPreMaxLength,t=Math.ceil(a.length/r),n=i?GLOBAL_STATE.placeholderWrapperId:GLOBAL_STATE.outsideWrapperId,o=document.getElementById(n);if(0<t)for(var s=function(n){setTimeout(function(){var e=document.createElement("pre");e.classList.add(GLOBAL_STATE.innerPreClassName);var t=n*r;e.innerText=a.substr(t,r),o.appendChild(e),!i&&GLOBAL_STATE.finder&&GLOBAL_STATE.finder.addTargetElement(e),0===n&&handleRendered(i)},n*GLOBAL_STATE.perInsertDelay)},l=0;l<t;l++)s(l);else handleRendered(i)}function handleDataParse(t,n){try{var e=new csv_min(t,{cast:!1});GLOBAL_STATE.unParsedRowList=e.parse()||[];var i=n?GLOBAL_STATE.placeholderWrapperId:GLOBAL_STATE.outsideWrapperId,a=document.getElementById(i),r=document.createElement("table");a.appendChild(r),GLOBAL_STATE.tableElement=r;var o=GLOBAL_STATE.perPageMaxLineLength,s=Math.ceil(GLOBAL_STATE.unParsedRowList.length/o);if(0<s)if(GLOBAL_STATE.enableLazyLoad)appendTbody(n),handleRendered(n),document.body.addEventListener("scroll",throttleScrollFunc);else for(var l=function(e){setTimeout(function(){appendTbody(n),0===e&&handleRendered(n)},e*GLOBAL_STATE.perInsertDelay)},c=0;c<s;c++)l(c);else handleDataInsert(t,n)}catch(e){console.error("csv error",e),handleDataInsert(t,n)}}function getInitialDataInAndroid(){window.jsObj&&window.jsObj.webviewIsReady&&window.jsObj.webviewIsReady()}function init(){window.addEventListener("message",function(e){var t=e.data;if(t.key&&t.value)switch(t.key){case PARENT_EVENT$1.INIT_SRC_DATA:t.value.canHighLight?handleDataParse(String(t.value.text),!0):handleDataInsert(String(t.value.text),!0);break;case PARENT_EVENT$1.SRC_DATA:GLOBAL_STATE.tempRawData+=String(t.value.text),t.value.hasMore||(GLOBAL_STATE.mandatoryHighLight||t.value.canHighLight?(GLOBAL_STATE.isHighLightMode=!0,handleDataParse(GLOBAL_STATE.tempRawData,!1)):handleDataInsert(GLOBAL_STATE.tempRawData,!1),GLOBAL_STATE.tempRawData="")}}),getInitialDataInAndroid()}init();