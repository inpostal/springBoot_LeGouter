// 折扣計算與更新總計
function calculateTotal() {
     let subtotalC = parseFloat(document.getElementById("subtotalC").textContent.replace("$", ""));
    // let discountAmount = parseFloat(document.getElementById("discountAmount").textContent.replace("$", ""));

    // 取得id為total的商品總額
    let totalElement = document.getElementById("total");
// 由於總額是以$為開頭的字符串，我們需要去除$，並將剩下的部分轉換成數字
    let total = subtotalC;
    document.getElementById("total").textContent = "$" + total.toFixed(2);

    if (total >= cpThreshold) {
        // 是則total -= cpDiscount
        total -= cpDiscount;
        // 更新畫面上的總額
        // totalElement.textContent = "$" + total.toFixed(2);
        document.getElementById("total").textContent = "$" + total.toFixed(2);

    }


    // let total = subtotalA + subtotalB + subtotalC - discountAmount;
    // document.getElementById("total").textContent = "$" + total.toFixed(2);
}



// 重置所有優惠券
function resetAllCoupons() {
    let checkboxes = document.querySelectorAll(".coupon-checkbox input[type='checkbox']");
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = false;
        document.getElementById(checkbox.id + "Text").textContent = "";
    });
    applyCouponDiscount(0);
    calculateTotal();
}

// 套用優惠券折扣
function applyCouponDiscount(discount) {
    let subtotalC = parseFloat(document.getElementById("subtotalC").textContent.replace("$", ""));
    let totalDiscount = subtotalC * (discount / 100);
    document.getElementById("discountAmount").textContent = "$" + totalDiscount.toFixed(2);
    let total = subtotalC - totalDiscount;
    document.getElementById("total").textContent = "$" + total.toFixed(2);
}


// 確認優惠券
function applyCoupon() {
        closeCouponModal(); // 優惠券選擇完成後，關閉優惠券模態框
}

// 顯示優惠券模態框

// 關閉優惠券模態框
function closeCouponModal() {
    let couponModal = document.getElementById("couponModal");
    couponModal.style.display = "none";
}
// 初始設定總計金額
calculateTotal();
