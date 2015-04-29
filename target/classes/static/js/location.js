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
	alert("lat ::"+currentlat+"long:::"+currentlon);
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
  var marker = new google.maps.Marker({position:latlng,map:map,title:"You are here!"});
  google.maps.event.addListener(marker, 'mouseover', function(i) {
		infowindow.setContent("Your Current Location");
		infowindow.open(map, this);
	});
}

var couponLocationList="";

function codeAddress() {
	//infowindow = new google.maps.InfoWindow();
	var enteredAddress=document.getElementById("address").value;
	
	$.post( "/getCouponList", {address:enteredAddress}).done(function(data) {
	    alert( "Data Loaded: " + JSON.stringify(data));
	    couponLocationList=JSON.stringify(data);
	    var jsonVal=JSON.parse(couponLocationList);
		alert("parsed value::"+jsonVal["1"][5]+"length is::::"+Object.keys(jsonVal).length);
		populateMap(jsonVal);
	  },"json");
	
	/*var locations = [
	                 ['Bondi Beach', -33.890542, 151.274856, 4],
	                 ['Coogee Beach', -33.923036, 151.259052, 5],
	                 ['Cronulla Beach', -34.028249, 151.157507, 3],
	                 ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
	                 ['Maroubra Beach', -33.950198, 151.259302, 1]
	               ];

	                map = new google.maps.Map(document.getElementById('map-canvas'), {
	                 zoom: 10,
	                 center: new google.maps.LatLng(-33.92, 151.25),
	                 mapTypeId: google.maps.MapTypeId.ROADMAP
	               });

	               var infowindow = new google.maps.InfoWindow();

	               var marker, i;
	               for (i = 0; i < locations.length; i++) {  
	                 marker = new google.maps.Marker({
	                   position: new google.maps.LatLng(locations[i][1], locations[i][2]),
	                   map: map
	                 });

	                 google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
	                     return function() {
	                       infowindow.setContent(locations[i][0]);
	                       infowindow.open(map, marker);
	                     }
	                   })(marker, i));
	                 
	                 //onclick
	                 google.maps.event.addListener(marker, 'click', function(i) {
	             		alert("in listner ");
	             		document.getElementById("content").innerHTML = (locations[i]);
	             	});
	                 
	               }*/
}

function populateMap(listedValues,callback)
{
	loctn =[];
	iVar=0;
	var length=Object.keys(listedValues).length;
	var latitude;
	var longitude;
	var geocoder = new google.maps.Geocoder();
	
	for (var prop in listedValues) {
	      alert(prop+":::::::::"+listedValues[prop][5]);
	      
		geocoder.geocode( { 'address': listedValues[prop][5]}, function(results, status) {

			  if (status == google.maps.GeocoderStatus.OK) {
			    latitude = results[0].geometry.location.lat();
			    longitude = results[0].geometry.location.lng();
			    alert(latitude);
			    /*createListforMap(listedValues,latitude,longitude,prop);*/
			  }
			  //createListforMap(listedValues,latitude,longitude,prop);
			  createMarker(listedValues,latitude,longitude,prop);
			});
		}
}

function createMarker(value,lat,long,key)
{
	alert("in marker creating");
	var marker;
	var placeLoc = new google.maps.LatLng(lat,long);
	var image="http://maps.gstatic.com/intl/en_ALL/mapfiles/dd-start.png";
	marker = new google.maps.Marker({
		map : map,
		position : placeLoc,
		icon:image
		//title : "Bike Location"
	});
	var latLng = marker.getPosition(); // returns LatLng object
	map.setCenter(latLng);
	//marker onhover
	google.maps.event.addListener(marker, 'mouseover', function(i) {
		infowindow.setContent("<p>"+"ShopName: "+value[key][1]+"<br/>"+"Save Value: "+value[key][2]+"</p>");
		infowindow.open(map, this);
	});
	
	//onclikc
	google.maps.event.addListener(marker, 'click', function(i) {
		alert("in listner ");
		document.getElementById("downloadPic").src = value[key][3];
	});
}

function createListforMap(value,lat,long,key)
{
	var newone=[value[key][1],lat,long];
	loctn[iVar]=newone;
	alert(loctn[iVar]);
	alert("location issss"+loctn);
	iVar++;
}

google.maps.event.addDomListener(window, 'load', getLocation);
