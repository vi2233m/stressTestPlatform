$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'test/stressHistoryResource/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', width: 50, key: true},
            {label: '版本', name: 'version', sortable: false, width: 70},
            {label: '报告ID', name: 'reportId', sortable: false, width: 55},
            {label: '文件ID', name: 'fileId', sortable: false, width: 55},
            {label: '应用名称', name: 'appName', sortable: false, width: 80},
            {label: '应用IP', name: 'appIp', sortable: false, width: 100},
            {label: 'User%', name: 'cpuUser', sortable: false, width: 60},
            {label: 'Sys%', name: 'cpuSys', sortable: false, width: 50},
            {label: 'Wait%', name: 'cpuWait', sortable: false, width: 50},
            {label: 'memfree(MB)', name: 'memFree', sortable: false, width: 65},
            {label: 'NetRead(KB/s)', name: 'netRead', sortable: false, width: 65},
            {label: 'NetWrite(KB/s)', name: 'netWrite', sortable: false, width: 65},
            {label: 'DiskRead(KB/s)', name: 'diskRead', sortable: false, width: 65},
            {label: 'DiskWrite(KB/s)', name: 'diskWrite', sortable: false, width: 65}
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
        resourceDetailEntity: {}
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
            vm.resourceDetailEntity = {};
        },
        update: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            $("#fileIdText").attr("disabled","disabled");
            $.get(baseURL + "test/stressHistoryResource/info/" + id, function (r) {
                vm.showList = false;
                vm.showEdit= true;
                vm.title = "修改";
                vm.resourceDetailEntity = r.resourceDetailEntity;
            });
        },
        saveOrUpdate: function () {
            if (vm.validator()) {
                return;
            }

            var url = vm.resourceDetailEntity.id == null ? "test/stressHistoryResource/save" : "test/stressHistoryResource/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.resourceDetailEntity),
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
                    url: baseURL + "test/stressHistoryResource/delete",
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
            if (isBlank(vm.resourceDetailEntity.version)) {
                alert("版本号不能为空");
                return true;
            }

            if (isBlank(vm.resourceDetailEntity.reportId)) {
                alert("报告ID不能为空");
                return true;
            }

            if (isBlank(vm.resourceDetailEntity.fileId)) {
                alert("文件ID不能为空");
                return true;
            }

            if (isBlank(vm.resourceDetailEntity.appName)) {
                alert("应用名称不能为空");
                return true;
            }

            if (isBlank(vm.resourceDetailEntity.appIp)) {
                alert("应用IP不能为空");
                return true;
            }
        }

    }
});
