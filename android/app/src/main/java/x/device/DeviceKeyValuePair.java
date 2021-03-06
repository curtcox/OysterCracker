package x.device;

public final class DeviceKeyValuePair {

    public final String key;
    public final Object value;
    
    public DeviceKeyValuePair(String key, Object value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return key + "=" + value;
    }
}
