function sandbox(){var U='',Cb='" for "gwt:onLoadErrorFn"',Ab='" for "gwt:onPropertyErrorFn"',nb='"><\/script>',cb='#',Kb='&',rc='.cache.html',eb='/',qb='//',ac='056C3662494D2AFB79F47679A920517C',cc='2C9614B2E3694A688600AF452FD4DFE4',dc='381D01C07780191733B4DBC4646FABAF',ec='4B68997EDF0081905B509A63234BD864',fc='5B1CA94D9B6DEBC26DAE22633AF2E7E5',qc=':',ub='::',tc='<script defer="defer">sandbox.onInjectionDone(\'sandbox\')<\/script>',mb='<script id="',xb='=',db='?',gc='A0EDE5A311C35CE009B9657BF12C810A',hc='A802961D27E304598EA84C50DEA55443',ic='AE16A04537BE30C8605698C9A6B7D82D',jc='B234025055B4C03EFDF3BE50B4A18B8C',kc='B417B0BB471BEA7F6C83F0AC6D4AC76A',zb='Bad handler "',lc='C655397C78E712ACDF7BA2F560025A15',mc='CF9A3678CDDBEE7ACABDB9D5B87B6084',nc='D82A27F4880D48E1A83290BBA27E3349',oc='DA7A94226F2327A2320F88660CF88CF9',sc='DOMContentLoaded',pc='FDECDB47881360A553B776A398F6BCEF',ob='SCRIPT',Nb='Unexpected exception in locale detection, using default: ',Mb='_',Lb='__gwt_Locale',lb='__gwt_marker_sandbox',pb='base',hb='baseUrl',Y='begin',X='bootstrap',gb='clear.cache.gif',wb='content',_b='default',bc='en',bb='end',Ib='fr',Vb='gecko',Wb='gecko1_8',Z='gwt.codesvr=',$='gwt.hosted=',_='gwt.hybrid',Bb='gwt:onLoadErrorFn',yb='gwt:onPropertyErrorFn',vb='gwt:property',Zb='hosted.html?sandbox',Sb='ie10',Ub='ie8',Tb='ie9',Db='iframe',fb='img',Eb="javascript:''",Yb='loadExternalRefs',Hb='locale',Jb='locale=',rb='meta',Gb='moduleRequested',ab='moduleStartup',Rb='msie',sb='name',Fb='position:absolute;width:0;height:0;border:none',Qb='safari',V='sandbox',jb='sandbox.nocache.js',tb='sandbox::',ib='script',$b='selectingPermutation',W='startup',kb='undefined',Xb='unknown',Ob='user.agent',Pb='webkit';var m=window,n=document,o=m.__gwtStatsEvent?function(a){return m.__gwtStatsEvent(a)}:null,p=m.__gwtStatsSessionId?m.__gwtStatsSessionId:null,q,r,s,t=U,u={},v=[],w=[],A=[],B=0,C,D;o&&o({moduleName:V,sessionId:p,subSystem:W,evtGroup:X,millis:(new Date).getTime(),type:Y});if(!m.__gwt_stylesLoaded){m.__gwt_stylesLoaded={}}if(!m.__gwt_scriptsLoaded){m.__gwt_scriptsLoaded={}}function F(){var b=false;try{var c=m.location.search;return (c.indexOf(Z)!=-1||(c.indexOf($)!=-1||m.external&&m.external.gwtOnLoad))&&c.indexOf(_)==-1}catch(a){}F=function(){return b};return b}
function G(){if(q&&r){var b=n.getElementById(V);var c=b.contentWindow;if(F()){c.__gwt_getProperty=function(a){return M(a)}}sandbox=null;c.gwtOnLoad(C,V,t,B);o&&o({moduleName:V,sessionId:p,subSystem:W,evtGroup:ab,millis:(new Date).getTime(),type:bb})}}
function H(){function e(a){var b=a.lastIndexOf(cb);if(b==-1){b=a.length}var c=a.indexOf(db);if(c==-1){c=a.length}var d=a.lastIndexOf(eb,Math.min(c,b));return d>=0?a.substring(0,d+1):U}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=n.createElement(fb);b.src=a+gb;a=e(b.src)}return a}
function g(){var a=K(hb);if(a!=null){return a}return U}
function h(){var a=n.getElementsByTagName(ib);for(var b=0;b<a.length;++b){if(a[b].src.indexOf(jb)!=-1){return e(a[b].src)}}return U}
function i(){var a;if(typeof isBodyLoaded==kb||!isBodyLoaded()){var b=lb;var c;n.write(mb+b+nb);c=n.getElementById(b);a=c&&c.previousSibling;while(a&&a.tagName!=ob){a=a.previousSibling}if(c){c.parentNode.removeChild(c)}if(a&&a.src){return e(a.src)}}return U}
function j(){var a=n.getElementsByTagName(pb);if(a.length>0){return a[a.length-1].href}return U}
function k(){var a=n.location;return a.href==a.protocol+qb+a.host+a.pathname+a.search+a.hash}
var l=g();if(l==U){l=h()}if(l==U){l=i()}if(l==U){l=j()}if(l==U&&k()){l=e(n.location.href)}l=f(l);t=l;return l}
function I(){var b=document.getElementsByTagName(rb);for(var c=0,d=b.length;c<d;++c){var e=b[c],f=e.getAttribute(sb),g;if(f){f=f.replace(tb,U);if(f.indexOf(ub)>=0){continue}if(f==vb){g=e.getAttribute(wb);if(g){var h,i=g.indexOf(xb);if(i>=0){f=g.substring(0,i);h=g.substring(i+1)}else{f=g;h=U}u[f]=h}}else if(f==yb){g=e.getAttribute(wb);if(g){try{D=eval(g)}catch(a){alert(zb+g+Ab)}}}else if(f==Bb){g=e.getAttribute(wb);if(g){try{C=eval(g)}catch(a){alert(zb+g+Cb)}}}}}}
function J(a,b){return b in v[a]}
function K(a){var b=u[a];return b==null?null:b}
function L(a,b){var c=A;for(var d=0,e=a.length-1;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
function M(a){var b=w[a](),c=v[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(D){D(a,d,b)}throw null}
var N;function O(){if(!N){N=true;var a=n.createElement(Db);a.src=Eb;a.id=V;a.style.cssText=Fb;a.tabIndex=-1;n.body.appendChild(a);o&&o({moduleName:V,sessionId:p,subSystem:W,evtGroup:ab,millis:(new Date).getTime(),type:Gb});a.contentWindow.location.replace(t+Q)}}
w[Hb]=function(){var b=null;var c=Ib;try{if(!b){var d=location.search;var e=d.indexOf(Jb);if(e>=0){var f=d.substring(e+7);var g=d.indexOf(Kb,e);if(g<0){g=d.length}b=d.substring(e+7,g)}}if(!b){b=K(Hb)}if(!b){b=m[Lb]}if(b){c=b}while(b&&!J(Hb,b)){var h=b.lastIndexOf(Mb);if(h<0){b=null;break}b=b.substring(0,h)}}catch(a){alert(Nb+a)}m[Lb]=c;return b||Ib};v[Hb]={'default':0,en:1,fr:2};w[Ob]=function(){var b=navigator.userAgent.toLowerCase();var c=function(a){return parseInt(a[1])*1000+parseInt(a[2])};if(function(){return b.indexOf(Pb)!=-1}())return Qb;if(function(){return b.indexOf(Rb)!=-1&&n.documentMode>=10}())return Sb;if(function(){return b.indexOf(Rb)!=-1&&n.documentMode>=9}())return Tb;if(function(){return b.indexOf(Rb)!=-1&&n.documentMode>=8}())return Ub;if(function(){return b.indexOf(Vb)!=-1}())return Wb;return Xb};v[Ob]={gecko1_8:0,ie10:1,ie8:2,ie9:3,safari:4};sandbox.onScriptLoad=function(){if(N){r=true;G()}};sandbox.onInjectionDone=function(){q=true;o&&o({moduleName:V,sessionId:p,subSystem:W,evtGroup:Yb,millis:(new Date).getTime(),type:bb});G()};I();H();var P;var Q;if(F()){if(m.external&&(m.external.initModule&&m.external.initModule(V))){m.location.reload();return}Q=Zb;P=U}o&&o({moduleName:V,sessionId:p,subSystem:W,evtGroup:X,millis:(new Date).getTime(),type:$b});if(!F()){try{L([_b,Qb],ac);L([bc,Qb],cc);L([_b,Tb],dc);L([Ib,Wb],ec);L([bc,Ub],fc);L([Ib,Qb],gc);L([Ib,Tb],hc);L([Ib,Ub],ic);L([_b,Ub],jc);L([_b,Sb],kc);L([bc,Sb],lc);L([bc,Wb],mc);L([bc,Tb],nc);L([_b,Wb],oc);L([Ib,Sb],pc);P=A[M(Hb)][M(Ob)];var R=P.indexOf(qc);if(R!=-1){B=Number(P.substring(R+1));P=P.substring(0,R)}Q=P+rc}catch(a){return}}var S;function T(){if(!s){s=true;G();if(n.removeEventListener){n.removeEventListener(sc,T,false)}if(S){clearInterval(S)}}}
if(n.addEventListener){n.addEventListener(sc,function(){O();T()},false)}var S=setInterval(function(){if(/loaded|complete/.test(n.readyState)){O();T()}},50);o&&o({moduleName:V,sessionId:p,subSystem:W,evtGroup:X,millis:(new Date).getTime(),type:bb});o&&o({moduleName:V,sessionId:p,subSystem:W,evtGroup:Yb,millis:(new Date).getTime(),type:Y});n.write(tc)}
sandbox();