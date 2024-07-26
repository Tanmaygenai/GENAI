import React, { useState, useEffect, useMemo } from "react";
import { useLocation } from "react-router-dom";
import Header from "./header/Header";
import Sidebar from "./sidebar/Sidebar";
import Subheader from "./header/subheader/Subheader";
import OpendTab from "./header/openedtab/OpendTab";
import FormModal from "../components/modals/formmodal/FormModal";
import { useForm } from "react-hook-form";
import ErrorMessage from "../components/modals/popupmodal/ErrorMessageModal";
import $, { get } from 'jquery';
import Loader from "../components/loader";
import useLoader from '../context/loader'

function Layout({ children, BreadCrum, TabName }) {
	const {loader} = useLoader();
	const location = useLocation();
	
	useEffect(() => {
		$('document').ready(function () {
			$(window).scrollTop(0);
		});
	}, [])
	// For Error Modal
	const [modalDate, setModalDate] = useState({
		show: false,
		title: "",
		body: "",
	});
	// For Form Modal
	const [modalState, setModalState] = useState({
		addPolicyTitle: "",
		AddPolicyData: [],
		Other_Policy_Data: [],
		ModalData: [],
		DriverData: [],
		VehicleData: [],
		BuildingLocData: [],
		ClaimData: [],
		editIndex: null,
		LocationData: [],
		CoverageData: [],
		BuildingRiskData: []
	});
	const {
		register,
		formState: { errors },
		handleSubmit,
		reset,
		control,
		setValue,
		getValues,
		trigger
	} = useForm();


	const onSubmit = async (data) => {
		
		if (modalState.addPolicyTitle === "Excess/Umbrella") {
			const policy = {
				AddUnderlyingPolicies: modalState.addPolicyTitle,
				underlying_premium: getValues("underlying_premium"),
				LimitofLiability: getValues("LimitofLiability"),
			};
			const clonePol = [...modalState.AddPolicyData];

			if (modalState.editIndex === null) {
				clonePol.push(policy);
				setModalState({ ...modalState, AddPolicyData: clonePol });
			} else {
				clonePol.splice(modalState.editIndex, 1, policy);
				setModalState({ ...modalState, AddPolicyData: clonePol, editIndex: null, addPolicyTitle: "" });
			}
		} else if (modalState.addPolicyTitle === "Other") {
			const Otherpolicy = {
				AddUnderlyingPolicies: modalState.addPolicyTitle,
				class_code_or_Description: getValues("class_code_or_Description"),
				underlying_premium: getValues("other_underlying_premium"),
				PolicyType: getValues("PolicyType"),
				AutoLimit: getValues("AutoLimit"),
				EachOccurrenceLimit: getValues("EachOccurrenceLimit"),
				GeneralAggregateLimit: getValues("GeneralAggregateLimit"),
				OccurrenceLimitEachAccident: getValues("OccurrenceLimitEachAccident"),
			};
			const cloneOther = [...modalState.AddPolicyData];

			if (modalState.editIndex === null) {
				cloneOther.push(Otherpolicy);
				setModalState({ ...modalState, AddPolicyData: cloneOther, addPolicyTitle: "" });
			} else {
				cloneOther.splice(modalState.editIndex, 1, Otherpolicy);
				setModalState({ ...modalState, AddPolicyData: cloneOther, editIndex: null, addPolicyTitle: "" });
			}
		} else if (modalState.addPolicyTitle === "Commercial Auto") {
			const Commercial_Auto_policy = {
				AddUnderlyingPolicies: modalState.addPolicyTitle,
				CombinedSingleLimit: getValues("CombinedSingleLimit"),
				underlying_premium: getValues("auto_underlying_premium"),
				LightVehicle: getValues("LightVehicle"),
				MediumVehicle: getValues("MediumVehicle"),
				HeavyVehicle: getValues("HeavyVehicle"),
				ExtraHeavyVehicle: getValues("ExtraHeavyVehicle"),
			};
			const cloneCommercialAuto = [...modalState.AddPolicyData];

			if (modalState.editIndex === null) {
				cloneCommercialAuto.push(Commercial_Auto_policy);
				setModalState({ ...modalState, AddPolicyData: cloneCommercialAuto, addPolicyTitle: "" });
			} else {
				cloneCommercialAuto.splice(modalState.editIndex, 1, Commercial_Auto_policy);
				setModalState({ ...modalState, AddPolicyData: cloneCommercialAuto, editIndex: null, addPolicyTitle: "" });
			}

		}
		else if (modalState.addPolicyTitle === "Commercial Monoline GL (or GL Portion of Commercial Package)") {
			const Commercial_Monoline_policy = {
				AddUnderlyingPolicies: modalState.addPolicyTitle,
				class_code_or_Description: getValues("class_code_or_Description"),
				underlying_premium: getValues("gl_underlying_premium"),
				EachOccurrenceLimit: getValues("EachOccurrenceLimit"),
				ProductsandCompletedOpsAggregateLimit: getValues(
					"ProductsandCompletedOpsAggregateLimit"
				),
				GeneralAggregateLimit: getValues("GeneralAggregateLimit"),
				PersonalAdvertisingLimit: getValues("PersonalAdvertisingLimit"),
			};
			const clone_Monoline_policy = [...modalState.AddPolicyData];

			if (modalState.editIndex === null) {
				clone_Monoline_policy.push(Commercial_Monoline_policy);
				setModalState({ ...modalState, AddPolicyData: clone_Monoline_policy, addPolicyTitle: "" });
			} else {
				clone_Monoline_policy.splice(modalState.editIndex, 1, Commercial_Monoline_policy);
				setModalState({ ...modalState, AddPolicyData: clone_Monoline_policy, editIndex: null, addPolicyTitle: "" });
			}
		}
		else if (modalState.addPolicyTitle === "Add Driver") {
			const dri = {
				Age: getValues("age"),
				TotalDriverPoints: getValues("total_driver_points"),
				FirstName: getValues("Driver_First_Name"),
				LastName: getValues("Driver_Last_Name"),
				DOB: getValues("Driver_Date_of_Birth"),
				DriverLicenseState: getValues("DriverLicenseState"),
				DriverLicenseNumber: getValues("DriverLicenseNumber"),
				DriverUSExperience: getValues("Driver_US_Experience"),
			};
			const cloneDri = [...modalState.DriverData];
			if (modalState.editIndex === null) {
				cloneDri.push(dri);
				setModalState({ ...modalState, DriverData: cloneDri });
			}
			else {
				cloneDri.splice(modalState.editIndex, 1, dri);
				setModalState({ ...modalState, DriverData: cloneDri, editIndex: null });
			}
		} 
		else if (modalState.addPolicyTitle === "Add New Driver") {
			const dri = {
				Age: getValues("age"),
				TotalDriverPoints: getValues("total_driver_points"),
				FirstName:getValues("Driver_First_Name"),
				LastName:getValues("Driver_Last_Name"),
				DOB:getValues("Driver_Date_of_Birth"),
				DriverLicenseState:getValues("DriverLicenseState"),
				DriverLicenseNumber: getValues("DriverLicenseNumber"),
				DriverUSExperience:getValues("Driver_US_Experience"),
			};
			const cloneDri = [...modalState.DriverData];
			if (modalState.editIndex === null) {
			cloneDri.push(dri);
			setModalState({ ...modalState, DriverData: cloneDri });
			}
			else {
				cloneDri.splice(modalState.editIndex, 1, dri);
				setModalState({ ...modalState, DriverData: cloneDri, editIndex: null});
			}
		} 
		else if (modalState.addPolicyTitle === "Add New Location") {
			var locationDesc = getValues("LocationDesc").split(" - ");
			const location = {
				LocationDesc: locationDesc[0] + " - " + getValues("Address") + " - " + getValues("City") + " - " + getValues("State") + " - " + getValues("zip_code"),
				StreetName: getValues("Address"),
				City:getValues("City"),
				State:getValues("State"),
				PostalCode:getValues("zip_code"),
				Country:getValues("country")
			};
			const locationDri = [...modalState.LocationData];
			if (modalState.editIndex === null) {
			locationDri.push(location);
			setModalState({ ...modalState, LocationData: locationDri });
			}else {
				locationDri.splice(modalState.editIndex, 1, location);
				setModalState({ ...modalState, LocationData: locationDri, editIndex: null });
			}
		} 
		else if (modalState.addPolicyTitle === "Add Location") {
			var locationDesc = getValues("LocationDesc").split(" - ");
			const location = {
				LocationDesc: locationDesc[0] + " - " + getValues("Address") + " - " + getValues("City") + " - " + getValues("State") + " - " + getValues("zip_code"),
				StreetName: getValues("Address"),
				City: getValues("City"),
				State: getValues("State"),
				PostalCode: getValues("zip_code"),
				Country: getValues("country")
			};
			const locationDri = [...modalState.LocationData];
			if (modalState.editIndex === null) {
				locationDri.push(location);
				setModalState({ ...modalState, LocationData: locationDri });
			} else {
				locationDri.splice(modalState.editIndex, 1, location);
				setModalState({ ...modalState, LocationData: locationDri, editIndex: null });
			}
		}
		else if (modalState.addPolicyTitle === "Add Coverage") {
			const coverage = {
				CoverageTypes: getValues("CoverageTypes"),
				BuildingLimit: getValues("BuildingLimit"),
				BuildingDeductible: getValues("BuildingDeductible"),
			};
			const coverageDri = [...modalState.CoverageData];
			coverageDri.push(coverage);
			setModalState({ ...modalState, CoverageData: coverageDri });
		}
		else if (modalState.addPolicyTitle === "Add Vehicle") {
			const veh = {
				Year: getValues("Year"),
				VIN: getValues("VIN"),
				Make: getValues("Make"),
				"Garage Location": getValues("garage_location_zip_code"),
				"Vehicle Cost": getValues("VehicleCostNew"),
				Symbol: getValues("Symbol"),
				"Gross Weight": getValues("GrossVehicleWeight"),
				Radius: getValues("Radius"),
				"Perils Deductible": getValues("SpecifiedPerilsDeductible"),
				"Collision Deductible": getValues("CollisionDeductible"),
				UMPD: getValues("UMPD"),
				Use: getValues("Use"),
				Model: getValues("Model"),
				RegisteredState: getValues("RegisteredState"),
				VehicleUse: getValues("VehicleUse"),
				VehicleOwnership: getValues("VehicleOwnership"),
				MainDriverofVehicle: getValues("MainDriverofVehicle"),
				VehicleOwnership: getValues("VehicleOwnership"),
				Howmanypeoplewilldriveyourvehicle: getValues("Howmanypeoplewilldriveyourvehicle"),
				CostNew: getValues("CostNew"),
				GarageAddress: getValues("GarageAddress"),
				Address: getValues("Address"),
				City: getValues("City"),
				State: getValues("State"),
				PostalCode: getValues("zip_code"),
				Country: getValues("country"),
				Vehicleconditionwhenpurchased: getValues("Vehicleconditionwhenpurchased"),
				Fleet: getValues("Fleet"),
				PrimaryUse: getValues("PrimaryUse"),
				Radiusofoperation: getValues("Radiusofoperation"),
				UsageType: getValues("UsageType"),
				Sizeclass: getValues("Sizeclass"),
				Comprehensive: getValues("Comprehensive"),
				ComprehensiveDeductible: getValues("ComprehensiveDeductible"),
				Collision: getValues("Collision"),
				Experience: getValues("Experience"),
				CollisionDeductible: getValues("CollisionDeductible"),
				VehicleType: getValues("VehicleType"),
				vehicleCode: getValues("vehicleCode")
			};
			const cloneVeh = [...modalState.VehicleData];
			if (modalState.editIndex === null) {
				cloneVeh.push(veh);
				setModalState({ ...modalState, VehicleData: cloneVeh });
			}
			else {
				cloneVeh.splice(modalState.editIndex, 1, veh);
				setModalState({ ...modalState, VehicleData: cloneVeh, editIndex: null });
			}
			
		} 
		else if (modalState.addPolicyTitle === "Add New Vehicle") {
			const veh = {
				Year: getValues("Year"),
				VIN: getValues("VIN"),
				Make: getValues("Make"),
				"Garage Location": getValues("garage_location_zip_code"),
				"Vehicle Cost": getValues("VehicleCostNew"),
				Symbol: getValues("Symbol"),
				"Gross Weight": getValues("GrossVehicleWeight"),
				Radius: getValues("Radius"),
				"Perils Deductible": getValues("SpecifiedPerilsDeductible"),
				"Collision Deductible": getValues("CollisionDeductible"),
				UMPD: getValues("UMPD"),
				Use: getValues("Use"),
				Model:getValues("Model"),
			    RegisteredState:getValues("RegisteredState"),
				VehicleUse:getValues("VehicleUse"),
				VehicleOwnership:getValues("VehicleOwnership"),
				MainDriverofVehicle:getValues("MainDriverofVehicle"),
				VehicleOwnership:getValues("VehicleOwnership"),
				Howmanypeoplewilldriveyourvehicle:getValues("Howmanypeoplewilldriveyourvehicle"),
				CostNew:getValues("CostNew"),
				GarageAddress:getValues("GarageAddress"),
				Address:getValues("Address"),
				City:getValues("City"),
				State:getValues("State"),
				PostalCode:getValues("zip_code"),
				Country:getValues("country"),
				Vehicleconditionwhenpurchased: getValues("Vehicleconditionwhenpurchased"),
				Fleet: getValues("Fleet"),
				PrimaryUse: getValues("PrimaryUse"),
				Radiusofoperation: getValues("Radiusofoperation"),
				UsageType: getValues("UsageType"),
				Sizeclass: getValues("Sizeclass"),
				Comprehensive: getValues("Comprehensive"),
				ComprehensiveDeductible: getValues("ComprehensiveDeductible"),
				Collision: getValues("Collision"),
				Experience: getValues("Experience"),
				CollisionDeductible: getValues("CollisionDeductible"),
				VehicleType: getValues("VehicleType"),
				vehicleCode: getValues("vehicleCode")
			};
			const cloneVeh = [...modalState.VehicleData];
			if (modalState.editIndex === null) {
			cloneVeh.push(veh);
			setModalState({ ...modalState, VehicleData: cloneVeh });
			}
			else{
				cloneVeh.splice(modalState.editIndex, 1, veh);
				setModalState({ ...modalState, VehicleData: cloneVeh, editIndex: null});
			}
			
		}
            else if (modalState.addPolicyTitle === "Add New Building") {
			const buldingLoc = {
				LocationNo:getValues("Location"),
				ProtectiveDevices:getValues("ProtectiveDevices"),
				ConstructionType:getValues("ConstructionType"),
				Sprinklered:getValues("Sprinklered"),
				RoofType:getValues("RoofType"),
				YearBuilt:getValues("YearBuilt"),
				SquareFootage:getValues("SquareFootage"),
				EstimatedReplacement:getValues("EstimatedReplacement"),
				ConstructionType:getValues("ConstructionType"),
				RoofType:getValues("RoofType"),
				BuildingLimit:getValues("BuildingLimit"),
				BuildingDeductible:getValues("BuildingDeductible"),
				BusinessPersonalPropertyLimit:getValues("BusinessPersonalPropertyLimit"),
				BusinessPersonalPropertyDeductible:getValues("BusinessPersonalPropertyDeductible")
			};
			const clonebuldingLoc = [...modalState.BuildingLocData];

			if (modalState.editIndex === null) {
			clonebuldingLoc.push(buldingLoc);
			setModalState({ ...modalState, BuildingLocData: clonebuldingLoc });
			}else {
				clonebuldingLoc.splice(modalState.editIndex, 1, buldingLoc);
				setModalState({ ...modalState, BuildingLocData: clonebuldingLoc, editIndex: null });
			}
		}
		else if (modalState.addPolicyTitle === "Add Building") {
			const buldingLoc = {
				LocationNo: getValues("Location"),
				ProtectiveDevices: getValues("ProtectiveDevices"),
				ConstructionType: getValues("ConstructionType"),
				Sprinklered: getValues("Sprinklered"),
				RoofType: getValues("RoofType"),
				YearBuilt: getValues("YearBuilt"),
				SquareFootage: getValues("SquareFootage"),
				EstimatedReplacement: getValues("EstimatedReplacement"),
				ConstructionType: getValues("ConstructionType"),
				RoofType: getValues("RoofType"),
				BuildingLimit: getValues("BuildingLimit"),
				BuildingDeductible: getValues("BuildingDeductible"),
				BusinessPersonalPropertyLimit: getValues("BusinessPersonalPropertyLimit"),
				BusinessPersonalPropertyDeductible: getValues("BusinessPersonalPropertyDeductible")
			};
			const clonebuldingLoc = [...modalState.BuildingLocData];

			if (modalState.editIndex === null) {
				clonebuldingLoc.push(buldingLoc);
				setModalState({ ...modalState, BuildingLocData: clonebuldingLoc });
			} else {
				clonebuldingLoc.splice(modalState.editIndex, 1, buldingLoc);
				setModalState({ ...modalState, BuildingLocData: clonebuldingLoc, editIndex: null });
			}
		}
		else if (modalState.addPolicyTitle === "Risk Information") {
			const buldingRiskData = {
				PropertyDeductible: getValues("PropertyDeductible"),
				ExpandedProperty: getValues("ExpandedProperty"),
				SewerDischarge: getValues("SewerDischarge"),
				WindHailDeductible: getValues("WindHailDeductible")
			};
			const cloneBuldingRiskData = [...modalState.BuildingRiskData];
			cloneBuldingRiskData.push(buldingRiskData);
			setModalState({ ...modalState, BuildingRiskData: cloneBuldingRiskData });
		}
		else if (modalState.addPolicyTitle === "Claim Details") {
			const ClaimData = {
				...modalState.ClaimData,
			};
			setModalState({ ...modalState, ClaimData: ClaimData });
		}

		reset();
		let openModals = document.querySelectorAll(".sds-modal");
		Array.from(openModals).forEach(function (openModal) {
			document.body.classList.remove("bound");
			openModal.classList.remove("is-active");
			// setModalDate({...modalState, ed})
		});
	};

	//All Modals
	const openModal = async (whichModal, title, claimData = null) => {
		if (claimData === null) {
			setModalState({
				...modalState,
				addPolicyTitle: title,
				editIndex: null
			});

		}
		else if (claimData.selectedVal || claimData.shortDesc) {
			setModalState({
				...modalState,
				addPolicyTitle: title,
				ClaimData: claimData
			});
		} else if (typeof claimData === 'number') {
			setModalState({
				...modalState,
				addPolicyTitle: title,
				editIndex: claimData
			});

		}
		// close all open modal at first
		let openModals = document.querySelectorAll(".sds-modal");
		Array.from(openModals).forEach(function (openModal) {
			openModal.classList.remove("is-active");
		});

		// target modal
		let targetModal = document.querySelector(`#${whichModal}`);

		// open target modal
		document.body.classList.add("bound");
		targetModal.classList.add("is-active");

		// exit target modal
		let exitModal = document.querySelectorAll(".sds-modal-exit");
		for (let i = 0; i < exitModal.length; i++) {
			exitModal[i].addEventListener("click", function () {
				document.body.classList.remove("bound");
				targetModal.classList.remove("is-active");
				setModalState({
					...modalState,
					editIndex: null,
					addPolicyTitle: "",
				});

				reset();

			});
		}
	};

	const resetmodal = () => {
		reset();
		setModalState({
			addPolicyTitle: "",
			AddPolicyData: [],
			Other_Policy_Data: [],
			ModalData: [],
			DriverData: [],
			VehicleData: [],
			BuildingLocData: [],
			ClaimData: [],
			editIndex: null,
			LocationData: [],
			CoverageData: [],
			BuildingRiskData: []
		});
	};

	const childrenToRender = React.Children.toArray(children);

	useEffect(() => {
		const bgDash = document.getElementById('bg-section')
		if (bgDash && location.pathname.includes('dashboard')) {
		  bgDash.classList.add('dashboard-bg');
		} else {
			bgDash.classList.remove('dashboard-bg');
		}
	  }, [])

	return (
		<div>
			<Header />
			<Subheader BreadCrum={BreadCrum} />
			<Sidebar />
			{loader && <Loader />}
			{/* <OpendTab TabName={TabName} /> */}
			<div className="innerpages">
				<main>
					<div id="bg-section" className="row sectionBg-color">
						<section className="mainContent">
							{childrenToRender.map((child) =>
								React.cloneElement(child, {
									openModal,
									modalState,
									setModalState,
									resetmodal,
									modalDate,
									setModalDate,
								})
							)}
						</section>
					</div>
				</main>
				<form onSubmit={handleSubmit(onSubmit)} className="allForm">
					<FormModal
						register={register}
						errors={errors}
						trigger={trigger}
						modalState={modalState}
						modalTitle={modalState.addPolicyTitle}
						claimData={modalState.ClaimData}
						control={control}
						getValues={getValues}
						setValue={setValue}
					/>
				</form>

				<ErrorMessage
					showModal={modalDate.show}
					modalTitle={modalDate.title}
					modalBody={modalDate.body}
					setShowModal={setModalDate}
				/>
			</div>
		</div>
	);
}

export default Layout;
