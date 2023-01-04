package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;



@Getter
@EqualsAndHashCode
public class IpPort {
    public String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
