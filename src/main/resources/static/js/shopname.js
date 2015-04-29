function getCouponsForShop(enteredshopname) {
	//infowindow = new google.maps.InfoWindow();
	
	//alert(shopname);
	
	
	$.post( "/getShopCouponList", {shopname:enteredshopname}).done(function(data) {
		$("#coupons_main_div").html("");
		var results2 = JSON.parse(JSON.stringify(data));
		
		for (var idx in results2) {
			addnotehtml(results2[idx]["coupon_title"],results2[idx]["coupon_desc"],
					results2[idx]["image_url"],
					results2[idx]["value"],results2[idx]["url"]);
        }
	    
	  },"json");
	
}


function addnotehtml(title,desc,img,value,url) {
	
	$("#coupons_main_div").append('<div class="col-lg-3 col-xs-6" ><div class="thumbnail unit-bg" style = "height:300px"><a href=""><img src="' + img +  '" alt="deal1" height="50%" width="50%"/></a><div class="caption"><h4> ' + title + '</h4><p> '+ value + '</p><span class="view-deal" style="position:absolute;right:25px;bottom:25px;"><a href="' + url +'" class="btn btn-large btn-success">View&nbsp;<i class="icon-chevron-right icon-white"></i></a></span></p></div></div></div>')
}