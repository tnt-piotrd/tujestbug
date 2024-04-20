package pl.tnt.tjb.serialization.restapi.reqres;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"data", "support"})
public class ReqResResponsePojo {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("support")
    private Support support;

    /** No args constructor for use in serialization */
    public ReqResResponsePojo() {}

    /**
     * @param data
     * @param support
     */
    public ReqResResponsePojo(Data data, Support support) {
        super();
        this.data = data;
        this.support = support;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("support")
    public Support getSupport() {
        return support;
    }

    @JsonProperty("support")
    public void setSupport(Support support) {
        this.support = support;
    }
}
