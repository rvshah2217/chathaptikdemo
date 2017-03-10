
package com.app.chatdemo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "count",
    "messages"
})
public class ChatResponseModel {

    @JsonProperty("count")
    public Integer count;
    @JsonProperty("messages")
    public List<MessageModel> messages = null;



}
