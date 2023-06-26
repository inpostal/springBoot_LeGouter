fetch('/header')
    .then(response => response.text())
    .then(data => {
        document.getElementById('header-div').outerHTML = data;
    })
    .catch(error => {
        console.error('Error:', error);
    });

fetch('/navbar')
    .then(response => response.text())
    .then(data => {
        document.getElementById('nav-div').outerHTML = data;
    })
    .catch(error => {
        console.error('Error:', error);
    });

fetch('/footer')
    .then(response => response.text())
    .then(data => {
        document.getElementById('footer-div').outerHTML = data;
    })
    .catch(error => {
        console.error('Error:', error);
    });

fetch('/copyright')
    .then(response => response.text())
    .then(data => {
        document.getElementById('copyright-div').outerHTML = data;
    })
    .catch(error => {
        console.error('Error:', error);
    });