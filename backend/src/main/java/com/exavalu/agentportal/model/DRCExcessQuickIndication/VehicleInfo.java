
package com.exavalu.agentportal.model.DRCExcessQuickIndication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "vehicleNumber", "manufacturer", "model", "modelYear", "vin", "grossVehicleWeight", "gvwGroup",
		"useCd", "drivingRadius", "vehicleSymbol", "unitType", "costNew", "registeredState", "vehicleUse",
		"vehicleOwnership", "mainDriverofVehicle", "numberOfDrivers", "VehicleType", "garageAddress", "addr" })
@Generated("jsonschema2pojo")
public class VehicleInfo {

	@JsonProperty("vehicleNumber")
	private Integer vehicleNumber;
	@JsonProperty("manufacturer")
	private String manufacturer;
	@JsonProperty("model")
	private String model;
	@JsonProperty("modelYear")
	private String modelYear;
	@JsonProperty("vin")
	private String vin;
	@JsonProperty("grossVehicleWeight")
	private String grossVehicleWeight;
	@JsonProperty("gvwGroup")
	private String gvwGroup;
	@JsonProperty("useCd")
	private String useCd;
	@JsonProperty("drivingRadius")
	private String drivingRadius;
	@JsonProperty("vehicleSymbol")
	private String vehicleSymbol;
	@JsonProperty("unitType")
	private String unitType;
	@JsonProperty("registeredState")
	private String registeredState;
	@JsonProperty("vehicleUse")
	private String vehicleUse;
	@JsonProperty("VehicleType")
	private String VehicleType;
	@JsonProperty("vehicleOwnership")
	private String vehicleOwnership;
	@JsonProperty("mainDriverofVehicle")
	private String mainDriverofVehicle;
	@JsonProperty("numberOfDrivers")
	private String numberOfDrivers;
	@JsonProperty("garageAddress")
	private Addr garageAddress;
	@JsonProperty("costNew")
	private String costNew;
	@JsonProperty("addr")
	private List<Addr__2> addr = new ArrayList<Addr__2>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("Vehicleconditionwhenpurchased")
	private String Vehicleconditionwhenpurchased;
	@JsonProperty("Fleet")
	private String Fleet;

	@JsonProperty("PrimaryUse")
	private String PrimaryUse;
	@JsonProperty("Radiusofoperation")
	private String Radiusofoperation;
	@JsonProperty("UsageType")
	private String UsageType;
	@JsonProperty("Sizeclass")
	private String Sizeclass;
	@JsonProperty("VehicleCode")
	private String VehicleCode;
	@JsonProperty("Comprehensive")
	private String Comprehensive;
	@JsonProperty("ComprehensiveDeductible")
	private String ComprehensiveDeductible;
	@JsonProperty("Collision")
	private String Collision;
	@JsonProperty("CollisionDeductible")
	private String CollisionDeductible;

	public String getVehicleconditionwhenpurchased() {
		return Vehicleconditionwhenpurchased;
	}

	public void setVehicleconditionwhenpurchased(String vehicleconditionwhenpurchased) {
		Vehicleconditionwhenpurchased = vehicleconditionwhenpurchased;
	}

	public String getFleet() {
		return Fleet;
	}

	public void setFleet(String fleet) {
		Fleet = fleet;
	}

	public String getPrimaryUse() {
		return PrimaryUse;
	}

	public void setPrimaryUse(String primaryUse) {
		PrimaryUse = primaryUse;
	}

	public String getRadiusofoperation() {
		return Radiusofoperation;
	}

	public void setRadiusofoperation(String radiusofoperation) {
		Radiusofoperation = radiusofoperation;
	}

	public String getUsageType() {
		return UsageType;
	}

	public void setUsageType(String usageType) {
		UsageType = usageType;
	}

	public String getSizeclass() {
		return Sizeclass;
	}

	public void setSizeclass(String sizeclass) {
		Sizeclass = sizeclass;
	}

	public String getVehicleCode() {
		return VehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		VehicleCode = vehicleCode;
	}

	public String getComprehensive() {
		return Comprehensive;
	}

	public void setComprehensive(String comprehensive) {
		Comprehensive = comprehensive;
	}

