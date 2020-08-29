var i=0;
var next=false;
var items=[];
var barcode;
var quantity;
var pos;
var self=this;
onmessage=function (e) {

    console.log(e.data);
    console.log((e.data)[0]);
    console.log((e.data)[1]);
    items=(e.data)[0];
    i=(e.data)[1];
    console.log("我来自主线程"+i);
    console.log(i+"___"+items.length);


    if(i==items.length){
        self.postMessage(["无","无","无",items,i]);
        return;
    }

    console.log("ajax"+i);
    barcode=items[i].clothing;
    quantity=items[i].quantity;
    console.log(items[i].isPick);
    if(items[i].isPick==true){
        self.postMessage([barcode,"该订单已经拣选过该产品","该订单已经拣选过该产品",items,i]);
        return;
    }

    var xmlHttp=new XMLHttpRequest();
    var url="/WareHouseManagement/stock/findClothingByBarcode";

    xmlHttp.open("post",url,true);
    xmlHttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    xmlHttp.onreadystatechange = function () {
        if(xmlHttp.readyState === XMLHttpRequest.DONE && xmlHttp.status === 200) {
            console.log(xmlHttp.response);
            console.log("拿到了");
            var data = xmlHttp.response;
            console.log(data);
            if(data==null){
                pos="该服装产品仓库无货";
                quantity="该产品库存数量不足";
            }
            else{
                if(data.quantity<quantity){
                    quantity="该产品库存数量不足";
                };
                var shelf=data.slot.shelf.name;
                var slot=data.slot.name;
                pos=shelf+"-"+slot;
            }
            console.log("我子线程要去了",barcode,quantity,pos,items,i);

            self.postMessage([barcode,quantity,pos,items,i]);

        }
    }
    xmlHttp.responseType='json';
    xmlHttp.send('{"barcode":"'+ barcode +'"}');

}







