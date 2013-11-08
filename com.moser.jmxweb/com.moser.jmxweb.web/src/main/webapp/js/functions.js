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

var rootURL = "./resources/";

$(function () {

    $("#tabs").tabs();

    $("#mBeanDetailLoader").hide()
    $("#mBeanDetails").hide()

    $('#mbeanTree').tree({

        dataUrl: rootURL + 'mbeantree',
        saveState: true,
        selectable: true,

        onCanSelectNode: function(node) {
            switch(node.type) {
                case 'DOMAIN':
                    return false;
                case 'GROUP':
                    return false;
            }
            return true;
        },

        onCreateLi: function(node, $li) {

            switch(node.type) {
                case 'DOMAIN':
                    $li.find('.jqtree-title').before('<span class="treeIconDomain"></span>');
                    break;
                case 'GROUP':
                    $li.find('.jqtree-title').before('<span class="treeIconGroup"></span>');
                    break;
                case 'MBEAN':
                    $li.find('.jqtree-title').before('<span class="treeIconMBean"></span>');
                    break;
            }
        }

    });

    $('#mbeanTree').bind(

        'tree.select',

        function(event) {

            $("#mBeanDetails").hide()

            if (event.node) {
                // node was selected
                var node = event.node;

                $('#mBeanDetailLoader').show();

                // TODO: Replace with MBean Resource (node.parent)

                $.ajax({
                    type: 'GET',
                    url: rootURL + 'mbeansearch',

                    data: {
                        objectName: node.objectName
                    },

                    dataType: 'json',
                    success: renderMBeanDetails
                });

            }
            else {
                // event.node is null
                // a node was deselected
                // e.previous_node contains the deselected node
            }
        }
    );

});

function renderMBeanDetails(mBean) {

    if (mBean) {
        if (mBean.name) {
            $('#mbeanName').empty().append('<td class="mbeanDetailHeader">Name:</td><td>' + mBean.name + '</td>');
        }
        if (mBean.type) {
            $('#mbeanType').empty().append('<td class="mbeanDetailHeader">Type:</td><td>' + mBean.type + '</td>');
        }
        if (mBean.mBeanClass) {
            $('#mbeanClass').empty().append('<td class="mbeanDetailHeader">MBean Class:</td><td>' + mBean.mBeanClass + '</td>');
        }
        if (mBean.description) {
            $('#mbeanDescription').empty().append('<td class="mbeanDetailHeader">Description:</td><td>' + mBean.description + '</td>');
        }

        $.each(mBean.attributes, function(index, attribute) {
            $('#mBeanAttributeTable tbody').append('<tr><td>' + attribute.name + '</td><td>' + attribute.accessType + '</td><td>' + attribute.type + '</td><td>' + attribute.description + '</td><td>' + attribute.value + '</td></tr>');
        });
    }

    $("#mBeanDetails").show()
    $('#mBeanDetailLoader').hide();

}
