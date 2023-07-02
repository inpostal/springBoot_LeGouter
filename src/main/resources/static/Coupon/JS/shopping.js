var cartItems = [];
var selectedDiscount = null;

function addToCart(productId) {
    var product = getProductById(productId);
    cartItems.push(product);

    // 獲取按鈕元素
    var addButton = document.querySelector('.add-to-cart[data-product-id="' + productId + '"]');

    // 改變按鈕文字為「已加入購物車」
    addButton.textContent = '已加入購物車';

    // 設定計時器，一段時間後將按鈕文字還原為「加入購物車」
    setTimeout(function () {
        addButton.textContent = '加入購物車';
    }, 1500);
}
function getProductById(productId) {
    switch (productId) {
        case 1:
            return {
                id: 1,
                name: '課程 1',
                price: 50.00,
                date: '2023-07-01',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.'
            };
        case 2:
            return {
                id: 2,
                name: '課程 2',
                price: 80.00,
                date: '2023-07-15',
                description: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'
            };
        case 3:
            return {
                id: 3,
                name: '課程 3',
                price: 60.00,
                date: '2023-08-01',
                description: 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
            };
        case 4:
            return {
                id: 4,
                name: '課程 4',
                price: 70.00,
                date: '2023-08-15',
                description: 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.'
            };
        case 5:
            return {
                id: 5,
                name: '課程 5',
                price: 90.00,
                date: '2023-09-01',
                description: 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'
            };
        case 6:
            return {
                id: 6,
                name: '課程 6',
                price: 75.00,
                date: '2023-09-15',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'
            };
        default:
            return null;
    }
}

// 在confirmCheckout()函式後面新增以下程式碼
var addToCartButtons = document.querySelectorAll('.add-to-cart');

addToCartButtons.forEach(function (button) {
    button.addEventListener('click', function () {
        var productId = parseInt(button.dataset.productId);
        addToCart(productId);
        button.style.backgroundColor = 'green';
        button.textContent = '已加入購物車';
        setTimeout(function () {
            button.style.backgroundColor = '';
            button.textContent = '加入購物車';
        }, 1500);
    });
});


/*優惠券彈跳視窗*/
var modal = document.getElementById("myModal");
    
    window.onload = function() {
      modal.style.display;
    //   modal.style.display = "block";

    };
    
    function closeModal() {
      modal.style.display = "none";
    }
    
    function claimCoupon() {
      // 執行領取優惠券的相關操作
      closeModal();
    }
/*優惠券確認按鈕*/
function showSuccessMessage() {
    var successMessage = document.getElementById("successMessage");
    successMessage.style.display = "block";
  }

  function closeSuccessMessage() {
    var successMessage = document.getElementById("successMessage");
    successMessage.style.display = "none";
  }