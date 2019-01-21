/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// JavaScript Document


$(document).ready(mainFunction());
function mainFunction(param) {

    var hoverBox;
    var hoverText;

    $(".edit_butns").on("mouseenter", showClientInfo);
    $(".edit_butns").on("mouseleave", hideInfo);
    $(".cart_butns").on("mouseenter", showClientInfo);
    $(".cart_butns").on("mouseleave", hideInfo);

    $(".search_submit").on("mouseenter", showSearchInfo);
    $(".search_submit").on("mouseleave", hideInfo);

    $(".class_addbtns").on("mouseenter", showAddInfo);
    $(".class_addbtns").on("mouseleave", hideInfo);

    $(".vanzari_add").on("mouseenter", vanzareHint);
    $(".vanzari_add").on("mouseleave", hideInfo);

    $(".c_ers").on("mouseenter", showClientInfo);
    $(".c_ers").on("mouseleave", hideInfo);
    $(".c_ers").on("click", confirmDelete);
    $(".p_mod").on("mouseenter", showProdusInfo);
    $(".p_mod").on("mouseleave", hideInfo);
    $(".p_ers").on("mouseenter", showProdusInfo);
    $(".p_ers").on("mouseleave", hideInfo);
    $(".p_ers").on("click", confirmDelete);


    $(".button_detalii").on("mouseenter", showDetaliiHint);
    $(".button_detalii").on("mouseleave", hideInfo);

    $(".class_search").attr("placeholder", "Cauta...");

    $(".td_numeclient, .td_prenumeclient, .td_adresaclient, .td_telefonclient").hover(function (event) {
        var one = $(event.current).find(".td_numeclient").text();
        var two = $(this).text();
        var thisRow = $(this).parents("tr").get(0);
        var tdNumeClient = $(thisRow).find(".td_numeclient").text();
        var tdPrenumeClient = $(thisRow).find(".td_prenumeclient").text();
        var tdCifraVanzari = $(thisRow).find(".td_cifravanzari").find("input[type=hidden]").val();
        var tdNrVanzari = $(thisRow).find(".td_nrvanzari").find("input[type=hidden]").val();
        var tdNrProduse = $(thisRow).find(".td_nrProduse").find("input[type=hidden]").val();
        msg = ("Client: " + tdPrenumeClient + " " + tdNumeClient + "\n");
        msg2 = ("Vanzari efectuate: " + tdNrVanzari + ". Produse achizitionate: " + tdNrProduse + ". Total Vanzari: " + tdCifraVanzari + " RON");
        setHint(msg, msg2);
        directShow();
    });

    $(".td_numeprodus, .td_descriereprodus, .td_pretprodus, .td_stocprodus").hover(function (event) {
        var one = $(event.current).find(".td_numeclient").text();
        var two = $(this).text();
        var thisRow = $(this).parents("tr").get(0);
        var tdNumeProdus = $(thisRow).find(".td_numeprodus").text();
        var tdDescriereProdus = $(thisRow).find(".td_descriereprodus").text();
        var tdPretProd = $(thisRow).find(".td_pretprodus").text();
        var tdProdComandate = $(thisRow).find(".td_produse_comandate").find("input[type=hidden]").val();
        var msg = ("Produs: " + tdNumeProdus + ". Pret: " + tdPretProd);
        var msg2 = ("Descriere: " + tdDescriereProdus + ". Produse vandute: " + tdProdComandate + ".");
        setHint(msg, msg2);
        directShow();
    });
}


var remButton;
var thisRow;

function showDetaliiHint(event) {
    msg = "Vanzare";
    msg2 = "Vezi detalii despre vanzare.";
    setHint(msg, msg2);
    directShow();
}

function showSearchInfo(event) {
    var searchType = $(".search_submit").attr("class");
    var strClassEvent = event.target.className;
    msg = "Cautare...";
//    var type = $(".class_add_type").val();
    if (strClassEvent.includes("client")) {
        msg2 = "dupa nume sau prenume";
    }
    if (strClassEvent.includes("produse")) {
        msg2 = "dupa nume sau descriere";
    }
    if (strClassEvent.includes("vanzari")) {
        msg2 = "dupa nume sau prenume";
    }
    setHint(msg, msg2);
    directShow();
}

$(".tr_clienti_info").on("mouseleave", hideInfo);

