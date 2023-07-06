// 搜尋產品
function searchOrders() {
  var input = document.getElementById("searchInput");
  var filter = input.value.toUpperCase();
  var table = document.getElementById("orderTable");
  var tr = table.getElementsByTagName("tr");
  var noResultsMessage = document.getElementById("noResultsMessage");
  var hasResults = false;

  for (var i = 0; i < tr.length; i++) {
    var tdOrderNumber = tr[i].getElementsByTagName("td")[1]; // 訂單編號欄位
    var tdProductName = tr[i].getElementsByTagName("td")[3]; // 產品名稱欄位

    if (tdOrderNumber && tdProductName) {
      var orderNumber = tdOrderNumber.textContent || tdOrderNumber.innerText;
      var productName = tdProductName.textContent || tdProductName.innerText;

      if (orderNumber.toUpperCase().indexOf(filter) > -1 || productName.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = ""; // 顯示符合條件的行
        hasResults = true;
      } else {
        tr[i].style.display = "none"; // 隱藏不符合條件的行
      }
    }
  }

  if (!hasResults) {
    noResultsMessage.style.display = "block"; // 顯示 "查無此訂單" 的訊息
  } else {
    noResultsMessage.style.display = "none"; // 隱藏訊息
  }
}


//訂單編號or日期排列函式
function sortTableByColumn(columnIndex, event) {
  event.preventDefault();

  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("orderTable");
  switching = true;

  while (switching) {
    switching = false;
    rows = table.rows;

    for (i = 1; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("td")[columnIndex];
      y = rows[i + 1].getElementsByTagName("td")[columnIndex];

      if (columnIndex === 2) {
        var dateX = new Date(x.innerHTML);
        var dateY = new Date(y.innerHTML);

        if (dateX < dateY) {
          shouldSwitch = true;
          break;
        }
      } else {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          shouldSwitch = true;
          break;
        }
      }
    }

    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}
//追蹤按鈕更新
function trackCourse(courseId) {
  window.location.href = '/course/coursecheckout?courseId=' + courseId;
  //
  // button.innerHTML = "已加入購物車";
  // button.disabled = true;
}
//取消追蹤
function removeCourseRow(button) {
  var row = button.closest("tr");
  row.remove();
}
