/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Nicolas Moser
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

$(function () {

    $("#tabs").tabs();

    $("#domainListLoader").hide()
    $("#mBeanListLoader").hide()

});

var rootURL = "http://localhost:8080/com.moser.jmxweb.web/resources/";

function loadDomains() {

    $('#domainList li').remove();

    $('#domainListLoader').show();

    $.ajax({
        type: 'GET',
        url: rootURL + 'domains/',
        dataType: 'json',
        success: renderDomainList
    });

}

function loadMBeans(domainName) {

    $('#mBeanList li').remove();

    $('#mBeanListLoader').show();

    $.ajax({
        type: 'GET',
        url: rootURL + 'domains/' + domainName + '/mbeans',
        dataType: 'json',
        success: renderMBeanList
    });

}

function renderDomainList(data) {

    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $('#domainListLoader').hide();

    $.each(list, function(index, domain) {
        $('#domainList').append('<li><div id="' + domain.name + '" class="listEntry" href="#" onclick="loadMBeans(\'' + domain.name + '\')">' + domain.name + '</div></li>');
    });
}

function renderMBeanList(data) {

    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $('#mBeanListLoader').hide();

    $.each(list, function(index, mBean) {
        $('#mBeanList').append('<li><div  class="listEntry">' + mBean.name + '</div></li>');
    });
}
