var i=0;
var next=false;
var items=[];
var barcode;
var quantity;
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
        self.postMessage([barcode,"该退货订单的该产品已经入库",items,i]);
        return;
    }

    self.postMessage([barcode,quantity,items,i]);

}







