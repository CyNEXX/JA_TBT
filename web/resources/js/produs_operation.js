/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// JavaScript Document

$(document).ready(function () {

    var name_pattern = new RegExp("^[A-Za-z -]+$");
    var addr_pattern = new RegExp("^[^\\s][A-Za-z0-9 .,-]+$");
    var pret_pattern = new RegExp("^[0-9]+[\\.]?[0-9]{0,2}$");
    var stoc_pattern = new RegExp("^[1-9]+[0-9]*$");

    var numePBun = false;
    var descrierePBuna = false;
    var stocPBun = false;
    var pretPBun = false;
    var produsOK = false;




    $(".inputs_numeprodus").attr("placeholder", "ex: Televizor");
    $(".inputs_stocprodus").attr("placeholder", "ex: 9");
    $(".inputs_pretprodus").attr("placeholder", "ex: 621.50 sau 125");
    $(".inputs_descriereprodus").attr("placeholder", "ex: LCD, diagonala: 86cm");

    $(".inputs_numeprodus").keyup(checkNumeProdus);
    $(".inputs_stocprodus").keyup(checkStocProdus);
    $(".inputs_pretprodus").keyup(checkPretProdus);
    $(".inputs_descriereprodus").keyup(checkDescriereProdus);



    $(".button_confirmare_vanzare").show();
//    disableConfirm();
    showConfirm();

    function checkNumeProdus(event) {
        var b = $(event.currentTarget).val();
        if (name_pattern.test(b)) {
            goodInput($(this));
            numePBun = true;
        } else {
            badInput($(this));
            numePBun = false;
        }
        checkProdus();
    }

    function checkStocProdus(event) {
        var b = $(event.currentTarget).val();
        if (stoc_pattern.test(b)) {
            goodInput($(this));
            stocPBun = true;
        } else {
            badInput($(this));
            stocPBun = false;
        }
        checkProdus();
    }

    function checkPretProdus(event) {
        var b = $(event.currentTarget).val();
        if (pret_pattern.test(b)) {
            goodInput($(this));
            pretPBun = true;
        } else {
            badInput($(this));
            pretPBun = false;
        }
        checkProdus();
    }

    function checkDescriereProdus(event) {
        var b = $(event.currentTarget).val();
        if (addr_pattern.test(b)) {
            goodInput($(this));
            descrierePBuna = true;
        } else {
            badInput($(this));
            descrierePBuna = false;
        }
        checkProdus();
    }

    function qcheckNumeProdus(param) {
        var b = $(".inputs_numeprodus").val();
        if (name_pattern.test(b)) {
            goodInput($(this));
            numePBun = true;
        } else {
            badInput($(this));
            numePBun = false;
        }

    }

    function qcheckStocProdus(param) {
        var b = $(".inputs_stocprodus").val();
        if (stoc_pattern.test(b)) {
            goodInput($(this));
            stocPBun = true;
        } else {
            badInput($(this));
            stocPBun = false;
        }
    }

    function qcheckPretProdus(param) {
        var b = $(".inputs_pretprodus").val();
        if (pret_pattern.test(b)) {
            goodInput($(this));
            pretPBun = true;
        } else {
            badInput($(this));
            pretPBun = false;
        }
    }

    function qcheckDescriereProdus(param) {
        var b = $(".inputs_descriereprodus").val();
        if (addr_pattern.test(b)) {
            goodInput($(this));
            descrierePBuna = true;
        } else {
            badInput($(this));
            descrierePBuna = false;
        }
    }

    function goodInput(param) {
        $(param).removeClass("input_bad");
        $(param).addClass("input_good");
    }

    function badInput(param) {
        $(param).removeClass("input_good");
        $(param).addClass("input_bad");
    }

    function checkProdus(parameters) {

        if (numePBun && descrierePBuna && stocPBun && pretPBun) {
            produsOK = true;
        } else {
            produsOK = false;
        }
        showConfirm();
    }

    function showConfirm(param) {
        lastCheck();
        if (produsOK) {
//            $(".button_right").show();
            enableConfirmButton(param);
        } else {
            //            $(".button_right").hide();
            disableConfirmButton();
        }
    }

    function lastCheck() {
        qcheckNumeProdus();
        qcheckDescriereProdus();
        qcheckStocProdus();
        qcheckPretProdus();
    }

    function enableConfirmButton(param) {
        var button = $("* .button_right");
        $(button).prop("disabled", false);
        $(button).removeClass("disabled_button");
    }

    function disableConfirmButton(param) {
        var button = $("* .button_right");
        $(button).prop("disabled", true);
        $(button).addClass("disabled_button");
    }


});

