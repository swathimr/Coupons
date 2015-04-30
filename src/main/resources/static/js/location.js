var geocoder;
var map;

//gets current location
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(initialize);
    } else { 
        //x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function initialize(position) {
	currentlat = position.coords.latitude;
	currentlon = position.coords.longitude;
	infowindow = new google.maps.InfoWindow();
  geocoder = new google.maps.Geocoder();
  var latlng = new google.maps.LatLng(currentlat,currentlon);
  mapholder = document.getElementById('map-canvas')
  mapholder.style.height = '650px';
  mapholder.style.width = '1000px';
  var mapOptions = {
    zoom: 12,
    center: latlng
  }
  map = new google.maps.Map(mapholder, mapOptions);
  marker = new google.maps.Marker({position:latlng,map:map,title:"You are here!"});
 google.maps.event.addListener(marker, 'mouseover', function(i) {
		infowindow.setContent("Your Current Location");
		infowindow.open(map, this);
	});
}

var LocationList="";

function codeAddress() {
	//infowindow = new google.maps.InfoWindow();
	var enteredAddress=document.getElementById("address").value;
	
	$.post( "/getLocationList", {address:enteredAddress}).done(function(data) {
	    //alert( "Data Loaded: " + JSON.stringify(data));
	    LocationList=JSON.stringify(data);
	    var jsonVal=JSON.parse(LocationList);
		//alert("parsed value::"+jsonVal["1"][0]+"length is::::"+Object.keys(jsonVal).length);
		populateMap(jsonVal);
	  },"json");
	}
var assignKey=null;
function populateMap(listedValues,callback)
{
	loctn =[];
	iVar=0;
	var length=Object.keys(listedValues).length;
	var latitude;
	var longitude;
	var geocoder = new google.maps.Geocoder();
	for (var prop in listedValues) {
	      //alert(prop+":::::::::"+listedValues[prop][1]);
		geocoder.geocode( { 'address': listedValues[prop][1]}, function(results, status) {

			  if (status == google.maps.GeocoderStatus.OK) {
			    latitude = results[0].geometry.location.lat();
			    longitude = results[0].geometry.location.lng();
			    createMarker(listedValues,latitude,longitude,prop);
			  }
			  
			});
		}
}

var incrVal=0;
function createMarker(value,lat,long,key)
{	
	var keyName = Object.keys(value)[incrVal];
	incrVal++;
	//alert("in marker creating"+keyName);
	var placeLoc = new google.maps.LatLng(lat,long);
	var image="http://maps.gstatic.com/intl/en_ALL/mapfiles/dd-start.png";
	marker = new google.maps.Marker({
		map : map,
		position : placeLoc,
		icon:image,
		content:"<p>"+"ShopName: "+value[keyName][0]+"<br/>"+"Save Value: "+value[keyName][1]+"</p>"
	});
	
	var latLng = marker.getPosition(); // returns LatLng object
	map.setCenter(latLng);
		
	google.maps.event.addListener(marker, 'mouseover', function(i) {
		infowindow.setContent(this.content);
		infowindow.open(map, this);
	});
	
	//onclikc
	google.maps.event.addListener(marker, 'click', function(i) {
		
		$.post( "/getCouponList", {shopname:value[keyName][0]}).done(function(data) {
		    //alert( "Data Loaded: " + JSON.stringify(data));
		    CouponList=JSON.stringify(data);
		   var jsonVal=JSON.parse(CouponList);
			//alert("parsed value::"+jsonVal["1"][0]+"length is::::"+Object.keys(jsonVal).length);
			$("#couponList tbody").html("");
			for(var prop in jsonVal)
			{
				var row = $("<tr />")
			    $('#couponList tbody').append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
			    row.append($('<td class="col-md-2">' + jsonVal[prop][0]+ '</td>'));
			    row.append($('<td class="col-md-4">' + jsonVal[prop][1]+ '</td>'));
			    row.append($('<td class="col-md-2"><img src="' +jsonVal[prop][2]+ '"/></td>'));
			    row.append($('<td class="col-md-2">' + jsonVal[prop][3] + '</td>'));
				
			}
			
		  },"json");
	});
}

google.maps.event.addDomListener(window, 'load', getLocation);