/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// JavaScript Document

$(document).ready(mainFunction());
function mainFunction() {

    $(".inputs_numeclient").attr("placeholder", "ex: Ion");
    $(".inputs_prenumeclient").attr("placeholder", "ex: Popescu");
    $(".inputs_telefonclient").attr("placeholder", "ex: 0700123456");
    $(".inputs_adresaclient").attr("placeholder", "ex: Bucuresti, Bd. Unirii nr 16, Romania");
    $(".inputs_numeprodus").attr("placeholder", "ex: Televizor");
    $(".inputs_stocprodus").attr("placeholder", "ex: 9");
    $(".inputs_pretprodus").attr("placeholder", "ex: 621.50 sau 125");
    $(".inputs_descriereprodus").attr("placeholder", "ex: LCD, diagonala: 86cm");

    $(".buttons_erase_row").on("click", checkRows);


    makeTotal();
    $(".button_confirmare_vanzare").show();
    disableConfirm();
    checkWishes();

    $(".inputs_stocdorit").keyup(function (event) {

        thisRow = $(event.target).parents("tr").get(0);
        stocValue = parseInt($(event.target).val());

        stocDorit = parseInt($(thisRow).find(".tds_stocdorit").text());
        stocProdus = parseInt($(thisRow).find(".tds_stocprodus").text().split(" ")[0]);

        pretItem = $(thisRow).find(".tds_pretprodus").text().split(" ")[0];

        if (isNaN(stocValue)) {
            stocValue = 0;
        }
        result = $(thisRow).find(".outputs_pret_total").text(stocValue * pretItem);
        makeTotal();

        if (stocValue > stocProdus) {
            $(event.target).addClass("stoc_gresit");
        } else if (stocValue < 1) {
            $(event.target).addClass("stoc_gresit");
        } else {
            $(event.target).removeClass("stoc_gresit");
        }
        checkWishes();
    });

    function makeTotal() {
        var total = 0;
        $(".outputs_pret_total").each(function () {
            total = total + parseFloat($(this).text());
        });
        $(".outputs_total").text(total + " RON");
    }

    function checkWishes(param) {
        var confirmOK = false;
        $(".inputs_stocdorit").each(function (i) {
            rowToCheck = $(this).parents("tr").get(0);

            var tempStocDorit = parseInt($(rowToCheck).find(".inputs_stocdorit").val());
            var tempStocProdus = parseInt($(rowToCheck).find(".tds_stocprodus").text().split(" ")[0]);

            if (tempStocDorit > tempStocProdus) {
                confirmOK = false;
                return false;
            } else if (tempStocDorit < 1) {
                confirmOK = false;
                return false;
            } else if (isNaN(tempStocDorit)) {
                confirmOK = false;
                return false;
            } else {
                confirmOK = true;
            }
        });

        if (confirmOK) {
            enableConfirm();
        } else {
            disableConfirm();
        }
    }

    function enableConfirm(param) {
        var button = $("*.noncos");
        $(button).prop("disabled", false);
        confirmB = $("*.noncos");
        confirmBClass = $(confirmB).attr("class");
        $(confirmB).removeClass("disabled_button");
    }

    function disableConfirm(param) {
        var button = $("*.noncos");
        $(button).prop("disabled", true);
        confirmB = $("*.noncos");
        confirmBClass = $(confirmB).attr("class");
        $(confirmB).addClass("disabled_button");
    }

    function checkRows(param) {
        var rowCount = $('.tabledata tr').length - 1;
        if (rowCount === 0) {
            disableConfirm();
        } else {
            enableConfirm();
        }
    }


}
;

