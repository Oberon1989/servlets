let phone="";
let lastSelected;
let id

function selectPhone(k) {
    if (lastSelected!=undefined||lastSelected!=null)
    {
        let lastHtmlNodes=lastSelected.children;
        for (let i = 0; i < lastHtmlNodes.length; i++) {
            lastHtmlNodes.item(i).setAttribute("style","background-color:antiquewhite")

        }
    }
  let a= document.getElementById(k);

      let b= a.children;
   for (let i = 0; i < b.length; i++) {
      b.item(i).setAttribute("style","background-color:blue")

   }
   lastSelected=a;
   id=k;
}

function goToUpdatePage()
{
    if(id!=null||id!=undefined)
    {
        let form = document.getElementById("form1");
        form.method="GET";
        form.id.value=id;
        form.action="addPhone.jsp?id="+id;
        form.submit();
    }
    else
    {
        alert("Не выбран элемент для изменения");
    }

}

function goToDeletePage()
{
    if(id!=null||id!=undefined)
    {
        let form = document.getElementById("form1");
        form.method="GET";
        form.id.value=id;
        form.action="add?id="+id;
        form.submit();
    }
    else
    {
        alert("Не выбран элемент для Удаления");
    }

}