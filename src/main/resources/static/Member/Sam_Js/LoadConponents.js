fetch('/header')
    .then(response=>response.text())
    .then(html=>{
        document.querySelector('#header-div').innerHTML = html;
    })
fetch('/navbar')
    .then(response=>response.text())
    .then(html=>{
        document.querySelector('#nav-bar').innerHTML = html;
    })
fetch('/footer')
    .then(response=>response.text())
    .then(html=>{
        document.querySelector('#footer-div').innerHTML = html;
    })
fetch('copyright')
    .then(response=>response.text())
    .then(html=>{
        document.querySelector('#copyright-div').innerHTML = html;
    })