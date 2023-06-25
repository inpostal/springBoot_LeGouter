var isEditing = false;
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