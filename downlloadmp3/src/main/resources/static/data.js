"use strict";

var yyhMedal = (function() {

    var nowDate = function () {
        // return '2018-08-27';
        var now = new Date();
        var m = now.getMonth() + 1;
        var d = now.getDate();
        return now.getFullYear() + '年'+('00' + m).substr(('' + m).length)+'月'+('00' + d).substr(('' + d).length)+'日';
        //return [now.getFullYear(), ('00' + m).substr(('' + m).length), ('00' + d).substr(('' + d).length)].join('-');
    };

    var buildRank = function (lists) {
        var gold = 0;
        var silver = 0;
        var bronze = 0;
        var html = lists.map(function (item, index) {
            gold += parseInt(item.glob);
            silver += parseInt(item.silver);
            bronze += parseInt(item.bronze);
            return '<tr>\n' +
                '<td>' + item.num + '</td>\n' +
                '<td class="td-country"><img src="' + item.logo + '" /><span>' + item.name + '</span></td>\n' +
                '<td>' + item.glob + '</td>\n' +
                '<td>' + item.silver + '</td>\n' +
                '<td>' + item.bronze + '</td>\n' +
                '<td>' + item.total + '</td>\n' +
                '</tr>';
        });
        $("#jpbData").children("table").children("tbody").html(html.join(''));
        $("#goldNum").html(gold);
        $("#silverNum").html(silver);
        $("#bronzeNum").html(bronze);
        $("#totalNum").html(gold+silver+bronze);
    };

    var getRankData = function () {
        return $.ajax({
            type: "get",
            async: false,
            url: "https://2018ag.sports.qq.com/api/rank.php",
            //url: "http://2017awg.sports.qq.com/yayunhui2018/api/rank.php",
            dataType: "jsonp",
            jsonpCallback: "callbackYayunhuiRankSucc"
        })
    };

    self.init =function(){
        $("#field-date").html('截止到'+ nowDate());
        getRankData().done(function (data) {
            buildRank(data);
        }).fail(function () {
            $("#jpbData").children("table").children("tbody").html('<tr><td colspan="6" class="empty">暂无奖牌榜数据</td></tr>');
        });
    };

    return self;

})();

$(function() {
    yyhMedal && yyhMedal.init();
});