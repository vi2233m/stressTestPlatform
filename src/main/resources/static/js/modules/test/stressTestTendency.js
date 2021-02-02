$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'test/stressTendency/list',
        datatype: "json",
        colModel: [
            {label: '文件ID', name: 'fileId', width: 30, key: true},
            {label: '文件名称', name: 'fileName', width: 100},
            {label: '描述', name: 'remark', width: 100, sortable: false},
            {label: '更新时间', name: 'updateTime', width: 80, sortable: false},
            {
                label: '执行操作', name: '', width: 60, sortable: false, formatter: function (value, options, row) {
                    // var btn = '';
                    // btn = "<a href='#' class='btn btn-primary' onclick='runOnce(" + row.fileId + ")' ><i class='fa fa-arrow-circle-right'></i>&nbsp;启动</a>";
                    // var stopBtn = "<a href='#' class='btn btn-primary' onclick='stop(" + row.fileId + ")' ><i class='fa fa-stop'></i>&nbsp;停止</a>";
                    // var stopNowBtn = "<a href='#' class='btn btn-primary' onclick='stopNow(" + row.fileId + ")' ><i class='fa fa-times-circle'></i>&nbsp;强制停止</a>";
                    // var editBtn = "&nbsp;&nbsp;<a href='" + baseURL + "test/stressFile/downloadFile/" + row.fileId + "' class='btn btn-primary'><i class='fa fa-download'></i>&nbsp;编辑</a>";
                    // var downloadFileBtn = "&nbsp;&nbsp;<a href='" + baseURL + "test/stressFile/downloadFile/" + row.fileId + "' class='btn btn-primary'><i class='fa fa-download'></i>&nbsp;下载</a>";
                    // return btn +downloadFileBtn;
                    var btn = "<a href='javascript:void(0);' class='btn btn-primary' onclick='" +"ShowRunning(" + row.fileId + ")' ><i class='fa fa-arrow-circle-right'></i>&nbsp;查看</a>";
                    // var editBtn = "&nbsp;&nbsp;<a href='#' class='btn btn-primary' onclick='" +"ShowRunningTest(" + row.fileId + ")'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</a>";
                    return btn;
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

var vm;
vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            fileId: null,
            disable: true
        },
        showList: true,
        showtTrendcyCharts: false,
        showEdit: false,
        stressTestTendency: {},
        title: null
    },
    methods: {
        query: function () {
            // if (vm.q.fileId != null) {
            //     vm.reload();
            // }
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'fileId': vm.q.fileId},
                page: 1
            }).trigger("reloadGrid");
        },

        add: function () {
            vm.showList = false;
            vm.showEdit= true;
            vm.showtTrendcyCharts = false;
            vm.title = "新增";
            $("#fileIdText").removeAttr("disabled");
            vm.stressTestTendency = {};
        },
        update: function () {
            var fileId = getSelectedRow();
            if (fileId == null) {
                return;
            }
            $("#fileIdText").attr("disabled","disabled");
            $.get(baseURL + "test/stressTendency/info/" + fileId, function (r) {
                vm.showList = false;
                vm.showEdit = true;
                vm.showtTrendcyCharts = false;

                vm.title = "修改";
                vm.stressTestTendency = r.stressTestTendency;
            });
        },
        saveOrUpdate: function () {
            if (vm.validator()) {
                return;
            }

            var url = vm.stressTestTendency.id == null ? "test/stressTendency/save" : "test/stressTendency/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.stressTestTendency),
                success: function (r) {
                    if (r.code === 0) {
                        // alert('操作成功', function(){
                        vm.reload();
                        // });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        validator: function () {
            if (isBlank(vm.stressTestTendency.fileId)) {
                alert("文件ID不能为空");
                return true;
            }
            if (isBlank(vm.stressTestTendency.fileName)) {
                alert("文件名称不能为空");
                return true;
            }
            if (isBlank(vm.stressTestTendency.remark)) {
                alert("描述不能为空");
                return true;
            }
        },
        back: function () {
            history.go(-1);
        },

        reload: function (event) {
            vm.showList = true;
            vm.showtTrendcyCharts = false;
            vm.showEdit = false;
            // vm.showDetail = false;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'fileId': vm.q.fileId},
                page: page
            }).trigger("reloadGrid");
            // 清除图表数据
            clearEcharts();
        }

    }
});


var responseTimeDataObj = {};
var responseTimeLegendData = [];
var ninetiethPctTimeDataObj = {};
var ninetiethTimeLegendData = [];
var throughputDataObj = {};
var throughputLegendData = [];

