$(function () {
    $(document).on('click', '.select-all', function () {
        let that = $(this),
            target = $(that.data('select-all-target')),
            checkbox = target.find('input[type="checkbox"]');

        if (that.prop('checked')) {
            checkbox.closest('tr').addClass('tr-selected');
            checkbox.prop('checked', true);
        } else {
            checkbox.closest('tr').removeClass('tr-selected');
            checkbox.prop('checked', false);
        }
    });

    $(document).on('click', '#orders input[type="checkbox"]', function () {
        let that = $(this);

        if (that.prop('checked')) {
            that.closest('tr').addClass('tr-selected');
        } else {
            that.closest('tr').removeClass('tr-selected');
        }
    });
});
// 搜尋產品function searchOrders() {
  function searchOrders() {
    let searchInput = $('#searchInput').val().trim().toLowerCase();
    let rows = $('#orders tbody tr');
  
    rows.hide();
  
    rows.each(function() {
      let orderNumber = $(this).find('td:first-child a').text().toLowerCase();
  
      if (orderNumber.includes(searchInput)) {
        $(this).show();
      }
    });
  
    let visibleRows = $('#orders tbody tr:visible');
  
    if (visibleRows.length === 0) {
      $('#orders').after('<p class="text-center mt-4">查無此訂單</p>');
    } else {
      $('.table + p').remove();
    }
  }