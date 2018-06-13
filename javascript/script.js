function getRandomColor() {
  var letters = '0123456789ABCDEF';
  var color = '#';
  for (var i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}
document.getElementById('box').onclick=function(){
var x=Math.random();
x=x*5000;
x=Math.floor(x); 
this.style.display='none'
var timeLeft=x+'s';
document.getElementById('time').innerHTML=timeLeft
setTimeout(function(){
document.getElementById('box').style.backgroundColor=getRandomColor();
document.getElementById('box').style.display="block";
},x)
}
