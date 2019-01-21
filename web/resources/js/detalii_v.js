/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// JavaScript Document

$(document).ready(function () {



    makeTotal();




    function makeTotal() {
        var total = 0;
        $(".outputs_pret_total").each(function () {
            total = total + parseFloat($(this).text());
        });
        $(".outputs_total").text(total + " RON");
    }



});

