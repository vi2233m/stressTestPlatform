$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'test/stressHistoryReport/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', width: 50, key: true},
            {label: '版本', name: 'version', sortable: false, width: 70},
            {label: '报告ID', name: 'reportId', sortable: false, width: 55},
            {label: '文件ID', name: 'fileId', sortable: false, width: 55},
            {label: '接口名称', name: 'apiName', sortable: false, width: 150},
            {label: 'Error%', name: 'errorPct', sortable: false, width: 60},
            {label: 'Avg(ms)', name: 'average', sortable: false, width: 60},
            {label: 'Min(ms)', name: 'min', sortable: false, width: 60},
            {label: 'Max(ms)', name: 'max', sortable: false, width: 60},
            {label: '90th pct', name: 'ninetiethPct', sortable: false, width: 65},
            // {label: '95th pct', name: 'ninetyfivePct', sortable: false, width: 65},
            // {label: '99th pct', name: 'ninetyninePct', sortable: false, width: 65},
            {label: 'tps', name: 'tps', sortable: false, width: 60},
            {label: 'rev(KB/s)', name: 'received', sortable: false, width: 70},
            {label: 'sent(KB/s)', name: 'sent', sortable: false, width: 70 }
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
            fileId: null
        },
        showList: true,
        showEdit: false,
        title: null,
        reportDetailEntity: {}
    },
    methods: {
        query: function () {
            if (vm.q.fileId != null) {
                vm.reload();
            }
        },
        add: function () {
            vm.showList = false;
            vm.showEdit= true;
            vm.title = "新增";
            $("#fileIdText").removeAttr("disabled");
            vm.reportDetailEntity = {};
        },
        update: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            $("#fileIdText").attr("disabled","disabled");
            $.get(baseURL + "test/stressHistoryReport/info/" + id, function (r) {
                vm.showList = false;
                vm.showEdit= true;
                vm.title = "修改";
                vm.reportDetailEntity = r.reportDetailEntity;
            });
        },
        saveOrUpdate: function () {
            if (vm.validator()) {
                return;
            }

            var url = vm.reportDetailEntity.id == null ? "test/stressHistoryReport/save" : "test/stressHistoryReport/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.reportDetailEntity),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function(){
                        vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function () {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "test/stressHistoryReport/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
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
            vm.showEdit= false;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'fileId': vm.q.fileId},
                page: page
            }).trigger("reloadGrid");
        },

        validator: function () {
            if (isBlank(vm.reportDetailEntity.version)) {
                alert("版本号不能为空");
                return true;
            }

            if (isBlank(vm.reportDetailEntity.reportId)) {
                alert("报告ID不能为空");
                return true;
            }

            if (isBlank(vm.reportDetailEntity.fileId)) {
                alert("文件ID不能为空");
                return true;
            }

            if (isBlank(vm.reportDetailEntity.apiName)) {
                alert("接口名称不能为空");
                return true;
            }
        }

    }
});
