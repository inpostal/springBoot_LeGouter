fetch('/get/navbar')
    .then(response => response.text())
    .then(data => {
        document.getElementById('nav-bar').innerHTML = data;
    })
    .catch(error => {
        console.error('Error:', error);
    });

fetch('/get/header')
    .then(response => response.text())
    .then(data => {
        document.getElementById('mainheader').innerHTML = data;
    })
    .catch(error => {
        console.error('Error:', error);
    });