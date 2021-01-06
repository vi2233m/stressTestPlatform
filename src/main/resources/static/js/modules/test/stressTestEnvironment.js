$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'test/stressEnv/list',
        datatype: "json",
        colModel: [
            {label: 'IP地址', name: 'ip', width: 30, key: true},
            {label: '应用名称', name: 'appName', width: 30, sortable: false},
            {label: '部署路径', name: 'homeDir', width: 50, sortable: false},
            {label: 'log路径', name: 'logDir', width: 35, sortable: false},
            {label: 'CPU核数', name: 'cpu', width: 20, sortable: false},
            {label: '内存(G)', name: 'mem', width: 20, sortable: false},
            {label: '磁盘(G)', name: 'disk', width: 20, sortable: false},
            {label: '更新时间', name: 'updateTime', width: 40, sortable: false},
            {
                label: '资源监控',
                name: '',
                width: 25,
                sortable: false, formatter: function (value, options, row) {
                    var viewBtn;
                    viewBtn = "&nbsp;&nbsp;<a href='" +"http://" + row.ip + ":" + row.port +"/report/" + row.reportPath + "' class='btn btn-primary'><i class='fa fa-arrow-circle-right'></i>&nbsp;查看</a>";
                    return viewBtn;
                }
            }
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
        startUp: function () {
            var appIps = getSelectedRows();
            if (appIps == null) {
                return;
            }
            confirm('确定要启动监控选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "test/stressEnv/start",
                    contentType: "application/json",
                    data: JSON.stringify(appIps),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveMonitor: function () {
            var appIps = getSelectedRows();
            if (appIps == null) {
                return;
            }
            confirm('确定保存选中的监控记录结果？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "test/stressEnv/saveMonitor",
                    contentType: "application/json",
                    data: JSON.stringify(appIps),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
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