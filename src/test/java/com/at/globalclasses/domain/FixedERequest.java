package com.at.globalclasses.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FixedERequest {
    private Double rent, transport, internet, feed, others, total;
}
