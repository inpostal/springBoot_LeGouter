function toggleDetails(button) {
    var content = button.parentElement.nextElementSibling;
    if (window.getComputedStyle(content).display === "none") {
      content.style.display = "block";
    } else {
      content.style.display = "none";
    }
  }