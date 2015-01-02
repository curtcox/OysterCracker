package c1.screenfactories;

import java.util.List;
import c1.device.*;

public final class DeviceInfoScreenFactory 
    extends AbstractItemListScreenFactory
{

    public DeviceInfoScreenFactory() {
        super("Device_Info");
    }
    
    protected List<DeviceKeyValuePair> getValues() {
        return DeviceInfo.asDeviceKeyValuePairs();
    }

}