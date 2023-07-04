function calculateSubtotal() {
    var priceA = 10.00;
    var priceB = 15.00;
    var priceC = 20.00;
    var quantityA = parseInt(document.getElementById("quantityA").value);
    var quantityB = parseInt(document.getElementById("quantityB").value);
    var quantityC = parseInt(document.getElementById("quantityC").value);
    
    document.getElementById("subtotalA").textContent = "$" + (priceA * quantityA).toFixed(2);
    document.getElementById("subtotalB").textContent = "$" + (priceB * quantityB).toFixed(2);
    document.getElementById("subtotalC").textContent = "$" + (priceC * quantityC).toFixed(2);
    
    var total = (priceA * quantityA) + (priceB * quantityB) + (priceC * quantityC);
    document.getElementById("total").textContent = "$" + total.toFixed(2);
}

function showCouponModal() {
    document.getElementById("couponModal").style.display = "block";
}

function applyCoupon() {
    var total = parseFloat(document.getElementById("total").textContent.slice(1));
    var coupon1 = document.getElementById("coupon1");
    var coupon2 = document.getElementById("coupon2");
    var coupon3 = document.getElementById("coupon3");

    var selectedCoupon = null;

    if (coupon1.checked) {
        selectedCoupon = coupon1;
    } else if (coupon2.checked) {
        selectedCoupon = coupon2;
    } else if (coupon3.checked) {
        selectedCoupon = coupon3;
    }

    if (selectedCoupon) {
        var discount = 0;

        if (selectedCoupon === coupon1) {
            discount += total * 0.1;
        } else if (selectedCoupon === coupon2) {
            discount += 20;
        } else if (selectedCoupon === coupon3) {
            discount += total * 0.3;
        }

        var discountedTotal = total - discount;
        document.getElementById("total").textContent = "$" + discountedTotal.toFixed(2);

        var discountAmount = total - discountedTotal;
        document.getElementById("discountAmount").textContent = "折扣金額: $" + discountAmount.toFixed(2);
    }

    document.getElementById("couponModal").style.display = "none";
}
function showCouponModal() {
    var total = parseFloat(document.getElementById("total").textContent.slice(1));
    var coupon1 = document.getElementById("coupon1");
    var coupon2 = document.getElementById("coupon2");
    var coupon3 = document.getElementById("coupon3");

    if (total >= 10) {
        coupon1.disabled = false;
        document.getElementById("coupon1Text").textContent = "";
    } else {
        coupon1.disabled = true;
        document.getElementById("coupon1Text").textContent = "需要滿 $" + (10 - total).toFixed(2) + " 才能使用";
    }

    if (total >= 50) {
        coupon2.disabled = false;
        document.getElementById("coupon2Text").textContent = "";
    } else {
        coupon2.disabled = true;
        document.getElementById("coupon2Text").textContent = "還差 $" + (50 - total).toFixed(2) + " 就能使用";
    }

    if (total >= 200) {
        coupon3.disabled = false;
        document.getElementById("coupon3Text").textContent = "";
    } else {
        coupon3.disabled = true;
        document.getElementById("coupon3Text").textContent = "還差$" + (200 - total).toFixed(2) + " 就能使用";
    }

    document.getElementById("couponModal").style.display = "block";
}

function handleCouponSelection(checkbox) {
    var coupon1 = document.getElementById("coupon1");
    var coupon2 = document.getElementById("coupon2");
    var coupon3 = document.getElementById("coupon3");

    if (checkbox === coupon1 && coupon1.checked) {
        coupon2.checked = false;
        coupon3.checked = false;
    } else if (checkbox === coupon2 && coupon2.checked) {
        coupon1.checked = false;
        coupon3.checked = false;
    } else if (checkbox === coupon3 && coupon3.checked) {
        coupon1.checked = false;
        coupon2.checked = false;
    }
}


function calculateChange() {
    var total = parseFloat(document.getElementById("total").textContent.slice(1));
    var payment = parseFloat(document.getElementById("paymentAmount").value);
    var change = payment - total;
    document.getElementById("changeAmount").textContent = "找零: $" + change.toFixed(2);
}

// 更新小計和總計
document.querySelectorAll("input[type='number']").forEach(input => {
    input.addEventListener("change", calculateSubtotal);
});