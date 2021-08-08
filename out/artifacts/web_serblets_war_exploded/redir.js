let id;
let lastSelected;

function redirect() {
    let isEdit = confirm('Добавить еще модель?');
    if (isEdit) {
        window.location.href = 'addPhone.jsp';
    } else {
        window.location.href = 'index.jsp';
    }
}

function showAlert(k) {
    alert(k);
}

function test(k) {
    if (lastSelected != undefined || lastSelected != null) {
        lastSelected.style.background = "";
    }
    let currentRow = document.getElementById(k);

    currentRow.style.background = "lightblue";
    lastSelected = currentRow;
    id = k;
}

function prints() {
    alert(i);
}

function openPage(k) {


    k.setAttribute("t", id)
    showAlert(k.getAttribute("t"))

}

function getId(k) {
    k.setAttribute("t", 12);
    showAlert(k.getAttribute("t"));
}