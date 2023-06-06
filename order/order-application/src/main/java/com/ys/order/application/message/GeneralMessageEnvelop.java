package com.ys.order.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralMessageEnvelop<T> implements MessageEnvelop<T> {

    String messageId;
    T payload;

    public GeneralMessageEnvelop(T payload) {
        this.messageId = Generators.timeBasedEpochGenerator().generate().toString();
        this.payload = payload;
    }
}
