// 해브스톡리스트 추가하기
window.addEventListener("load", function () {

  google.charts.load('current', { 'packages': ['corechart'] });
  // google.charts.load('current', { packages: ['corechart', 'line'] });
  google.charts.setOnLoadCallback(load);

  var section = this.document.querySelector("#card");
  var tbody = section.querySelector("table tbody");
  var updateButton = section.querySelector("#card-bottom");

  var load = function () {
    var request = new XMLHttpRequest();
    request.open("GET", "../../card/asset/myAsset-json", true);

    request.onload = function () {
      alert(request.responseText);

      var list = JSON.parse(request.responseText);

      let tDataReg = new Array();
      let tDataValue = new Array();

      tDataReg.push("regdate");
      tDataValue.push("value");

      function stringToDate(date) {
        var y = "20" + date.substr(0, 2),
          m = (date.substr(2, 2)) - 1,
          d = date.substr(4, 2)
        return new Date(y, m, d);
      }

      for (var i = 0; i < list[1].trendJson.length + 1; i++) {

        if (i < (list[1].trendJson.length)) {
          tDataReg.push(stringToDate(list[1].trendJson[i].regdate));
          tDataValue.push(list[1].trendJson[i].value);
        }
        else {
          tDataReg.push(new Date());
          tDataValue.push(list[2].trendPrsentJson);
        }
      }
      //console.log(tDataValue);

      var valueList = new Array();

      for (i = 1; i < tDataValue.length; i++) {

        valueList.push(tDataValue[i])
        // Asset Value : trendData.wg[0].c[1].v
      }
      //console.log(valueList)

      // find Max Asset 
      max = valueList[0];

      for (i = 0; i < valueList.length; i++) {
        tmp = valueList[i];

        if (max < tmp) {
          max = tmp;
        }
        else {
          continue;
        }
      }

      // console.log("max: " + max);
      // make tick max
      let maxLength = max.toString().length;
      let roundValue = max.toString()[1];
      let valueLength = 10 ** (maxLength - 1);
      let roundValueLength = 10 ** (maxLength - 2);
      let maxTick;
      // console.log("valueLength: " + valueLength);
      if (roundValue >= 5)
        maxTick = Math.trunc(max / valueLength) * valueLength + 5 * roundValueLength;
      else if (roundValue < 5) {
        // console.log("1: " + Math.ceil(max / valueLength));
        // console.log("2: " + max / valueLength);
        maxTick = Math.trunc(max / valueLength) * valueLength;
      }
      // console.log("maxLength: " + maxLength);
      // console.log("roundValue: " + roundValue);
      // console.log("maxTick: " + maxTick)
      // make ticks array
      var dynamicTicks = new Array();
      dynamicTicks.push(maxTick);
      dynamicTicks.push(maxTick / 2);
      dynamicTicks.push(0);


      var trendChart = bb.generate({
        bindto: "#chart",
        size: {
          height: 280
        },
        data: {
          columns: [
            tDataReg,
            tDataValue
          ],
          "x": "regdate",
          colors: {
            value: "#9bb8c9"
          }
        },
        subchart: {
          show: true,
          size: {
            height: 20
            // transform: translate(80.5,200)
        },
        axis: {
          x: {
            tick: {
              values: dynamicTicks,
              count: 5,
              fit: true,
              outer: false
            }
            }
          }
        },
        line: {
          classes: [
            "line-class-data1"
          ]
        },
        tooltip: {
          format: {
        name: function(){
          return "";		
      },
        value: function(value){
          var format = d3.format(',')
          return format(value);
        }
    }
  },
        axis: {
          x: {
            type: "timeseries",
            tick: {
              format: "%y/%m/%d",
              count: 3,
              fit: true,
              outer: false
            },
            classes: "xTextStyle"
          },
          y: {
            max: maxTick + (maxTick-maxTick/2)/2,
            tick: {
              values: dynamicTicks,
              outer: false,
              format: function(x) {
                return d3.format(",.2r")(x);
              }
            }
          }
        },
        point: {
          show: true,
          r: 0,
     
          focus: {
              expand: {
                  enabled: true,
                  r: 3
              }
          }
        },
        legend: {
          show: false
        },
        padding: {
          right: 20
        },
        grid: {
          y: {
            //show: true,
            lines:  [
              {value: 0},
              {value:(maxTick-maxTick/2)/2},
              {value:maxTick/2},
              {value:maxTick-(maxTick-maxTick/2)/2},
              {value: maxTick},
              {value: maxTick + (maxTick-maxTick/2)/2}
            ]
          }
        }
      });

      
      //chart.resize({height: 500, width: 300});
      
      // console.log(trendChart);
      
      // 자산 분포도 그래프
      var distrListTemplate = section.querySelector(".template-list-stock");
      
      tbody.innerHTML = "";
      
      //
      for (var i = 0; i < list[0].distJson.length; i++) {
        var distrImage = '<img src="../../images/distr_list_0' + ((i % 4) + 1) + '.png">';
        
        var cloneTr = document.importNode(distrListTemplate.content, true);
        var tds = cloneTr.querySelectorAll("td");
        
        tds[0].innerHTML = distrImage;
        tds[1].innerText = list[0].distJson[i].stockName;
        
        tbody.append(cloneTr);
      }
      
      let dData = new Array();
      let dDataValue = new Array();
      let distrColorData = {};
      let distrColorValue =["#F2F2F2","#BF737C","#689ABC","#585B5E"];
      
      for (var i = 0; i < list[0].distJson.length; i++) {
        distrColorData[(list[0].distJson[i].stockName)]=distrColorValue[i];
        dDataValue.push(list[0].distJson[i].stockName);
        dDataValue.push(list[0].distJson[i].assetValue);
        dData.push(dDataValue);
        dDataValue=[];
      }
      
      var distrChart = bb.generate({
        bindto: "#donutChart",
        data: {
          columns: dData,
          type: "donut",
          colors: distrColorData,
          onclick: function(d, element) {
            console.log("onclick", d, element);
          },
          onover: function(d, element) {
        console.log("onover", d, element);
      },
      onout: function(d, i) {
        console.log("onout", d, i);
      }
    },
        donut: {
          title: "보유 비중\n(%)",
          padAngle: 0,
          width: 45,
          expand: true
        },
        legend: {
          show: false
        },
        size: {
          height:180
        },
        tooltip: {
          format: {
            name: function(){
              return "";		
            },
            value: function(value, ratio){
              var valueFormat = d3.format(',');
              var ratioFormat = d3.format(".1f");
              return valueFormat(value)+" ("+ratioFormat(ratio*100)+")";
            }
      }
    }
  });
  console.log(distrChart.internal.charts[1]);
  
    };
    request.send();
  };
  load();

});