$(".td_numeclient, .td_prenumeclient, .td_adresaclient, .td_telefonclient").hover(function (event) {
    msg = "";
    msg2 = "";
//    var one = $(event.current).find(".td_numeclient").text();
//    var two = $(this).text();
    var thisRow = $(this).parents("tr").get(0);
    var tdNumeClient = $(thisRow).find(".td_numeclient").text();
    var tdPrenumeClient = $(thisRow).find(".td_prenumeclient").text();
    var tdCifraVanzari = $(thisRow).find(".td_cifravanzari").find("input[type=hidden]").val();
    var tdNrVanzari = $(thisRow).find(".td_nrvanzari").find("input[type=hidden]").val();
    var tdNrProduse = $(thisRow).find(".td_nrProduse").find("input[type=hidden]").val();
    var msg = ("Client: " + tdPrenumeClient + " " + tdNumeClient);
    var msg2 = ("Vanzari efectuate: " + tdNrVanzari + ". Produse achizitionate: " + tdNrProduse + ". Total Vanzari: " + tdCifraVanzari + " RON");
    setHint(msg, msg2);
    directShow();
});

hideInfo();

function setHint(param, param2) {
    $("#info_title").text(param);
    $("#info_text").text(param2);
}

function showClientInfo(event) {
    var one = $(event.current).find(".td_numeclient").val();
    hoverText = $("#floating_info_text");
}

function showClientInfo(event) {
    hoverText = $("#floating_info_text");
    str = "";
    strId = "";
    var strClassEvent = event.target.className;
    var thisRowInfo = $(this).parents("tr").get(0);
    var tdNumeClient = $(thisRowInfo).find(".td_numeclient").text();
    var tdPrenumeClient = $(thisRowInfo).find(".td_prenumeclient").text();
    strId = tdPrenumeClient + " " + tdNumeClient;
    if (strClassEvent.includes("c_ers")) {
        str = "Sterge acest client.";
    } else if (strClassEvent.includes("c_mod")) {
        str = "Editeaza datele acestui client";
    } else if (strClassEvent.includes("cart_butns")) {
        str = "Creaza o vanzare catre acest client";
    }
    setHint(strId, str);
    directShow();
}

function showProdusInfo(event) {
    hoverText = $("#floating_info_text");
    var str;
    var thisRow = $(this).parents("tr").get(0)
    var strClassEvent = event.target.className;
    var thisRowInfo = $(this).parents("tr").get(0);

    var tdNumeProdus = $(thisRow).find(".td_numeprodus").text();
    var tdDescriereProdus = $(thisRow).find(".td_descriereprodus").text();
    var tdPretProd = $(thisRow).find(".td_pretprodus").text();
    var tdProdComandate = $(thisRow).find(".td_produse_comandate").find("input[type=hidden]").val();
    var msg = ("Produs: " + tdNumeProdus + ". Pret: " + tdPretProd);



    if (strClassEvent.includes("p_mod")) {
        str = "Editeaza datele produsului.";
    } else if (strClassEvent.includes("p_ers")) {
        str = "Sterge acest produs.";
    }
    setHint(msg, str);
    directShow();
}

function showAddInfo(event) {
    var strId;
    var thisBClass = $(this).attr("class");
    if (thisBClass.includes("clienti_add")) {
        strId = "Adauga";
        str = "Client nou";
    } else if (thisBClass.includes("produse_add")) {
        strId = "Adauga";
        str = "Produs nou";
    }
    setHint(strId, str);
    directShow();
}

function vanzareHint(event) {
    strId = "Adauga";
    str = "Vanzare noua";

    setHint(strId, str);
    directShow();
}

function directShow(event) {
    hoverBox = $("#floating_info");
    hoverBox.removeClass("invisible_hover");
    hoverBox.addClass("visible_hover");
}

function hideInfo(e) {
    hoverBox = $("#floating_info");
    hoverBox.removeClass("visible_hover");
    hoverBox.addClass("invisible_hover");
}


function confirmDelete(event) {
    var strClassEvent = event.target.className;
    var msg;
    var hiddenButton = event.target.parentElement.parentElement.querySelector(".buttons_hidden_erase");
    if (strClassEvent.includes("p_ers")) {
        msg = "produs";
    } else if (strClassEvent.includes("c_ers")) {
        msg = "client";
    }

    if (confirm("Sunteti sigur ca vreti sa stergeti acest " + msg + "?")) {
        $(hiddenButton).trigger("click");
    } else {
    }
}






