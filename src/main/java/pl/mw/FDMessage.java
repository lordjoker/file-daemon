package pl.mw;

/**
 * Created by mwisniewski.
 */
public class FDMessage {
    
    private final FDHeader header;
    private final FDPayload payload;

    public FDMessage(FDHeader header, FDPayload payload) {
        this.header = header;
        this.payload = payload;
    }

    public FDHeader getHeader() {
        return header;
    }

    public FDPayload getPayload() {
        return payload;
    }
}
