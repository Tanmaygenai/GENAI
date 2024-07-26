import open_work_icon from '../../../assets/img/icons/open_work.svg';
import recent_view_icon from '../../../assets/img/icons/recent_view.svg';
import PerformenceStaticsTableOpenWork from "./OpenWork";
import PerformenceStaticsTableRecentList from "./RecentList";

const TabsTable = ({openWorkDBItem, setOpenWorkDBItem}) => {

    return (
        <div className="demtable">
            <ul className="tabMenu">
                <li className="item"><a href="javascript:void(0);" className="actv"><span><img src={open_work_icon} /></span> Open Work</a></li>
                <li className="item"><a href="javascript:void(0);" ><span><img src={recent_view_icon} /></span> Policy Expiration Report</a></li>
            </ul>
            <PerformenceStaticsTableOpenWork openWorkDBItem={openWorkDBItem} setOpenWorkDBItem={setOpenWorkDBItem}/>
            <PerformenceStaticsTableRecentList openWorkDBItem={openWorkDBItem} />
        </div>
    )
}
export default TabsTable;