	@JsonProperty("ComprehensiveDeductible")
	public String getComprehensiveDeductible() {
		return ComprehensiveDeductible;
	}

	@JsonProperty("ComprehensiveDeductible")
	public void setComprehensiveDeductible(String comprehensiveDeductible) {
		ComprehensiveDeductible = comprehensiveDeductible;
	}

	public String getCollision() {
		return Collision;
	}

	public void setCollision(String collision) {
		Collision = collision;
	}

	@JsonProperty("CollisionDeductible")
	public String getCollisionDeductible() {
		return CollisionDeductible;
	}

	@JsonProperty("CollisionDeductible")
	public void setCollisionDeductible(String collisionDeductible) {
		CollisionDeductible = collisionDeductible;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@JsonProperty("vehicleNumber")
	public Integer getVehicleNumber() {
		return vehicleNumber;
	}

	@JsonProperty("vehicleNumber")
	public void setVehicleNumber(Integer vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public VehicleInfo withVehicleNumber(Integer vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
		return this;
	}

	@JsonProperty("manufacturer")
	public String getManufacturer() {
		return manufacturer;
	}

	@JsonProperty("manufacturer")
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public VehicleInfo withManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		return this;
	}

	@JsonProperty("model")
	public String getModel() {
		return model;
	}

	@JsonProperty("model")
	public void setModel(String model) {
		this.model = model;
	}

	public VehicleInfo withModel(String model) {
		this.model = model;
		return this;
	}

	public VehicleInfo withCostNew(String costNew) {
		this.costNew = costNew;
		return this;
	}

	@JsonProperty("registeredState")
	public String getRegisteredState() {
		return registeredState;
	}

	@JsonProperty("registeredState")
	public void setRegisteredState(String registeredState) {
		this.registeredState = registeredState;
	}

	public VehicleInfo withRegisteredState(String registeredState) {
		this.registeredState = registeredState;
		return this;
	}

	@JsonProperty("vehicleUse")
	public String getVehicleUse() {
		return vehicleUse;
	}

	@JsonProperty("vehicleUse")
	public void setVehicleUse(String vehicleUse) {
		this.vehicleUse = vehicleUse;
	}

	public VehicleInfo withVehicleUse(String vehicleUse) {
		this.vehicleUse = vehicleUse;
		return this;
	}

	@JsonProperty("vehicleOwnership")
	public String getVehicleOwnership() {
		return vehicleOwnership;
	}

	@JsonProperty("vehicleOwnership")
	public void setVehicleOwnership(String vehicleOwnership) {
		this.vehicleOwnership = vehicleOwnership;
	}

	public VehicleInfo withVehicleOwnership(String vehicleOwnership) {
		this.vehicleOwnership = vehicleOwnership;
		return this;
	}

	@JsonProperty("mainDriverofVehicle")
	public String getMainDriverofVehicle() {
		return mainDriverofVehicle;
	}

	@JsonProperty("mainDriverofVehicle")
	public void setMainDriverofVehicle(String mainDriverofVehicle) {
		this.mainDriverofVehicle = mainDriverofVehicle;
	}

	public VehicleInfo withMainDriverofVehicle(String mainDriverofVehicle) {
		this.mainDriverofVehicle = mainDriverofVehicle;
		return this;
	}

	@JsonProperty("numberOfDrivers")
	public String getNumberOfDrivers() {
		return numberOfDrivers;
	}

	@JsonProperty("numberOfDrivers")
	public void setNumberOfDrivers(String numberOfDrivers) {
		this.numberOfDrivers = numberOfDrivers;
	}

	public VehicleInfo withNumberOfDrivers(String numberOfDrivers) {
		this.numberOfDrivers = numberOfDrivers;
		return this;
	}

	public Addr getGarageAddress() {
		return garageAddress;
	}

	@JsonProperty("garageAddress")
	public void setGarageAddress(Addr garageAddress) {
		this.garageAddress = garageAddress;
	}

	@JsonProperty("garageAddress")
	public VehicleInfo withGarageAddress(Addr garageAddress) {
		this.garageAddress = garageAddress;
		return this;
	}

	@JsonProperty("modelYear")
	public String getModelYear() {
		return modelYear;
	}

	@JsonProperty("modelYear")
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public VehicleInfo withModelYear(String modelYear) {
		this.modelYear = modelYear;
		return this;
	}

	@JsonProperty("vin")
	public String getVin() {
		return vin;
	}

	@JsonProperty("vin")
	public void setVin(String vin) {
		this.vin = vin;
	}

	public VehicleInfo withVin(String vin) {
		this.vin = vin;
		return this;
	}

	@JsonProperty("grossVehicleWeight")
	public String getGrossVehicleWeight() {
		return grossVehicleWeight;
	}

	@JsonProperty("VehicleType")
	public String getVehicleType() {
		return VehicleType;
	}

	@JsonProperty("VehicleType")
	public void setVehicleType(String VehicleType) {
		this.VehicleType = VehicleType;
	}
	
	public VehicleInfo withVehicleType(String VehicleType) {
		this.VehicleType = VehicleType;
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VehicleInfo [");
		if (vehicleNumber != null) {
			builder.append("vehicleNumber=");
			builder.append(vehicleNumber);
			builder.append(", ");
		}
		if (manufacturer != null) {
			builder.append("manufacturer=");
			builder.append(manufacturer);
			builder.append(", ");
		}
		if (model != null) {
			builder.append("model=");
			builder.append(model);
			builder.append(", ");
		}
		if (modelYear != null) {
			builder.append("modelYear=");
			builder.append(modelYear);
			builder.append(", ");
		}
		if (vin != null) {
			builder.append("vin=");
			builder.append(vin);
			builder.append(", ");
		}
		if (grossVehicleWeight != null) {
			builder.append("grossVehicleWeight=");
			builder.append(grossVehicleWeight);
			builder.append(", ");
		}
		if (gvwGroup != null) {
			builder.append("gvwGroup=");
			builder.append(gvwGroup);
			builder.append(", ");
		}
		if (useCd != null) {
			builder.append("useCd=");
			builder.append(useCd);
			builder.append(", ");
		}
		if (drivingRadius != null) {
			builder.append("drivingRadius=");
			builder.append(drivingRadius);
			builder.append(", ");
		}
		if (vehicleSymbol != null) {
			builder.append("vehicleSymbol=");
			builder.append(vehicleSymbol);
			builder.append(", ");
		}
		if (unitType != null) {
			builder.append("unitType=");
			builder.append(unitType);
			builder.append(", ");
		}
		if (registeredState != null) {
			builder.append("registeredState=");
			builder.append(registeredState);
			builder.append(", ");
		}
		if (vehicleUse != null) {
			builder.append("vehicleUse=");
			builder.append(vehicleUse);
			builder.append(", ");
		}
		if (VehicleType != null) {
			builder.append("VehicleType=");
			builder.append(VehicleType);
			builder.append(", ");
		}
		if (vehicleOwnership != null) {
			builder.append("vehicleOwnership=");
			builder.append(vehicleOwnership);
			builder.append(", ");
		}
		if (mainDriverofVehicle != null) {
			builder.append("mainDriverofVehicle=");
			builder.append(mainDriverofVehicle);
			builder.append(", ");
		}
		if (numberOfDrivers != null) {
			builder.append("numberOfDrivers=");
			builder.append(numberOfDrivers);
			builder.append(", ");
		}
		if (garageAddress != null) {
			builder.append("garageAddress=");
			builder.append(garageAddress);
			builder.append(", ");
		}
		if (costNew != null) {
			builder.append("costNew=");
			builder.append(costNew);
			builder.append(", ");
		}
		if (addr != null) {
			builder.append("addr=");
			builder.append(addr);
			builder.append(", ");
		}
		if (additionalProperties != null) {
			builder.append("additionalProperties=");
			builder.append(additionalProperties);
			builder.append(", ");
		}
		if (Vehicleconditionwhenpurchased != null) {
			builder.append("Vehicleconditionwhenpurchased=");
			builder.append(Vehicleconditionwhenpurchased);
			builder.append(", ");
		}
		if (Fleet != null) {
			builder.append("Fleet=");
			builder.append(Fleet);
			builder.append(", ");
		}
		if (PrimaryUse != null) {
			builder.append("PrimaryUse=");
			builder.append(PrimaryUse);
			builder.append(", ");
		}
		if (Radiusofoperation != null) {
			builder.append("Radiusofoperation=");
			builder.append(Radiusofoperation);
			builder.append(", ");
		}
		if (UsageType != null) {
			builder.append("UsageType=");
			builder.append(UsageType);
			builder.append(", ");
		}
		if (Sizeclass != null) {
			builder.append("Sizeclass=");
			builder.append(Sizeclass);
			builder.append(", ");
		}
		if (VehicleCode != null) {
			builder.append("VehicleCode=");
			builder.append(VehicleCode);
			builder.append(", ");
		}
		if (Comprehensive != null) {
			builder.append("Comprehensive=");
			builder.append(Comprehensive);
			builder.append(", ");
		}
		if (ComprehensiveDeductible != null) {
			builder.append("ComprehensiveDeductible=");
			builder.append(ComprehensiveDeductible);
			builder.append(", ");
		}
		if (Collision != null) {
			builder.append("Collision=");
			builder.append(Collision);
			builder.append(", ");
		}
		if (CollisionDeductible != null) {
			builder.append("CollisionDeductible=");
			builder.append(CollisionDeductible);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Collision == null) ? 0 : Collision.hashCode());
		result = prime * result + ((CollisionDeductible == null) ? 0 : CollisionDeductible.hashCode());
		result = prime * result + ((Comprehensive == null) ? 0 : Comprehensive.hashCode());
		result = prime * result + ((ComprehensiveDeductible == null) ? 0 : ComprehensiveDeductible.hashCode());
		result = prime * result + ((Fleet == null) ? 0 : Fleet.hashCode());
		result = prime * result + ((PrimaryUse == null) ? 0 : PrimaryUse.hashCode());
		result = prime * result + ((Radiusofoperation == null) ? 0 : Radiusofoperation.hashCode());
		result = prime * result + ((Sizeclass == null) ? 0 : Sizeclass.hashCode());
		result = prime * result + ((UsageType == null) ? 0 : UsageType.hashCode());
		result = prime * result + ((VehicleCode == null) ? 0 : VehicleCode.hashCode());
		result = prime * result
				+ ((Vehicleconditionwhenpurchased == null) ? 0 : Vehicleconditionwhenpurchased.hashCode());
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((costNew == null) ? 0 : costNew.hashCode());
		result = prime * result + ((drivingRadius == null) ? 0 : drivingRadius.hashCode());
		result = prime * result + ((garageAddress == null) ? 0 : garageAddress.hashCode());
		result = prime * result + ((grossVehicleWeight == null) ? 0 : grossVehicleWeight.hashCode());
		result = prime * result + ((gvwGroup == null) ? 0 : gvwGroup.hashCode());
		result = prime * result + ((mainDriverofVehicle == null) ? 0 : mainDriverofVehicle.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((modelYear == null) ? 0 : modelYear.hashCode());
		result = prime * result + ((numberOfDrivers == null) ? 0 : numberOfDrivers.hashCode());
		result = prime * result + ((registeredState == null) ? 0 : registeredState.hashCode());
		result = prime * result + ((unitType == null) ? 0 : unitType.hashCode());
		result = prime * result + ((useCd == null) ? 0 : useCd.hashCode());
		result = prime * result + ((vehicleNumber == null) ? 0 : vehicleNumber.hashCode());
		result = prime * result + ((vehicleOwnership == null) ? 0 : vehicleOwnership.hashCode());
		result = prime * result + ((vehicleSymbol == null) ? 0 : vehicleSymbol.hashCode());
		result = prime * result + ((VehicleType == null) ? 0 : VehicleType.hashCode());
		result = prime * result + ((vehicleUse == null) ? 0 : vehicleUse.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VehicleInfo other = (VehicleInfo) obj;
		if (Collision == null) {
			if (other.Collision != null) {
				return false;
			}
		} else if (!Collision.equals(other.Collision)) {
			return false;
		}
		if (CollisionDeductible == null) {
			if (other.CollisionDeductible != null) {
				return false;
			}
		} else if (!CollisionDeductible.equals(other.CollisionDeductible)) {
			return false;
		}
		if (Comprehensive == null) {
			if (other.Comprehensive != null) {
				return false;
			}
		} else if (!Comprehensive.equals(other.Comprehensive)) {
			return false;
		}
		if (ComprehensiveDeductible == null) {
			if (other.ComprehensiveDeductible != null) {
				return false;
			}
		} else if (!ComprehensiveDeductible.equals(other.ComprehensiveDeductible)) {
			return false;
		}
		if (Fleet == null) {
			if (other.Fleet != null) {
				return false;
			}
		} else if (!Fleet.equals(other.Fleet)) {
			return false;
		}
		if (PrimaryUse == null) {
			if (other.PrimaryUse != null) {
				return false;
			}
		} else if (!PrimaryUse.equals(other.PrimaryUse)) {
			return false;
		}
		if (Radiusofoperation == null) {
			if (other.Radiusofoperation != null) {
				return false;
			}
		} else if (!Radiusofoperation.equals(other.Radiusofoperation)) {
			return false;
		}
		if (Sizeclass == null) {
			if (other.Sizeclass != null) {
				return false;
			}
		} else if (!Sizeclass.equals(other.Sizeclass)) {
			return false;
		}
		if (UsageType == null) {
			if (other.UsageType != null) {
				return false;
			}
		} else if (!UsageType.equals(other.UsageType)) {
			return false;
		}
		if (VehicleCode == null) {
			if (other.VehicleCode != null) {
				return false;
			}
		} else if (!VehicleCode.equals(other.VehicleCode)) {
			return false;
		}
		if (Vehicleconditionwhenpurchased == null) {
			if (other.Vehicleconditionwhenpurchased != null) {
				return false;
			}
		} else if (!Vehicleconditionwhenpurchased.equals(other.Vehicleconditionwhenpurchased)) {
			return false;
		}
		if (additionalProperties == null) {
			if (other.additionalProperties != null) {
				return false;
			}
		} else if (!additionalProperties.equals(other.additionalProperties)) {
			return false;
		}
		if (addr == null) {
			if (other.addr != null) {
				return false;
			}
		} else if (!addr.equals(other.addr)) {
			return false;
		}
		if (costNew == null) {
			if (other.costNew != null) {
				return false;
			}
		} else if (!costNew.equals(other.costNew)) {
			return false;
		}
		if (drivingRadius == null) {
			if (other.drivingRadius != null) {
				return false;
			}
		} else if (!drivingRadius.equals(other.drivingRadius)) {
			return false;
		}
		if (garageAddress == null) {
			if (other.garageAddress != null) {
				return false;
			}
		} else if (!garageAddress.equals(other.garageAddress)) {
			return false;
		}
		if (grossVehicleWeight == null) {
			if (other.grossVehicleWeight != null) {
				return false;
			}
		} else if (!grossVehicleWeight.equals(other.grossVehicleWeight)) {
			return false;
		}
		if (gvwGroup == null) {
			if (other.gvwGroup != null) {
				return false;
			}
		} else if (!gvwGroup.equals(other.gvwGroup)) {
			return false;
		}
		if (mainDriverofVehicle == null) {
			if (other.mainDriverofVehicle != null) {
				return false;
			}
		} else if (!mainDriverofVehicle.equals(other.mainDriverofVehicle)) {
			return false;
		}
		if (manufacturer == null) {
			if (other.manufacturer != null) {
				return false;
			}
		} else if (!manufacturer.equals(other.manufacturer)) {
			return false;
		}
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}
		if (modelYear == null) {
			if (other.modelYear != null) {
				return false;
			}
		} else if (!modelYear.equals(other.modelYear)) {
			return false;
		}
		if (numberOfDrivers == null) {
			if (other.numberOfDrivers != null) {
				return false;
			}
		} else if (!numberOfDrivers.equals(other.numberOfDrivers)) {
			return false;
		}
		if (registeredState == null) {
			if (other.registeredState != null) {
				return false;
			}
		} else if (!registeredState.equals(other.registeredState)) {
			return false;
		}
		if (unitType == null) {
			if (other.unitType != null) {
				return false;
			}
		} else if (!unitType.equals(other.unitType)) {
			return false;
		}
		if (useCd == null) {
			if (other.useCd != null) {
				return false;
			}
		} else if (!useCd.equals(other.useCd)) {
			return false;
		}
		if (vehicleNumber == null) {
			if (other.vehicleNumber != null) {
				return false;
			}
		} else if (!vehicleNumber.equals(other.vehicleNumber)) {
			return false;
		}
		if (vehicleOwnership == null) {
			if (other.vehicleOwnership != null) {
				return false;
			}
		} else if (!vehicleOwnership.equals(other.vehicleOwnership)) {
			return false;
		}
		if (vehicleSymbol == null) {
			if (other.vehicleSymbol != null) {
				return false;
			}
		} else if (!vehicleSymbol.equals(other.vehicleSymbol)) {
			return false;
		}
		if (VehicleType == null) {
			if (other.VehicleType != null) {
				return false;
			}
		} else if (!VehicleType.equals(other.VehicleType)) {
			return false;
		}
		if (vehicleUse == null) {
			if (other.vehicleUse != null) {
				return false;
			}
		} else if (!vehicleUse.equals(other.vehicleUse)) {
			return false;
		}
		if (vin == null) {
			if (other.vin != null) {
				return false;
			}
		} else if (!vin.equals(other.vin)) {
			return false;
		}
		return true;
	}

	@JsonProperty("grossVehicleWeight")
	public void setGrossVehicleWeight(String grossVehicleWeight) {
		this.grossVehicleWeight = grossVehicleWeight;
	}

	public VehicleInfo withGrossVehicleWeight(String grossVehicleWeight) {
		this.grossVehicleWeight = grossVehicleWeight;
		return this;
	}

	@JsonProperty("gvwGroup")
	public String getGvwGroup() {
		return gvwGroup;
	}

	@JsonProperty("gvwGroup")
	public void setGvwGroup(String gvwGroup) {
		this.gvwGroup = gvwGroup;
	}

	public VehicleInfo withGvwGroup(String gvwGroup) {
		this.gvwGroup = gvwGroup;
		return this;
	}

	@JsonProperty("useCd")
	public String getUseCd() {
		return useCd;
	}

	@JsonProperty("useCd")
	public void setUseCd(String useCd) {
		this.useCd = useCd;
	}

	public VehicleInfo withUseCd(String useCd) {
		this.useCd = useCd;
		return this;
	}

	@JsonProperty("drivingRadius")
	public String getDrivingRadius() {
		return drivingRadius;
	}

	@JsonProperty("drivingRadius")
	public void setDrivingRadius(String drivingRadius) {
		this.drivingRadius = drivingRadius;
	}

	public VehicleInfo withDrivingRadius(String drivingRadius) {
		this.drivingRadius = drivingRadius;
		return this;
	}

	@JsonProperty("vehicleSymbol")
	public String getVehicleSymbol() {
		return vehicleSymbol;
	}

	@JsonProperty("vehicleSymbol")
	public void setVehicleSymbol(String vehicleSymbol) {
		this.vehicleSymbol = vehicleSymbol;
	}

	public VehicleInfo withVehicleSymbol(String vehicleSymbol) {
		this.vehicleSymbol = vehicleSymbol;
		return this;
	}

	@JsonProperty("unitType")
	public String getUnitType() {
		return unitType;
	}

	@JsonProperty("unitType")
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public VehicleInfo withUnitType(String unitType) {
		this.unitType = unitType;
		return this;
	}

	@JsonProperty("costNew")
	public String getCostNew() {
		return costNew;
	}

	@JsonProperty("costNew")
	public void setCostNew(String costNew) {
		this.costNew = costNew;
	}

	@JsonProperty("addr")
	public List<Addr__2> getAddr() {
		return addr;
	}

	@JsonProperty("addr")
	public void setAddr(List<Addr__2> addr) {
		this.addr = addr;
	}

	public VehicleInfo withAddr(List<Addr__2> addr) {
		this.addr = addr;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public VehicleInfo withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}
}