var cpuPctDataObj = {};
var cpuPctLegendData = [];
var memFreeDataObj = {};
var memFreeLegendData = [];
var netReadDataObj = {};
var netReadLegendData = [];
var netWriteDataObj = {};
var netWriteLegendData = [];
var diskReadDataObj = {};
var diskReadLegendData = [];
var diskWriteDataObj = {};
var diskWriteLegendData = [];
var xAxisData = [];
var fileIdData;

function startInterval(fileId) {
    // 如果是多个脚本同时运行，切换监控页面时会发生这种情况。
    // if (fileIdData > 0 && fileIdData != fileId) {
    //     clearEcharts();
    // }
    fileIdData = fileId;
    setEchartData(fileId);
}

function setEchartData(fileId) {
    $.get(baseURL + "test/stressTendency/statInfo/" + fileId, function (r) {
        var responseTimeMap = r.statInfo.responseTimesMap;
        var ninetiethPctTimesMap = r.statInfo.ninetiethPctTimesMap;
        var throughputMap = r.statInfo.throughputMap;
        var cpuPctMap = r.statInfo.cpuPctMap;
        var memFreeMap = r.statInfo.memFreeMap;
        var netReadMap = r.statInfo.netReadMap;
        var netWriteMap = r.statInfo.netWriteMap;
        var diskReadMap = r.statInfo.diskReadMap;
        var diskWriteMap = r.statInfo.diskWriteMap;
        // xAxisData.push("2018", "2019", "2020");
        xAxisData = r.statInfo.versionList;


        var responseTimesEChartOption = getOption(responseTimeMap, responseTimeLegendData, responseTimeDataObj, null);
        var ninetiethPctTimesEChartOption = getOption(ninetiethPctTimesMap, ninetiethTimeLegendData, ninetiethPctTimeDataObj, null);
        var getThroughputMapOption = getOption(throughputMap, throughputLegendData, throughputDataObj, null);
        var cpuPctMapOption = getOption(cpuPctMap, cpuPctLegendData, cpuPctDataObj, null);
        var memFreeMapOption = getOption(memFreeMap, memFreeLegendData, memFreeDataObj, null);
        var netReadMapOption = getOption(netReadMap, netReadLegendData, netReadDataObj, null);
        var netWriteMapOption = getOption(netWriteMap, netWriteLegendData, netWriteDataObj, null);
        var diskReadMapOption = getOption(diskReadMap, diskReadLegendData, diskReadDataObj, null);
        var diskWriteMapOption = getOption(diskWriteMap, diskWriteLegendData, diskWriteDataObj, null);

        responseTimesEChart.setOption(responseTimesEChartOption);
        ninetiethPctTimesEChart.setOption(ninetiethPctTimesEChartOption);
        throughputEChart.setOption(getThroughputMapOption);
        cpuPctEChart.setOption(cpuPctMapOption);
        memFreeEChart.setOption(memFreeMapOption);
        netReadEChart.setOption(netReadMapOption);
        netWriteEChart.setOption(netWriteMapOption);
        diskReadEChart.setOption(diskReadMapOption);
        diskWriteEChart.setOption(diskWriteMapOption);

    });
}

// function ShowRunningTest(fileId) {
//     vm.showtTrendcyCharts = false;
//     vm.showDetail = true;
//     vm.showEdit = false;
//     vm.showList = false;
//     // startInterval(fileId);
// }

function ShowRunning(fileId) {
    vm.showList = false;
    vm.showtTrendcyCharts = true;
    vm.showEdit = false;
    startInterval(fileId);
}

function clearEcharts() {
    responseTimeDataObj = {};
    responseTimeLegendData = [];
    ninetiethPctTimeDataObj = {};
    ninetiethTimeLegendData = [];
    throughputDataObj = {};
    throughputLegendData = [];
    cpuPctDataObj = {};
    cpuPctLegendData = [];
    memFreeDataObj = {};
    memFreeLegendData = [];
    netReadDataObj = {};
    netReadLegendData = [];
    netWriteDataObj = {};
    netWriteLegendData = [];
    diskReadDataObj = {};
    diskReadLegendData = [];
    diskWriteDataObj = {};
    diskWriteLegendData = [];
    xAxisData = [];

    // 清空数据
    responseTimesEChart.setOption(option, true);
    ninetiethPctTimesEChart.setOption(option, true);
    throughputEChart.setOption(option, true);
    cpuPctEChart.setOption(option, true);
    memFreeEChart.setOption(option, true);
    netReadEChart.setOption(option, true);
    netWriteEChart.setOption(option, true);
    diskReadEChart.setOption(option, true);
    diskWriteEChart.setOption(option, true);
}

