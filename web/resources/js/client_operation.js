/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// JavaScript Document

$(document).ready(function () {

    var name_pattern = new RegExp("^[A-Za-z -]+$");
    var addr_pattern = new RegExp("^[^\\s][A-Za-z0-9 .,-]+$");
    var tel_pattern = new RegExp("^[0][0-9]{9,}$");

    var numeBun = false;
    var prenumeBun = false;
    var adresaBuna = false;
    var telefonBun = false;
    var clientOK = false;




    $(".inputs_numeclient").attr("placeholder", "ex: Ion");
    $(".inputs_prenumeclient").attr("placeholder", "ex: Popescu");
    $(".inputs_telefonclient").attr("placeholder", "ex: 0700123456");
    $(".inputs_adresaclient").attr("placeholder", "ex: Bucuresti, Bd. Unirii nr 16, Romania");

    $(".inputs_numeclient").keyup(checkNume);
    $(".inputs_prenumeclient").keyup(checkPrenume);
    $(".inputs_telefonclient").keyup(checkTelefon);
    $(".inputs_adresaclient").keyup(checkAdresa);



    $(".button_confirmare_vanzare").show();
    showConfirm();

    function checkNume(event) {
        var b = $(event.currentTarget).val();
        if (name_pattern.test(b)) {
            goodInput($(this));
            numeBun = true;
        } else {
            badInput($(this));
            numeBun = false;
        }
        checkClient();
    }

    function checkPrenume(event) {

        var b = $(event.currentTarget).val();
        if (name_pattern.test(b)) {
            goodInput($(this));
            prenumeBun = true;
        } else {
            badInput($(this));
            prenumeBun = false;
        }
        checkClient();
    }

    function checkTelefon(event) {
        var b = $(event.currentTarget).val();
        if (tel_pattern.test(b)) {
            goodInput($(this));
            telefonBun = true;
        } else {
            badInput($(this));
            telefonBun = false;
        }
        checkClient();
    }

    function checkAdresa(event) {

        var b = $(event.currentTarget).val();
        if (addr_pattern.test(b)) {
            goodInput($(this));
            adresaBuna = true;
        } else {
            badInput($(this));
            adresaBuna = false;
        }
        checkClient();
    }

    function goodInput(param) {
        $(param).removeClass("input_bad");
        $(param).addClass("input_good");
    }

    function badInput(param) {
        $(param).removeClass("input_good");
        $(param).addClass("input_bad");
    }

    function checkClient(parameters) {

        if (numeBun && prenumeBun && adresaBuna && telefonBun) {
            clientOK = true;
        } else {
            clientOK = false;
        }
        showConfirm();
    }

    function showConfirm(param) {
        lastCheck();
        if (clientOK) {
//            $(".button_right").show();
            enableConfirmButton(param);
        } else {
            //            $(".button_right").hide();
            disableConfirmButton();
        }
    }

    function qcheckNume(param) {
        var b = $(".inputs_numeclient").val();
        if (name_pattern.test(b)) {
            goodInput($(this));
            numeBun = true;
        } else {
            badInput($(this));
            numeBun = false;
        }
    }

    function qcheckPrenume(param) {

        var b = $(".inputs_prenumeclient").val();
        if (name_pattern.test(b)) {
            goodInput($(this));
            prenumeBun = true;
        } else {
            badInput($(this));
            prenumeBun = false;
        }
    }

    function qcheckTelefon(param) {
        var b = $(".inputs_telefonclient").val();
        if (tel_pattern.test(b)) {
            goodInput($(this));
            telefonBun = true;
        } else {
            badInput($(this));
            telefonBun = false;
        }
    }

    function qcheckAdresa(param) {
        var b = $(".inputs_adresaclient").val();
        if (addr_pattern.test(b)) {
            goodInput($(this));
            adresaBuna = true;
        } else {
            badInput($(this));
            adresaBuna = false;
        }
    }

    function lastCheck() {
        qcheckNume();
        qcheckPrenume();
        qcheckTelefon();
        qcheckAdresa();
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

