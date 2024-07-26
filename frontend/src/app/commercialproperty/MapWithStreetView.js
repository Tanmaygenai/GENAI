import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useLocation } from "react-router-dom";
const javascriptURL = process.env.REACT_APP_JavaScript_URL;

const MapWithStreetView = () => {
  const {
    state,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [fullAddress, setFullAddress] = useState(
    sessionStorage.getItem("fullAddress")
      ? sessionStorage.getItem("fullAddress")
      : null
  );
  const [addressComponents, setAddressComponents] = useState({
    StreetName: "",
    City: "",
    State: "",
    PostalCode: "",
    Country: "",
  });

  useEffect(() => {
    if (fullAddress == null) {
      let tempaddress = window.myData ? window.myData.address : null;

      if (tempaddress) {
        const { StreetName, City, State, PostalCode, Country } = tempaddress;
        var tempFullAddress = `${StreetName}, ${City}, ${State}, ${PostalCode}, ${Country}`;
        setFullAddress(tempFullAddress);
        setAddressComponents({
          StreetName,
          City,
          State,
          PostalCode,
          Country,
        });
      }
    } else {
      var tempAddress = fullAddress.split(",");
      setAddressComponents({
        StreetName: tempAddress[0],
        City: tempAddress[1],
        State: tempAddress[2],
        PostalCode: tempAddress[3],
        Country: tempAddress[4],
      });
    }
  }, []);

  if (sessionStorage.getItem("fullAddress")) {
  }
  useEffect(() => {
    if (fullAddress !== null) {
      sessionStorage.setItem("fullAddress", fullAddress);
      const loadMap = () => {
        const script = document.createElement("script");
        script.src = javascriptURL;
        script.async = true;
        script.defer = true;
        document.head.appendChild(script);

        script.onload = () => {
          initMap();
        };
      };
      const initMap = () => {
        const geocoder = new window.google.maps.Geocoder();

        geocoder.geocode({ address: fullAddress }, (results, status) => {
          if (status === "OK") {
            const location = results[0].geometry.location;
            const panorama = new window.google.maps.StreetViewPanorama(
              document.getElementById("pano"),
              {
                position: location,
                pov: { heading: 165, pitch: 0 },
                zoom: 1,
              }
            );

            const map = new window.google.maps.Map(
              document.getElementById("map"),
              {
                center: location,
                zoom: 18,
                mapTypeId: "satellite",
                streetViewControl: false,
                styles: [
                  {
                    featureType: "all",
                    elementType: "labels",
                    stylers: [{ visibility: "on" }],
                  },
                ],
              }
            );

            const marker = new window.google.maps.Marker({
              position: location,
              map: map,
              title: "Marker Title",
            });

            map.setStreetView(panorama);
          } else {
            alert(
              "Geocode was not successful for the following reason: " + status
            );
          }
        });
      };

      loadMap();

      return () => {
        const scripts = document.getElementsByTagName("script");
        for (let i = 0; i < scripts.length; i++) {
          if (
            scripts[i].src.includes("https://maps.googleapis.com/maps/api/js")
          ) {
            document.head.removeChild(scripts[i]);
            sessionStorage.removeItem("fullAddress");
            break;
          }
        }
      };
    }
  }, [fullAddress]);


  return (
    <div className="mapgrid">
      <div
        id="map"
        style={{ width: "600px", height: "400px", marginTop: "50px",marginLeft:'10px' }}
      ></div>
      <div
        id="pano"
        style={{ width: "600px", height: "400px", marginTop: "50px" }}
      ></div>
          
      <div style={{marginBottom:'15px'}}>
        <div>
          <label style={{ fontWeight: "bold", marginLeft: "10px", minWidth:"65px" }}>Address</label>:
          <span style={{paddingLeft: '15px'}}>{addressComponents.StreetName.trim()}</span>
        </div>

        <div>
          <label style={{ fontWeight: "bold", marginLeft: "10px", minWidth:"65px" }}>City</label>:
          <span style={{paddingLeft: '15px'}}>{addressComponents.City.trim()}</span>
        </div>

        <div>
          <label style={{ fontWeight: "bold", marginLeft: "10px", minWidth:"65px" }}>State</label>:
          <span style={{paddingLeft: '15px'}}>{addressComponents.State.trim()}</span>
        </div>

        <div>
          <label style={{ fontWeight: "bold", marginLeft: "10px", minWidth:"65px"}}>Zip</label>:
          <span style={{paddingLeft: '15px'}}>{addressComponents.PostalCode.trim()}</span>
        </div>

        <div>
          <label style={{ fontWeight: "bold", marginLeft: "10px", minWidth:"65px" }}>Country</label>:
          <span style={{paddingLeft: '15px'}}>{addressComponents.Country.trim()}</span>
        </div>
      </div>
    </div>
  );
};

export default MapWithStreetView;
