$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'test/stressEnv/list',
        datatype: "json",
        colModel: [
            {label: 'IP地址', name: 'ip', width: 40, key: true},
            {label: '应用名称', name: 'appName', width: 50, sortable: false},
            {label: '部署路径', name: 'homeDir', width: 80, sortable: false},
            {label: 'log路径', name: 'logDir', width: 60, sortable: false},
            {label: 'CPU核数', name: 'cpu', width: 30, sortable: false},
            {label: '内存（G）', name: 'mem', width: 30, sortable: false},
            {label: '磁盘（G）', name: 'disk', width: 30, sortable: false}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 50,
        rowList: [10, 30, 50, 100, 200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            appName: null
        },
        showList: true,
        title: null,
        stressTestEnvironment: {}
    },
    methods: {
        query: function () {
            if (vm.q.appName != null) {
                vm.reload();
            }
        },

        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'appName': vm.q.appName},
                page: page
            }).trigger("reloadGrid");
        }
        // add: function () {
        //     vm.showList = false;
        //     vm.title = "新增";
        //     vm.stressTestSlave = {
        //         status: 0,
        //         ip: "127.0.0.1",
        //         jmeterPort: 1099,
        //         sshPort: 22,
        //         homeDir: "/home/apache-jmeter-4.0",
        //         weight: "100"
        //     };
        // },
        // update: function () {
        //     var slaveId = getSelectedRow();
        //     if (slaveId == null) {
        //         return;
        //     }
        //
        //     $.get(baseURL + "test/stressSlave/info/" + slaveId, function (r) {
        //         vm.showList = false;
        //         vm.title = "修改";
        //         vm.stressTestSlave = r.stressTestSlave;
        //     });
        // },
        // saveOrUpdate: function () {
        //     if (vm.validator()) {
        //         return;
        //     }
        //
        //     var url = vm.stressTestSlave.slaveId == null ? "test/stressSlave/save" : "test/stressSlave/update";
        //     $.ajax({
        //         type: "POST",
        //         url: baseURL + url,
        //         contentType: "application/json",
        //         data: JSON.stringify(vm.stressTestSlave),
        //         success: function (r) {
        //             if (r.code === 0) {
        //                 // alert('操作成功', function(){
        //                 vm.reload();
        //                 // });
        //             } else {
        //                 alert(r.msg);
        //             }
        //         }
        //     });
        // }
    }
});