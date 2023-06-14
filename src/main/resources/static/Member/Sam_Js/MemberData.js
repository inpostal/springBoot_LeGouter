var isEditing = false;

document.getElementById("update").addEventListener("click", function () {
    var nameCell = document.getElementById("name-cell");
    var passwordCell = document.getElementById("password-cell");
    var genderCell = document.getElementById("gender-cell");
    var phoneCell = document.getElementById("phone-cell");
    var addressCell = document.getElementById("address-cell");

    if (!isEditing) {
        // 開始編輯，將元素轉為輸入框或選擇框
        nameCell.innerHTML = '<input type="text" id="name-input" name="name" value="' + nameCell.textContent + '">';
        passwordCell.innerHTML = '<input type="text" id="password-input" name="password" value="' + passwordCell.textContent + '">';
        genderCell.innerHTML = '<select id="gender-select" name="gender"><option value="男">男</option><option value="女">女</option></select>';
        phoneCell.innerHTML = '<input type="text" id="phone-input" name="phone" value="' + phoneCell.textContent + '">';
        addressCell.innerHTML = '<input type="text" id="address-input" name="address" value="' + addressCell.textContent + '">';

        this.textContent = "修改完成";
        this.type = "submit";
    } else {
        // 結束編輯，儲存變更並將輸入框和選擇框轉回文字
        nameCell.textContent = document.getElementById("name-input").value;
        passwordCell.textContent = document.getElementById("password-input").value;
        genderCell.textContent = document.getElementById("gender-select").value;
        phoneCell.textContent = document.getElementById("phone-input").value;
        addressCell.textContent = document.getElementById("address-input").value;

        this.textContent = "修改會員資料";
        this.type = "button";
    }

    isEditing = !isEditing;
});



function $id(id) {
    return document.getElementById(id);
}



window.onload = function () {


    // 側邊欄位動畫
    function leftSide(liid, subUlid) {
        document.getElementById(liid).addEventListener("click", function () {
            var subUl = document.getElementById(subUlid);
            if (subUl.getAttribute("class") === "hide") {
                subUl.setAttribute("class", "show");
            } else {
                subUl.setAttribute("class", "hide");
            }
        });
    }
    leftSide("dessert", "subUl1");
    leftSide("member-class", "subUl2");
    leftSide("member-group", "subUl3");
};