function getOption(map, legendData, dataObj, areaStyle) {
    for (var runLabel in map) {
        var runValue = map[runLabel];
        if (legendData.indexOf(runLabel) == -1) {
            legendData.push(runLabel);
        }
        if (!dataObj[runLabel]) {
            dataObj[runLabel] = [];
        }
        // dataObj[runLabel].push(runValue);
        dataObj[runLabel] = runValue;
    }
    var returnOption = {
        legend: {
            data: legendData
        },
        xAxis: [
            {
                data: xAxisData
            }
        ],
        series: (function () {
            var series = [];
            for (var runLabel in map) {
                var item = {
                    name: runLabel,
                    type: 'line',
                    data: dataObj[runLabel]
                };
                if (areaStyle) {
                    item.stack = '总量';
                    item.itemStyle = {normal: {areaStyle: {type: 'default'}}};
                }
                series.push(item);
            }
            return series;
        })()
    };

    if (areaStyle) {
        returnOption.calculable = true;
    }
    return returnOption;
}

setEChartSize();
// console.log(document.getElementById('responseTimesChart'))
var responseTimesEChart = echarts.init(document.getElementById('responseTimesChart'), 'shine');
var ninetiethPctTimesEChart = echarts.init(document.getElementById('ninetiethPctTimesEChart'), 'shine');
var throughputEChart = echarts.init(document.getElementById('throughputEChart'), 'shine');
var cpuPctEChart = echarts.init(document.getElementById('cpuPctEChart'), 'shine');
var memFreeEChart = echarts.init(document.getElementById('memFreeEChart'), 'shine');
var netReadEChart = echarts.init(document.getElementById('netReadEChart'), 'shine');
var netWriteEChart = echarts.init(document.getElementById('netWriteEChart'), 'shine');
var diskReadEChart = echarts.init(document.getElementById('diskReadEChart'), 'shine');
var diskWriteEChart = echarts.init(document.getElementById('diskWriteEChart'), 'shine');

//用于使chart自适应高度和宽度
window.onresize = function () {
    setEChartSize();
    responseTimesEChart.resize();
    ninetiethPctTimesEChart.resize();
    throughputEChart.resize();
    cpuPctEChart.resize();
    memFreeEChart.resize();
    netReadEChart.resize();
    netWriteEChart.resize();
    diskReadEChart.resize();
    diskWriteEChart.resize();
};

function setEChartSize() {
    //重置容器高宽
    $("#responseTimesChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);
    $("#ninetiethPctTimesEChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);
    $("#throughputEChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);
    $("#cpuPctEChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);
    $("#memFreeEChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);
    $("#netReadEChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);
    $("#netWriteEChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);
    $("#diskReadEChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);
    $("#diskWriteEChart").css('width', $("#rrapp").width() * 0.95).css('height', $("#rrapp").width() / 3);

}

// 指定图表的配置项和数据
var option = {
    tooltip: {
        trigger: 'axis'
    },
    toolbox: {
        show: true,
        orient: 'vertical',      // 布局方式，默认为水平布局，可选为：'horizontal' ¦ 'vertical'
        borderWidth: 0,            // 工具箱边框线宽，单位px，默认为0（无边框）
        padding: 5,                // 工具箱内边距，单位px，默认各方向内边距为5，
        showTitle: true,
        disableColor: '#ddd',
        effectiveColor: 'red',
        feature: {
            dataZoom: {
                show: true,
                title: {
                    dataZoom: '区域缩放',
                    dataZoomReset: '区域缩放-后退'
                }
            },
            dataView: {
                show: true,
                title: '数据视图',
                readOnly: false,
                lang: ['数据视图', '关闭', '刷新'],
            },
            magicType: {
                show: true,
                title: {
                    line: '折线图',
                    bar: '柱形图',
                    stack: '堆积'
                },
                type: ['line', 'bar', 'stack']
            },
            restore: {
                show: true,
                title: '还原',
                color: 'black'
            }
        }
    },
    dataZoom: {
        show: true,
        start: 0,
        end: 100
    },
    legend: {},
    xAxis: [
        {
            type: 'category',
            boundaryGap: true,
            data: []
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
responseTimesEChart.setOption(option);
ninetiethPctTimesEChart.setOption(option);
throughputEChart.setOption(option);
cpuPctEChart.setOption(option);
memFreeEChart.setOption(option);
netReadEChart.setOption(option);
netWriteEChart.setOption(option);
diskReadEChart.setOption(option);
diskWriteEChart.setOption(option);
